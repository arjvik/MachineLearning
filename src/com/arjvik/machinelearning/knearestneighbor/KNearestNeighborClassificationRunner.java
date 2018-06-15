package com.arjvik.machinelearning.knearestneighbor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KNearestNeighborClassificationRunner {
	public static void main(String... args) throws IOException {
		if(args.length == 0) {
			main("testdata/knearestneighbor/dataset1_training.csv", "testdata/knearestneighbor/dataset1_test1.csv");
			return;
		}
		if(args.length != 2) {
			System.err.println("Usage: java com.arjvik.machinelearning.knearestneighbor "
								+ "path/to/trainingData path/to/testData");
			return;
		}
		Dataset training = readDataset(args[0]);
		int numberOfFeatures = training.getTrainingData().get(0).getValues().length;
		KNearestNeighborClassifier classifier = new KNearestNeighborClassifier(numberOfFeatures);
		classifier.train(training);
		Dataset test = readDataset(args[1]);
		for (int k : new int[]{1,3,5}) {
			System.out.printf("%d-NearestNeighbor classifier:%n----------------------------%n", k);
			classifier.setK(k);
			for (boolean normalizeValues : new boolean[]{false, true}) {
				System.out.printf("With%s normalization:%n", normalizeValues ? "" : "out");
				classifier.setNormalizeValues(normalizeValues);
				double error = 0;
				int correctCount = 0;
				for (InputOutput io : test.getTrainingData()) {
					Input input = new Input(io.getValues());
					InputOutput io2 = classifier.classify(input);
					error += Math.abs(io.getOutput() - io2.getOutput());
					if(Math.round(io.getOutput()) == Math.round(io2.getOutput()))
						correctCount++;
				}
				System.out.printf("Average error: %e%nClassification percentage: %.2f%%%n%n", error/test.getTrainingData().size(), (correctCount*100d)/test.getTrainingData().size());
			}
		}
	}

	private static Dataset readDataset(String path) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(new BufferedReader(new FileReader(path)));
		int numberOfFeatures = in.nextLine().split(",").length - 1;
		List<InputOutput> trainingData = new ArrayList<>();
		while(in.hasNextLine()) {
			String[] line = in.nextLine().split(",");
			if(line.length != numberOfFeatures + 1)
				throw new IncorrectNumberOfFeaturesException();
			double[] values = new double[numberOfFeatures];
			for(int i = 0; i < numberOfFeatures; i++) {
				values[i] = Double.parseDouble(line[i]);
			}
			double output = Double.parseDouble(line[line.length-1]);
			InputOutput io = new InputOutput(output, values);
			trainingData.add(io);
		}
		in.close();
		return new Dataset(trainingData);
	}
}
