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
		while (getScanUtil().isNum(tmp)) {
			putChar(tmp);
			tmp = getNextChar();
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

}
