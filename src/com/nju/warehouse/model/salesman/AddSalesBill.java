package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.result.AddResult;

public class AddSalesBill extends Observable{
	private SalesBill salesBill;

	public SalesBill getSalesBill() {
		return salesBill;
	}

	public void setSalesBill(SalesBill salesBill) {
		this.salesBill = salesBill;
	}

	public AddSalesBill(SalesBill salesBill) {
		super();
		this.salesBill = salesBill;
	}

	public AddSalesBill() {
		super();
	}

	public void add(SalesBill salesBill, AddResult result) {
		setSalesBill(salesBill);
		
		setChanged();
		notifyObservers(result);
	}
	
	public void complete() {
		setSalesBill(null);
		
		setChanged();
		notifyObservers();
	}
}
