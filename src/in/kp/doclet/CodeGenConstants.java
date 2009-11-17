package in.kp.doclet;

/**
 * @author kpothula
 *
 */
public interface CodeGenConstants {
	String ANN_TEST = "@Test";
	String ANN_BEFORE = "@Before";
	String ANN_AFTER = "@After";

	String CLASS_COMMENT = "/**" 
		+ "\n * Testcase automatically generated." 
		+ "\n * [AUTO GENERATED]." 
		+ "\n\n * @author kpothula" 
		+ "\n */";

	String METHOD_COMMENT = "/**" 
		+ "\n * Test Method for testing Setter and Getter methods." 
		+ "\n * [AUTO GENERATED]." 
		+ "\n */";
	
	String DATA_COMMENT = "//Default test data";

}
