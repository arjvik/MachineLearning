package com.arjvik.machinelearning.decisiontree.bool;

public class InformationGainHeuristic implements Heuristic {

	@Override
	public double calculcate(Dataset headDataset, Dataset posDataset, Dataset negDataset) {
		double single = entropySingle(headDataset);
		double branched = entropyBranched(posDataset, negDataset);
		return single - branched;
	}

	public double entropySingle(Dataset dataset) {
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
	
	public double entropyBranched(Dataset posDataset, Dataset negDataset) {
		int posCount = posDataset.getTrainingData().size();
		int negCount = negDataset.getTrainingData().size();
		double total = posCount + negCount;
		return ((posCount/total)*(entropySingle(posDataset))) + 
			   ((negCount/total)*(entropySingle(negDataset)));
	}


}
