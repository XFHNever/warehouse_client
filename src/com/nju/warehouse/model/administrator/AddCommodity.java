package com.nju.warehouse.model.administrator;

import java.util.Observable;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.result.AddResult;

public class AddCommodity extends Observable{
	private Commodity commodity = null;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public AddCommodity(Commodity commodity) {
		super();
		this.commodity = commodity;
	}
	
	public void add(Commodity commodity, AddResult result) {
		setCommodity(commodity);
		
		setChanged();
		notifyObservers(result);
	}
	
	public void complete() {
		setCommodity(null);
		
		setChanged();
		notifyObservers();
	}
}
