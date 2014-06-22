package com.nju.warehouse.controller.administrator;

import java.rmi.RemoteException;

import com.nju.warehouse.model.administrator.StorageList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.view.administratot.StorageManageView;

public class StorageManageController {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private StorageManageView view = null;
	private StorageList model = null;
	
	public StorageManageController(StorageManageView storageManageView, StorageList list) {
		this.view = storageManageView;
		this.model = list;
		
		this.model.addObserver(this.view);
		
		try {
			model.setStorages(service.getAllStorage());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setModel(StorageList model) {
		this.model.setStorages(model.getStorages());
	}	
}
