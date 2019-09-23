package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

public class CalculatorServiceTest {

	Calculator calculator = new Calculator();
	// Calculator IS CALLED BY CalculatorService
	CalculatorService classUnderTest = new CalculatorServiceImpl(calculator);

	@Test
	public void add_returnsTheSum_ofTwoPositiveNumbers() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 1, 2)).getSolution();
		assertThat(result).isEqualTo(3);
	}

	@Test
	public void add_returnsTheSum_ofTwoNegativeNumbers() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, -1, 2))
				.getSolution();

		assertThat(result).isEqualTo(1);
	}

	@Test
	public void add_returnsTheSum_ofZeroAndZero() {
		final int result = classUnderTest.calculate(
				new CalculationModel(CalculationType.ADDITION, 0, 0)).getSolution();
		assertThat(result).isEqualTo(0);
	}
}
