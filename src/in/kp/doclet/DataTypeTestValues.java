package in.kp.doclet;

import java.io.StringWriter;

public class DataTypeTestValues {
	public static String generateCode(final String className) {
		StringWriter sw = new StringWriter();
		if(className != null) {
			if(TestableType.STRING_TYPE.equals(className)) {
				sw.append(codeForString());
			} else if(TestableType.LONG_TYPE.equals(className)) {
				sw.append(codeForLong());
			} else if(TestableType.LIST_TYPE.equals(className)) {
				sw.append(codeForList());
			} else if(TestableType.ARRAYLIST_TYPE.equals(className)) {
				sw.append(codeForArrayList());
			}
		}
		return sw.toString();
	}

	private static String codeForString() {
		return "String[] tests = {new String(), null};";
	}
	private static String codeForLong() {
		return "Long[] tests = {new Long(), null};";
	}
	private static String codeForList() {
		return "List[] tests = {new ArrayList(), null};";
	}
	private static String codeForArrayList() {
		return "ArrayList[] tests = {new ArrayList(), null};";
	}
}
