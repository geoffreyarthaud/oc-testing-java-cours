package com.openclassrooms.testing.calcul.service;

import com.openclassrooms.testing.calcul.domain.ConversionCalculator;
import com.openclassrooms.testing.calcul.domain.model.ConversionModel;

public class ConversionCalculatorServiceImpl implements ConversionCalculatorService {

	private final ConversionCalculator calculator;

	public ConversionCalculatorServiceImpl(ConversionCalculator calculator) {
		this.calculator = calculator;
	}

	@Override
	public ConversionModel calculate(ConversionModel conversionModel) {
		double response = 0;
		final double argument = conversionModel.getArgument();
		switch (conversionModel.getConversionType()) {
		case CELSIUS_TO_FARENHEIT:
			response = calculator.celsiusToFahrenheit(argument);
			break;
		case FARENHEIT_TO_CELSIUS:
			response = calculator.fahrenheitToCelsius(argument);
			break;
		case LITRE_TO_GALLON:
			response = calculator.litresToGallons(argument);
			break;
		case GALLON_TO_LITRE:
			response = calculator.gallonsToLitres(argument);
			break;
		default:
			throw new UnsupportedOperationException("Conversion non supportée.");
		}
		conversionModel.setSolution(response);
		return conversionModel;
	}

}
