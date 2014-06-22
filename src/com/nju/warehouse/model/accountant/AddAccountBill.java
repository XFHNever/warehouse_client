package com.nju.warehouse.model.accountant;

import java.util.Observable;

import com.nju.warehouse.model.Account;
import com.nju.warehouse.model.AccountBill;
import com.nju.warehouse.result.AddResult;

public class AddAccountBill extends Observable{
	private AccountBill accountBill;

	public AddAccountBill() {
		super();
	}

	public AddAccountBill(AccountBill accountBill) {
		super();
		this.accountBill = accountBill;
	}

	public AccountBill getAccountBill() {
		return accountBill;
	}

	public void setAccountBill(AccountBill accountBill) {
		this.accountBill = accountBill;
	}


	public void add(AccountBill accountBill, AddResult result) {
		setAccountBill(accountBill);
		
		setChanged();
		notifyObservers(result);
	}
	
	public void complete() {
		setAccountBill(null);
		
		setChanged();
		notifyObservers();
	}
}
