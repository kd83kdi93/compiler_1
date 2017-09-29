package symboltable;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class SymbolTable {
	private SymbolTable(){};
	private static SymbolTable symbolTable = new SymbolTable();
	private static Map<String,Object> symbolMap = new HashMap<String, Object>();
	public static SymbolTable getInstances() {
		return symbolTable;
	}
	public static void putValue(String name, Object value) {
		symbolMap.put(name, value);
	}
	
	public static Object getValue(String name) {
		return symbolMap.get(name);
	}
	
	public void print() {
		Iterator<Entry<String, Object>> entryIterator = symbolMap.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Entry<String, Object> entry = entryIterator.next();
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}
