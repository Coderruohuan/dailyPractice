package io;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class FileClass {
	
	private  File file=new File(System.getProperty("user.dir","F:")+"/myFile.txt");
	public  void main() throws IOException {
		//user.dir是获取工作路径，如果没有取默认的F
        File file=new File(System.getProperty("user.dir","F:")+"/myFile.txt");
        System.out.println("绝对路径absoluteFile："+file.getAbsoluteFile());
        System.out.println("绝对路径absolutePath："+file.getAbsolutePath());
        System.out.println("绝对路径canonicalPath："+file.getCanonicalPath());
	}
	
	/**
	 * 获取文件系统的根目录(windows是盘符)，当时如果插了u盘，u盘的盘符也会输出
	 * 
	 */
	@Test
	public void getFileRoots() {
		File[] files=File.listRoots();
		for(File f:files) {
	        System.out.println("磁盘"+f.getPath()+"的可用空间为："+new BigDecimal(f.getUsableSpace()).divide(new BigDecimal(Math.pow(1024, 3)), 2, RoundingMode.DOWN));
	        System.out.println("磁盘"+f.getPath()+"的总空间为："+new BigDecimal(f.getTotalSpace()).divide(new BigDecimal(Math.pow(1024, 3)), 2, RoundingMode.DOWN));
	        System.out.println("磁盘"+f.getPath()+"的剩余空间为："+new BigDecimal(f.getFreeSpace()).divide(new BigDecimal(Math.pow(1024, 3)), 2, RoundingMode.DOWN));
		}
	}
	
	/**
	 * 创建修改文件
	 * {@link java.io.File#renameTo(File)}
	 *  {@link java.io.File#mkdir()}
	 * @throws IOException
	 */
	@Test
	public void createAndModifyFile() throws IOException {
		System.out.println(file.createNewFile());
		//前缀不能小于三个，不然报错；后缀如果传null，会默认加.tmp,还有第三个参数是指定文件创建目录，如果没有指定就会有默认的路径，当前机器的是C:\Users\WANGWE~1\AppData\Local\Temp\
		System.out.println(File.createTempFile("wxz", "x"));
		file.deleteOnExit();
		System.out.println(System.getProperty("java.io.tmpdir"));//获取本机的临时目录
	}
	
	/**
	 * 设置权限
	 * @throws IOException 
	 */
	@Test
	public  void setPermission()  {
		System.out.println(file.setExecutable(false, true));//TODO验证非本人创建的文件设置权限
		System.out.println(file.setWritable(false));
		System.out.println("execute:"+file.canExecute()+";write:"+file.canWrite()+";read:"+file.canRead());
		
	}
}
