package com.arjvik.machinelearning.hamspamclassifier;

import java.util.List;

@lombok.Data
@lombok.EqualsAndHashCode(callSuper=true)
public class InputOutput extends Input {
	private final Classification output;
	public InputOutput(List<String> words, Classification output) {
		super(words);
		this.output = output;
	}
}
