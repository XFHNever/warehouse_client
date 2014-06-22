package com.nju.warehouse.view;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nju.warehouse.controller.LoginController;
import com.nju.warehouse.model.User;
import com.nju.warehouse.result.UserType;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.accountant.AccountantView;
import com.nju.warehouse.view.administratot.AdministratorView;
import com.nju.warehouse.view.salesman.SalesManView;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements Observer{
	
	private LoginView loginView = new LoginView();
	private JPanel view = null;
	
	private User user = null;
	
	
	public MainFrame(User user) {
		this.user = user;
		initView();
	} 	
	
	public void initView() {
		setSize(ConstantsUtil.FRAME_WIDTH, ConstantsUtil.FRAME_HEIGHT);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setTitle("WarehouseSystem");
		
		getContentPane().add(loginView);
		
		this.setVisible(true);
	}
	

	@Override
	public void update(Observable o, Object arg) {
	
		if(arg == null) {
			JOptionPane.showMessageDialog(this, "用户名或者密码错误，请重新输入!","登陆提示",JOptionPane.INFORMATION_MESSAGE);
			loginView.clear();
		}else if(arg instanceof Integer) {
			loginView.clear();
		} else {
			user = (User)arg;
	
			if(user.getName().equals("") || user.getPassword().equals("")) {
				JOptionPane.showMessageDialog(this, "用户名或者密码不能为空，请重新输入!","登陆提示",JOptionPane.ERROR_MESSAGE);
			} else {
				
				if(user.getType() == UserType.库存管理员) {
					LogUtil.getInstance().log(Level.INFO, "MainFrame to AdministratorView");
					view = new AdministratorView(user);
			
				} else if(user.getType() == UserType.销售人员) {
					view = new SalesManView(user);
					LogUtil.getInstance().log(Level.INFO, "MainFrame to SalesManView");
				
				} else {
					view = new AccountantView(user);
					LogUtil.getInstance().log(Level.INFO, "MainFrame to AccountantView");
				}
				this.getContentPane().removeAll();
				this.getContentPane().add(view);
				this.validate();
			}
			
		}
		
	}
	
	
	public void addActionListener(LoginController controller) {
		loginView.addActionListener(controller);
	}
	
	public JButton getLoginButton() {
		return loginView.getOk_btn();
	}
	
	public String getUserName() {
		return loginView.getName_textField().getText().trim();
	}
	
	public String getPassword() {
		return String.valueOf(loginView.getPassword_textField().getPassword());
	}
	
	public String getType() {
		return loginView.getType_comboBox().getSelectedItem().toString().trim();
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public JPanel getView() {
		return view;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

}

