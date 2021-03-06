package com.arjvik.machinelearning.linearregression.gradientdescent.multivar;

public class LinearRegressionModel {

	private final int numberOfFeatures;
	private double[] theta;
	private double[] tempTheta;
	
	@lombok.Getter @lombok.Setter
	private double alpha = 0.0005;
	
	@lombok.Getter @lombok.Setter
	private double tolerance = 1e-10;
	
	public LinearRegressionModel(int numberOfFeatures) {
		this.numberOfFeatures = numberOfFeatures;
		theta = new double[numberOfFeatures + 1];
		tempTheta = new double[numberOfFeatures + 1];
	}
	
	public void train(Dataset dataset) {
		for(int i = 0; i < numberOfFeatures + 1; i++)
			theta[i] = 0;
		boolean unchanged;
		do {
			System.out.printf("Current: %s, Cost: %e, ", this, cost(dataset));
			tempTheta[0] = theta[0] - calculateDifferentialTheta0(dataset);
			for (int j = 1; j < theta.length; j++) {
				tempTheta[j] = theta[j] - calculateDifferentialThetaJ(dataset, j);
			}
			double[] temp = theta;
			theta = tempTheta;
			tempTheta = temp;
			temp = null;
			double error = 0;
			for (int i = 0; i < theta.length; i++) {
				double delta = theta[i] - tempTheta[i];
				error += delta*delta;
			}
			System.out.printf("Error: %e%n", error);
			unchanged = error < tolerance;
		} while(!unchanged);
	}
	
	private double calculateDifferentialThetaJ(Dataset dataset, int j) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			sum += (hypothesis(datapoint.getX()) - datapoint.getY())*datapoint.getX()[j-1];
		}
		return (alpha * sum) / dataset.getTrainingData().size();
	}
	
	private double calculateDifferentialTheta0(Dataset dataset) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			sum += (hypothesis(datapoint.getX()) - datapoint.getY());
		}
		return (alpha * sum) / dataset.getTrainingData().size();
	}
	
	private double hypothesis(double... x) {
		if(x.length + 1 != theta.length)
			throw new IncorrectNumberOfFeaturesException();
		double sum = theta[0];
		for (int i = 1; i < theta.length; i++) {
			sum += theta[i] * x[i-1];
		}
		return sum;
	}
	
	public double cost(Dataset dataset) {
		double sum = 0;
		for (InputOutput datapoint : dataset.getTrainingData()) {
			double difference = (hypothesis(datapoint.getX()) - datapoint.getY());
			sum += difference * difference;
		}
		return sum / (dataset.getTrainingData().size() * 2);
	}
	
	public InputOutput calculate(Input input) {
		return new InputOutput(hypothesis(input.getX()), input.getX());
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(String.format("h(x...) = %.4f", theta[0]));
		for (int i = 1; i < theta.length; i++) {
			buffer.append(String.format(" + %.4fx(%d)", theta[i], i));
		}
		return buffer.toString();
	}
	
}
