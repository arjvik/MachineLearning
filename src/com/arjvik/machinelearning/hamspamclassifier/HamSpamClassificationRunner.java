package com.arjvik.machinelearning.hamspamclassifier;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.arjvik.machinelearning.hamspamclassifier.naivebayes.NaiveBayesClassifier;

public class HamSpamClassificationRunner {

	private static final String SPLIT_REGEX = "[\\s.,-]+";
	@SuppressWarnings("serial")
	private static final Map<String, Class<? extends HamSpamClassifier>> classifiers = 
				 new HashMap<String, Class<? extends HamSpamClassifier>>() {{
		put("Naive Bayes", NaiveBayesClassifier.class);
	}};

	public static void main(String... args) throws IOException {
		if(args.length == 0) {
			main(prependArgs("testdata/hamspamclassifier/","dataset1","dataset2","dataset3","sampledataset"));
			return;
		}
		for (int i = 0; i < args.length; i++) {
			System.out.printf("Dataset %d:%n-----------%n", i+1);
			File testdataDir = new File(args[i]);
			Dataset training = readDataset(new File(testdataDir, "train"));
			Dataset testing  = readDataset(new File(testdataDir, "test"));
			for (String classifierName : classifiers.keySet()) {
				HamSpamClassifier classifier = instantiate(classifiers.get(classifierName));
				Metrics m = classifier.trainAndTest(training, testing);
				System.out.printf("Algorithm: %s%nAccuracy: %.2f%%%n", classifierName, m.getClassificationPercentage()*100);
			}
			System.out.printf("%n%n");
		}
	}

	private static Dataset readDataset(File testdataDir) {
		List<InputOutput> data = new ArrayList<>();
		addData(data, new File(testdataDir, "ham"), Classification.HAM);
		addData(data, new File(testdataDir, "spam"), Classification.SPAM);
		return new Dataset(data);
	}

	private static void addData(List<InputOutput> data, File dataDir, Classification classification) {
		if(!dataDir.isDirectory()) {
			throw new RuntimeException("Unable to read file "+dataDir.getName()+" ("+dataDir.getPath()+")");
		}
		File[] files = dataDir.listFiles();
		for (File datafile : files) {
			Input input = new Input(getWordsFromFile(datafile));
			InputOutput inputOutput = new InputOutput(input.getWords(), classification);
			data.add(inputOutput);
		}
	}

	private static List<String> getWordsFromFile(File datafile) {
		return Arrays.asList(readFile(datafile).split(SPLIT_REGEX));
	}

	private static String readFile(File datafile) {
		try {
			URI uri = datafile.toURI();
			byte[] bytes = java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(uri));
			return new String(bytes);
		} catch (IOException e) {
			throw new RuntimeException("Unable to read file "+datafile.getName()+" ("+datafile.getPath()+")", e);
		}
	}

	public static String[] prependArgs(String prefix, String... args) {
		for (int i = 0; i < args.length; i++) {
			args[i] = prefix + args[i];
		}
		return args;
	}

	private static HamSpamClassifier instantiate(Class<? extends HamSpamClassifier> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
