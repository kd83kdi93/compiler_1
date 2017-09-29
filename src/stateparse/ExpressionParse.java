package stateparse;

import parse.ParseBase;
import returnvalue.ReturnValue;
import util.ParseUtil;
import word.Word;

public class ExpressionParse extends ParseBase{

	@Override
	protected ReturnValue parse() {
		return expression();
	}

	private ReturnValue expression() {
		ReturnValue result = term();
		result = expression1(result);
		return result;
	}

	private ReturnValue expression1(ReturnValue aTmp) {
		ReturnValue a = aTmp;
		ReturnValue b = null;
		ReturnValue head = null;
		Word op = currentWord;
		if (op != null) {
			while (ParseUtil.isPlus(op) || ParseUtil.isSub(op)) {
				matchCalculatorSymbol(op);
				b = term();
				head = new ReturnValue(op.getValue(), String.class, op.getType());
				head.setLeft(a);
				head.setRight(b);
				a = head;
				op = currentWord;
				if (op == null) {
					break;
				}
			}
		}
		return a;
	}

	private ReturnValue term() {
		ReturnValue result = factor();
		result = term1(result);
		return result;
	}

	private ReturnValue term1(ReturnValue aTmp) {
		ReturnValue a = aTmp;
		ReturnValue b = null;
		ReturnValue head = null;
		Word op = currentWord;
		if (op != null) {
			while (ParseUtil.isMul(op) || ParseUtil.isDiv(op)) {
				matchCalculatorSymbol(op);
				b = factor();
				head = new ReturnValue(op.getValue(), String.class, op.getType());
				head.setLeft(a);
				head.setRight(b);
				a = head;
				op = currentWord;
				if (op == null) {
					break;
				}
			}
		}
		return a;
	}

	private ReturnValue factor() {
		ReturnValue result = null;
		if ("num".equals(currentWord.getType())) {
			double factorValue = Double.parseDouble(currentWord.getValue());
			result = new ReturnValue(factorValue, Double.class, "num");
			matchByType("num");
		}
		else if ("(".equals(currentWord.getType())) {
			matchByValue("(");
			result = expression();
			matchByValue(")");
		}
		else {
			printErr("factor 不能识别的单词 " + currentWord);
		}
		return result;
	}
	
	
	private void matchCalculatorSymbol(Word op) {
		if (ParseUtil.isMul(op)) {
			matchByValue("*");
		} else if (ParseUtil.isDiv(op)) {
			matchByValue("/");
		} else if (ParseUtil.isPlus(op)) {
			matchByValue("+");
		} else if (ParseUtil.isSub(op)) {
			matchByValue("-");
		}
	}
}
