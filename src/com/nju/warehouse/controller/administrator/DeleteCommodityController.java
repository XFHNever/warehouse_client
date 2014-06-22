package com.nju.warehouse.controller.administrator;

import java.rmi.RemoteException;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.view.administratot.CommodityManageView;

public class DeleteCommodityController {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private CommodityManageView commodityManageView = null;
	private CommodityList model = null;
	private String name = null;
	private String type = null;
	
	public DeleteCommodityController(CommodityManageView view, String name, String type) {
		this.commodityManageView = view;
		this.name = name;
		this.type = type;
		model = new CommodityList();
		
		model.addObserver(this.commodityManageView);	
		
		
		try {
			service.deleteCommodity(this.name, this.type);
			
			model.setList(service.getAllCommodity());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
