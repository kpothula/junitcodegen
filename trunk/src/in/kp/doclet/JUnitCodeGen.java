package in.kp.doclet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import com.sun.javadoc.*;

/**
 * Class JUnitCodeGen
 * 
 */
public class JUnitCodeGen {
	private static String outDir = null;
	private static boolean overwriteDestDir = false;

	private static void init(RootDoc root) {
		outDir = readOptions(root.options());

	}

	public static boolean start(RootDoc root) {
		try {
			init(root);
			ClassDoc[] classes = root.classes();
			ArrayList<String> generatesTestClasses = new ArrayList<String>();
			for (int i = 0; i < classes.length; i++) {
				ClassDoc classDoc = classes[i];
				if (classDoc.isAbstract() || classDoc.isInterface() || classDoc.isEnum() || classDoc.isPrivate() || classDoc.isAnnotationType()) {
				} else if (!hasDefaultConstructor(classDoc)) {
					System.out.println(classDoc.qualifiedTypeName() + " does not have default Constructor.");
				} else {
					ClassBean testClassBean = ClassBeanCreator.create(classDoc);
					if (testClassBean.hasMethods()) {
						JavaCodeWriter writer = getWriter(testClassBean);
						if(writer != null) {
							JavaCodeGenerator.generate(testClassBean, writer);
							generatesTestClasses.add(classDoc.qualifiedTypeName());
							writer.close();
						}
					} else {
						System.out.println(classDoc.qualifiedTypeName() + " does not have any methods to cover.");
					}
				}
			}
			System.out.println("---------REPORT-----------------------------------------------");
			for (Iterator iterator = generatesTestClasses.iterator(); iterator.hasNext();) {
				String clsName = (String) iterator.next();
				System.out.println("\t"+clsName);
			}
			System.out.println("-------------------------------------------------------------");
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean hasDefaultConstructor(ClassDoc classDoc) {
		ConstructorDoc[] cdocs = classDoc.constructors();
		for (int i = 0; i < cdocs.length; i++) {
			ConstructorDoc cdoc = cdocs[i];
			if (cdoc.parameters().length == 0) {
				if (!cdoc.isPrivate()) {
					return true;
				}
			}
		}
		return false;
	}

	private static JavaCodeWriter getWriter(ClassBean classBean) throws IOException {
		String packagePath = classBean.getPackageName();
		packagePath = packagePath.replace('.', '/');
		File packageDirs = new File(outDir, packagePath);
		packageDirs.mkdirs();
		File testFile = new File(packageDirs.getAbsoluteFile(), classBean.getName() + ".java");
		if(testFile.exists()) {
			System.out.println("File already exists");
			return null;
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(testFile, false));
		return new JavaCodeWriter(out);
	}

	private static String readOptions(final String[][] options) {
		Properties p = new Properties(System.getProperties());
		String outputDir = null;
		for (int i = 0; i < options.length; i++) {
			String[] opt = options[i];
			if (opt[0].equals("-d")) {
				outputDir = opt[1];
			} else if (opt[0].equals("-overwrite")) {
				overwriteDestDir = Boolean.valueOf(opt[1]);
			}
		}
		if (outputDir == null) {
			outputDir = p.getProperty("user.dir");
		}
		return outputDir;
	}

	public static int optionLength(String option) {
		if (option.equals("-d")) {
			return 2;
		}
		if (option.equals("-overwrite")) {
			return 2;
		}
		return 0;
	}

	public static boolean validOptions(String options[][], DocErrorReporter reporter) {
		boolean foundDirOption = false;
		for (int i = 0; i < options.length; i++) {
			String[] opt = options[i];
			if (opt[0].equals("-d")) {
				if (foundDirOption) {
					reporter.printError("Only one -d option allowed.");
					return false;
				} else {
					foundDirOption = true;
				}
			} else if (opt[0].equals("-overwrite")) {
				
			}

		}
		if (!foundDirOption) {
			reporter.printError("Usage: javadoc -d outputDir " + "-doclet in.kp.doclet.sample.ListClass " + "... ");
		}
		return foundDirOption;

	}
}
