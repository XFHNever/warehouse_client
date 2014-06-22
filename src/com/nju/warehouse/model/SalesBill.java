package com.nju.warehouse.model;

import java.io.Serializable;
import java.util.Date;

import com.nju.warehouse.result.SalesBillType;


@SuppressWarnings("serial")
public class SalesBill implements Serializable{
	private Date date;
	private SalesBillType type;
	private String customer;
	private String commodityName;
	private String commodityType;
	private int amount;
	private double price;
	private double total;
	private boolean isDelete;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public SalesBillType getType() {
		return type;
	}
	public void setType(SalesBillType type) {
		this.type = type;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isDelete() {
		return isDelete;
	}
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
	public SalesBill(Date date, SalesBillType type, String customer,
			String commodityName, String commodityType, int amount,
			double price, double total, boolean isDelete) {
		super();
		this.date = date;
		this.type = type;
		this.customer = customer;
		this.commodityName = commodityName;
		this.commodityType = commodityType;
		this.amount = amount;
		this.price = price;
		this.total = total;
		this.isDelete = isDelete;
	}
	public SalesBill() {
		super();
	}
}
