package com.arjvik.machinelearning.linearregression.gradientdescent.singlevar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingleVarLinearRegressionModelRunner {
	public static void main(String... args) throws IOException {
		if(args.length == 0) {
			main("testdata/gradientdescent/test_set_1.csv", "0.0005", "1e-10");
			return;
		}
		if(args.length != 3) {
			System.err.println("Usage: java com.arjvik.machinelearning.linearregression.gradientdescent.singlevar.SingleVarLinearRegressionModelRunner  "
					+ "<test-set:path-to-csv-file> <alpha:double> <tolerance:double>");
			return;
		}
		trainModel(generateModel(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}

	private static Dataset generateModel(String csv) throws IOException {
		try (Scanner in = new Scanner(new BufferedReader(new FileReader(csv)))) {
			in.nextLine();
			List<InputOutput> trainingData = new ArrayList<>();
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(",");
				trainingData.add(new InputOutput(Double.parseDouble(line[0]), Double.parseDouble(line[1])));
			}
			return new Dataset(trainingData);
		}
	}

	private static void trainModel(Dataset dataset, double alpha, double tolerance) {
		SingleVarLinearRegressionModel model = new SingleVarLinearRegressionModel();
		model.setAlpha(alpha);
		model.setTolerance(tolerance);
		long startTime = System.currentTimeMillis();
		model.train(dataset);
		System.out.println("\n\n---------------------------------------------");
		System.out.printf("Generated model: %s%n",model);
		System.out.printf("Cost: %e%n",model.cost(dataset));
		System.out.printf("Time taken: %.3fs%n",(System.currentTimeMillis()-startTime)/1000d);
	}
}
