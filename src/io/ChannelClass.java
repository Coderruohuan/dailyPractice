package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import org.junit.Test;

public class ChannelClass {
		
	
   @Test
   public void getChannel() {
	  try(RandomAccessFile file =new RandomAccessFile("output.txt", "r")){
		 FileChannel fileChannel= file.getChannel();
		 System.out.println(fileChannel);
	  } catch (FileNotFoundException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
		  
	  } catch (IOException e) {

	  }
	   
   }
}
