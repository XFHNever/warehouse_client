package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.model.Customer;
import com.nju.warehouse.model.salesman.AddCustomer;
import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AddCustomerResult;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.administratot.AdministratorView;
import com.nju.warehouse.view.salesman.AddNewCustomerView;
import com.nju.warehouse.view.salesman.SalesManView;

public class AddNewCustomerController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AddNewCustomerView view = null;
	private AddCustomer model = null;
	
	
	public AddNewCustomerController(AddNewCustomerView view, AddCustomer model) {
		LogUtil.getInstance().log(Level.INFO, "AddNewCommodityController---constructor");
		
		this.view = view;
		this.model = model;
		
		model.addObserver(this.view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			AddCustomerResult result = null;
			Customer customer = null;
			
			String name = view.getName();
			String phone = view.getPhone();


			
			if(name.length() == 0 || phone.length() ==0 ) {
				result = AddCustomerResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isPhone(phone)) {
					result = AddCustomerResult.添加失败电话号码请输入正确格式;
				} else {
					customer = new Customer(name, phone, 0, 0, 0);
					
					try {
						result = service.addNewCustomer(customer);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			model.add(customer, result);
			
			CustomerList customerList = new CustomerList();
			customerList.addObserver(SalesManView.getCustomerManageView());
			try {
				customerList.setList(service.getAllCustomer());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	public AddNewCustomerView getView() {
		return view;
	}
	
	

	
}
