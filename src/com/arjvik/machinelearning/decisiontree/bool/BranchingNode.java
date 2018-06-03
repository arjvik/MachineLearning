package com.arjvik.machinelearning.decisiontree.bool;

import java.util.List;

public class BranchingNode implements Node {
	
	private final Scenario scenario;
	private final List<Integer> featuresAvailable;
	private final int currentFeature;
	private final int posOutputCount;
	private final int negOutputCount;
	private Node posChild;
	private Node negChild;
	
	public BranchingNode(Scenario scenario, List<Integer> featuresAvailable, int currentFeature, int posOutputCount, int negOutputCount) {
		super();
		this.scenario = scenario;
		this.featuresAvailable = featuresAvailable;
		this.currentFeature = currentFeature;
		this.posOutputCount = posOutputCount;
		this.negOutputCount = negOutputCount;
	}

	public Node getPosChild() {
		return posChild;
	}

	public void setPosChild(Node posChild) {
		this.posChild = posChild;
	}

	public Node getNegChild() {
		return negChild;
	}

	public void setNegChild(Node negChild) {
		this.negChild = negChild;
	}

	public List<Integer> getFeaturesAvailable() {
		return featuresAvailable;
	}

	public int getCurrentFeature() {
		return currentFeature;
	}

	public int getPosOutputCount() {
		return posOutputCount;
	}

	public int getNegOutputCount() {
		return negOutputCount;
	}

	@Override
	public Scenario getScenario() {
		return scenario;
	}

	@Override
	public boolean classify(Data data) {
		if(data.getData()[currentFeature] == true)
			return posChild.classify(data);
		else
			return negChild.classify(data);
			
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentFeature;
		result = prime * result + ((featuresAvailable == null) ? 0 : featuresAvailable.hashCode());
		result = prime * result + ((negChild == null) ? 0 : negChild.hashCode());
		result = prime * result + negOutputCount;
		result = prime * result + ((posChild == null) ? 0 : posChild.hashCode());
		result = prime * result + posOutputCount;
		result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BranchingNode other = (BranchingNode) obj;
		if (currentFeature != other.currentFeature)
			return false;
		if (featuresAvailable == null) {
			if (other.featuresAvailable != null)
				return false;
		} else if (!featuresAvailable.equals(other.featuresAvailable))
			return false;
		if (negChild == null) {
			if (other.negChild != null)
				return false;
		} else if (!negChild.equals(other.negChild))
			return false;
		if (negOutputCount != other.negOutputCount)
			return false;
		if (posChild == null) {
			if (other.posChild != null)
				return false;
		} else if (!posChild.equals(other.posChild))
			return false;
		if (posOutputCount != other.posOutputCount)
			return false;
		if (scenario == null) {
			if (other.scenario != null)
				return false;
		} else if (!scenario.equals(other.scenario))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BranchingNode [scenario=" + scenario + ", featuresAvailable=" + featuresAvailable + ", currentFeature="
				+ currentFeature + ", posOutputCount=" + posOutputCount + ", negOutputCount=" + negOutputCount
				+ ", posChild=" + posChild + ", negChild=" + negChild + "]";
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
