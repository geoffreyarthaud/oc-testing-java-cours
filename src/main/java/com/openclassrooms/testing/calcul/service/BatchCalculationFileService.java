package com.openclassrooms.testing.calcul.service;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * Enables interacting with a batch file for calculations
 */
public interface BatchCalculationFileService {

	/**
	 * Reads the calculations from a batch file and returns a stream of written
	 * calculations.
	 * 
	 * @param file path of the batchfile
	 * @return stream of calculations in the form "2 + 2"
	 */
	Stream<String> read(String file) throws IOException;

}
