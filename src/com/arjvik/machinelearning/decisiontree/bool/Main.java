package com.arjvik.machinelearning.decisiontree.bool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String... args) throws IOException {
		if(args.length == 0) {
			System.out.println("Dataset 1:");
			main("10", "10", "testdata/decisiontree/training_set_1.csv", "testdata/decisiontree/validation_set_1.csv", "testdata/decisiontree/test_set_1.csv", "no");
			System.out.println("\nDataset 2:");
			main("10", "10", "testdata/decisiontree/training_set_2.csv", "testdata/decisiontree/validation_set_2.csv", "testdata/decisiontree/test_set_2.csv", "no");
//			main("10", "10", "testdata/decisiontree/test_set_1.csv", "testdata/decisiontree/validation_set_1.csv", "testdata/decisiontree/training_set_1.csv", "no");
//			main("10", "10", "testdata/decisiontree/training_set_custom.csv", "testdata/decisiontree/training_set_custom.csv", "testdata/decisiontree/training_set_custom_full.csv", "no");
			return;
		}
		if(args.length != 6) {
			System.err.println("Usage: Main <L:integer> <K:integer> <training-set:path-to-csv-file> "
					+ "<validation-set:path-to-csv-file> <test-set:path-to-csv-file> <to-print:yes|no>");
			return;
		}
		int L = Integer.parseInt(args[0]);
		int K = Integer.parseInt(args[1]);
		String training = args[2];
		String validation = args[3];
		String test = args[4];
		String toPrintString = args[5];
		boolean toPrint = toPrintString.equalsIgnoreCase("yes") ||
						  toPrintString.equalsIgnoreCase("true") ||
						  toPrintString.equalsIgnoreCase("1");
		generateDatasetAndBuildTree(L, K, training, validation, test, toPrint);
	}

	private static void generateDatasetAndBuildTree(int l, int k, String training, String validation, String test, boolean toPrint) throws IOException {
		Scenario s;
		Dataset trainingDataset;
		Dataset validationDataset;
		Dataset testDataset;
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(training)))) {
			String[] firstLine = in.nextLine().split(",");
			List<String> featureNames = new ArrayList<>(Arrays.asList(firstLine));
			featureNames.remove(featureNames.size()-1);
			s = new Scenario(featureNames);
			List<DataOutput> dataOutputs = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				List<String> dataOutputList = Arrays.asList(line);
				boolean[] data = new boolean[dataOutputList.size()-1];
				for (int i = 0; i < dataOutputList.size()-1; i++) {
					data[i] = dataOutputList.get(i).equals("1");
				}
				boolean output = dataOutputList.get(dataOutputList.size()-1).equals("1");
				DataOutput dataOutput = new DataOutput(s, output, data);
				dataOutputs.add(dataOutput);
			}
			trainingDataset = new Dataset(s, dataOutputs);
		}
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(validation)))) {
			in.nextLine();
			List<DataOutput> dataOutputs = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				List<String> dataOutputList = Arrays.asList(line);
				boolean[] data = new boolean[dataOutputList.size()-1];
				for (int i = 0; i < dataOutputList.size()-1; i++) {
					data[i] = dataOutputList.get(i).equals("1");
				}
				boolean output = dataOutputList.get(dataOutputList.size()-1).equals("1");
				DataOutput dataOutput = new DataOutput(s, output, data);
				dataOutputs.add(dataOutput);
			}
			validationDataset = new Dataset(s, dataOutputs);
		}
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(test)))) {
			in.nextLine();
			List<DataOutput> dataOutputs = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				List<String> dataOutputList = Arrays.asList(line);
				boolean[] data = new boolean[dataOutputList.size()-1];
				for (int i = 0; i < dataOutputList.size()-1; i++) {
					data[i] = dataOutputList.get(i).equals("1");
				}
				boolean output = dataOutputList.get(dataOutputList.size()-1).equals("1");
				DataOutput dataOutput = new DataOutput(s, output, data);
				dataOutputs.add(dataOutput);
			}
			testDataset = new Dataset(s, dataOutputs);
		}
		buildTree(l, k, s, trainingDataset, validationDataset, testDataset, toPrint);
	}

	private static void buildTree(int l, int k, Scenario scenario, Dataset training, Dataset validation, Dataset test, boolean toPrint) {
		BooleanDecisionTree t = new BooleanDecisionTree(scenario);

		System.out.println("Information Gain Heuristic:");
		t.setHeuristicFunction(BooleanDecisionTree.INFORMATION_GAIN_HEURISTIC);
		Metrics m = t.train(training, test);
		System.out.printf("Pre-pruning accuracy: %.2f%%%n",m.getClassificationPercentage()*100);
		if(toPrint)
			t.print();
		

		System.out.println("Variance Impurity Gain Heuristic:");
		t.setHeuristicFunction(BooleanDecisionTree.VARIANCE_IMPURITY_GAIN_HEURISTIC);
		m = t.train(training, test);
		System.out.printf("Pre-pruning accuracy: %.2f%%%n",m.getClassificationPercentage()*100);
		if(toPrint)
			t.print();
		
		System.out.println("Random Heuristic:");
		t.setHeuristicFunction(BooleanDecisionTree.RANDOM_HEURISTIC);
		m = t.train(training, test);
		System.out.printf("Pre-pruning accuracy: %.2f%%%n",m.getClassificationPercentage()*100);
		if(toPrint)
			t.print();
		
	}
	
}
