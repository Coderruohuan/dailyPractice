package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

public class SocketClass {
	
	public  void SocketTest() throws IOException {
      ServerSocketChannel channel=ServerSocketChannel.open();//打开channel通道
      channel.bind(new InetSocketAddress("127.0.0.1", 9001));//绑定到这个地址和端口

		
	}

}
