package com.nju.warehouse.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Commodity implements Serializable{
	private String name;
	private String type;
	private int quantity;
	private double defaultPurchasePrice;
	private double defaultSellingPrice;
	private double lastPurchasePrice;
	private double lastSellingPrice;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDefaultPurchasePrice() {
		return defaultPurchasePrice;
	}
	public void setDefaultPurchasePrice(double defaultPurchasePrice) {
		this.defaultPurchasePrice = defaultPurchasePrice;
	}
	public double getDefaultSellingPrice() {
		return defaultSellingPrice;
	}
	public void setDefaultSellingPrice(double defaultSellingPrice) {
		this.defaultSellingPrice = defaultSellingPrice;
	}
	public double getLastPurchasePrice() {
		return lastPurchasePrice;
	}
	public void setLastPurchasePrice(double lastPurchasePrice) {
		this.lastPurchasePrice = lastPurchasePrice;
	}
	public double getLastSellingPrice() {
		return lastSellingPrice;
	}
	public void setLastSellingPrice(double lastSellingPrice) {
		this.lastSellingPrice = lastSellingPrice;
	}
	public Commodity() {
		super();
	}
	public Commodity(String name, String type, int quantity,
			double defaultPurchasePrice, double defaultSellingPrice,
			double lastPurchasePrice, double lastSellingPrice) {
		super();
		this.name = name;
		this.type = type;
		this.quantity = quantity;
		this.defaultPurchasePrice = defaultPurchasePrice;
		this.defaultSellingPrice = defaultSellingPrice;
		this.lastPurchasePrice = lastPurchasePrice;
		this.lastSellingPrice = lastSellingPrice;
	}
}
