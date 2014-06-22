package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;

import com.nju.warehouse.model.Customer;
import com.nju.warehouse.model.salesman.AddCustomer;
import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.salesman.AddNewCustomerView;
import com.nju.warehouse.view.salesman.CustomerManageView;

public class CustomerManageController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private CustomerManageView view = null;
	private CustomerList model = null;
	
	
	public CustomerManageController(CustomerManageView view, CustomerList list) {
		LogUtil.getInstance().log(Level.INFO, "CustomerManageController---constructor");
		
		this.view = view;
		this.model = list;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.view.setCustomerList(this.model);
		
		this.model.notifyObservers();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LogUtil.getInstance().log(Level.INFO, "CommodityManageController--actionPerformed!");
		
		if(e.getSource() == view.getSearch_btn()) {
			String searchText = view.getSearchText();
			ArrayList<Customer> newList = null;
			try {
				newList = service.getCustomerByParam(searchText);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			model.setList(newList);
			
		} else {
			new AddNewCustomerController(new AddNewCustomerView(), new AddCustomer(null));
		}
	}

	public void setModel(CustomerList model) {
		this.model.setList(model.getList());
	}
}
