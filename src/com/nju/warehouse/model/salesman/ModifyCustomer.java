package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.Customer;
import com.nju.warehouse.result.ModifyResult;

public class ModifyCustomer extends Observable{
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ModifyCustomer(Customer customer) {
		super();
		this.customer = customer;
	}

	public ModifyCustomer() {
		super();
	}
	
	public void modify(Customer customer, ModifyResult result) {
		setCustomer(customer);
		
		setChanged();
		notifyObservers(result);
	}
	
	public void complete() {
		setCustomer(null);
		
		setChanged();
		notifyObservers();
	}
}
