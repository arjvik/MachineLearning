package com.arjvik.machinelearning.decisiontree.bool;

import java.util.List;

public class Dataset implements ScenarioHolder {
	private final Scenario scenario;
	private final List<DataOutput> trainingData;

	public Dataset(Scenario scenario, List<DataOutput> trainingData) {
		super();
		this.scenario = scenario;
		this.trainingData = trainingData;
	}
	
	public Scenario getScenario() {
		return scenario;
	}

	public List<DataOutput> getTrainingData() {
		return trainingData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
		result = prime * result + ((trainingData == null) ? 0 : trainingData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dataset other = (Dataset) obj;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		if (trainingData == null) {
			if (other.trainingData != null)
				return false;
		} else if (!trainingData.equals(other.trainingData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Dataset [scenario=" + scenario + ", trainingData=" + trainingData + "]";
	}

}
