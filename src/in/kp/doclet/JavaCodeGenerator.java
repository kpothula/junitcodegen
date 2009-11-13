package in.kp.doclet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class JavaCodeGenerator {
    private ClassBean classBean;
    private JavaCodeWriter out;
    
    public static void generate(ClassBean pClassBean, JavaCodeWriter pOut) throws IOException {
        JavaCodeGenerator g = new JavaCodeGenerator(pClassBean, pOut);
        g.generate();
    }
   
    private JavaCodeGenerator(ClassBean pClassBean, JavaCodeWriter pOut) {
        super();
        classBean = pClassBean;
        out = pOut;
    }

    private void generate() throws IOException {
        writeHeader();
        writeClassDef();
        writeBody();
        //writeFooter();
    }
    
    private void writeHeader() throws IOException {
        writePackage();
        writeImports();
    }
    private void writePackage() throws IOException {
        out.print("package ");
        out.print(classBean.getPackageName());
        out.print(";");
        out.println();
        out.println();
    }
    private void writeImports() throws IOException {
        List<String> imports = classBean.getUniqImports();
        for (Iterator<String> iterator = imports.iterator(); iterator.hasNext();) {
            String importStmt = (String) iterator.next();
            out.print("import ");
            out.print(importStmt);
            out.print(";");
            out.println();
        }
        out.println();
    }
    private void writeClassDef() throws IOException {
        out.print(classBean.getModifiers());
        out.print(" class ");
        out.print(classBean.getName());
        out.println();
    }

    private void writeBody() throws IOException {
        out.print('{');
        out.println();
        writeMethods();
        out.print('}');
    }
    
    private void writeMethods() throws IOException {
        List<MethodBean> methods = classBean.getMethods();
        for (Iterator iterator = methods.iterator(); iterator.hasNext();) {
            MethodBean methodBean = (MethodBean) iterator.next();
            writeMethod(methodBean);
            out.println();
        }
    }
    private void writeMethod(MethodBean methodBean) throws IOException {
        out.println();
        out.println(methodBean.getAnnotation());
        out.print(methodBean.getModifiers());
        out.print(methodBean.getName());
        out.println("(){");
        out.print(methodBean.getBody());
        out.print('}');
    }
}
