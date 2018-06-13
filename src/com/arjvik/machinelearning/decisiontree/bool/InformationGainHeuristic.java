package com.arjvik.machinelearning.decisiontree.bool;

public class InformationGainHeuristic extends AbstractGainHeuristic implements Heuristic {

	@Override
	public double calculateSingle(Dataset dataset) {
		double posOutputCount = 0;
		double negOutputCount = 0;
		for (DataOutput output : dataset.getTrainingData()) {
			if(output.getOutput() == true)
				posOutputCount++;
			else
				negOutputCount++;
		}
		double count = posOutputCount + negOutputCount;
		return (-1*(
					((posOutputCount != 0) ? (((posOutputCount/count)*Math.log(posOutputCount/count))) : 0) +
					((negOutputCount != 0) ? (((negOutputCount/count)*Math.log(negOutputCount/count))) : 0)
				)) / Math.log(2);
	}

}
