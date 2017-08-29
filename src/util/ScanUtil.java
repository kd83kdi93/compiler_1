package util;

import java.util.HashMap;
import java.util.Map;

public class ScanUtil {
	private static Map<Character, String> symbolMap = new HashMap<Character, String>();
	static {
		initSymbol();
	}

	private static void initSymbol() {
		symbolMap.put('+', "+");
		symbolMap.put('-', "-");
		symbolMap.put('*', "*");
		symbolMap.put('/', "/");
		symbolMap.put('(', "(");
		symbolMap.put(')', ")");
		symbolMap.put('[', "[");
		symbolMap.put(']', "]");
		symbolMap.put('{', "{");
		symbolMap.put('}', "}");
		symbolMap.put('!', "!");
		symbolMap.put(',', ",");
		symbolMap.put('\\', "\\");
		symbolMap.put('"', "\"");
		symbolMap.put('.', ".");
		symbolMap.put(';', ";");
		symbolMap.put('=', "=");
		symbolMap.put('@', "@");
		symbolMap.put(':', ":");
		symbolMap.put('_', "_");
		symbolMap.put('<', "<");
		symbolMap.put('>', ">");
	}

	public static boolean isNum(char c) {
		return Character.isDigit(c);
	}

	public static boolean isLetter(char c) {
		return Character.isLetter(c);
	}

	public static boolean isBlank(char c) {
		boolean result = false;
		if (c == ' ' || c == '\r' || c == '\n' || c == '\t') result = true;
		return result;
	}

	public static String isSymbol(char c) {
		String result = null;
		result = symbolMap.get(c);
		return result;
	}
}
