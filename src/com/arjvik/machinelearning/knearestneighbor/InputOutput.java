package com.arjvik.machinelearning.knearestneighbor;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class InputOutput extends Input {
	
	private final double output;
	
	public InputOutput(double output, double... values) {
		super(values);
		this.output = output;
	}
	
	public InputOutput(Input input, double output) {
		this(output, input.getValues());
	}
	
}
