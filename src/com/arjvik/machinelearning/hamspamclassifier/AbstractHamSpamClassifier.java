package com.arjvik.machinelearning.hamspamclassifier;

public abstract class AbstractHamSpamClassifier implements HamSpamClassifier {

	@Override
	public abstract void train(Dataset training);

	@Override
	public InputOutput classify(Input input) {
		return new InputOutput(input.getWords(), classifyEmail(input));
	}
	
	protected abstract Classification classifyEmail(Input input);
	

	@Override
	public abstract Metrics test(Dataset testing);
	
	@Override
	public Metrics trainAndTest(Dataset training, Dataset testing) {
		train(training);
		return test(testing);
	}

}
