package stateparse;

import parse.ParseBase;
import parseinterface.ParseInterface;
import parsemanager.ParseManager;
import returnvalue.ReturnValue;
import word.Word;

public class IfParse extends ParseBase{
	
	@Override
	protected ReturnValue parse() {
		return ifExpression();
	}

	private ReturnValue ifExpression() {
		ParseInterface expresseionParse = ParseManager.getParse(ExpressionParse.class);
		ParseInterface statementParse = ParseManager.getParse(StatementParse.class);
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
			left = statementParse.getParseTree();
			matchByValue("}");
			head.setLeft(left);
			if (currentWord.getValue().equals("else")) {
				matchByValue("else");
				matchByValue("{");
				right = statementParse.getParseTree();
				matchByValue("}");
				head.setRight(right);
			}
		} else {
			printErr("不能识别的单词"+currentWord+"期待 if");
		}
		return head;
	}
}
