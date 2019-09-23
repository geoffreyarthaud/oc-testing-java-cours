package com.openclassrooms.testing.calcul.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.openclassrooms.testing.calcul.domain.model.CalculationModel;

public class BatchCalculatorServiceImpl implements BatchCalculatorService {

	private final CalculatorService calculatorService;

	public BatchCalculatorServiceImpl(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@Override
	public List<CalculationModel> batchCalculate(Stream<String> operations) {
		return operations.map(s -> calculatorService.calculate(CalculationModel.fromText(s)))
				.collect(Collectors.toList());
	}

}
