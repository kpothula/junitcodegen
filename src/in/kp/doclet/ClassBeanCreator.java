package in.kp.doclet;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;

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
            if (method.isStatic() || method.isPrivate() || method.isProtected()) {
                System.out.println(method.name() + " skipped");
            } else if (method.name().startsWith("set")) {
                String setterName = method.name().substring(3);
                String getMethod = "get" + setterName;
                if (methodsList.contains(getMethod)) {
                    createTestForSetter(setterName);
                } else {
                    System.out.println(setterName + " doesnt have a getter");
                }
            }
        }
        return classBean;
    }

    private void createTestForSetter(String pMethodName) {
        MethodBean method = new MethodBean("setAndGet" + pMethodName);
        classBean.addImport("org.junit.Test");
        method.setAnnotation(CodeGenConstants.ANN_TEST);
        method.setModifiers("public final void ");
        StringWriter sw = new StringWriter();
        sw.append("");
        method.setBody(sw.toString());
        classBean.addMethod(method);
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
    }
}
