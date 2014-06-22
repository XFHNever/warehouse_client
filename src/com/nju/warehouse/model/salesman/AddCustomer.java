package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.Customer;
import com.nju.warehouse.result.AddCustomerResult;

public class AddCustomer extends Observable{
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public AddCustomer(Customer customer) {
		super();
		this.customer = customer;
	}

	public AddCustomer() {
		super();
	}
	
	public void add(Customer customer, AddCustomerResult result) {
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
