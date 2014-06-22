package com.nju.warehouse.controller.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.AddCommodity;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.administratot.AddNewCommodityView;
import com.nju.warehouse.view.administratot.AdministratorView;

public class AddNewCommodityController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AddNewCommodityView view = null;
	private AddCommodity model = null;
	
	
	public AddNewCommodityController(AddNewCommodityView view, AddCommodity commodity) {
		LogUtil.getInstance().log(Level.INFO, "AddNewCommodityController---constructor");
		
		this.view = view;
		this.model = commodity;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			AddResult result = null;
			Commodity commodity = null;
			
			String name = view.getName();
			String type = view.getType();
			String defaultPurchasePrice = view.getDefaultPurchasePrice();
			String defaultSellingPrice = view.getDefaultSellingPrice();
			
			if(name.length() == 0 || type.length() ==0 || 
					defaultPurchasePrice.length() == 0 || defaultSellingPrice.length() == 0) {
				result = AddResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isNumber(defaultPurchasePrice) || !NumberUtil.isNumber(defaultSellingPrice)) {
					result = AddResult.添加失败价格请输入数字;
				} else {
					commodity = new Commodity(name, type, 0, Double.parseDouble(defaultPurchasePrice), Double.parseDouble(defaultSellingPrice), 0, 0);
					
					try {
						result = service.addNewCommodity(commodity);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			model.add(commodity, result);
			
			CommodityList commodityList = new CommodityList();
			commodityList.addObserver(AdministratorView.getCommodityManageView());
			try {
				commodityList.setList(service.getAllCommodity());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	public AddNewCommodityView getView() {
		return view;
	}
	
	

	
}
