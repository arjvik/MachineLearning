package com.arjvik.machinelearning.hamspamclassifier;

public interface HamSpamClassifier {

	public void train(Dataset training);
	
	public InputOutput classify(Input input);
	
	public Metrics test(Dataset testing);

	public Metrics trainAndTest(Dataset training, Dataset testing);
	
}
