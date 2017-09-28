package util;

import word.Word;

public class ParseUtil {
	public static boolean isPlus(Word word) {
		return "+".equals(word.getValue());
	}
	
	public static boolean isSub(Word word) {
		return "-".equals(word.getValue());
	}
	
	public static boolean isMul(Word word) {
		return "*".equals(word.getValue());
	}
	
	public static boolean isDiv(Word word) {
		return "/".equals(word.getValue());
	}

	public static boolean isRp(Word word) {
		return ")".equals(word.getValue());
	}
}
