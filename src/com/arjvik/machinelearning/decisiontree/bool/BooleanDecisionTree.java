package com.arjvik.machinelearning.decisiontree.bool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanDecisionTree implements ScenarioHolder {
	
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

	public Metrics validate(Dataset validation, Dataset testing) {
		validateScenarios(validation, testing);
		validate(validation);
		return test(testing);
	}
	
	private void validate(Dataset validation) {
		// TODO Auto-generated method stub
		//throw new RuntimeException("VALIDATION NOT IMPLEMENTED");
		System.err.println("VALIDATION NOT IMPLEMENTED");
	}

	private Metrics test(Dataset testing) {
		int total = 0;
		int correct = 0;
		for (DataOutput dataTesting : testing.getTrainingData()) {
			total++;
			DataOutput dataOutput = classify(dataTesting);
			if(dataOutput.getOutput() == dataTesting.getOutput())
				correct++;
		}
		double percentage = ((double) correct) / total;
		return new Metrics(percentage);
	}

	public DataOutput classify(Data data) {
		boolean output = head.classify(data);
		return new DataOutput(scenario, output, data.getData());
	}
	
	public void print() {
		System.out.println(head.print(0, true).replaceAll("\n\n", "\n")
				  .replaceAll("\n\n", "\n")
				  .replaceAll("\n\n", "\n")
				  .replaceAll("\n\n", "\n"));
	}
	
}
