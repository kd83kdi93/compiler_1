package statescan;

import scan.StateScan;
import stateenum.State;
import word.Word;
import exception.ScanException;

public class NumState extends StateScan {

	public NumState(String path) {
		super(path);
	}

	@Override
	protected Word scanWord(char c) {
		Word word = null;
		char tmp = c;
		if (getScanUtil().isNum(tmp)) {
			while (getScanUtil().isNum(tmp)) {
				putChar(tmp);
				tmp = getNextChar();
				if (checkDalAndAfterNums(tmp)){
					break;
				}
			}
		} else if (getScanUtil().isDal(tmp)) {
			checkDalAndAfterNums(tmp);
		}
		if (getScanUtil().isLetter(tmp)) {
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

	public boolean checkDalAndAfterNums(char tmp) {
		boolean result = false;
		if (getScanUtil().isDal(tmp)) {
			putChar(tmp);
			tmp = getNextChar();
			while (getScanUtil().isNum(tmp)) {
				putChar(tmp);
				tmp = getNextChar();
			}
			result = true;
		}
		return result;
	}

}
