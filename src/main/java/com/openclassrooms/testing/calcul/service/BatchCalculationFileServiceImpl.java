package com.openclassrooms.testing.calcul.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.inject.Named;

/**
 * Reads a text file and returns a stream of calculations
 */
@Named
public class BatchCalculationFileServiceImpl implements BatchCalculationFileService {

	/**
	 * Returns a stream of the rows in a file with file. The file format should have
	 * a calculation with a mathematical operator and an argument on each side,
	 * separated by a spaces. Eg:
	 *
	 * <pre>
	 *     2 + 2
	 *     3 - 2
	 *     2022 / 4
	 * </pre>
	 *
	 * @param file path of the batch file
	 * @return Stream of calculations
	 */
	@Override
	public Stream<String> read(String file) throws IOException {
		return Files.lines(Paths.get(file));
	}
}
