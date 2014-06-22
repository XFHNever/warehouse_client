package com.nju.warehouse.model.administrator;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.Commodity;

public class CommodityList extends Observable{
	private ArrayList<Commodity> list;

	public ArrayList<Commodity> getList() {
		return list;
	}

	public void setList(ArrayList<Commodity> list) {
		this.list = list;
		
		this.setChanged();
		this.notifyObservers(list);
	}

	public CommodityList() {
		super();
	}

	public CommodityList(ArrayList<Commodity> list) {
		super();
		this.list = list;
	}
	
	public void addACommodity() {
		this.setChanged();
		this.notifyObservers();
	}
	
}
