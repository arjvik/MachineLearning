package com.arjvik.machinelearning.decisiontree.bool;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class DataOutput extends Data implements ScenarioHolder {

	@lombok.Getter(lombok.AccessLevel.NONE)
	private final boolean output;
	
	public DataOutput(Scenario scenario, boolean output, boolean... data) {
		super(scenario, data);
		this.output = output;
	}
	
	public boolean getOutput() {
		return output;
	}

}
