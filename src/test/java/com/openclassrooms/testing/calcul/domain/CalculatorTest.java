package com.openclassrooms.testing.calcul.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(LoggingExtension.class)
public class CalculatorTest {

	private static Instant startedAt;

	private Calculator calculatorUnderTest;

	private Logger logger;

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	@BeforeEach
	public void initCalculator() {
		logger.info("Appel avant chaque test");
		calculatorUnderTest = new Calculator();
	}

	@AfterEach
	public void undefCalculator() {
		logger.info("Appel après chaque test");
		calculatorUnderTest = null;
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests");
		final Instant endedAt = Instant.now();
		final long duration = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}

	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		final int a = 2;
		final int b = 3;

		// Act
		final int somme = calculatorUnderTest.add(a, b);

		// Assert
		assertThat(somme).isEqualTo(5);
		assertEquals(5, somme);
	}

	@Test
	public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
		// Arrange
		final int a = 42;
		final int b = 11;

		// Act
		final int produit = calculatorUnderTest.multiply(a, b);

		// Assert
		assertEquals(462, produit);
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = { 1, 2, 42, 1011, 5089 })
	public void multiply_shouldReturnZero_ofZeroWithMultipleIntegers(int arg) {
		// Arrange -- Tout est prêt !

		// Act -- Multiplier par zéro
		final int actualResult = calculatorUnderTest.multiply(arg, 0);

		// Assert -- ça vaut toujours zéro !
		assertEquals(0, actualResult);
	}

	@ParameterizedTest(name = "{0} + {1} doit être égal à {2}")
	@CsvSource({ "1,1,2", "2,3,5", "42,57,99" })
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectResult) {
		// Arrange -- Tout est prêt !

		// Act
		final int actualResult = calculatorUnderTest.add(arg1, arg2);

		// Assert
		assertEquals(expectResult, actualResult);
	}

	@Timeout(1)
	@Test
	public void longCalcul_shouldComputeInLessThan1Second() {
		// Arrange

		// Act
		calculatorUnderTest.longCalculation();

		// Assert
		// ...
	}

	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofPositiveInteger() {
		// GIVEN
		final int number = 95897;

		// WHEN
		final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);

		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(9, 5, 8, 7);
		final Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
		assertEquals(expectedDigits, actualDigits);
	}

	@Test
	public void listDigits_shouldReturnsTheListOfDigits_ofNegativeInteger() {
		final int number = -124432;
		final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		assertThat(actualDigits).containsExactlyInAnyOrder(1, 2, 3, 4);
	}

	@Test
	public void listDigits_shouldReturnsTheListOfZero_ofZero() {
		final int number = 0;
		final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		assertThat(actualDigits).containsExactly(0);
	}

	@Disabled("Stoppé car cela échoue tous les mardis")
	@Test
	public void testDate() {
		// GIVEN
		final LocalDateTime dateTime = LocalDateTime.now();

		// WHEN

		// THEN
		assertThat(dateTime.getDayOfWeek()).isNotEqualTo(DayOfWeek.TUESDAY);
	}

	@Test
	public void fact12_shouldReturnsTheCorrectAnswer() {
		// GIVEN
		final int number = 12;

		// WHEN
		final int cacheFactorial = calculatorUnderTest.fact(number);

		// THEN
		assertThat(cacheFactorial).isEqualTo(12 * 11 * 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2);

	}

	@Test
	public void digitsSetOfFact12_shouldReturnsTheCorrectAnswser() {
		// GIVEN
		final int cacheFactorial = 479001600;

		// WHEN
		final Set<Integer> actualDigits = calculatorUnderTest.digitsSet(cacheFactorial);

		// THEN
		assertThat(actualDigits).containsExactlyInAnyOrder(0, 1, 4, 6, 7, 9);
	}

	@Test
	public void multiplyAndDivide_shouldBeIdentity() {
		// GIVEN
		final Random r = new Random();
		final int a = 1 + r.nextInt(100);
		final int b = 1 + r.nextInt(3);

		// WHEN on multiplie a par b puis on divise par b
		final int c = calculatorUnderTest.divide(calculatorUnderTest.multiply(a, b), b);

		// THEN on ré-obtient a
		assertThat(c).isEqualTo(a);
	}

}
