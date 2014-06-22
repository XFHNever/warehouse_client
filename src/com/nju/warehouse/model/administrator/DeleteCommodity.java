package com.nju.warehouse.model.administrator;

import com.nju.warehouse.model.Commodity;


public class DeleteCommodity {
	private Commodity commodity = null;

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
		
		
	}

	public DeleteCommodity(Commodity commodity) {
		super();
		this.commodity = commodity;
	}
}
