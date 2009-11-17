package in.kp.doclet;

import java.util.ArrayList;
import java.util.List;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Type;

public class ClassBeanCreator {
	private ClassDoc classDoc;
	private ClassBean classBean = null;

	private ClassBeanCreator(ClassDoc pClassDoc) {
		super();
		classDoc = pClassDoc;
	}

	public static ClassBean create(ClassDoc pClassDoc) {
		ClassBeanCreator cbc = new ClassBeanCreator(pClassDoc);
		return cbc.create();
	}

	private ClassBean create() {
		classBean = new ClassBean(classDoc.containingPackage().name(), classDoc.name() + "Test", "public");
		addDefaultImports();
		MethodDoc[] methods = classDoc.methods();
		List<String> methodsList = loadMethodsList(methods);
		for (int i = 0; i < methods.length; i++) {
			MethodDoc method = methods[i];
			if (method.isStatic() || method.isPrivate() || method.isProtected() || method.parameters().length > 1) {
				System.out.println(method.name() + " skipped");
			} else if (method.name().startsWith("set")) {
				String setterName = method.name().substring(3);
				String getMethod = "get" + setterName;
				if (methodsList.contains(getMethod)) {
					createTestForSetter(method, setterName);
				} else {
					System.out.println(setterName + " doesnt have a getter");
				}
			}
		}
		return classBean;
	}

	private void createTestForSetter(MethodDoc method, String pMethodName) {
		MethodBean methodBean = new MethodBean("setAndGet" + pMethodName);
		methodBean.setAnnotation(CodeGenConstants.ANN_TEST);
		methodBean.setModifiers("public final void ");
		Parameter[] params = method.parameters();
		if (params.length == 1) {
			Parameter param = params[0];
			Type paramType = param.type();
			if (isImport(paramType)) {
				addImport(methodBean,paramType.qualifiedTypeName());
			}
			methodBean.addToBody(CodeGenConstants.DATA_COMMENT);
			String testValues = DataTypeTestValues.generateCode(paramType);
			if(testValues == null || testValues.equals("")){
				return;
			}
			methodBean.addToBody(testValues);
			String className = classDoc.name();
			String classVarName = "m" + className;
			methodBean.addToBody("//creating instance for the class to be tested");
			methodBean.addToBody(className +" "+ classVarName + " = new " + className + "();");
			methodBean.addToBody("for (int i = 0; i < tests.length; i++) {");
			methodBean.addToBody(classVarName + ".set" + pMethodName + "(tests[i]);");
			methodBean.addToBody("assertEquals(tests[i], " + classVarName + ".get" + pMethodName + "());");
			methodBean.addToBody("}");
		}
		classBean.addMethod(methodBean);
	}
	
	private void addImport(MethodBean methodBean, String className) {
		methodBean.addMethodImport(className);
		if(className.equals("java.util.List"))
			methodBean.addMethodImport("java.util.ArrayList");
		else if(className.equals("java.util.Map"))
			methodBean.addMethodImport("java.util.HashMap");
	}

	private boolean isImport(Type paramType) {
		String className = paramType.qualifiedTypeName();
		if (paramType.isPrimitive() || className.startsWith("java.lang") || className.equals("java.sql.Date")) {
			return false;
		} else {
			return true;
		}
	}
	
	private boolean isPrimitive(String dataType) {
		if("long".equals(dataType) || "int".equals(dataType) || "double".equals(dataType) || "char".equals(dataType) || "float".equals(dataType))
			return true;
		else
			return false;
	}

	private List<String> loadMethodsList(MethodDoc[] methods) {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < methods.length; i++) {
			l.add(methods[i].name());
		}
		return l;
	}

	private void addDefaultImports() {
		classBean.addImport("org.junit.Assert.*");
		classBean.addImport("org.junit.Test");
		classBean.addImport("static org.junit.Assert.assertEquals");
		classBean.addImport("static org.junit.Assert.assertTrue");
	}
}
