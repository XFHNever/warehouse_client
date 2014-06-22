package com.nju.warehouse.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Storage implements Serializable{
	private String name;
	private String type;
	private int purchasesQuantity;
	private Double averagePurchasesPrice;
	private Double totalPurchasesPrice;
	private int saleQuantity;
	private Double averageSaleQuantity;
	private Double tatalSaleQuantity;
	private int storageQuanttiy;
	private Double averageStoragePrice;
	private Double totalStoragePrice;
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
	public int getPurchasesQuantity() {
		return purchasesQuantity;
	}
	public void setPurchasesQuantity(int purchasesQuantity) {
		this.purchasesQuantity = purchasesQuantity;
	}
	public Double getAveragePurchasesPrice() {
		return averagePurchasesPrice;
	}
	public void setAveragePurchasesPrice(Double averagePurchasesPrice) {
		this.averagePurchasesPrice = averagePurchasesPrice;
	}
	public Double getTotalPurchasesPrice() {
		return totalPurchasesPrice;
	}
	public void setTotalPurchasesPrice(Double totalPurchasesPrice) {
		this.totalPurchasesPrice = totalPurchasesPrice;
	}
	public int getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(int saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Double getAverageSaleQuantity() {
		return averageSaleQuantity;
	}
	public void setAverageSaleQuantity(Double averageSaleQuantity) {
		this.averageSaleQuantity = averageSaleQuantity;
	}
	public Double getTatalSaleQuantity() {
		return tatalSaleQuantity;
	}
	public void setTatalSaleQuantity(Double tatalSaleQuantity) {
		this.tatalSaleQuantity = tatalSaleQuantity;
	}
	public int getStorageQuanttiy() {
		return storageQuanttiy;
	}
	public void setStorageQuanttiy(int storageQuanttiy) {
		this.storageQuanttiy = storageQuanttiy;
	}
	public Double getAverageStoragePrice() {
		return averageStoragePrice;
	}
	public void setAverageStoragePrice(Double averageStoragePrice) {
		this.averageStoragePrice = averageStoragePrice;
	}
	public Double getTotalStoragePrice() {
		return totalStoragePrice;
	}
	public void setTotalStoragePrice(Double totalStoragePrice) {
		this.totalStoragePrice = totalStoragePrice;
	}
	public Storage() {
		super();
	}
	public Storage(String name, String type, int purchasesQuantity,
			Double averagePurchasesPrice, Double totalPurchasesPrice,
			int saleQuantity, Double averageSaleQuantity,
			Double tatalSaleQuantity, int storageQuanttiy,
			Double averageStoragePrice, Double totalStoragePrice) {
		super();
		this.name = name;
		this.type = type;
		this.purchasesQuantity = purchasesQuantity;
		this.averagePurchasesPrice = averagePurchasesPrice;
		this.totalPurchasesPrice = totalPurchasesPrice;
		this.saleQuantity = saleQuantity;
		this.averageSaleQuantity = averageSaleQuantity;
		this.tatalSaleQuantity = tatalSaleQuantity;
		this.storageQuanttiy = storageQuanttiy;
		this.averageStoragePrice = averageStoragePrice;
		this.totalStoragePrice = totalStoragePrice;
	}
	
}
