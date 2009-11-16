package in.kp.doclet;

import java.util.ArrayList;
import java.util.List;

public class MethodBean {
    private String name;
    private String modifiers;
    private String annotation;
    private ArrayList<VariableBean> params = new ArrayList<VariableBean>();
    private ArrayList<Class> exceptions = new ArrayList<Class>();
    private List<String> body = new ArrayList<String>();
    private ArrayList<String> methodImports = new ArrayList<String>();
    public MethodBean() {
    }
    public MethodBean(String pName) {
        super();
        name = pName;
    }
    public String getName() {
        return name;
    }
    public void setName(String pName) {
        name = pName;
    }
    public String getModifiers() {
        return modifiers;
    }
    public void setModifiers(String pModifiers) {
        modifiers = pModifiers;
    }
    public ArrayList<VariableBean> getParams() {
        return params;
    }
    public void setParams(ArrayList<VariableBean> pParams) {
        params = pParams;
    }
    public ArrayList<Class> getExceptions() {
        return exceptions;
    }
    public void setExceptions(ArrayList<Class> pExceptions) {
        exceptions = pExceptions;
    }
    public  List<String> getBody() {
        return body;
    }
    public void addToBody(String pLine) {
        body.add(pLine);
    }
    public ArrayList<String> getMethodImports() {
        return methodImports;
    }
    public void setMethodImports(ArrayList<String> pMethodImports) {
        methodImports = pMethodImports;
    }
    public void addMethodImport(String pMethodImport) {
        methodImports.add(pMethodImport);
    }
    public String getAnnotation() {
        return annotation;
    }
    public void setAnnotation(String pAnnotation) {
        annotation = pAnnotation;
    }
    
}
