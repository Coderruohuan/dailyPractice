package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 *UnicastRemoteObject 用于从远程机器获取对象
 *在class 目录下输入 rmic rmi.RemoteServiceImpl 然后生成RemoteServiceImpl_Stub.class 存根
 * @author wwn
 *
 */
public class RemoteServiceImpl extends UnicastRemoteObject implements RemoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected RemoteServiceImpl() throws RemoteException {
	}

	@Override
	public int add(int a, int b) throws RemoteException{
		return  a+b;
	}
 
}
