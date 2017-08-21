package scaninterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Scan {
	private File file;
	private FileReader fileReader;
	private char[] buf = null;
	private final int BUFSIZE = 1;
	private int bufIndex = -1;
	private int currentBufSize = -1;

	public Scan(String path) {
		checkFileExist(path);
	}

	private void checkFileExist(String path) {
		try {
			file = new File(path);
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public char getNextChar() {
		char result = 0;
		if (bufIndex >= currentBufSize) {
			currentBufSize = readInBuff();
			if (currentBufSize != -1) {
				bufIndex = 0;
			}
		}
		if (currentBufSize > 0) {
			result = buf[bufIndex++];
		}
		return result;
	}

	private int readInBuff() {
		int result = -1;
		if (buf == null) {
			buf = new char[BUFSIZE];
		}
		try {
			result = fileReader.read(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
