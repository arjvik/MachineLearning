package com.arjvik.machinelearning.decisiontree.bool;

public class VarianceImpurityGainHeuristic extends AbstractGainHeuristic implements Heuristic {

	@Override
	public double calculateSingle(Dataset dataset) {
		int posOutputCount = 0;
		int negOutputCount = 0;
		for (DataOutput output : dataset.getTrainingData()) {
			if(output.getOutput() == true)
				posOutputCount++;
			else
				negOutputCount++;
		}
		double count = posOutputCount + negOutputCount;
		return (posOutputCount*negOutputCount)/(count*count);
	}

}
