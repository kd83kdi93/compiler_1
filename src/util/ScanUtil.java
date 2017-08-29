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

	public static String isSymbol(char c) {
		String result = null;
		switch (c) {
		case '+':
			result = "+";
			break;
		case '-':
			result = "-";
			break;
		case '*':
			result = "*";
			break;
		case '/':
			result = "/";
			break;
		case '(':
			result = "(";
			break;
		case ')':
			result = ")";
			break;
		case '"':
			result = "\"";
			break;
		case ';':
			result = ";";
			break;
		case '{':
			result = "{";
			break;
		case '}':
			result = "}";
			break;
		case '.':
			result = ".";
			break;
		case '@':
			result = "@";
			break;
		case '=':
			result = "=";
			break;
		case ':':
			result = ":";
			break;
		case '_':
			result = "_";
			break;
		case '<':
			result = "<";
			break;
		case '>':
			result = ">";
			break;
		case '[':
			result = "[";
			break;
		case ']':
			result = "]";
			break;
		}
		return result;
	}
}
