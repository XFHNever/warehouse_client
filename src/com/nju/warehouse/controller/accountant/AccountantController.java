package com.nju.warehouse.controller.accountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.nju.warehouse.model.Account;
import com.nju.warehouse.model.AccountBill;
import com.nju.warehouse.model.accountant.AccountAll;
import com.nju.warehouse.model.accountant.AccountBillList;
import com.nju.warehouse.model.accountant.AccountOperation;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.Operation;
import com.nju.warehouse.view.accountant.AccountantView;

public class AccountantController  implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AccountantView view = null;
	private AccountOperation model = null;
	
	private AccountAllController accountAllController = null;
	private AccountDetailController accountDetailController = null;
	
	
	public AccountantController(AccountantView accountantView) {
		this.view = accountantView;
		model = new AccountOperation(Operation.AccountAll);
		this.view.addActionListener(this);

		model.addObserver(this.view);
		
		initAccount();
		
	}
	
	public void initAccount() {
		Account account = null;
		try {
			account = service.getAccount();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		accountAllController = new AccountAllController(AccountantView.getAccountAllView(), new AccountAll(account));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == view.getAll_button()) {
			model.setOperation(Operation.AccountAll);
		
			Account account = null;
			try {
				account = service.getAccount();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			
			if(accountAllController == null ) {
		
				accountAllController = new AccountAllController(AccountantView.getAccountAllView(), new AccountAll(account));
			} else {
				accountAllController.setModel(new AccountAll(account));
			}
			
		} else {
			model.setOperation(Operation.AcountDetail);
			
			ArrayList<AccountBill> accountBills = null;
			try {
				accountBills = service.getAllAccountBill();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			if(accountDetailController == null) {

				accountDetailController = new AccountDetailController(AccountantView.getAccountDetailView(), new AccountBillList(accountBills));
			} else {
				accountDetailController.setModel(new AccountBillList(accountBills));
			}
		}
	}
	
	
	
}
