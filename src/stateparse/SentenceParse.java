package stateparse;

import parse.ParseBase;
import parseinterface.ParseInterface;
import parsemanager.ParseManager;
import returnvalue.ReturnValue;

public class SentenceParse extends ParseBase {
	

	@Override
	protected ReturnValue parse() {
		return sentense();
	}

	private ReturnValue sentense() {
		ParseInterface expresseionParse = ParseManager.getParse(ExpressionParse.class);
		ParseInterface ifParse = ParseManager.getParse(IfParse.class);
		ReturnValue head = null;
		ReturnValue left = null;
		ReturnValue right = null;
		do {
			if (currentWord == null) {
				break;
			} else if (!(currentWord.getType().equals("id") || currentWord.getValue().equals("if"))) {
//				putBackWord();
				break;
			}
			if (currentWord.getValue().equals("if")) {
				head = ifParse.getParseTree();
			}
			else if (currentWord.getType().equals("id")) {
				left = new ReturnValue(currentWord.getValue(), String.class, currentWord.getType());
				matchByType("id");
				matchByValue("=");
				head = new ReturnValue("=", String.class, "=");
				right = expresseionParse.getParseTree();
				head.setLeft(left);
				head.setRight(right);
			}
			matchByValue(";");
		} while (false);
		return head;
	}

}
