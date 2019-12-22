package algorithm.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;

public class EncryptUtils {
	
	public static void main(String[] args) throws Exception{
		//生成密钥对
		KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
		KeyPair keyPair=keyPairGenerator.generateKeyPair();
		String str="这是一个测试";
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());//私钥加密
		byte[] bytes=cipher.doFinal(str.getBytes());
		System.out.println(new String(bytes));
		
		Cipher cipher1=Cipher.getInstance("RSA");
		cipher1.init(Cipher.DECRYPT_MODE, keyPair.getPublic());//私钥加密
		System.out.println("公钥解密："+new String(cipher1.doFinal(bytes)));
	}

}
