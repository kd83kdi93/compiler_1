package test;

import java.util.List;

import org.junit.Test;

import scanmanager.ScanManager;
import scanmanagerinterface.ScanManagerInterface;
import word.Word;

public class ScanTestCase {



	@Test
	public void test() {
		ScanManagerInterface scan = new ScanManager("D:/personal_work/num.txt");
		List<Word> list = scan.getWords();
		for (Word w : list) {
			System.out.println(w);
		}
	}
	
	@Test
	public void test1() {
		ScanManagerInterface scan = new ScanManager("D:/personal_work/compiler_1/src/word/Word.java");
		List<Word> list = scan.getWords();
		for (Word w : list) {
			System.out.println(w);
		}
	}
	
}
