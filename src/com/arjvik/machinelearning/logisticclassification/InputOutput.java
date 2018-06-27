package com.arjvik.machinelearning.logisticclassification;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class InputOutput extends Input {
	
	private final double positiveProbability;
	
	public InputOutput(double positiveProbability, double... x) {
		super(x);
		this.positiveProbability = positiveProbability;
	}
	
	public InputOutput(boolean output, double... x) {
		this(output ? 1 : 0, x);
	}
	
	public boolean isPositiveClassification() {
		return positiveProbability >= 0.5d;
	}
	
	public double getNegativeProbability() {
		return 1 - positiveProbability;
	}
	
	public double getClassificationProbability() {
		return isPositiveClassification() ? getPositiveProbability()
										  : getNegativeProbability();
	}
	
}
