package com.nju.warehouse.model.accountant;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.AccountBill;

public class AccountBillList extends Observable{
	private ArrayList<AccountBill> list;

	public ArrayList<AccountBill> getList() {
		return list;
	}

	public void setList(ArrayList<AccountBill> list) {
		this.list = list;
		
		setChanged();
		notifyObservers(this.list);
	}

	public AccountBillList(ArrayList<AccountBill> list) {
		super();
		this.list = list;
	}

	public AccountBillList() {
		super();
	}
	
}
