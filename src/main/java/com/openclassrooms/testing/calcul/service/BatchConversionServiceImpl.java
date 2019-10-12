package com.openclassrooms.testing.calcul.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.openclassrooms.testing.calcul.domain.model.ConversionModel;

public class BatchConversionServiceImpl implements BatchConversionService {

	private final ConversionCalculatorService conversionService;

	public BatchConversionServiceImpl(ConversionCalculatorService conversionService) {
		this.conversionService = conversionService;
	}

	@Override
	public List<ConversionModel> batchConvert(Stream<String> operations) {
		return operations.map(s -> conversionService.calculate(ConversionModel.fromText(s)))
				.collect(Collectors.toList());
	}

}
