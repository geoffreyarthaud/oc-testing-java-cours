package com.openclassrooms.testing.calcul.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;


import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


@SpringBootTest
@AutoConfigureMockMvc
public class CalculationSteps {
	
		@Inject
		MockMvc mockMvc;
		
		private Integer lastLeftArgument;
		private Integer lastRightArgument;
		private String calculationType;
		
		
		@Given("un élève utilise le calculateur")
		public void a_student_is_using_calculator() throws Exception {
			
			mockMvc.perform(MockMvcRequestBuilders.get("/calculator"))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		}
		
		@When("{int} et {int} sont additionnés")
		public void and_are_added(Integer leftArgument, Integer rightArgument) throws Exception {
			lastLeftArgument= leftArgument;
			lastRightArgument=rightArgument;
			calculationType= "ADDITION";
		}
		@Then("{int} et {int} sont additionnés")
		public void and_are_added(Integer expectedResult) throws Exception {
			final MvcResult result = 
					mockMvc.perform(MockMvcRequestBuilders.post("/calculator")
					.param("leftArgument",lastLeftArgument.toString())
					.param("rightArgument",lastRightArgument.toString())
					.param("calculationType",calculationType))
					.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();
			
			assertThat(result.getResponse().getContentAsString())
			.contains(">" + expectedResult + "<");
					
		}
		


}
