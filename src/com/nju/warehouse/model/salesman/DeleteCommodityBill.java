package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.result.DeleteResult;

public class DeleteCommodityBill extends Observable{
	private CommodityBill commodityBill;

	public CommodityBill getCommodityBill() {
		return commodityBill;
	}

	public void setCommodityBill(CommodityBill commodityBill) {
		this.commodityBill = commodityBill;
	}

	public DeleteCommodityBill(CommodityBill commodityBill) {
		super();
		this.commodityBill = commodityBill;
	}

	public DeleteCommodityBill() {
		super();
	}
	
	public void delete(CommodityBill commodityBill, DeleteResult result) {
		setCommodityBill(commodityBill);
		
		setChanged();
		
		notifyObservers(result);
	}
}
