package com.nju.warehouse.controller.accountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;

import com.nju.warehouse.model.accountant.AccountBillList;
import com.nju.warehouse.model.accountant.AddAccountBill;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.accountant.AccountDetailView;
import com.nju.warehouse.view.accountant.AddAccountBillView;

public class AccountDetailController implements ActionListener{
	
	private AccountDetailView view = null;
	private AccountBillList model = null;
	
	
	public AccountDetailController(AccountDetailView view, AccountBillList list) {
		LogUtil.getInstance().log(Level.INFO, "AccountDetailController---constructor");
		
		this.view = view;
		this.model = list;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.view.setAccountBillList(this.model);
		
		this.model.setList(this.model.getList());
				
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		LogUtil.getInstance().log(Level.INFO, "AccountDetailController--actionPerformed!");
		
		new AddAccountBillController(new AddAccountBillView(), new AddAccountBill());

	}

	public void setModel(AccountBillList model) {
		this.model.setList(model.getList());
	}
}
