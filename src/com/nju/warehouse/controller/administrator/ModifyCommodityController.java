package com.nju.warehouse.controller.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.AddCommodity;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.model.administrator.ModifyCommodity;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.result.ModifyResult;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.administratot.AddNewCommodityView;
import com.nju.warehouse.view.administratot.AdministratorView;
import com.nju.warehouse.view.administratot.ModifyCommodityView;


public class ModifyCommodityController implements ActionListener{
private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private ModifyCommodityView view = null;
	private ModifyCommodity model = null;
	
	
	public ModifyCommodityController(ModifyCommodityView view, ModifyCommodity modifyCommodity) {
		LogUtil.getInstance().log(Level.INFO, "ModifyCommodityController---constructor");
		
		this.view = view;
		this.model = modifyCommodity;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			ModifyResult result = null;
			Commodity commodity = null;
			
			String name = view.getName();
			String type = view.getType();
			String defaultPurchasePrice = view.getDefaultPurchasePrice();
			String defaultSellingPrice = view.getDefaultSellingPrice();
			
			if(name.length() == 0 || type.length() ==0 || 
					defaultPurchasePrice.length() == 0 || defaultSellingPrice.length() == 0) {
				result = ModifyResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isNumber(defaultSellingPrice) || !NumberUtil.isNumber(defaultPurchasePrice)) {
					result = ModifyResult.修改失败请输入数字类型;
				} else {
					commodity = new Commodity(name, type, view.getQuantity(), 
							Double.parseDouble(defaultPurchasePrice), Double.parseDouble(defaultSellingPrice), 
							view.getLastPurchasePrice(), view.getLastSellingPrice());
					
					try {
						result = service.modifyCommodity(commodity);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
				model.modify(commodity, result);
			
			}
			
			if(result == ModifyResult.修改成功) {
				CommodityList commodityList = new CommodityList();
				commodityList.addObserver(AdministratorView.getCommodityManageView());
				try {
					commodityList.setList(service.getAllCommodity());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			

		}
		
	}
	 
}
