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
		} else if (getScanUtil().isLetter(c)) {
			setState(State.IDENTIFY);
		} else {
			try {
				printErr("½âÎö´íÎó    "+c);
			} catch (ScanException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		putChar(c);
		return null;
	}

}
