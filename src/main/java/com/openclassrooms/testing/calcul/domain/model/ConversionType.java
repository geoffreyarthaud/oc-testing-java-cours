package com.openclassrooms.testing.calcul.domain.model;

import java.util.Arrays;

public enum ConversionType {
	CELSIUS_TO_FARENHEIT("°C->°F"),
	FARENHEIT_TO_CELSIUS("°F->°C"),
	LITRE_TO_GALLON("L->GAL"),
	GALLON_TO_LITRE("GAL->L");

	private String unitString;

	ConversionType(String unitString) {
		this.unitString = unitString;
	}

	@Override
	public String toString() {
		return unitString;
	}

	public static ConversionType of(String unitString) {
		return Arrays.asList(ConversionType.values()).stream()
				.filter(v -> v.unitString.equals(unitString))
				.findFirst()
				.orElseThrow(
						() -> new IllegalArgumentException(unitString + " n'est pas un type de conversion connu."));
	}
}
