package statescan;

import exception.ScanException;
import scan.StateScan;
import stateenum.State;
import word.Word;

public class SymbolState extends StateScan {

	public SymbolState(String path) {
		super(path);
	}

	@Override
	protected Word scanWord(char c) {
		Word word = null;
		String type = null;
		type = getScanUtil().isSymbol(c);
		if (type != null) {
			word = makeWord(type, c+"");
			setState(State.START);
			clearChars();
		} else {
			try {
				throw new ScanException("不能识别的标识符 "+c);
			} catch (ScanException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		return word;
	}

}
