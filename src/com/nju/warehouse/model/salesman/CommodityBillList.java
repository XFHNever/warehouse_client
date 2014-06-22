package com.nju.warehouse.model.salesman;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.CommodityBill;

public class CommodityBillList extends Observable{
	private ArrayList<CommodityBill> commodityBills;

	public ArrayList<CommodityBill> getCommodityBills() {
		return commodityBills;
	}

	public void setCommodityBills(ArrayList<CommodityBill> commodityBills) {
		this.commodityBills = commodityBills;
		
		setChanged();
		notifyObservers(this.commodityBills);
	}

	public CommodityBillList(ArrayList<CommodityBill> commodityBills) {
		super();
		this.commodityBills = commodityBills;
	}

	public CommodityBillList() {
		super();
	}
}
