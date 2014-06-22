package com.nju.warehouse.controller.accountant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import com.nju.warehouse.model.accountant.AccountAll;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.view.accountant.AccountAllView;

public class AccountAllController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AccountAllView view = null;
	private AccountAll model = null;
	public AccountAllController(AccountAllView view, AccountAll model) {
		super();
		this.view = view;
		this.model = model;
		
		this.model.addObserver(this.view);
		this.view.addListener(this);
		
		this.model.setAccount(this.model.getAccount());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			service.initAccount();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
		model.init();
	}

	public void setModel(AccountAll model) {
		this.model.setAccount(model.getAccount());
	}
	
	
}
