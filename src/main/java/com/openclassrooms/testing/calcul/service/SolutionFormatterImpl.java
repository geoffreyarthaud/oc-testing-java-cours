package com.openclassrooms.testing.calcul.service;

import java.util.Locale;

import javax.inject.Named;

@Named
public class SolutionFormatterImpl implements SolutionFormatter {

	@Override
	public String format(int solution) {
		return String.format(Locale.FRENCH, "%,d", solution);
	}
}
