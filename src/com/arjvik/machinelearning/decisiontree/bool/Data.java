package com.arjvik.machinelearning.decisiontree.bool;

@lombok.Data
public class Data implements ScenarioHolder {
	
	private final Scenario scenario;
	private final boolean[] data;
	
	public Data(Scenario scenario, boolean... data) {
		super();
		if(scenario.getFeatures().size() != data.length)
			throw new IllegalArgumentException("Data length must match provided Scenario");
		this.scenario = scenario;
		this.data = data;
	}
	
}
