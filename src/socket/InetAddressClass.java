package socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class InetAddressClass {

	@Test
	public void  InetAddressTest() throws UnknownHostException {
		//创建本地和DNS服务器的一个链接，查找名字和数字地址，找不到报UnknownHostException异常
		InetAddress address=InetAddress.getByName("www.baidu.com");
		System.out.println(address.getHostAddress());//获取ip地址
		//可以反向找
		InetAddress ipAddress=InetAddress.getByName("180.97.33.108");
		//如果对应的ip没有主机名或者安全管理器阻止确定主机名，就会返回之前输入的ip地址
		System.out.println(ipAddress.getHostName());

	}
}
