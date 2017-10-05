package returnvalue;

import symboltable.SymbolTable;

public class ReturnValue {
	private Object value;
	private Class classType;
	private String type;
	private ReturnValue left = null;
	private ReturnValue right = null;
	private ReturnValue nextSentence = null;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Class getClassType() {
		return classType;
	}

	public void setClassType(Class classType) {
		this.classType = classType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ReturnValue getLeft() {
		return left;
	}

	public void setLeft(ReturnValue left) {
		this.left = left;
	}

	public ReturnValue getRight() {
		return right;
	}

	public void setRight(ReturnValue right) {
		this.right = right;
	}

	public ReturnValue getNextSentence() {
		return nextSentence;
	}

	public void setNextSentence(ReturnValue nextSentence) {
		this.nextSentence = nextSentence;
	}

	public ReturnValue(Object value, Class classType, String type) {
		this.value = value;
		this.classType = classType;
		this.type = type;
	}

	private static Double execute(ReturnValue head, Double a, Double b) {
		Double result = null;
		if (head.getValue().equals("+")) {
			result = a + b;
		}
		else if (head.getValue().equals("-")) {
			result = a - b;
		}
		else if (head.getValue().equals("*")) {
			result = a * b;
		}
		else if (head.getValue().equals("/")) {
			result = a / b;
		}
		else {
			result = (Double) head.getValue();
		}
		return result;
	}

	public static void print(ReturnValue head) {
		if (head == null) { return; }
		print(head.getLeft());
		System.out.println(head.value);
		print(head.getRight());
	}

	public static void executeIf(ReturnValue head) {
		if (head.getType().equals("if")) {
			double result = calculator(head.getClass().cast(head.getValue()));
			if (result < 5) {
				executeStatement(head.getLeft());
			}
			else {
				executeStatement(head.getRight());
			}
		}
	}

	public static Double calculator(ReturnValue head) {
		if (head == null) { return null; }
		Double a = calculator(head.getLeft());
		Double b = calculator(head.getRight());
		return execute(head, a, b);

	}

	public static void executeSentense(ReturnValue head) {
		if (head.getType().equals("=")) {
			String name = (String) head.getLeft().getValue();
			double result = head.getRight().calculator(head.getRight());
			SymbolTable.putValue(name, result);
		}
	}

	public static void executeStatement(ReturnValue head) {
		ReturnValue tmpHead = head;
		while (tmpHead != null) {
			if (tmpHead.getType().equals("=")) {
				executeSentense(tmpHead);
			} else if (tmpHead.getType().equals("if")) {
				executeIf(tmpHead);
			}
			tmpHead = tmpHead.getNextSentence();
		}
		
	}
}
