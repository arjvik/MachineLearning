package com.arjvik.machinelearning.decisiontree.bool;

public class Metrics {
	
	private final double classificationPercentage;

	public Metrics(double classificationPercentage) {
		super();
		this.classificationPercentage = classificationPercentage;
	}

	public double getClassificationPercentage() {
		return classificationPercentage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(classificationPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Metrics other = (Metrics) obj;
		if (Double.doubleToLongBits(classificationPercentage) != Double
				.doubleToLongBits(other.classificationPercentage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Metrics [classificationPercentage=" + classificationPercentage + "]";
	}
	
}
