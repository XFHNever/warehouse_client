package com.nju.warehouse.controller.accountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import com.nju.warehouse.model.AccountBill;
import com.nju.warehouse.model.accountant.AccountBillList;
import com.nju.warehouse.model.accountant.AddAccountBill;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AccountType;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.accountant.AccountantView;
import com.nju.warehouse.view.accountant.AddAccountBillView;

public class AddAccountBillController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AddAccountBillView view = null;
	private AddAccountBill model = null;
	
	
	public AddAccountBillController(AddAccountBillView view, AddAccountBill accountBill) {
		LogUtil.getInstance().log(Level.INFO, "AddAccountBillController---constructor");
		
		this.view = view;
		this.model = accountBill;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			AddResult result = null;
			AccountBill accountBill = null;
			
			String name = view.getName();
			AccountType type = view.getType();
			String money = view.getMoney();
			
			if(name.length() == 0 || money.length() == 0) {
				result = AddResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isNumber(money) ) {
					result = AddResult.添加失败价格请输入数字;
				} else {
					
					Calendar d = Calendar.getInstance();
					Date nowDate = d.getTime(); 
					
					
					accountBill = new AccountBill(nowDate,type,name,Double.parseDouble(money));
					
					try {
						result = service.addNewAccountBill(accountBill);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			model.add(accountBill, result);
			
			AccountBillList accountBillList = new AccountBillList();
			accountBillList.addObserver(AccountantView.getAccountDetailView());
			try {
				accountBillList.setList(service.getAllAccountBill());
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	public AddAccountBillView getView() {
		return view;
	}
	
	

	
}
