package com.openclassrooms.testing.calcul.domain;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

@Named
public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}

	public int sub(int a, int b) {
		return a - b;
	}

	public int multiply(int a, int b) {
		return a * b;
	}

	public int divide(int a, int b) {
		return a / b;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public double sub(double a, double b) {
		return a - b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public double divide(double a, double b) {
		return a / b;
	}

	public int fact(int a) {
		if (a < 0 || a > 12) {
			throw new IllegalArgumentException("Doit être compris entre 0 et 12.");
		}
		if (a <= 1) {
			return a;
		}
		return a * fact(a - 1);
	}

	public void longCalculation() {
		try {
			Thread.sleep(500);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Set<Integer> digitsSet(int number) {
		final Set<Integer> integers = new HashSet<Integer>();
		final String numberString = String.valueOf(number);

		for (int i = 0; i < numberString.length(); i++) {
			if (numberString.charAt(i) != '-') {
				integers.add(Integer.parseInt(numberString, i, i + 1, 10));
			}
		}
		return integers;
	}

}
