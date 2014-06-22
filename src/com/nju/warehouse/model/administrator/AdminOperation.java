package com.nju.warehouse.model.administrator;

import java.util.Observable;

import com.nju.warehouse.result.Operation;

public class AdminOperation extends Observable{
	private Operation operation;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
		
		this.setChanged();
		this.notifyObservers(operation);
	}

	public AdminOperation(Operation operation) {
		super();
		this.operation = operation;
	}
}
