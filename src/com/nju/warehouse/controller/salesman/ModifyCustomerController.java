package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.model.Customer;
import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.model.salesman.ModifyCustomer;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.ModifyResult;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.salesman.ModifyCustomerView;
import com.nju.warehouse.view.salesman.SalesManView;

public class ModifyCustomerController implements ActionListener{
private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private ModifyCustomerView  view = null;
	private ModifyCustomer model = null;
	
	
	public ModifyCustomerController(ModifyCustomerView view, ModifyCustomer modifyCustomer) {
		LogUtil.getInstance().log(Level.INFO, "ModifyCustomerController---constructor");
		
		this.view = view;
		this.model = modifyCustomer;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			ModifyResult result = null;
			Customer customer = null;
			
			String name = view.getName();
			String phone = view.getPhone();
			Double receiveAccount = view.getReceiveAccount();
			Double payAccount = view.getPayAccount();
			Double totalAccount = view.getTotalAccount();
			
			if(phone.length() == 0) {
				result = ModifyResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isPhone(phone)) {
					result = ModifyResult.修改失败请输入数字类型;
				} else {
					customer = new Customer(name, phone, receiveAccount, payAccount, totalAccount);
					
					try {
						result = service.modifyCustomer(customer);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
				model.modify(customer, result);
			
			}
			
			if(result == ModifyResult.修改成功) {
				CustomerList customerList = new CustomerList();
				customerList.addObserver(SalesManView.getCustomerManageView());
				try {
					customerList.setList(service.getAllCustomer());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
			

		}
		
	}
	 
}
