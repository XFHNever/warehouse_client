package com.nju.warehouse.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable{
	private Double total;
	private Double receiveMoney;
	private Double payMoney;
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getReceiveMoney() {
		return receiveMoney;
	}
	public void setReceiveMoney(Double receiveMoney) {
		this.receiveMoney = receiveMoney;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public Account(Double total, Double receiveMoney, Double payMoney) {
		super();
		this.total = total;
		this.receiveMoney = receiveMoney;
		this.payMoney = payMoney;
	}
	public Account() {
		super();
	}
}
