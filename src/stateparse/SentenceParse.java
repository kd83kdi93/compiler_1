package stateparse;

import parse.ParseBase;
import returnvalue.ReturnValue;

public class SentenceParse extends ParseBase {
	ExpressionParse expressionParse = new ExpressionParse();
	IfParse ifParse = new IfParse();

	@Override
	protected ReturnValue parse() {
		return sentense();
	}

	private ReturnValue sentense() {
		ReturnValue head = null;
		ReturnValue left = null;
		ReturnValue right = null;
		do {
			if (currentWord == null) {
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
				right = expressionParse.getParseTree();
				head.setLeft(left);
				head.setRight(right);
			}
			matchByValue(";");
		} while (false);
		return head;
	}

}
