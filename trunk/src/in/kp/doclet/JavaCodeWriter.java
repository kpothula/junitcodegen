package in.kp.doclet;

import java.io.BufferedWriter;
import java.io.IOException;

public class JavaCodeWriter {
    private BufferedWriter writer;
    public JavaCodeWriter(BufferedWriter pWriter) {
        this.writer = pWriter;
    }
    
    public void print(String str) throws IOException {
        writer.write(str);
    }
    public void println(String str) throws IOException {
        writer.write(str);
        writer.newLine();
    }
    public void print(int c) throws IOException {
        writer.write(c);
    }
    public void print(char[] c) throws IOException {
        writer.write(c);
    }
    public void println() throws IOException {
        writer.newLine();
    }

    public void flush() throws IOException {
        writer.flush();
    }

    public void close() throws IOException {
        writer.flush();
        writer.close();
    }
    
}
