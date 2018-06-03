package com.arjvik.machinelearning.decisiontree.bool;

public interface ScenarioHolder {
	
	public Scenario getScenario();

	default void validateScenarios(ScenarioHolder... scenarioHolders) {
		for (ScenarioHolder scenarioHolder : scenarioHolders)
			validateScenario(scenarioHolder);
	}

	default void validateScenario(ScenarioHolder scenarioHolder) {
		if(!getScenario().equals(scenarioHolder.getScenario()))
			throw new IllegalArgumentException("Provided argument must have the same Scenario");
	}

}
