package com.openclassrooms.testing.calcul.domain.model;

import java.text.MessageFormat;

public class ConversionModel {

	private Double argument;
	private ConversionType conversionType;
	private Double solution;

	public ConversionModel(Double argument, ConversionType conversionType) {
		this.argument = argument;
		this.conversionType = conversionType;
	}

	public Double getArgument() {
		return argument;
	}

	public void setArgument(Double argument) {
		this.argument = argument;
	}

	public ConversionType getConversionType() {
		return conversionType;
	}

	public void setConversionType(ConversionType conversionType) {
		this.conversionType = conversionType;
	}

	public Double getSolution() {
		return solution;
	}

	public void setSolution(Double solution) {
		this.solution = solution;
	}

	public String getFormattedSolution() {
		return MessageFormat.format("{0} {1}", solution, conversionType);
	}

	public static ConversionModel fromText(String s) {
		final String[] parts = s.split(" ");
		return new ConversionModel(Double.parseDouble(parts[0]), ConversionType.of(parts[1]));
	}

}
