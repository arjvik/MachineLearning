package com.arjvik.machinelearning.linearregression.gradientdescent.multivar;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class InputOutput extends Input {
	
	private final double y;
	
	public InputOutput(double y, double... x) {
		super(x);
		this.y = y;
	}
}
