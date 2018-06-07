package com.arjvik.machinelearning.decisiontree.bool;

import java.util.Arrays;
import java.util.List;

@lombok.Data
@lombok.AllArgsConstructor
public class Scenario implements ScenarioHolder {
	
	private final List<String> features;

	public Scenario(String...features) {
		this(Arrays.asList(features));
	}

	@Override
	public Scenario getScenario() {
		return this;
	}
	
}
