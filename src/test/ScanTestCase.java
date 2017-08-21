package test;

import org.junit.Test;

import scaninterface.Scan;

public class ScanTestCase {

	@Test
	public void test() {
		Scan scan = new Scan("e:/c.txt");
		char c = scan.getNextChar();
		while (c != 0) {
			System.out.print(c);
			c = scan.getNextChar();
		}
	}

}
