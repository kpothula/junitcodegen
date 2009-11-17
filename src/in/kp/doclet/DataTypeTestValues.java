package in.kp.doclet;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.sun.javadoc.Type;

public class DataTypeTestValues {

	public static String generateCode(Type classType) {
		StringWriter sw = new StringWriter();
		String className = classType.qualifiedTypeName();
		if (className != null) {
			String testData = dataTypeTestValues.get(className);
			if (testData != null) {
				sw.append(testData);
			} else {
				if(hasDefaultConstructor(className)) {
					String clazz = classType.simpleTypeName();
					sw.append(clazz + "[] tests = {new " + clazz + "(), null};");
				}
			}
		}
		return sw.toString();
	}

	private static boolean hasDefaultConstructor(String className) {
		Class clazz;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("CLASS NOT FOUND:" + className);
			return false;
		}
		if(clazz.isAnnotation() || clazz.isInterface() || clazz.isPrimitive() || clazz.isArray())
			return false;
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for (int i = 0; i < constructors.length; i++) {
			if (constructors[i].getParameterTypes().length == 0)
				return true;
		}
		return false;
	}

	private static Map<String, String> dataTypeTestValues = new HashMap<String, String>();

	static {
		dataTypeTestValues.put("int", "int[] tests = {0,-1,1};");
		dataTypeTestValues.put("long", "long[] tests = {0,-1,1};");
		dataTypeTestValues.put("char", "char[] tests = {'a','b',0};");

		dataTypeTestValues.put("java.lang.String", "String[] tests = {new String(), null};");
		dataTypeTestValues.put("java.lang.Long", "Long[] tests = {new Long(0l), null};");
		dataTypeTestValues.put("java.lang.Boolean", "Boolean[] tests = {new Boolean(true), null};");
		dataTypeTestValues.put("java.lang.Integer", "Integer[] tests = {new Integer(0), null};");
		dataTypeTestValues.put("java.lang.Double", "Double[] tests = {new Double(0), null};");

		dataTypeTestValues.put("java.util.List", "List[] tests = {new ArrayList(), null};");
		dataTypeTestValues.put("java.util.ArrayList", "ArrayList[] tests = {new ArrayList(), null};");
		dataTypeTestValues.put("java.util.Map", "Map[] tests = {new HashMap(), null};");
		dataTypeTestValues.put("java.util.HashMap", "HashMap[] tests = {new HashMap(), null};");
		dataTypeTestValues.put("java.util.Set", "Set[] tests = {new HashSet(), null};");

		dataTypeTestValues.put("java.util.Date", "Date[] tests = {new Date(System.currentTimeMillis()), null};");
		dataTypeTestValues.put("java.util.Calendar", "Calendar[] tests = {Calendar.getInstance(), null};");
		dataTypeTestValues.put("java.util.TimeZone", "TimeZone[] tests = {TimeZone.getDefault(), null};");

		dataTypeTestValues.put("java.sql.Date", "java.sql.Date[] tests = {new java.sql.Date(System.currentTimeMillis()), null};");
		
		
	}

}
