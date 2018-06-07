package com.arjvik.machinelearning.decisiontree.bool;

import java.util.List;

@lombok.Data
public class BranchingNode implements Node {
	
	private final Scenario scenario;
	private final List<Integer> featuresAvailable;
	private final int currentFeature;
	private final int posOutputCount;
	private final int negOutputCount;
	private Node posChild;
	private Node negChild;
	
	@Override
	public boolean classify(Data data) {
		if(data.getData()[currentFeature] == true)
			return posChild.classify(data);
		else
			return negChild.classify(data);
			
	}
	
	@Override
	public String print(int indent, boolean newline) {
		StringBuffer buffer = new StringBuffer();
		if(newline)
			buffer.append("\n");
		for (int i = 0; i < indent; i++) {
			buffer.append("|");
		}
		String indentString = buffer.toString();
		return String.format("%s%s = 0 :%s%s%s = 1 :%s\n",indentString, scenario.getFeatures().get(currentFeature), negChild.print(indent+1, true),
														  indentString, scenario.getFeatures().get(currentFeature), posChild.print(indent+1, true));
	}

}
