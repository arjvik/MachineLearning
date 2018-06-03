package com.arjvik.machinelearning.decisiontree.bool;

public class LeafNode implements Node {

	private final Scenario scenario;
	private final int posOutputCount;
	private final int negOutputCount;
	
	public LeafNode(Scenario scenario, int posOutputCount, int negOutputCount) {
		super();
		this.scenario = scenario;
		this.posOutputCount = posOutputCount;
		this.negOutputCount = negOutputCount;
	}

	public int getPosCount() {
		return posOutputCount;
	}

	public int getNegCount() {
		return negOutputCount;
	}

	@Override
	public Scenario getScenario() {
		return scenario;
	}

	@Override
	public boolean classify(Data data) {
		return (posOutputCount > negOutputCount) ? true :
			   (posOutputCount < negOutputCount) ? false :
				   								   true;
	}

	@Override
	public String print(int indent, boolean newline) {
		return String.format(" %d [+%d, -%d]\n", classify(null) ? 1 : 0, posOutputCount, negOutputCount);
	}

}
