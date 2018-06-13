package com.arjvik.machinelearning.hamspamclassifier.naivebayes;

import java.util.HashSet;
import java.util.Set;

import com.arjvik.machinelearning.hamspamclassifier.AbstractHamSpamClassifier;
import com.arjvik.machinelearning.hamspamclassifier.Classification;
import com.arjvik.machinelearning.hamspamclassifier.Dataset;
import com.arjvik.machinelearning.hamspamclassifier.HamSpamClassifier;
import com.arjvik.machinelearning.hamspamclassifier.Input;
import com.arjvik.machinelearning.hamspamclassifier.InputOutput;
import com.arjvik.machinelearning.hamspamclassifier.Metrics;

public class NaiveBayesClassifier extends AbstractHamSpamClassifier implements HamSpamClassifier {
	
	@lombok.Getter @lombok.Setter
	private int laplaceConstant = 1;
	
	private Set<String> uniqueWords = new HashSet<>();
	private EmailWordCounter spamWordCounter = new EmailWordCounter();
	private EmailWordCounter hamWordCounter  = new EmailWordCounter();
	private int numberOfSpamEmails = 0;
	private int numberOfHamEmails  = 0;
	private double priorSpamPercentage;
	private double priorHamPercentage;
	
	@Override
	public void train(Dataset spam) {
		for (InputOutput io : spam.getData()) {
			if(io.getOutput().equals(Classification.SPAM)) {
				numberOfSpamEmails++;
				for (String word : io.getWords()) {
					spamWordCounter.addWord(word);
					uniqueWords.add(word);
				}
			} else {
				numberOfHamEmails++;
				for (String word : io.getWords()) {
					hamWordCounter.addWord(word);
					uniqueWords.add(word);
				}
			}
		}
		priorSpamPercentage = ((double) numberOfSpamEmails)/(numberOfSpamEmails + numberOfHamEmails);
		priorHamPercentage  =  ((double) numberOfHamEmails)/(numberOfSpamEmails + numberOfHamEmails);
	}

	@Override
	protected Classification classifyEmail(Input input) {
		double spam = Math.log(priorSpamPercentage);
		double ham  = Math.log(priorHamPercentage);
		for (String word : input.getWords()) {
			double pSpam = spamWordCounter.getProbabilityOfWord(word, laplaceConstant, uniqueWords.size());
			double pHam = hamWordCounter.getProbabilityOfWord(word, laplaceConstant, uniqueWords.size());
			spam += Math.log(pSpam);
			ham  += Math.log(pHam);
		}
		return spam > ham ? Classification.SPAM :
							Classification.HAM;
	}
	
	@Override
	public Metrics test(Dataset testing) {
		int total = 0;
		int correct = 0;
		for (InputOutput outputTesting: testing.getData()) {
			total++;
			InputOutput output = classify(new Input(outputTesting.getWords()));
			if(output.getOutput().equals(outputTesting.getOutput()))
				correct++;
		}
		double percentage = ((double) correct) / total;
		return new Metrics(percentage);
	}
	
}
