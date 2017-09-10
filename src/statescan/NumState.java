package statescan;

import scan.StateScan;
import stateenum.State;
import word.Word;
import exception.ScanException;

public class NumState extends StateScan {

	char tmp = 0;
	
	public NumState(String path) {
		super(path);
	}

	@Override
	protected Word scanWord(char c) {
		Word word = null;
		tmp = c;
		if (getScanUtil().isNum(tmp)) {
			while (getScanUtil().isNum(tmp)) {
				putChar(tmp);
				tmp = getNextChar();
				if (getDalResult(tmp)) {
					break;
				}
			}
		} else if (getScanUtil().isDal(tmp)) {
			getDalResult(tmp);
		}
		if (getScanUtil().isLetter(tmp) || getScanUtil().isDal(tmp)) {
			try {
				throw new ScanException("不能识别的标识符" + getWordString() + tmp);
			} catch (ScanException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		putBackChar();
		String wordValue = getWordString();
		word = makeWord("num", wordValue);
		setState(State.START);
		clearChars();
		return word;
	}

	private char checkDalAndAfterNums(char tmp) {
		char result = 0x01;
		if (getScanUtil().isDal(tmp)) {
			putChar(tmp);
			tmp = getNextChar();
			while (getScanUtil().isNum(tmp)) {
				putChar(tmp);
				tmp = getNextChar();
			}
			result = tmp;
		}
		return result;
	}
	
	private boolean getDalResult(char c) {
		boolean result = false;
		char tmp_c = checkDalAndAfterNums(c);
		if (tmp_c != 0x01){
			tmp = tmp_c;
			result = true;
		}
		return result;
	}

}
