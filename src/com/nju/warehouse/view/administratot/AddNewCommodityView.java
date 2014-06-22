package com.nju.warehouse.view.administratot;

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

import com.nju.warehouse.controller.administrator.AddNewCommodityController;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class AddNewCommodityView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel type_label = null;
	private JTextField type_textField = null;
	private JLabel defaultPurchasePrice_label = null;
	private JTextField defaultPurchasePrice_textField = null;
	private JLabel defaultSellingPrice_label = null;
	private JTextField defaultSellingPrice_textField = null;
	
	public AddNewCommodityView() {
		LogUtil.getInstance().log(Level.INFO, "AddNewCommodityView---constructor");
		initView();
	}
	
	public void initView() {
		this.setTitle("添加商品");
		this.setSize(280, 350);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 20, 200, 240);
		inner.setLayout(new GridLayout(5, 2, 15, 10));
		
		name_label = new JLabel("名称",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		
		type_label = new JLabel("型号",JLabel.CENTER);
		inner.add(type_label);
		
		type_textField = new JTextField();
		inner.add(type_textField);
		
		defaultPurchasePrice_label = new JLabel("默认进价",JLabel.CENTER);
		inner.add(defaultPurchasePrice_label);
		
		defaultPurchasePrice_textField = new JTextField();
		inner.add(defaultPurchasePrice_textField);
		
		
		defaultSellingPrice_label = new JLabel("默认售价",JLabel.CENTER);
		inner.add(defaultSellingPrice_label);
		
		defaultSellingPrice_textField = new JTextField();
		inner.add(defaultSellingPrice_textField);
		
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
			LogUtil.getInstance().log(Level.INFO, "AddNewCommodity--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "AddNewCommodity--update--OK");
			
			AddResult result = (AddResult)arg;
			
			if(result == AddResult.添加成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "添加新商品提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "添加新商品提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(AddNewCommodityController controller) {
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
	
	public String getType() {
		return type_textField.getText();
	}
	
	public String getDefaultPurchasePrice() {
		return defaultPurchasePrice_textField.getText();
	}
	
	public String getDefaultSellingPrice() {
		return defaultSellingPrice_textField.getText();
	}
	
}
