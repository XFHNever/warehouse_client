package com.nju.warehouse.net;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class LaunchClient {
	public static  String IP = "127.0.0.1";            //ipµØÖ·
	public static final int PORT = 9999;                    //¶Ë¿ÚºÅ
	private static  IDataRemoteService dataRemoteService;
	
	public static void main(String[] args) {
		LaunchClient.init();
	}
	
	public static void init() {
		try {
			dataRemoteService = (IDataRemoteService) Naming.lookup("rmi://" + IP + ":" + PORT + "/warehouse");
			
			System.out.println("connect successful!");
			
			System.out.println(dataRemoteService.hello("Never"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static IDataRemoteService getDatabaseFactory() {
		if(dataRemoteService == null) {
			init();
		}
		
		return dataRemoteService;
	}
	
	
}
