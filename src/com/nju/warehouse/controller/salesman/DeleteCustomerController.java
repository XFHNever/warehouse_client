package com.nju.warehouse.controller.salesman;

import java.rmi.RemoteException;

import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.view.salesman.CustomerManageView;

public class DeleteCustomerController {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private CustomerManageView customerManageView = null;
	private CustomerList model = null;
	private String name = null;
	
	public DeleteCustomerController(CustomerManageView view, String name) {
		this.customerManageView = view;
		this.name = name;
		model = new CustomerList();
		
		model.addObserver(this.customerManageView);	
		
		
		try {
			service.deleteCustomer(this.name);
			
			model.setList(service.getAllCustomer());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
