package com.openclassrooms.testing.calcul.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.testing.calcul.domain.model.Calculation;
import com.openclassrooms.testing.calcul.domain.model.CalculationModel;
import com.openclassrooms.testing.calcul.domain.model.CalculationType;
import com.openclassrooms.testing.calcul.service.CalculatorService;

@Controller
public class CalculatorController {

	public static final String CALCULATOR_TEMPLATE = "calculator";

	@Inject
	CalculatorService calculatorService;

	@GetMapping("/")
	public String index(Calculation calculation) {
		return "redirect:/calculator";
	}

	@GetMapping("/calculator")
	public String root(Calculation calculation) {
		return CALCULATOR_TEMPLATE; // cf. resources/templates/calculator.html
	}

	@PostMapping("/calculator")
	public String calculate(@Valid Calculation calculation, BindingResult bindingResult, Model model) {

		final CalculationType type = CalculationType.valueOf(calculation.getCalculationType());
		final CalculationModel calculationModel = new CalculationModel(
				type,
				calculation.getLeftArgument(),
				calculation.getRightArgument());

		final CalculationModel response = calculatorService.calculate(calculationModel);

		model.addAttribute("response", response);
		return CALCULATOR_TEMPLATE; // cf. resources/templates/calculator.html
	}
}
