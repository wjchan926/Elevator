package elevator;

import java.io.*;

/**
 * This class writes the data to an output file for the elevator simulation.
 * There are no elevator functions or calculations found in this class. This
 * class is solely used for read/write purposes.
 * 
 * @author Wesley Chan
 *
 */
public class WriteFile {

	private String filepath;
	private FileWriter outFile;
	private PrintWriter printFile;

	/**
	 * Constructor for WriteFile class with a filepath as a argument
	 * 
	 * @param s
	 *            filepath
	 * @throws IOException
	 *             throws exception if it cannot find a file
	 */
	WriteFile(String s) throws IOException {
		filepath = s;
		outFile = new FileWriter(filepath);
		printFile = new PrintWriter(outFile);
	}

	/**
	 * Writes a formatted string to file
	 * 
	 * @param s
	 *            string to be written to output file
	 * @throws IOException
	 *             throws exception if it cannot find file
	 */
	public void writeToFile(String s) throws IOException {
		printFile.printf(s);
	}

	/**
	 * Closes the printFile object
	 */
	public void closeFile() {
		printFile.close();
	}
}
