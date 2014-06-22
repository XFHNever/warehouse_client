package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.model.salesman.AddCommodityBill;
import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.salesman.AddCommodityBillView;
import com.nju.warehouse.view.salesman.PurchasesManageView;

public class PurchasesManageController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private PurchasesManageView view = null;
	private CommodityBillList model = null;
	
	
	public PurchasesManageController(PurchasesManageView view, CommodityBillList list) {
		LogUtil.getInstance().log(Level.INFO, "PurchasesManageController---constructor");
		
		this.view = view;
		this.model = list;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.view.setCommodityBillList(this.model);
		
		this.model.notifyObservers();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LogUtil.getInstance().log(Level.INFO, "PurchasesManageController--actionPerformed!");
		
		if(e.getSource() == view.getSearch_btn()) {
			
			Date date = null;
			if (view.getSearchText().length() != 0) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					date = sdf.parse(view.getSearchText().trim());
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
					
			ArrayList<CommodityBill> newList = null;
			try {
				newList = service.getCommodityBillsByDate(date);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			model.setCommodityBills(newList);
			
		} else {
			new AddCommodityBillController(new AddCommodityBillView(), new AddCommodityBill(null));
		}
	}

	public void setModel(CommodityBillList model) {
		this.model.setCommodityBills(model.getCommodityBills());
	}

	
}