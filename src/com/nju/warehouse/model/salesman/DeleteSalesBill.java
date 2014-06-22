package com.nju.warehouse.model.salesman;

import java.util.Observable;

import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.result.DeleteResult;

public class DeleteSalesBill extends Observable{
	private SalesBill salesBill;

	public SalesBill getSalesBill() {
		return salesBill;
	}

	public void setSalesBill(SalesBill salesBill) {
		this.salesBill = salesBill;
	}

	public DeleteSalesBill(SalesBill salesBill) {
		super();
		this.salesBill = salesBill;
	}

	public DeleteSalesBill() {
		super();
	}
	
	public void delete(SalesBill salesBill, DeleteResult result) {
		setSalesBill(salesBill);
		
		setChanged();
		
		notifyObservers(result);
	}
}
