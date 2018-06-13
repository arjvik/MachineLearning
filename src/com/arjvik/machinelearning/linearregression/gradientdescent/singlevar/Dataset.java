package com.arjvik.machinelearning.linearregression.gradientdescent.singlevar;

import java.util.List;

@lombok.Data
public class Dataset {
	private final List<InputOutput> trainingData;
}
