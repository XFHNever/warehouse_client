package com.nju.warehouse.net;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDataRemoteService extends Remote{
	public String hello(String name) throws RemoteException;
}
