package stateparse;

import parse.ParseBase;
import returnvalue.ReturnValue;
import word.Word;

public class IfParse extends ParseBase{
	ParseBase expresseionParse = new ExpressionParse();
	
	@Override
	protected ReturnValue parse() {
		return ifExpression();
	}

	private ReturnValue ifExpression() {
		ReturnValue head = null;
		ReturnValue left = null;
		ReturnValue right = null;
		if (currentWord.getValue().equals("if")) {
			matchByValue("if");
			matchByValue("(");
			head = expresseionParse.getParseTree();
			matchByValue(")");
			head = new ReturnValue(head, ReturnValue.class, "if");
			matchByValue("{");
			left = expresseionParse.getParseTree();
			matchByValue("}");
			head.setLeft(left);
			if (currentWord.getValue().equals("else")) {
				matchByValue("else");
				matchByValue("{");
				right = expresseionParse.getParseTree();
				matchByValue("}");
				head.setRight(right);
			}
		} else {
			printErr("不能识别的单词 "+currentWord+"期待 if");
		}
		return head;
	}
}
