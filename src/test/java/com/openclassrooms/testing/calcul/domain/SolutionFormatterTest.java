package com.openclassrooms.testing.calcul.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SolutionFormatterTest {
	
	private SolutionFormatter solutionFormatter;
	
	@BeforeEach
	public void initFormatter() {
		solutionFormatter = new SolutionFormatter();
	}
	
	@Test
	public void format_shouldFormatAnyBigNumber() {
		// GIVEN
		int number = 1234567890;
		
		// WHEN
		String result = solutionFormatter.format(number);
		
		// THEN
		assertThat(result).isEqualTo("1 234 567 890");
	}
	
}
