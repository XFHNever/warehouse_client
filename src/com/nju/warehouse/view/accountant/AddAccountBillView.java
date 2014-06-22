package com.nju.warehouse.view.accountant;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nju.warehouse.controller.accountant.AddAccountBillController;
import com.nju.warehouse.result.AccountType;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class AddAccountBillView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel type_label = null;
	private JComboBox type_comboBox = null;
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel money_label = null;
	private JTextField money_textField = null;
	
	public AddAccountBillView() {
		LogUtil.getInstance().log(Level.INFO, "AddAccountBillView---constructor");
		initView();
	}
	
	public void initView() {
		this.setTitle("添加账单");
		this.setSize(280, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 20, 200, 180);
		inner.setLayout(new GridLayout(4, 2, 15, 10));
		
		
		
		type_label = new JLabel("类型",JLabel.CENTER);
		inner.add(type_label);
		
		type_comboBox = new JComboBox(ConstantsUtil.account_types);
		inner.add(type_comboBox);
		
		name_label = new JLabel("客户",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		
		money_label = new JLabel("金额",JLabel.CENTER);
		inner.add(money_label);
		
		money_textField = new JTextField();
		inner.add(money_textField);
		
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
			LogUtil.getInstance().log(Level.INFO, "AddAccountBillView--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "AddAccountBillView--update--OK");
			
			AddResult result = (AddResult)arg;
			
			if(result == AddResult.添加成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "添加新账单提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "添加新账单提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(AddAccountBillController controller) {
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
	

	public String getMoney() {
		return money_textField.getText();
	}
	
	public AccountType getType() {
		return AccountType.valueOf(type_comboBox.getSelectedItem().toString());
	}
	
}

