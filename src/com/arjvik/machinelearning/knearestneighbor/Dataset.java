package com.arjvik.machinelearning.knearestneighbor;

import java.util.List;

@lombok.Data
public class Dataset {
	
	private final List<InputOutput> trainingData;
	
	public void checkNumberOfFeatures(int numberOfFeatures) {
		for (InputOutput io : trainingData) {
			if(io.getValues().length != numberOfFeatures)
				throw new IncorrectNumberOfFeaturesException();
		}
	}
	
}
