package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;

import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.model.salesman.AddSalesBill;
import com.nju.warehouse.model.salesman.SalesBillList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.salesman.AddSalesBillView;
import com.nju.warehouse.view.salesman.SalesManageView;

public class SalesManageController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private SalesManageView view = null;
	private SalesBillList model = null;
	
	
	public SalesManageController(SalesManageView view, SalesBillList list) {
		LogUtil.getInstance().log(Level.INFO, "SalesManageController---constructor");
		
		this.view = view;
		this.model = list;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.view.setSalesBillList(this.model);
		
		this.model.notifyObservers();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LogUtil.getInstance().log(Level.INFO, "SalesManageController--actionPerformed!");
		
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
					
			ArrayList<SalesBill> newList = null;
			try {
				newList = service.getSalesBillsByDate(date);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			model.setList(newList);
			
		} else {
			new AddSalesBillController(new AddSalesBillView(), new AddSalesBill(null));
		}
	}

	public void setModel(SalesBillList model) {
		this.model.setList(model.getList());
	}

}