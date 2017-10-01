package elevator;

import java.io.*;

/**
 * This class reads the input data for the elevator simulation
 * 
 * @author Wesley Chan
 *
 */
public class Reader {

	private File f;
	private String[] fileLines;

	/**
	 * Constructor with file path and number of lines as arguments
	 * 
	 * @param fileName
	 *            filepath
	 * @param numLines
	 *            number of records in file
	 * @throws IOException
	 *             throws exception if it cannot find the file
	 */
	public Reader(String fileName) throws IOException {
		f = new File(fileName);
		fileLines = new String[countLines()];
	}

	/**
	 * Gets the file being read
	 * 
	 * @return f file
	 */
	public File getFile() {
		return f;
	}

	/**
	 * Gets the fileLines array
	 * 
	 * @return fileLines array
	 */
	public String[] getFileLines() {
		return fileLines;
	}

	/**
	 * Reads the file line by line and stores each in an index of the fileLines
	 * array
	 * 
	 * @throws IOException
	 *             throws exception if it cannot find the file
	 */
	public void readFromFile() throws IOException {

		FileReader fr = new FileReader(f);
		BufferedReader input = new BufferedReader(fr);

		for (int i = 0; i < fileLines.length; i++) {
			fileLines[i] = input.readLine();
		}
		input.close();
	}

	/**
	 * Method that counts the number of lines in the data source file. It
	 * utilizes a buffered binary read.
	 * 
	 * @return count the number of lines in file
	 * @throws IOException
	 *             if the file f cannot be found
	 */
	private int countLines() throws IOException {

		InputStream inputFile = new BufferedInputStream(new FileInputStream(f.getName()));

		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;

			// Loop until end of stream
			while ((readChars = inputFile.read(c)) != -1) {
				// Read characters until return sequence is read
				for (int i = 0; i < readChars; i++) {
					// Search for number of line returns "\n"
					if (c[i] == '\n') {
						// Increment count for every line return read
						count++;
					}
				}
			}
			return count;
		} finally {
			// Close out InputStream
			inputFile.close();
		}
	}	

}
