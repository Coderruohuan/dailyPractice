package rmi;

import java.rmi.Naming;

public class Client {
	public static void main(String[] args) throws Exception {
		String url="rmi://localhost:1099/remoteServer";
		RemoteService service=(RemoteService) Naming.lookup(url);
		int result=service.add(1, 2);
		System.out.println("1+2="+result);
				
	}
                 
}
