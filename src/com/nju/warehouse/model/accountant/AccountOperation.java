package com.nju.warehouse.model.accountant;

import java.util.Observable;

import com.nju.warehouse.result.Operation;

public class AccountOperation extends Observable{
	private Operation operation;

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
		
		this.setChanged();
		this.notifyObservers(operation);
	}

	public AccountOperation(Operation operation) {
		super();
		this.operation = operation;
	}
}
