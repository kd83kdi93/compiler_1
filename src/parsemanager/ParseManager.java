package parsemanager;

import java.util.HashMap;
import java.util.Map;

import parse.ParseBase;
import parseinterface.ParseInterface;
import scaninterface.ScanInterface;
import scanmanagerinterface.ScanManagerInterface;
import stateparse.ExpressionParse;
import stateparse.IfParse;
import stateparse.SentenceParse;
import stateparse.StatementParse;

public class ParseManager{
	private ParseManager(){};
	private static Map<Class,ParseInterface> parses = new HashMap<Class,ParseInterface>();
	static {
		initMap();
	}
	private static void initMap() {
		parses.put(StatementParse.class, new StatementParse());
		parses.put(IfParse.class, new IfParse());
		parses.put(SentenceParse.class, new SentenceParse());
		parses.put(ExpressionParse.class, new ExpressionParse());
	}
	public static ParseInterface getParse(Class classType) {
		return parses.get(classType);
	}
	public static void setScanInterface(ScanManagerInterface scanManagerInterface) {
		ParseBase.setScanManager(scanManagerInterface);
	}
}
