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

import com.nju.warehouse.controller.salesman.AddSalesBillController;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class AddSalesBillView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel customer_label = null;
	private JTextField customer_textField = null;
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel type_label = null;
	private JTextField type_textField = null;
	private JLabel quantity_label = null;
	private JTextField quantity_textField = null;
	private JLabel price_label = null;
	private JTextField price_textField = null;
	
	public AddSalesBillView() {
		LogUtil.getInstance().log(Level.INFO, "AddSalesBillView---constructor");
		initView();
	}
	
	public void initView() {
		this.setTitle("销售");
		this.setSize(280, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 10, 200, 330);
		inner.setLayout(new GridLayout(6, 2, 15, 10));
		
		customer_label = new JLabel("用户名",JLabel.CENTER);
		inner.add(customer_label);
		
		customer_textField = new JTextField();
		inner.add(customer_textField);
		
		name_label = new JLabel("商品名称",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		
		type_label = new JLabel("商品型号",JLabel.CENTER);
		inner.add(type_label);
		
		type_textField = new JTextField();
		inner.add(type_textField);
	
		quantity_label = new JLabel("数量",JLabel.CENTER);
		inner.add(quantity_label);
		
		quantity_textField = new JTextField();
		inner.add(quantity_textField);
		
		price_label = new JLabel("单价",JLabel.CENTER);
		inner.add(price_label);
		
		price_textField = new JTextField();
		inner.add(price_textField);

		
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
			LogUtil.getInstance().log(Level.INFO, "AddSalesBillView--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "AddSalesBillView--update--OK");
			
			AddResult result = (AddResult)arg;
			
			if(result == AddResult.添加成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "销售提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "销售提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(AddSalesBillController controller) {
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
	
	public String getCustomer() {
		return customer_textField.getText();
	}
	
	public String getName() {
		return name_textField.getText();
	}
	
	public String getType() {
		return type_textField.getText();
	}
	
	public String getQuantity() {
		return quantity_textField.getText();
	}
	
	public String getPrice() {
		return price_textField.getText();
	}

}

