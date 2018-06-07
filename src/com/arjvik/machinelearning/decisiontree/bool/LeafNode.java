package com.arjvik.machinelearning.decisiontree.bool;

@lombok.Data
public class LeafNode implements Node {

	private final Scenario scenario;
	private final int posOutputCount;
	private final int negOutputCount;
	
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
