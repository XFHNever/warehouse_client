package com.nju.warehouse.model;

import java.io.Serializable;

public class Customer implements Serializable{
	private String name;
	private String phone;
	private double receiveAccount;
	private double payAccount;
	private double totalAccount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getReceiveAccount() {
		return receiveAccount;
	}
	public void setReceiveAccount(double receiveAccount) {
		this.receiveAccount = receiveAccount;
	}
	public double getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(double payAccount) {
		this.payAccount = payAccount;
	}
	public double getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(double totalAccount) {
		this.totalAccount = totalAccount;
	}
	public Customer(String name, String phone, double receiveAccount,
			double payAccount, double totalAccount) {
		super();
		this.name = name;
		this.phone = phone;
		this.receiveAccount = receiveAccount;
		this.payAccount = payAccount;
		this.totalAccount = totalAccount;
	}
}
