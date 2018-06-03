package com.arjvik.machinelearning.decisiontree.bool;

import java.util.Arrays;

public class DataOutput extends Data implements ScenarioHolder {

	private final boolean output;
	
	public DataOutput(Scenario scenario, boolean output, boolean... data) {
		super(scenario, data);
		this.output = output;
	}

	public boolean getOutput() {
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (output ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataOutput other = (DataOutput) obj;
		if (output != other.output)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrainingData [output=" + output + ", scenario=" + getScenario() + ", data="
				+ Arrays.toString(getData()) + "]";
	}

}
