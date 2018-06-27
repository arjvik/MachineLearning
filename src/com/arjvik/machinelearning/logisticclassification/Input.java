package com.arjvik.machinelearning.logisticclassification;

@lombok.Data
public class Input {

	private final double[] x;
	
	public Input(double... x) {
		this.x = x;
	}

}
