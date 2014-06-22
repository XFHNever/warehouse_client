package com.nju.warehouse.model.administrator;

import java.util.Observable;

import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.result.ModifyResult;

public class ModifyCommodity extends Observable{
	private Commodity commodity = null;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public ModifyCommodity(Commodity commodity) {
		super();
		this.commodity = commodity;
	}
	
	public void modify(Commodity commodity, ModifyResult result) {
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
