package com.nju.warehouse.model.salesman;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.SalesBill;

public class SalesBillList extends Observable{
	private ArrayList<SalesBill> list;

	public ArrayList<SalesBill> getList() {
		return list;
	}

	public void setList(ArrayList<SalesBill> list) {
		this.list = list;
		
		setChanged();
		notifyObservers(this.list);
	}

	public SalesBillList(ArrayList<SalesBill> list) {
		super();
		this.list = list;
	}

	public SalesBillList() {
		super();
	}
}
