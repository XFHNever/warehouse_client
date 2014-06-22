package com.nju.warehouse.model;

import java.io.Serializable;
import java.util.Observable;
import java.util.logging.Level;

import com.nju.warehouse.result.LoginResult;
import com.nju.warehouse.result.UserType;
import com.nju.warehouse.util.LogUtil;

public class User extends Observable implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4647367105613733520L;
	private String name;
	private String password;
	private UserType type;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	
	public User(String name, String password, UserType type) {
		super();
		this.name = name;
		this.password = password;
		this.type = type;
	}
	public User() {
		super();
	}
	public void login(User user) {
		if(user != null) {
			this.name = user.getName();
			this.password = user.getPassword();
			this.type = user.getType();
		} 

		LogUtil.getInstance().log(Level.INFO, "validate user's login: ");
		
		setChanged();
		notifyObservers(user);
	}
	
	public void clear() {
		this.name = null;
		this.password = null;
		
		setChanged();
		notifyObservers(1);
	}
}
