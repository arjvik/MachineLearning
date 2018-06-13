package com.arjvik.machinelearning.hamspamclassifier.naivebayes;

import java.util.HashMap;

public class EmailWordCounter {
	
	private HashMap<String, Integer> wordCounter = new HashMap<>();
	private int totalWords = 0;
	
	/** Calculates <code>P(Xi|Y)</code>,
	 * where <code>Xi</code> is <code>word</code>
	 * and <code>Y</code> is this EmailWordCounter's classification */
	public double getProbabilityOfWord(String word, int laplaceConstant, int numberOfUniqueWords) {
		return asDouble(wordCounter.getOrDefault(word.toLowerCase(), 0) + laplaceConstant)/(totalWords + numberOfUniqueWords);
	}
	
	public void addWord(String word) {
		totalWords++;
		word = word.toLowerCase();
		wordCounter.put(word, wordCounter.getOrDefault(word, 0)+1);
	}

	private static double asDouble(int i) {
		return i;
	}
	
}
