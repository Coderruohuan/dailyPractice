package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 远程接口，简单指示接口使用远程方法，所有远程方法都可能报RemoteException异常
 * @author wwn
 *
 */
public interface RemoteService  extends Remote{
	
	int add(int a,int b) throws RemoteException;

}
