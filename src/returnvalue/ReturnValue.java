package returnvalue;


public class ReturnValue<T> {
	private Object value;
	private Class<T> classType;
	public ReturnValue(Class<T> classType, Object value) {
		this.value = value;
		this.classType = classType;
	}
	public T getValue() {
		return classType.cast(value);
	}
	
}
