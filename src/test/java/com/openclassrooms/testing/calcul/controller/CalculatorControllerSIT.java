package com.openclassrooms.testing.calcul.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.openclassrooms.testing.calcul.domain.Calculator;
import com.openclassrooms.testing.calcul.service.CalculatorService;
import com.openclassrooms.testing.calcul.service.SolutionFormatter;

@WebMvcTest(controllers = { CalculatorController.class, CalculatorService.class })
@ExtendWith(SpringExtension.class)
public class CalculatorControllerSIT {

	@Inject
	private MockMvc mockMvc;

	@MockBean
	private SolutionFormatter solutionFormatter;

	@MockBean
	private Calculator calculator;

	@Test
	public void givenACalculatorApp_whenRequestToAdd_thenSolutionIsShown() throws Exception {
		// GIVEN
		when(calculator.add(2, 3)).thenReturn(5);

		// WHEN
		final MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/calculator")
						.param("leftArgument", "2")
						.param("rightArgument", "3")
						.param("calculationType", "ADDITION"))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
				.andReturn();

		// THEN
		assertThat(result.getResponse().getContentAsString())
				.contains("id=\"solution\"")
				.contains(">5</span");
		verify(calculator).add(2, 3);
		verify(solutionFormatter).format(5);
	}
}
