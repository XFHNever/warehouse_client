package com.nju.warehouse.view.salesman;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nju.warehouse.controller.salesman.AddNewCustomerController;
import com.nju.warehouse.result.AddCustomerResult;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class AddNewCustomerView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel phone_label = null;
	private JTextField phone_textField = null;
	
	public AddNewCustomerView() {
		LogUtil.getInstance().log(Level.INFO, "AddNewCustomerView---constructor");
		initView();
	}
	
	public void initView() {
		this.setTitle("添加商品");
		this.setSize(280, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 10, 200, 160);
		inner.setLayout(new GridLayout(3, 2, 15, 10));
		
		name_label = new JLabel("名称",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		
		phone_label = new JLabel("联系方式",JLabel.CENTER);
		inner.add(phone_label);
		
		phone_textField = new JTextField();
		inner.add(phone_textField);
		
		ok_btn = new JButton("OK");
		inner.add(ok_btn);
		
		cancel_btn = new JButton("Cancel");
		inner.add(cancel_btn);
		
		
		container.add(inner);
			
		this.setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		if(arg == null) {
			LogUtil.getInstance().log(Level.INFO, "AddNewCustomerView--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "AddNewCustomerView--update--OK");
			
			AddCustomerResult result = (AddCustomerResult)arg;
			
			if(result == AddCustomerResult.添加成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "添加新用户提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "添加新用户提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(AddNewCustomerController controller) {
		this.ok_btn.addActionListener(controller);
		this.cancel_btn.addActionListener(controller);
	}

	public JButton getCancel_btn() {
		return cancel_btn;
	}

	public void setCancel_btn(JButton cancel_btn) {
		this.cancel_btn = cancel_btn;
	}

	public JButton getOk_btn() {
		return ok_btn;
	}
	
	public String getName() {
		return name_textField.getText();
	}
	
	public String getPhone() {
		return phone_textField.getText();
	}
	

}

