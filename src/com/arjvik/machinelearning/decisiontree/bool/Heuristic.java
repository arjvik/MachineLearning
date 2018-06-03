package com.arjvik.machinelearning.decisiontree.bool;

public interface Heuristic {
	
	public double calculcate(Dataset headDataset, Dataset posDataset, Dataset negDataset);
	
}
