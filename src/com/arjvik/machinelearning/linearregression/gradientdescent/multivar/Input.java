package com.arjvik.machinelearning.linearregression.gradientdescent.multivar;

@lombok.Data
public class Input {

	private final double[] x;
	
	public Input(double... x) {
		this.x = x;
	}

}
