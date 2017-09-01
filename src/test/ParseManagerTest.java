package test;

import java.util.List;

import org.junit.Test;

import parsemanager.ParseManager;
import returnvalue.ReturnValue;
import scanmanager.ScanManager;
import scanmanagerinterface.ScanManagerInterface;
import word.Word;

public class ParseManagerTest {

	@Test
	public void test() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/cal.txt");
		List<Word> list = scanManagerInterface.getWords();
		for (Word w : list) {
			System.out.println(w);
		}
		ParseManager parseManager = new ParseManager(scanManagerInterface);
		ReturnValue result = parseManager.parse();
		System.out.println(result.getValue());
	}
	
	
	@Test
	public void test1() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/cal.txt");
		ParseManager parseManager = new ParseManager(scanManagerInterface);
		Word word = parseManager.getNextWord();
		while (word != null) {
			System.out.println(word);
			word = parseManager.getNextWord();
		}
		parseManager.putBackWord();
		parseManager.putBackWord();
		parseManager.putBackWord();
		word = parseManager.getNextWord();
		System.out.println(word);
	}

}
