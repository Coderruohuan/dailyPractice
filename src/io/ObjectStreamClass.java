package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import collection.MyObject;
/**
 * 对象流
 * serialVersionUID不指定的话系统会给你默认指定；如果你自己指定了，你反序列化的时候加个属性也不会报错；如果由系统给出，多个字段肯定报错
 * @author wwn
 *
 */
public class ObjectStreamClass {
	
	@Test
	public  void objectStreamTest() {
		try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("output.txt"));
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("output.txt"))) {
			MyObject writeobj=new MyObject();
			writeobj.setName("时间：2019-08-19 16:55");
			oos.writeObject(writeobj);		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	@Test
	public void objectInputStream() {
		try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream("output.txt"))) {	
				MyObject obj=(MyObject) ois.readObject();
				System.out.println(obj.getName());		
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

}
