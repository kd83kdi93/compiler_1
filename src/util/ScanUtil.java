package util;

public class ScanUtil {
	public static boolean isNum(char c) {
		return Character.isDigit(c);
	}

	public static boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	public static boolean isBlank(char c) {
		boolean result = false;
		if (c == ' ' || c == '\r' || c == '\n' || c == '\t')
			result = true;
		return result;
	}
}
