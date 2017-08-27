package statescan;

import scan.StateScan;
import stateenum.State;
import word.Word;

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
		putBackChar();
		String wordValue = getWordString();
		word = makeWord("num",wordValue);
		setState(State.START);
		clearChars();
		return word;
	}
	
	

}
