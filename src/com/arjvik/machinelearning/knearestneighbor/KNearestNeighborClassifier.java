package com.arjvik.machinelearning.knearestneighbor;

import java.util.ArrayList;
import java.util.List;

public class KNearestNeighborClassifier {
	
	@lombok.Getter private final int numberOfFeatures;
	@lombok.Getter @lombok.Setter private int K = 3;
	@lombok.Getter @lombok.Setter private boolean normalizeValues = true;
	private double[] stdDeviation;
	private final List<InputOutput> data = new ArrayList<>();

	public KNearestNeighborClassifier(int numberOfFeatures) {
		this.numberOfFeatures = numberOfFeatures;
	}
	
	public void train(Dataset training) {
		training.checkNumberOfFeatures(numberOfFeatures);
		data.addAll(training.getTrainingData());
		stdDeviation = getStandardDeviation();
	}
	
	private double[] getStandardDeviation() {
		double[] stdDev = new double[numberOfFeatures];
		for (int i = 0; i < numberOfFeatures; i++) {
			final int $i = i;
			final double avg = data.stream()
								   .mapToDouble(io -> io.getValues()[$i])
								   .average()
								   .getAsDouble();
			stdDev[i] = Math.sqrt(data.stream()
									  .mapToDouble(io -> io.getValues()[$i])
									  .map(d -> d-avg)
									  .map(d -> d*d)
									  .sum() / data.size());
		}
		return stdDev;
	}

	public InputOutput classify(Input input) {
		if(input.getValues().length != numberOfFeatures)
			throw new IncorrectNumberOfFeaturesException();
		return new InputOutput(input, classify(input.getValues()));
	}
	
	private double classify(double[] values) {
		return data.stream()
				   .map(io -> new Tuple<>(io, getEuclidianDistance(values, io.getValues())))
				   .sorted((t1, t2) -> t1.getY().compareTo(t2.getY()))
				   .limit(K)
				   .mapToDouble(t -> t.getX().getOutput())
				   .average()
				   .getAsDouble();
	}
	
	private double getEuclidianDistance(double[] values, double[] values2) {
		double sum = 0;
		for (int i = 0; i < values.length; i++) {
			double delta = (values[i] - values2[i]);
			if(normalizeValues)
				delta /= stdDeviation[i];
			sum += delta*delta;
		}
//		return Math.sqrt(sum);
		return sum;
	}
}
