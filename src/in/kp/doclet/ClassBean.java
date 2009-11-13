package in.kp.doclet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ClassBean {
    private String packageName;
    private String name;
    private String modifiers;
    private String extendsClass;
    private List<String> imports = new ArrayList<String>();
    private List<Class> implementedClasses = new ArrayList<Class>();
    private String comments;
    private List<MethodBean> methods = new ArrayList<MethodBean>();
    private List<VariableBean> variables = new ArrayList<VariableBean>();

    public ClassBean(String pPackageName, String pName, String pModifiers) {
        super();
        packageName = pPackageName;
        name = pName;
        modifiers = pModifiers;
    }

    public ClassBean(String pPackageName, String pName, String pModifiers, String pExtendsClass,
            List<String> pImports, List<Class> pImplementedClasses) {
        super();
        packageName = pPackageName;
        name = pName;
        modifiers = pModifiers;
        extendsClass = pExtendsClass;
        imports = pImports;
        implementedClasses = pImplementedClasses;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param pName
     *            the name to set
     */
    public void setName(String pName) {
        name = pName;
    }

    /**
     * @return the modifiers
     */
    public String getModifiers() {
        return modifiers;
    }

    /**
     * @param pModifiers
     *            the modifiers to set
     */
    public void setModifiers(String pModifiers) {
        modifiers = pModifiers;
    }

    /**
     * @return the extendsClass
     */
    public String getExtendsClass() {
        return extendsClass;
    }

    /**
     * @param pExtendsClass
     *            the extendsClass to set
     */
    public void setExtendsClass(String pExtendsClass) {
        extendsClass = pExtendsClass;
    }

    /**
     * @return the imports
     */
    public List<String> getImports() {
        return imports;
    }

    /**
     * @return the imports
     */
    public List<String> getUniqImports() {
        ArrayList<String> uniqImports = new ArrayList<String>();
        Set<String> tempSet = new HashSet<String>();
        for (Iterator<String> iterator = imports.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            if(!tempSet.contains(key)) {
                tempSet.add(key);
                uniqImports.add(key);
            }
        }
        return uniqImports;
    }

    /**
     * @param pImports
     *            the imports to set
     */
    public void setImports(List<String> pImports) {
        imports = pImports;
    }

    public void addImport(String pImport) {
        imports.add(pImport);
    }
    /**
     * @return the implementedClasses
     */
    public List<Class> getImplementedClasses() {
        return implementedClasses;
    }

    /**
     * @param pImplementedClasses
     *            the implementedClasses to set
     */
    public void setImplementedClasses(List<Class> pImplementedClasses) {
        implementedClasses = pImplementedClasses;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param pComments
     *            the comments to set
     */
    public void setComments(String pComments) {
        comments = pComments;
    }

    /**
     * @return the packageName
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * @param pPackageName
     *            the packageName to set
     */
    public void setPackageName(String pPackageName) {
        packageName = pPackageName;
    }

    /**
     * @return the methods
     */
    public List<MethodBean> getMethods() {
        return methods;
    }

    public void addMethod(MethodBean pMethod) {
        methods.add(pMethod);
        imports.addAll(pMethod.getMethodImports());
    }

    /**
     * @param pMethods
     *            the methods to set
     */
    public void setMethods(List<MethodBean> pMethods) {
        methods = pMethods;
    }

    /**
     * @return the variables
     */
    public List<VariableBean> getVariables() {
        return variables;
    }

    /**
     * @param pVariables
     *            the variables to set
     */
    public void setVariables(List<VariableBean> pVariables) {
        variables = pVariables;
    }
}
