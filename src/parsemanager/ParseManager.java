package parsemanager;

import java.util.List;

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
		if (result != null) {
			result = expression1(result);
		}
		else {
			printErr("expression 无法解析的单词 " + result);
		}
		return result;
	}

	private ReturnValue expression1(ReturnValue a) {
		Word op = getNextWord();
		boolean errorFlag = true;
		ReturnValue b = null;
		ReturnValue result = a;
		double total = (double) a.getValue();
		if (op != null) {
			
			while (ParseUtil.isPlus(op) || ParseUtil.isSub(op)) {
				errorFlag = false;
				b = term();
				if (ParseUtil.isPlus(op)) {
					total += (double) b.getValue();
				}
				else {
					total -= (double) b.getValue();
				}
				op = getNextWord();
				if (op == null) {
					break;
				}
			}
			if (errorFlag) {
				try {
					throw new ParseException("不能识别的单词 " + currentWord);
				} catch (ParseException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			result = new ReturnValue(Double.class, total);
		}
		return result;
	}

	private ReturnValue term() {
		ReturnValue result = factor();
		if (result != null) {
			result = term1(result);
		} else {
			printErr("term 无法识别的单词" + result);
		}
		return result;
	}

	private ReturnValue term1(ReturnValue a) {
		boolean errorFlag = true;
		double total = (double) a.getValue();
		ReturnValue result = a;
		ReturnValue b = null;
		Word op = getNextWord();
		if (op != null) {
			while (ParseUtil.isMul(op) || ParseUtil.isDiv(op)) {
				errorFlag = false;
				b = factor();
				if (b != null) {
					if (ParseUtil.isMul(op)) {
						total *= (double) b.getValue();
					} else {
						total /= (double) b.getValue();
					}
					op = getNextWord();
					if (op == null) {
						break;
					} else if (ParseUtil.isPlus(op) || ParseUtil.isSub(op)) {
						putBackWord();
					}
				} else {
					printErr("term1 不能识别的单词 " + b);
				}
			}
			if (errorFlag) {
				if (!(ParseUtil.isPlus(op) || ParseUtil.isSub(op))) {
					printErr("term1 不能识别的单词 " + op);
				}
				putBackWord();
			}
			result = new ReturnValue(Double.class, total);
		}
		return result;
	}

	private ReturnValue factor() {
		ReturnValue result = null;
		currentWord = getNextWord();
		if ("num".equals(currentWord.getType())) {
			double factorValue = Double.parseDouble(currentWord.getValue());
			result = new ReturnValue(Double.class, factorValue);
		} else {
			printErr("factor 不能识别的单词 "+currentWord);
		}
		// 先不考虑括号
//		else if ("(".equals(currentWord.getType())) {
//			currentWord = getNextWord();
//			result = expression();
//			if (!match(")")) {
//				try {
//					throw new ParseException("不能识别的单词 " + currentWord);
//				} catch (ParseException e) {
//					e.printStackTrace();
//					System.exit(0);
//				}
//			}
//		}
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

	private boolean match(String matchWord) {
		if (currentWord.getValue().equals(matchWord)) {
			currentWord = getNextWord();
			return true;
		}
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
