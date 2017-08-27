package test;

import java.util.List;

import org.junit.Test;

import scanmanager.ScanManager;
import scanmanagerinterface.ScanManagerInterface;
import word.Word;

public class ScanTestCase {



	@Test
	public void test() {
		ScanManagerInterface scan = new ScanManager("e:/d.txt");
		List<Word> list = scan.getWords();
		for (Word w : list) {
			System.out.println(w);
		}
	}
	
}
