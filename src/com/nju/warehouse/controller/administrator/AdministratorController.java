package com.nju.warehouse.controller.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.nju.warehouse.model.Storage;
import com.nju.warehouse.model.administrator.AdminOperation;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.model.administrator.StorageList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.Operation;
import com.nju.warehouse.view.MainFrame;
import com.nju.warehouse.view.administratot.AdministratorView;

public class AdministratorController implements ActionListener {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AdministratorView administratorView = null;
	private AdminOperation model = null;
	
	private CommodityManageController commodityManageController = null;
	private StorageManageController storageManageController = null;
	
	
	public AdministratorController(AdministratorView administratorView) {
		this.administratorView = administratorView;
		model = new AdminOperation(Operation.CommodityManage);
		this.administratorView.addActionListener(this);
		model.addObserver(administratorView);
		
		initCommodity();
		
	}
	
	public void initCommodity() {
		CommodityList commodityList = new CommodityList();
		commodityList.addObserver(AdministratorView.getCommodityManageView());
		try {
			commodityList.setList(service.getAllCommodity());
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		commodityManageController = new CommodityManageController(AdministratorView.getCommodityManageView(),
				commodityList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == administratorView.getCommodiy_button()) {
			model.setOperation(Operation.CommodityManage);
			
			CommodityList commodityList = null;
			
			try {
				commodityList = new CommodityList(service.getAllCommodity());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(commodityManageController == null ) {	
				commodityManageController = new CommodityManageController(AdministratorView.getCommodityManageView(),
						commodityList);
			} else {
				commodityManageController.setModel(commodityList);
			}
			
		} else {
			model.setOperation(Operation.StorageMange);
			
			ArrayList<Storage> storages = null;
			
			try {
				storages = service.getAllStorage();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			
			if(storageManageController == null) {
				storageManageController = new StorageManageController(AdministratorView.getStorageManageView(),
						new StorageList(storages));
			} else {
				storageManageController.setModel(new StorageList(storages));
			}
		}
	}

	
}
