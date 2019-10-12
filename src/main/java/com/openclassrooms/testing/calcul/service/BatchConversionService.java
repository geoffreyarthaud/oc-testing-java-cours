package com.openclassrooms.testing.calcul.service;

import java.util.List;
import java.util.stream.Stream;

import com.openclassrooms.testing.calcul.domain.model.ConversionModel;

public interface BatchConversionService {
	public List<ConversionModel> batchConvert(Stream<String> operations);
}
