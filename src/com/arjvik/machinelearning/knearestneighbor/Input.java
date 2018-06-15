package com.arjvik.machinelearning.knearestneighbor;

@lombok.Data
public class Input {
	
	private double[] values;
	
	public Input(double... values) {
		this.values = values;
	}
	
}
