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

import com.nju.warehouse.controller.salesman.ModifyCustomerController;
import com.nju.warehouse.model.Customer;
import com.nju.warehouse.result.ModifyResult;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class ModifyCustomerView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel phone_label = null;
	private JTextField phone_textField = null;
	private JLabel receiveAccount_label = null;
	private JTextField receiveAccount_textField = null;
	private JLabel payAccount_label = null;
	private JTextField payAccount_textField = null;
	private JLabel totalAccount_label = null;
	private JTextField totalAccount_textField = null;
	
	private Customer customer = null;
	
	public ModifyCustomerView(Customer customer) {
		LogUtil.getInstance().log(Level.INFO, "ModifyCustomerView---constructor");
		this.customer = customer;
		
		initView();
	}
	
	public void initView() {
		this.setTitle("添加商品");
		this.setSize(280, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 10, 210, 360);
		inner.setLayout(new GridLayout(6, 2, 15, 10));
		
		name_label = new JLabel("姓名",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		name_textField.setText(customer.getName());
		name_textField.setEditable(false);
		
		
		phone_label = new JLabel("联系方式",JLabel.CENTER);
		inner.add(phone_label);
		
		
		phone_textField = new JTextField();
		inner.add(phone_textField);
		phone_textField.setText(customer.getPhone());
		
		receiveAccount_label = new JLabel("应收账款",JLabel.CENTER);
		inner.add(receiveAccount_label);
		
		
		receiveAccount_textField = new JTextField();
		inner.add(receiveAccount_textField);
		receiveAccount_textField.setText(customer.getReceiveAccount() + "");
		receiveAccount_textField.setEditable(false);
		
		payAccount_label = new JLabel("应付账款",JLabel.CENTER);
		inner.add(payAccount_label);
		
		
		payAccount_textField = new JTextField();
		inner.add(payAccount_textField);
		payAccount_textField.setText(customer.getPayAccount() + "");
		payAccount_textField.setEditable(false);
		
		totalAccount_label = new JLabel("总计",JLabel.CENTER);
		inner.add(totalAccount_label);
		
		
		totalAccount_textField = new JTextField();
		inner.add(totalAccount_textField);
		totalAccount_textField.setText(customer.getTotalAccount() + "");
		totalAccount_textField.setEditable(false);
		
		
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
			LogUtil.getInstance().log(Level.INFO, "ModifyCustomerView--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "ModifyCustomerView--update--OK");
			
			ModifyResult result = (ModifyResult)arg;
			
			if(result == ModifyResult.修改成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "修改用户提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "修改用户提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(ModifyCustomerController controller) {
		this.ok_btn.addActionListener(controller);
		this.cancel_btn.addActionListener(controller);
	}

	public JButton getCancel_btn() {
		return cancel_btn;
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

	public Double getReceiveAccount() {
		return Double.parseDouble(receiveAccount_textField.getText());
	}
	
	public Double getPayAccount() {
		return Double.parseDouble(payAccount_textField.getText());
	}
	
	public Double getTotalAccount() {
		return Double.parseDouble(totalAccount_textField.getText());
	}
	
	

}
