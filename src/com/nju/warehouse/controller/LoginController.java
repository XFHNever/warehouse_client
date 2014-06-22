package com.nju.warehouse.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;

import com.nju.warehouse.controller.accountant.AccountantController;
import com.nju.warehouse.controller.administrator.AdministratorController;
import com.nju.warehouse.controller.salesman.SalesManController;
import com.nju.warehouse.model.User;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.UserType;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.MainFrame;
import com.nju.warehouse.view.accountant.AccountantView;
import com.nju.warehouse.view.administratot.AdministratorView;
import com.nju.warehouse.view.salesman.SalesManView;

public class LoginController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private MainFrame view;
	private User model;
	
	
	
	public LoginController() {
		model = new User();
		view = new MainFrame(model);
		
		model.addObserver(view);
		
		view.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == view.getLoginButton()) {
			User loginUser = null;
			String name = view.getUserName();
			String password = view.getPassword();
			String type = view.getType();
			
			boolean notComplete =  name.length() == 0 || password.length() == 0 || type.length() == 0 ;
			
			if(notComplete) {
				loginUser = new User(name, password, UserType.valueOf(type));
				LogUtil.getInstance().log(Level.INFO, "LoginController---empty");
			} else {
				try {
					loginUser = service.login(name, password, type);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				LogUtil.getInstance().log(Level.INFO, "LoginController--login" + loginUser);
			}

			model.login(loginUser);
			
			if(!notComplete && loginUser != null) {
				if(loginUser.getType() == UserType.库存管理员) {
					new AdministratorController((AdministratorView)view.getView());
				} else if (loginUser.getType() == UserType.销售人员){
					new SalesManController((SalesManView)view.getView());
				} else {
					new AccountantController((AccountantView)view.getView());
				}
			}
			
		} else {
			model.clear();
			LogUtil.getInstance().log(Level.INFO, "cancel_button actionListener handlers events");
		}
	}
}

