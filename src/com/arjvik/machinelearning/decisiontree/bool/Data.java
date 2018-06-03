package com.arjvik.machinelearning.decisiontree.bool;

import java.util.Arrays;

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

	public Scenario getScenario() {
		return scenario;
	}

	public boolean[] getData() {
		return data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
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
		Data other = (Data) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Data [scenario=" + scenario + ", data=" + Arrays.toString(data) + "]";
	}
	
}
