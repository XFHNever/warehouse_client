package com.nju.warehouse.model.salesman;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.Customer;

public class CustomerList extends Observable{
	private ArrayList<Customer> list;

	public ArrayList<Customer> getList() {
		return list;
	}

	public void setList(ArrayList<Customer> list) {
		this.list = list;
		setChanged();
		notifyObservers(this.list);
	}

	public CustomerList(ArrayList<Customer> list) {
		super();
		this.list = list;
	}

	public CustomerList() {
		super();
	}
	
}
