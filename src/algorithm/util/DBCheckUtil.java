package algorithm.util;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DBCheckUtil {
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException {
		File file=new File("E:\\gitworkspace\\dev\\microbus-time-share\\PublicTransaction-Biz-Facade\\target\\classes\\org\\publicTransaction\\model");
    	File[] files=file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
	            if(pathname.getName().contains("TRentalUser")&&!pathname.isDirectory()) {
	            	return true;
	            }
				return false;
			}
		});	
    	
    	ClassLoader classLoader=ClassLoader.getSystemClassLoader();
    	for(File f:files) {
    		System.out.print(f.getName());
    		Class<?> c=classLoader.loadClass(f.getName().replace(".class", ""));
    		System.out.println(c);
    		//if(c.getAnnotation())
    	}
    	
	}
    	

}
