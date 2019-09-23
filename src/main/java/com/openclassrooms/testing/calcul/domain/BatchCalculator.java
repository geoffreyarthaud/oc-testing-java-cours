package com.openclassrooms.testing.calcul.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

public class BatchCalculator {
	
	private Calculator calculator;

	public BatchCalculator(Calculator calculator) {
		this.calculator = calculator;
	}

	public List<CalculationModel> batchCalculate(Stream<String> operations) {
		return operations.map(s -> solve(CalculationModel.fromText(s)))
				.collect(Collectors.toList());
	}
	
    private CalculationModel solve(CalculationModel calculationModel) {
        CalculationType type = calculationModel.getType();

        Integer response = null;
        switch (type) {
            case ADDITION:
                response = calculator.add(
                        calculationModel.getLeftArgument(),
                        calculationModel.getRightArgument());
                break;
            case MULTIPLICATION:
                response = calculator.multiply(
                        calculationModel.getLeftArgument(),
                        calculationModel.getRightArgument());
                break;
            default:
                throw new UnsupportedOperationException("Unsupported calculations");
        }

        calculationModel.setSolution(response);
        return calculationModel;
    }

}
