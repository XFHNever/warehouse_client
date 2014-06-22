package com.nju.warehouse.model.administrator;

import java.util.ArrayList;
import java.util.Observable;

import com.nju.warehouse.model.Storage;

public class StorageList extends Observable{
	private ArrayList<Storage> storages;

	public ArrayList<Storage> getStorages() {
		return storages;
	}

	public void setStorages(ArrayList<Storage> storages) {
		this.storages = storages;
		
		setChanged();
		notifyObservers(this.storages);
	}

	public StorageList(ArrayList<Storage> storages) {
		super();
		this.storages = storages;
	}

	public StorageList() {
		super();
	}
}
