package statescan;

import exception.ScanException;
import scan.StateScan;
import stateenum.State;
import word.Word;

public class StartState extends StateScan {

	public StartState(String path) {
		super(path);
	}

	@Override
	protected Word scanWord(char c) {
		if (getScanUtil().isNum(c)) {
			setState(State.NUM);
			putChar(c);
		} else if (getScanUtil().isLetter(c)) {
			setState(State.IDENTIFY);
			putChar(c);
		} else if (getScanUtil().isSymbol(c) != null) {
			setState(State.SYMBOL);
			putBackChar();
		}
		else if (getScanUtil().isBlank(c)) {
			c = getNextChar();
			while (getScanUtil().isBlank(c)) {
				c = getNextChar();
			}
			putBackChar();
		} else {
			try {
				printErr("无法处理的字符   "+c);
			} catch (ScanException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return null;
	}

}
