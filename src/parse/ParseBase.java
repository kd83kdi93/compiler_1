package parse;

import java.util.List;

import parseinterface.ParseInterface;
import returnvalue.ReturnValue;
import scanmanagerinterface.ScanManagerInterface;
import word.Word;
import exception.ParseException;

public abstract class ParseBase implements ParseInterface{
	protected static List<Word> words;
	protected static int wordIndex;
	protected static Word currentWord;
	
	public static void setScanManager(ScanManagerInterface scan) {
		words = scan.getWords();
		currentWord = getNextWord();
	}
	
	protected static Word getNextWord() {
		Word result = null;
		if (wordIndex + 1 <= words.size()) {
			result = words.get(wordIndex++);
		}
		return result;
	}

	protected static void putBackWord() {
		if (wordIndex - 1 >= 0) {
			wordIndex--;
		}
	}

	protected static boolean matchByValue(String value) {
		if (currentWord.getValue().equals(value)) {
			currentWord = getNextWord();
			return true;
		}
		printErr("不能匹配 "+currentWord.getValue()+" 对应 "+value);
		return false;
	}
	
	
	protected static boolean matchByType(String type) {
		if (currentWord.getType().equals(type)) {
			currentWord = getNextWord();
			return true;
		}
		printErr("不能匹配 "+currentWord.getValue()+" 对应 "+type);
		return false;
	}

	protected static void printErr(String msg) {
		try {
			throw new ParseException(msg);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/**
	 * 子类需要重写该方法来解析语法
	 * @return
	 */
	protected abstract ReturnValue parse(); 
	
	@Override
	public ReturnValue getParseTree() {
		return parse();
	}
}
