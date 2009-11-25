package in.kp.doclet;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

import com.sun.tools.javadoc.Main;

public class JUnitCodeGenTask extends Task {
	private String destdir;
	private String packagenames;
	private String doclet;
	private String docletpath;
	private boolean debug = false;
	private String sourcedir;

	@Override
	public void execute() throws BuildException {
		System.out.println();
	}

	private void runJavaDoc() {
		String[] javadocargs = { "-d", "docs", "-sourcepath", "/home/user/src", "java.applet" };
		Main.execute(javadocargs);
	}
}
