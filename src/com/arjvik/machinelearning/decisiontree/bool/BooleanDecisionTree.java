package com.arjvik.machinelearning.decisiontree.bool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BooleanDecisionTree implements ScenarioHolder {
	
	private static final double PRUNING_ACCURACY_GAIN_OFFSET = 0.01;
	
	private final Scenario scenario;
	private Node head;
	private Heuristic heuristicFunction;

	public static final Heuristic INFORMATION_GAIN_HEURISTIC = new InformationGainHeuristic();
	public static final Heuristic VARIANCE_IMPURITY_GAIN_HEURISTIC = new VarianceImpurityGainHeuristic();
	public static final Heuristic RANDOM_HEURISTIC = new RandomHeuristic();
	
	public BooleanDecisionTree(Scenario scenario) {
		super();
		this.scenario = scenario;
	}

	public Scenario getScenario() {
		return scenario;
	}
	
	public Heuristic getHeuristicFunction() {
		return heuristicFunction;
	}

	public void setHeuristicFunction(Heuristic heuristicFunction) {
		this.heuristicFunction = heuristicFunction;
	}

	public Metrics train(Dataset training, Dataset testing) {
		if(heuristicFunction == null)
			throw new NullPointerException("Heuristic Function cannot be null. Please initialize it before attempting to train the tree.");
		validateScenarios(training, testing);
		head = train(training, getFullFeaturesAvailableList());
		return test(testing);
	}

	private Node train(Dataset training, List<Integer> featuresAvailable) {
		int posOutputCount = 0;
		int negOutputCount = 0;
		for (DataOutput output : training.getTrainingData()) {
			if(output.getOutput() == true)
				posOutputCount++;
			else
				negOutputCount++;
		}
		if(featuresAvailable.isEmpty())
			return new LeafNode(scenario, posOutputCount, negOutputCount);
		double maxHeuristic = 0;
		int bestFeature = -1;
		Dataset bestPosDataset = null;
		Dataset bestNegDataset = null;
		for (int featureToEvaluate : featuresAvailable) {
			List<DataOutput> trainingData = training.getTrainingData();
			List<DataOutput> posData = trainingData.stream()
												   .filter(d -> d.getData()[featureToEvaluate])
												   .collect(Collectors.toList());
			List<DataOutput> negData = trainingData.stream()
												   .filter(d -> !d.getData()[featureToEvaluate])
												   .collect(Collectors.toList());
			Dataset posDataset = new Dataset(scenario, posData);
			Dataset negDataset = new Dataset(scenario, negData);
			double heuristic = heuristicFunction.calculcate(training, posDataset, negDataset);
			if(heuristic > maxHeuristic) {
				maxHeuristic = heuristic;
				bestFeature = featureToEvaluate;
				bestPosDataset = posDataset;
				bestNegDataset = negDataset;
			}
		}
		if(bestFeature == -1)
			return new LeafNode(scenario, posOutputCount, negOutputCount);
		List<Integer> newFeaturesAvailable = new ArrayList<>(featuresAvailable);
		newFeaturesAvailable.remove((Integer) bestFeature);
		BranchingNode n = new BranchingNode(scenario, newFeaturesAvailable, bestFeature, posOutputCount, negOutputCount);
		n.setPosChild(train(bestPosDataset, newFeaturesAvailable));
		n.setNegChild(train(bestNegDataset, newFeaturesAvailable));
		return n;
	}

	private List<Integer> getFullFeaturesAvailableList() {
		int featureCount = scenario.getFeatures().size();
		List<Integer> featuresAvailable = new ArrayList<Integer>();
		for (int i = 0; i < featureCount; i++) {
			featuresAvailable.add(i);
		}
		return featuresAvailable;
	}

	public Metrics prune(Dataset validation, Dataset testing, int l, int k) {
		validateScenarios(validation, testing);
		prune(validation, l, k);
		return test(testing);
	}
	
	private void prune(Dataset validation, int l, int k) {
		Node bestHead = head;
		Metrics bestAccuracy = test(validation);
		System.out.printf("Pre-pruning validation accuracy: %.2f%%%n",bestAccuracy.getClassificationPercentage()*100);
		Random rand = new Random();
		for (int i = 0; i < l; i++) {
			Node newHead = clone(head);
			int m = rand.nextInt(k) + 1;
			for (int j = 0; j < m; j++) {
				List<Node> nodes = new ArrayList<>();
				List<Boolean> dir = new ArrayList<>();
				Node toRecurseOn = newHead;
				while(toRecurseOn instanceof BranchingNode) {
					nodes.add(toRecurseOn);
					switch(rand.nextInt(2)) {
					case 0:
						dir.add(true);
						toRecurseOn =  ((BranchingNode) toRecurseOn).getPosChild();
						break;
					case 1:
						dir.add(false);
						toRecurseOn =  ((BranchingNode) toRecurseOn).getNegChild();
						break;
					default:
						throw new RuntimeException();
					}
				}
				if(nodes.size() == 1 || nodes.size() == 0)
					continue;
				int p = rand.nextInt(nodes.size() - 1);
				BranchingNode toRemove = (BranchingNode) nodes.get(p+1);
				Node toReplace = new LeafNode(scenario, toRemove.getPosOutputCount(), toRemove.getNegOutputCount());
				if(dir.get(p))
					((BranchingNode) nodes.get(p)).setPosChild(toReplace);
				else
					((BranchingNode) nodes.get(p)).setNegChild(toReplace);
			}
			Metrics accuracy = test(validation, newHead);
			if(accuracy.getClassificationPercentage() > bestAccuracy.getClassificationPercentage() + PRUNING_ACCURACY_GAIN_OFFSET) {
				bestHead = newHead;
				bestAccuracy = accuracy;
				System.out.printf("Pruning (round %d of %d) validation new highest accuracy: %.2f%%%n", i+1, l, accuracy.getClassificationPercentage()*100);
			}
		}
		head = bestHead;
	}

	private Node clone(Node head) {
		if(head instanceof LeafNode)
			return new LeafNode(scenario, ((LeafNode) head).getPosCount(), ((LeafNode) head).getNegCount());
		BranchingNode newHead = new BranchingNode(scenario, ((BranchingNode) head).getFeaturesAvailable(), ((BranchingNode) head).getCurrentFeature(), ((BranchingNode) head).getPosOutputCount(), ((BranchingNode) head).getNegOutputCount());
		newHead.setPosChild(clone(((BranchingNode) head).getPosChild()));
		newHead.setNegChild(clone(((BranchingNode) head).getNegChild()));
		return newHead;
	}

	private Metrics test(Dataset testing) {
		return test(testing, head);
	}
	
	private Metrics test(Dataset testing, Node head) {
		int total = 0;
		int correct = 0;
		for (DataOutput dataTesting : testing.getTrainingData()) {
			total++;
			DataOutput dataOutput = classify(dataTesting, head);
			if(dataOutput.getOutput() == dataTesting.getOutput())
				correct++;
		}
		double percentage = ((double) correct) / total;
		return new Metrics(percentage);
	}
	
	public DataOutput classify(Data data) {
		return classify(data, head);
	}

	private DataOutput classify(Data data, Node head) {
		boolean output = head.classify(data);
		return new DataOutput(scenario, output, data.getData());
	}
	
	public void print() {
		System.out.println(head.print(0, true).replaceAll("\n{2,}", "\n"));
	}
	
}
