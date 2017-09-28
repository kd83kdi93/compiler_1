package parsemanager;

import java.util.List;

import returnvalue.ReturnValue;
import returnvalue.ReturnValue;
import scanmanagerinterface.ScanManagerInterface;
import util.ParseUtil;
import word.Word;
import exception.ParseException;

public class ParseManager {
	private List<Word> words;
	private int wordIndex;
	private Word currentWord = null;

	public ParseManager(ScanManagerInterface scan) {
		words = scan.getWords();
	}

	public ReturnValue parse() {
		return expression();
	}

	private ReturnValue expression() {
		ReturnValue result = term();
		result = expression1(result);
		return result;
	}

	private ReturnValue expression1(ReturnValue aTmp) {
		ReturnValue a = aTmp;
		ReturnValue b = null;
		ReturnValue head = null;
		Word op = getNextWord();
		if (op != null) {
			while (ParseUtil.isPlus(op) || ParseUtil.isSub(op)) {
				b = term();
				head = new ReturnValue(op.getValue(), String.class, op.getType());
				head.setLeft(a);
				head.setRight(b);
				a = head;
				op = getNextWord();
				if (op == null) {
					break;
				}
			}
			if ((op!=null)&&(ParseUtil.isRp(op))) {
				putBackWord();
			}
		}
		return a;
	}

	private ReturnValue term() {
		ReturnValue result = factor();
		result = term1(result);
		return result;
	}

	private ReturnValue term1(ReturnValue aTmp) {
		ReturnValue a = aTmp;
		ReturnValue b = null;
		ReturnValue head = null;
		Word op = getNextWord();
		if (op != null) {
			while (ParseUtil.isMul(op) || ParseUtil.isDiv(op)) {
				b = factor();
				head = new ReturnValue(op.getValue(), String.class, op.getType());
				head.setLeft(a);
				head.setRight(b);
				a = head;
				op = getNextWord();
				if (op == null) {
					break;
				}
			}
			if ((op!=null)&&(ParseUtil.isPlus(op) || ParseUtil.isSub(op) || ParseUtil.isRp(op))) {
				putBackWord();
			}
		}
		return a;
	}

	private ReturnValue factor() {
		ReturnValue result = null;
		Word currentWord = getNextWord();
		if ("num".equals(currentWord.getType())) {
			double factorValue = Double.parseDouble(currentWord.getValue());
			result = new ReturnValue(factorValue, Double.class, "num");
		}
		else if ("(".equals(currentWord.getType())) {
			result = expression();
			currentWord = getNextWord();
			if (!match(currentWord,")")) {
				printErr("不能识别的单词:" + currentWord + "期待的是)");
			}
		}
		else {
			printErr("factor 不能识别的单词 " + currentWord);
		}
		return result;
	}

	public Word getNextWord() {
		Word result = null;
		if (wordIndex + 1 <= words.size()) {
			result = words.get(wordIndex++);
		}
		return result;
	}

	public void putBackWord() {
		if (wordIndex - 1 >= 0) {
			wordIndex--;
		}
	}

	private boolean match(Word matchWord,String matchType) {
		if (matchWord.getValue().equals(matchType)) {
			return true;
		}
		printErr("不能匹配 "+matchWord.getValue()+" 对应 "+matchType);
		return false;
	}

	private void printErr(String msg) {
		try {
			throw new ParseException(msg);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
