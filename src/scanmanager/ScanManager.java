package scanmanager;

import java.util.ArrayList;
import java.util.List;

import exception.ScanException;
import scan.StateScan;
import scanmanagerinterface.ScanManagerInterface;
import stateenum.State;
import statescan.IdentifyState;
import statescan.NumState;
import statescan.StartState;
import word.Word;

public class ScanManager implements ScanManagerInterface{
	private List<Word> words;
	
	public ScanManager(String path) {
		words = new ArrayList<Word>();
		initStateScan(path);
	}

	private void initStateScan(String path) {
		StateScan.addStateScan(State.START, new StartState(path));
		StateScan.addStateScan(State.NUM, new NumState(path));
		StateScan.addStateScan(State.IDENTIFY, new IdentifyState(path));
	}

	
	
	@Override
	public List<Word> getWords() {
		StateScan scan = null;
		Word word = null;
		while (StateScan.getState() != State.END) {
			State state = StateScan.getState();
			scan = StateScan.getScan(state);
			if (scan != null) {
				word = scan.getWord();
				if (word != null) {
					words.add(word);
					System.out.println(word);
				}
			} else {
				try {
					throw new ScanException("������������   "+StateScan.getState());
				} catch (ScanException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			
		}
		return words;
	}
}