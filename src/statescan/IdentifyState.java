package statescan;

import scan.StateScan;
import stateenum.State;
import word.Word;

public class IdentifyState extends StateScan {

	public IdentifyState(String path) {
		super(path);
	}

	@Override
	protected Word scanWord(char c) {
		Word word = null;
		char tmp = c;
		while (getScanUtil().isLetter(tmp) || getScanUtil().isNum(tmp)) {
			putChar(tmp);
			tmp = getNextChar();
		}
		putBackChar();
		String wordValue = getWordString();
		word = makeWord("id", wordValue);
		setState(State.START);
		clearChars();
		return word;
	}

}
