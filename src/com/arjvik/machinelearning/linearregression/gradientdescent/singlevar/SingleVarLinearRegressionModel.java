package com.arjvik.machinelearning.linearregression.gradientdescent.singlevar;

public class SingleVarLinearRegressionModel {
	
	private double theta0;
	private double theta1;
	
	@lombok.Getter @lombok.Setter
	private double alpha = 0.0005;
	
	@lombok.Getter @lombok.Setter
	private double tolerance = 1e-10;
	
	public void train(Dataset dataset) {
		theta0 = 0;
		theta1 = 0;
		boolean unchanged;
		do {
			System.out.printf("Current: %s, Cost: %e, ", this, cost(dataset));
			double temp0 = theta0 - calculateDifferentialTheta0(dataset);
			double temp1 = theta1 - calculateDifferentialTheta1(dataset);
			unchanged = (theta0-temp0)*(theta0-temp0) + (theta1-temp1)*(theta1-temp1) < tolerance;
			System.out.printf("Error: %e%n",(theta0-temp0)*(theta0-temp0) + (theta1-temp1)*(theta1-temp1));
			theta0 = temp0;
			theta1 = temp1;
		} while (!unchanged);
	}
	
	private double calculateDifferentialTheta0(Dataset dataset) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			sum += hypothesis(datapoint.getX()) - datapoint.getY();
		}
		return (alpha * sum) / dataset.getTrainingData().size();
	}
	
	private double calculateDifferentialTheta1(Dataset dataset) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			sum += (hypothesis(datapoint.getX()) - datapoint.getY())*datapoint.getX();
		}
		return (alpha * sum) / dataset.getTrainingData().size();
	}

	public InputOutput calculate(Input input) {
		return new InputOutput(input.getX(), hypothesis(input.getX()));
	}
	
	private double hypothesis(double x) {
		return theta0 + theta1*x;
	}
	
	public double cost(Dataset dataset) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			double difference = (hypothesis(datapoint.getX()) - datapoint.getY());
			sum += difference * difference;
		}
		return sum / (dataset.getTrainingData().size() * 2);
	}
	
	@Override
	public String toString() {
		return String.format("h(x) = %fx + %f", theta1, theta0);
	}
	
}
