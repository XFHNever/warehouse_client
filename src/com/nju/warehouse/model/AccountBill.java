package com.nju.warehouse.model;

import java.io.Serializable;
import java.util.Date;

import com.nju.warehouse.result.AccountType;

@SuppressWarnings("serial")
public class AccountBill implements Serializable{
	private Date date;
	private AccountType type;
	private String name;
	private Double money;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public AccountBill(Date date, AccountType type, String name, Double money) {
		super();
		this.date = date;
		this.type = type;
		this.name = name;
		this.money = money;
	}
	public AccountBill() {
		super();
	}
	
	
}	
