package com.arjvik.machinelearning.decisiontree.bool;

import java.util.List;

@lombok.Data
public class Dataset implements ScenarioHolder {
	
	private final Scenario scenario;
	private final List<DataOutput> trainingData;
	
}
