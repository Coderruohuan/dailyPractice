package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.junit.Test;

public class OutputStreamWriterClass {
	
	@Test
	public  void outputStreamWriterTest() throws IOException {
		try(OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("output.txt"))){
			osw.write("wohenkunwoxiangyaoshuijiaoweishenmerenshengrucijiannan");	
		}
		
	}

}
