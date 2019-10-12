package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.openclassrooms.testing.calcul.domain.ConversionCalculator;
import com.openclassrooms.testing.calcul.domain.model.ConversionModel;
import com.openclassrooms.testing.calcul.domain.model.ConversionType;

public class ConversionQuestion5Test {
	@Mock
	ConversionCalculator calculator;

	ConversionCalculatorServiceImpl conversionCalculatorService;

	@BeforeEach
	public void init() {
		conversionCalculatorService = new ConversionCalculatorServiceImpl(calculator);
	}

	@Test
	public void calculate_shouldUseCalculator_forCelsiusToFarenheit() {
		// GIVEN
		final double resultatAttendu = 32.;

		// WHEN
		final double resultat = conversionCalculatorService.calculate(
				new ConversionModel(0.0, ConversionType.CELSIUS_TO_FARENHEIT)).getSolution();

		// THEN
		verify(calculator).celsiusToFahrenheit(0.0);
		assertThat(resultat).isEqualTo(resultatAttendu);

	}
}
