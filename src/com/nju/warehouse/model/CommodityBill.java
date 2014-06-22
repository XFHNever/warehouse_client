package com.nju.warehouse.model;

import java.io.Serializable;
import java.util.Date;

import com.nju.warehouse.result.CommodityBillType;

@SuppressWarnings("serial")
public class CommodityBill implements Serializable{
	private Date date;
	private CommodityBillType type;
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
	public CommodityBillType getType() {
		return type;
	}
	public void setType(CommodityBillType type) {
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
	public CommodityBill(Date date, CommodityBillType type, String customer,
			String commodityName, String commodityType, int amount,
			double price, double total,boolean isDelete) {
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
	public CommodityBill() {
		super();
	}
}
