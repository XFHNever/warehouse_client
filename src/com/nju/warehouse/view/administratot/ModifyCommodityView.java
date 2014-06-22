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

import com.nju.warehouse.controller.administrator.ModifyCommodityController;
import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.result.ModifyResult;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class ModifyCommodityView extends JFrame implements Observer{
	private JButton ok_btn = null;
	private JButton cancel_btn = null;
	
	private JLabel name_label = null;
	private JTextField name_textField = null;
	private JLabel type_label = null;
	private JTextField type_textField = null;
	private JLabel quantity_label = null;
	private JTextField quantity_textField = null;
	private JLabel lastPurchasePrice_label = null;
	private JTextField lastPurchasePrice_textField = null;
	private JLabel lastSellingPrice_label = null;
	private JTextField lastSellingPrice_textField = null;
	private JLabel defaultPurchasePrice_label = null;
	private JTextField defaultPurchasePrice_textField = null;
	private JLabel defaultSellingPrice_label = null;
	private JTextField defaultSellingPrice_textField = null;
	
	private Commodity commodity = null;
	
	public ModifyCommodityView(Commodity commodity) {
		LogUtil.getInstance().log(Level.INFO, "ModifyCommodityView---constructor");
		this.commodity = commodity;
		
		initView();
	}
	
	public void initView() {
		this.setTitle("添加商品");
		this.setSize(280, 545);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		
		Container container = this.getContentPane();
		
		JPanel inner = new JPanel();
		inner.setBounds(10, 10, 210, 440);
		inner.setLayout(new GridLayout(8, 2, 15, 10));
		
		name_label = new JLabel("名称",JLabel.CENTER);
		inner.add(name_label);
		
		name_textField = new JTextField();
		inner.add(name_textField);
		name_textField.setText(commodity.getName());
		name_textField.setEditable(false);
		
		
		type_label = new JLabel("型号",JLabel.CENTER);
		inner.add(type_label);
		
		
		type_textField = new JTextField();
		inner.add(type_textField);
		type_textField.setText(commodity.getType());
		type_textField.setEditable(false);
		
		quantity_label = new JLabel("数量",JLabel.CENTER);
		inner.add(quantity_label);
		
		
		quantity_textField = new JTextField();
		inner.add(quantity_textField);
		quantity_textField.setText(commodity.getQuantity() + "");
		quantity_textField.setEditable(false);
		
		lastPurchasePrice_label = new JLabel("最近进价",JLabel.CENTER);
		inner.add(lastPurchasePrice_label);
		
		
		lastPurchasePrice_textField = new JTextField();
		inner.add(lastPurchasePrice_textField);
		lastPurchasePrice_textField.setText(commodity.getLastPurchasePrice() + "");
		lastPurchasePrice_textField.setEditable(false);
		
		lastSellingPrice_label = new JLabel("最近售价",JLabel.CENTER);
		inner.add(lastSellingPrice_label);
		
		
		lastSellingPrice_textField = new JTextField();
		inner.add(lastSellingPrice_textField);
		lastSellingPrice_textField.setText(commodity.getLastSellingPrice() + "");
		lastSellingPrice_textField.setEditable(false);
		
		
		defaultPurchasePrice_label = new JLabel("默认进价",JLabel.CENTER);
		inner.add(defaultPurchasePrice_label);
		
		defaultPurchasePrice_textField = new JTextField();
		inner.add(defaultPurchasePrice_textField);
		defaultPurchasePrice_textField.setText(commodity.getDefaultPurchasePrice() + "");
		
		defaultSellingPrice_label = new JLabel("默认售价",JLabel.CENTER);
		inner.add(defaultSellingPrice_label);
		
		defaultSellingPrice_textField = new JTextField();
		inner.add(defaultSellingPrice_textField);
		defaultSellingPrice_textField.setText(commodity.getDefaultSellingPrice() + "");
		
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
			LogUtil.getInstance().log(Level.INFO, "ModifyCommodityView--update--cancel");
			
			this.dispose();
		} else {
			LogUtil.getInstance().log(Level.INFO, "ModifyCommodityView--update--OK");
			
			ModifyResult result = (ModifyResult)arg;
			
			if(result == ModifyResult.修改成功) {
				this.dispose();
				JOptionPane.showMessageDialog(this, result.toString(), "修改商品提示", JOptionPane.CLOSED_OPTION);
			} else {
				JOptionPane.showMessageDialog(this, result.toString(), "修改商品提示", JOptionPane.ERROR_MESSAGE);
			}
		}
		

	}
	
	public void addListener(ModifyCommodityController controller) {
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
	
	public String getType() {
		return type_textField.getText();
	}

	public Integer getQuantity() {
		return Integer.parseInt(quantity_textField.getText());
	}
	
	public Double getLastPurchasePrice() {
		return Double.parseDouble(lastPurchasePrice_textField.getText());
	}
	
	public Double getLastSellingPrice() {
		return Double.parseDouble(lastSellingPrice_textField.getText());
	}
	
	public String getDefaultPurchasePrice() {
		return defaultPurchasePrice_textField.getText();
	}
	
	public String getDefaultSellingPrice() {
		return defaultSellingPrice_textField.getText();
	}
	

}
