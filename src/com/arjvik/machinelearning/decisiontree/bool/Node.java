package com.arjvik.machinelearning.decisiontree.bool;

public interface Node extends ScenarioHolder {

	public boolean classify(Data data);
	
	public String print(int indent, boolean newline);
	
}
