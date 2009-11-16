package in.kp.doclet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

public class JavaCodeWriter {
    private BufferedWriter writer;
    private StringWriter bufferText = new StringWriter();
    private int indents = 0;
    private boolean indent = true;
    private static char INDENT_CHAR = '\t';
    
    public JavaCodeWriter(BufferedWriter pWriter) {
        this.writer = pWriter;
    }
    
    public void startIndent() {
    	indents++;
    }
    public void endIndent() {
    	if(indents != 0)
    		indents--;
    }

    public void print(String str) throws IOException {
    	bufferText.append(str);
        //writer.write(str);
    }
    private void writeIndents() throws IOException {
    	if(indent){
    		for (int i = 0; i < indents; i++) {
				writer.write(INDENT_CHAR);
			}
    	}
    }
    private void writebuffer() throws IOException {
		writeIndents();
    	if(bufferText.toString().length() != 0){
    		writer.write(bufferText.toString());
    		bufferText.getBuffer().setLength(0);
    	}
    }
    public void print(int c) throws IOException {
        writer.write(c);
    }
    public void print(char[] c) throws IOException {
        writer.write(c);
    }

    public void println(String str) throws IOException {
    	writebuffer();
        writer.write(str);
        writer.newLine();
    }
    public void println() throws IOException {
    	writebuffer();
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
