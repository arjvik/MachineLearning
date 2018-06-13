package com.arjvik.machinelearning.linearregression.gradientdescent.singlevar;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class InputOutput extends Input {
	
	private final double y;
	
	public InputOutput(double x, double y) {
		super(x);
		this.y = y;
	}
	
}
