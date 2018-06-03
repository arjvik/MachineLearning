package com.arjvik.machinelearning.decisiontree.bool;

import java.util.Arrays;
import java.util.List;


public class Scenario implements ScenarioHolder {
	
	private final List<String> features;

	public Scenario(List<String> features) {
		super();
		this.features = features;
	}
	
	public Scenario(String...features) {
		this(Arrays.asList(features));
	}

	public List<String> getFeatures() {
		return features;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((features == null) ? 0 : features.hashCode());
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
		Scenario other = (Scenario) obj;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Scenario [features=" + features + "]";
	}

	@Override
	public Scenario getScenario() {
		return this;
	}
	
}
