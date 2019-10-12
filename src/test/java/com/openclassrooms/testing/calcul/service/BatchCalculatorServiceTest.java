package com.openclassrooms.testing.calcul.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;

@ExtendWith(MockitoExtension.class)
public class BatchCalculatorServiceTest {

	@Mock
	CalculatorService calculatorService;

	BatchCalculatorService batchCalculatorService;

	BatchCalculatorService batchCalculatorServiceNoMock;

	@BeforeEach
	public void init() {
		batchCalculatorService = new BatchCalculatorServiceImpl(calculatorService);

		batchCalculatorServiceNoMock = new BatchCalculatorServiceImpl(
				new CalculatorServiceImpl(new Calculator(),
						new SolutionFormatterImpl()));
	}

	@Test
	public void givenOperationsList_whenbatchCalculate_thenReturnsCorrectAnswerList()
			throws IOException, URISyntaxException {
		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();

		// WHEN
		final List<CalculationModel> results = batchCalculatorServiceNoMock.batchCalculate(operations);

		// THEN
		assertThat(results).extracting(CalculationModel::getSolution).containsExactly(4, 1, 48, 3);
	}

	@Test
	public void givenOperationsList_whenbatchCalculate_thenCallsServiceWithCorrectArguments() {
		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		final ArgumentCaptor<CalculationModel> calculationModelCaptor = ArgumentCaptor.forClass(CalculationModel.class);

		// WHEN
		batchCalculatorService.batchCalculate(operations);

		// THEN
		verify(calculatorService, times(4)).calculate(calculationModelCaptor.capture());
		final List<CalculationModel> calculationModels = calculationModelCaptor.getAllValues();
		assertThat(calculationModels)
				.extracting(CalculationModel::getLeftArgument, CalculationModel::getType,
						CalculationModel::getRightArgument)
				.containsExactly(
						tuple(2, CalculationType.ADDITION, 2),
						tuple(5, CalculationType.SUBTRACTION, 4),
						tuple(6, CalculationType.MULTIPLICATION, 8),
						tuple(9, CalculationType.DIVISION, 3));
	}

	@Test
	public void givenOperationsList_whenbatchCalculate_thenCallsServiceAndReturnsAnswer() {
		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		when(calculatorService.calculate(any(CalculationModel.class)))
				.then(invocation -> {
					final CalculationModel model = invocation.getArgument(0, CalculationModel.class);
					switch (model.getType()) {
					case ADDITION:
						model.setSolution(4);
						break;
					case SUBTRACTION:
						model.setSolution(1);
						break;
					case MULTIPLICATION:
						model.setSolution(48);
						break;
					case DIVISION:
						model.setSolution(3);
						break;
					default:
					}
					return model;
				});

		// WHEN
		final List<CalculationModel> results = batchCalculatorService.batchCalculate(operations);

		// THEN
		verify(calculatorService, times(4)).calculate(any(CalculationModel.class));
		assertThat(results).extracting("solution").containsExactly(4, 1, 48, 3);

	}

	@Test
	public void givenOperationsList_whenbatchCalculate_thenCallsServiceAndReturnsAnswer2() {
		// GIVEN
		final Stream<String> operations = Arrays.asList("2 + 2", "5 - 4", "6 x 8", "9 / 3").stream();
		when(calculatorService.calculate(any(CalculationModel.class)))
				.thenReturn(new CalculationModel(CalculationType.ADDITION, 2, 2, 4))
				.thenReturn(new CalculationModel(CalculationType.SUBTRACTION, 5, 4, 1))
				.thenReturn(new CalculationModel(CalculationType.MULTIPLICATION, 6, 8, 48))
				.thenReturn(new CalculationModel(CalculationType.DIVISION, 9, 3, 3));

		// WHEN
		final List<CalculationModel> results = batchCalculatorService.batchCalculate(operations);

		// THEN
		verify(calculatorService, times(4)).calculate(any(CalculationModel.class));
		assertThat(results).extracting("solution").containsExactly(4, 1, 48, 3);

	}

}
