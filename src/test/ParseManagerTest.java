package test;

import java.util.List;

import org.junit.Test;

import parse.ParseBase;
import returnvalue.ReturnValue;
import scanmanager.ScanManager;
import scanmanagerinterface.ScanManagerInterface;
import stateparse.ExpressionParse;
import stateparse.IfParse;
import stateparse.SentenceParse;
import stateparse.StatementParse;
import symboltable.SymbolTable;
import word.Word;

public class ParseManagerTest {

	@Test
	public void expressionTest() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/123.txt");
		List<Word> list = scanManagerInterface.getWords();
		for (Word w : list) {
			System.out.println(w);
		}
		ParseBase parseBase = new ExpressionParse();
		parseBase.setScanManager(scanManagerInterface);
		ReturnValue tree = parseBase.getParseTree();
		tree.print(tree);
		System.out.println("Total: "+tree.calculator(tree));
	}
	
	
	@Test
	public void ifTest() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/num.txt");
		List<Word> list = scanManagerInterface.getWords();
//		for (Word w : list) {
//			System.out.println(w);
//		}
		ParseBase parseBase = new IfParse();
		parseBase.setScanManager(scanManagerInterface);
		ReturnValue tree = parseBase.getParseTree();
//		tree.print(tree);
		tree.executeIf(tree);
	}
	
	
	@Test
	public void sentenseTest() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/num.txt");
		List<Word> list = scanManagerInterface.getWords();
//		for (Word w : list) {
//			System.out.println(w);
//		}
		ParseBase parseBase = new SentenceParse();
		parseBase.setScanManager(scanManagerInterface);
		ReturnValue tree = parseBase.getParseTree();
//		tree.print(tree);
		tree.executeSentense(tree);
		SymbolTable.getInstances().print();
	}
	
	
	@Test
	public void statementTest() {
		ScanManagerInterface scanManagerInterface = new ScanManager("D:/personal_work/num.txt");
		List<Word> list = scanManagerInterface.getWords();
//		for (Word w : list) {
//			System.out.println(w);
//		}
		ParseBase parseBase = new StatementParse();
		parseBase.setScanManager(scanManagerInterface);
		ReturnValue tree = parseBase.getParseTree();
//		tree.print(tree);
		tree.executeStatement(tree);
		SymbolTable.getInstances().print();
	}
}
