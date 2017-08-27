package scan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.ScanException;

public abstract class BaseScan {
	private static File file;
	private static FileReader fileReader;
	private static char[] buf = null;
	private static final int BUFSIZE = 1;
	private static int bufIndex = -1;
	private static int currentBufSize = -1;


	public BaseScan(String path) {
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

	protected char getNextChar() {
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
	
	protected void putBackChar() {
		if (bufIndex-1 >= 0)
			bufIndex--;
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

	protected void printErr(String msg) throws ScanException{
		throw new ScanException(msg);
	}

}
