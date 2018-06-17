package com.arjvik.machinelearning.linearregression.gradientdescent.multivar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LinearRegressionModelRunner {
	public static void main(String... args) throws IOException {
		if(args.length == 0) {
			main("testdata/gradientdescent/multivar/test_set_2.csv", "0.00001", "1e-12");
			return;
		}
		if(args.length != 3) {
			System.err.println("Usage: java com.arjvik.machinelearning.linearregression.gradientdescent.singlevar.SingleVarLinearRegressionModelRunner  "
					+ "<test-set:path-to-csv-file> <alpha:double> <tolerance:double>");
			return;
		}
		trainModel(generateModel(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}

	private static void trainModel(Dataset dataset, double alpha, double tolerance) {
		LinearRegressionModel model = new LinearRegressionModel(dataset.getTrainingData().get(0).getX().length);
		model.setAlpha(alpha);
		model.setTolerance(tolerance);
		long startTime = System.currentTimeMillis();
		model.train(dataset);
		System.out.println("\n\n---------------------------------------------");
		System.out.printf("Generated model: %s%n",model);
		System.out.printf("Cost: %e%n",model.cost(dataset));
		System.out.printf("Time taken: %.3fs%n",(System.currentTimeMillis()-startTime)/1000d);
	}

	private static Dataset generateModel(String csv) throws IOException {
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(csv)))) {
			int numberOfFeatures = in.nextLine().split(",").length - 1;
			List<InputOutput> trainingData = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				if(line.length != numberOfFeatures + 1)
					throw new IncorrectNumberOfFeaturesException();
				double[] x = new double[numberOfFeatures];
				for (int i = 0; i < numberOfFeatures; i++) {
					x[i] = Double.parseDouble(line[i]);
				}
				double y = Double.parseDouble(line[numberOfFeatures]);
				trainingData.add(new InputOutput(y, x));
			}
			return new Dataset(trainingData);
		}
	}
}
