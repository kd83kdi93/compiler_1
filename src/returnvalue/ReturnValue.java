package returnvalue;

import util.ParseUtil;

public class ReturnValue {
	private Object value;
	private Class classType;
	private String type;
	private ReturnValue left = null;
	private ReturnValue right = null;
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
	public ReturnValue(Object value, Class classType, String type) {
		this.value = value;
		this.classType = classType;
		this.type = type;
	}
	
	public static void  print(ReturnValue head) {
		if (head == null) {
			return;
		}
		print(head.getLeft());
		System.out.println(head.value);
		print(head.getRight());
	}
	
	public static Double calculator(ReturnValue head) {
		if (head == null) {
			return null;
		}
		Double a = calculator(head.getLeft());
		Double b = calculator(head.getRight());
		return execute(head , a , b);
		
	}
	
	private static Double execute(ReturnValue head, Double a, Double b) {
		Double result = null;
		if (head.getValue().equals("+")) {
			result = a + b;
		} else if (head.getValue().equals("-")) {
			result = a - b;
		} else if (head.getValue().equals("*")) {
			result = a * b;
		} else if (head.getValue().equals("/")) {
			result = a / b;
		} else {
			result = (Double)head.getValue();
		}
		return result;
	}
}
