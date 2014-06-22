package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.model.salesman.SalesBillList;
import com.nju.warehouse.model.salesman.SalesManOperation;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.Operation;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.salesman.SalesManView;

public class SalesManController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private SalesManView view = null;
	private SalesManOperation model = null;
	
	private PurchasesManageController purchasesManageController = null;
	private SalesManageController salesManageController = null;
	private CustomerManageController customerManageController = null;
	
	public SalesManController(SalesManView salesManView) {
		LogUtil.getInstance().log(Level.INFO, "SalesManController---construtor");
		
		this.view = salesManView;
		model = new SalesManOperation(Operation.PurchasesManage);
		this.view.addActionListener(this);

		model.addObserver(this.view);
		
		initPurchases();
		
	}
	
	public void initPurchases() {
		if(purchasesManageController == null ) {
			CommodityBillList commodityBillList = null;
			
			try {
				commodityBillList = new CommodityBillList(service.getAllCommofityBill());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			purchasesManageController = new PurchasesManageController(SalesManView.getPurchasesManageView(), commodityBillList);
			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == view.getPurchases_button()) {
			model.setOperation(Operation.PurchasesManage);
		
			CommodityBillList commodityBillList = null;
			
			try {
				commodityBillList = new CommodityBillList(service.getAllCommofityBill());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(purchasesManageController == null ) {
		
				purchasesManageController = new PurchasesManageController(SalesManView.getPurchasesManageView(), commodityBillList);
			} else {
				purchasesManageController.setModel(commodityBillList);
			}
			
		} else if(e.getSource() == view.getSales_button()) { 
			model.setOperation(Operation.SalesManage);

			SalesBillList salesBillList = null;
			try {
				salesBillList = new SalesBillList(service.getAllSalesBill());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(salesManageController == null) {
				
				salesManageController = new SalesManageController(SalesManView.getSalesManageView(), salesBillList);
			} else {
				salesManageController.setModel(salesBillList);
			}
			
			
		} else {
			model.setOperation(Operation.CustomerManage);

			CustomerList customerList = null;
			try {
				customerList = new CustomerList(service.getAllCustomer());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(customerManageController == null) {
				
				customerManageController = new CustomerManageController(SalesManView.getCustomerManageView(), customerList);
			} else {
				customerManageController.setModel(customerList);
			}
			
		}
	}
	
}
