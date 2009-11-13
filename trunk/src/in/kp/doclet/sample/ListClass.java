package in.kp.doclet.sample;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.RootDoc;

public class ListClass {
  public static boolean start(RootDoc root) {
    ClassDoc[] classes = root.classes();
    System.out.println("-----------------------------");
    for (int i = 0; i < classes.length; ++i) {
      ClassDoc cls = classes[i];
      System.out.println(cls.containingPackage()+cls.name());
      MethodDoc[] methods = cls.methods();
      for (int j = 0; j < methods.length; j++) {
        MethodDoc method = methods[j];
        System.out.println("\t" + method.name() +"::"+ method.signature());
        //System.out.println("\t\t" + method.flatSignature());
        //System.out.println("\t\t" + method.modifiers());
      }
    }
    System.out.println("-----------------------------");
    return true;
  }
}