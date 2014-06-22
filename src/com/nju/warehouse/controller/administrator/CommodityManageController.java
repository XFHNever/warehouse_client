package com.nju.warehouse.controller.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.AddCommodity;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.administratot.AddNewCommodityView;
import com.nju.warehouse.view.administratot.CommodityManageView;

public class CommodityManageController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private CommodityManageView view = null;
	private CommodityList model = null;
	
	
	public CommodityManageController(CommodityManageView view, CommodityList list) {
		LogUtil.getInstance().log(Level.INFO, "CommodityManageController---constructor");
		
		this.view = view;
		this.model = list;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.view.setCommodityList(this.model);
		
		this.model.notifyObservers();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LogUtil.getInstance().log(Level.INFO, "CommodityManageController--actionPerformed!");
		
		if(e.getSource() == view.getSearch_btn()) {
			String searchText = view.getSearchText();
			ArrayList<Commodity> newList = null;
			try {
				newList = service.getCommodityByParam(searchText);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			model.setList(newList);
			
		} else {
			new AddNewCommodityController(new AddNewCommodityView(), new AddCommodity(new Commodity()));
		}
	}

	public void setModel(CommodityList model) {
		this.model.setList(model.getList());
	}

	
}
