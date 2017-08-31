package scan;

import java.util.HashMap;
import java.util.Map;

import scaninterface.ScanInterface;
import stateenum.State;
import util.ScanUtil;
import word.Word;

public abstract class StateScan extends BaseScan implements ScanInterface {

	private static Map<State, StateScan> stateMap = new HashMap<State, StateScan>();
	private static State currentState = State.START;
	private static StringBuilder sb = new StringBuilder();
	private static ScanUtil scanUtil;

	public StateScan(String path) {
		super(path);
	}

	public static void addStateScan(State state, StateScan scan) {
		stateMap.put(state, scan);
	}

	protected void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}

	protected void putChar(char c) {
		sb.append(c);
	}

	protected void clearChars() {
		sb.setLength(0);
	}

	protected String getWordString() {
		return sb.toString();
	}

	public static StateScan getScan(State state) {
		return stateMap.get(state);
	}

	protected ScanUtil getScanUtil() {
		return scanUtil;
	}

	public static int getBuffLength() {
		return sb.length();
	}

	protected Word makeWord(String type, String value) {
		Word word = new Word();
		word.setType(type);
		word.setValue(value);
		return word;
	}

	@Override
	public Word getWord() {
		Word word = null;
		char c = getNextChar();
		if (c != 0) {
			word = scanWord(c);
		}
		else if (c == 0 && getBuffLength() != 0) {
			word = scanLastCharacter();
			clearChars();
		}
		else {
			setState(State.END);
		}
		return word;
	}

	private Word scanLastCharacter() {
		Word result = null;
		char c = getWordString().charAt(0);
		String type = null;
		if (ScanUtil.isLetter(c)) {
			type = "id";
		}
		else if (ScanUtil.isNum(c)) {
			type = "num";
		}
		else if (ScanUtil.isSymbol(c) != null) {
			type = ScanUtil.isSymbol(c);
		}
		if (!ScanUtil.isBlank(c)) {
			result = new Word();
			result.setType(type);
			result.setValue(c+"");
		}
		return result;
	}

	/**
	 * 子类覆盖该方法用于实现不同状态的解析
	 * 
	 * @return
	 */
	protected abstract Word scanWord(char c);
}
