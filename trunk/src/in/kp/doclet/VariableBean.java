package in.kp.doclet;

public class VariableBean {
    private String modifiers;
    private String name;
    private Class type;
    
    
    public VariableBean(String pModifiers, String pName, Class pType) {
        super();
        modifiers = pModifiers;
        name = pName;
        type = pType;
    }
    
    /**
     * @return the modifiers
     */
    public String getModifiers() {
        return modifiers;
    }
    /**
     * @param pModifiers the modifiers to set
     */
    public void setModifiers(String pModifiers) {
        modifiers = pModifiers;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param pName the name to set
     */
    public void setName(String pName) {
        name = pName;
    }
    /**
     * @return the type
     */
    public Class getType() {
        return type;
    }
    /**
     * @param pType the type to set
     */
    public void setType(Class pType) {
        type = pType;
    }
}
