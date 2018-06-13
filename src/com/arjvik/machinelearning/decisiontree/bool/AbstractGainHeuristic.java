package com.arjvik.machinelearning.decisiontree.bool;

public abstract class AbstractGainHeuristic implements Heuristic {

	@Override
	public double calculcate(Dataset headDataset, Dataset posDataset, Dataset negDataset) {
		//Split for easy debuging access to heuristics
		double single = calculateSingle(headDataset);
		double branched = calculateBranched(posDataset, negDataset);
		return single - branched;
	}

	public double calculateBranched(Dataset posDataset, Dataset negDataset) {
		int posCount = posDataset.getTrainingData().size();
		int negCount = negDataset.getTrainingData().size();
		double total = posCount + negCount;
		return ((posCount/total)*(calculateSingle(posDataset))) + 
			   ((negCount/total)*(calculateSingle(negDataset)));
	}

	public abstract double calculateSingle(Dataset dataset);

}