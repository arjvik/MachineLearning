package com.arjvik.machinelearning.decisiontree.bool;

import java.util.Random;

public class RandomHeuristic implements Heuristic {

	private final Random r = new Random();
	
	@Override
	public double calculcate(Dataset headDataset, Dataset posDataset, Dataset negDataset) {
		return r.nextDouble();
	}

}
