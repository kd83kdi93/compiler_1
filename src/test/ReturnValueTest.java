package test;

import org.junit.Test;

import returnvalue.ReturnValue;

public class ReturnValueTest {

	@Test
	public void test() {
		ReturnValue result = new ReturnValue(Double.class,11d);
		System.out.println(result.getValue());
	}

}
