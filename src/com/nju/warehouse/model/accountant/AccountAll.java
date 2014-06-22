package com.nju.warehouse.model.accountant;

import java.util.Observable;

import com.nju.warehouse.model.Account;

public class AccountAll extends Observable{
	private Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	
		setChanged();
		notifyObservers(this.account);
	}

	public AccountAll(Account account) {
		super();
		this.account = account;
	}

	public AccountAll() {
		super();
	}
	
	public void init() {
		account.setTotal(10000.0);
		
		setChanged();
		notifyObservers(account);
	}
}
