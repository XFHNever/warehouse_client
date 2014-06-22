package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.result.AddResult;

public class AddCommodityBill extends Observable{
	private CommodityBill commodityBill;
	
	public AddCommodityBill() {
		super();
	}

	public AddCommodityBill(CommodityBill commodityBill) {
		super();
		this.commodityBill = commodityBill;
	}

	public CommodityBill getCommodityBill() {
		return commodityBill;
	}

	public void setCommodityBill(CommodityBill commodityBill) {
		this.commodityBill = commodityBill;
	}

	public void add(CommodityBill commodityBill, AddResult result) {
		setCommodityBill(commodityBill);
		
		setChanged();
		notifyObservers(result);
	}
	
	public void complete() {
		setCommodityBill(null);
		
		setChanged();
		notifyObservers();
	}
}