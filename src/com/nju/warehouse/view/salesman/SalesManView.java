package com.nju.warehouse.view.salesman;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.warehouse.model.User;
import com.nju.warehouse.result.Operation;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class SalesManView extends JPanel implements Observer{
	private User user = null;
	
	private JPanel top_panel = null;
	private JPanel left_panel = null;
	private JPanel main_panel = null;
	
	private JButton purchases_button = null;
	private JButton sales_button = null;
	private JButton customer_button = null;
	
	private JLabel type_label = null;
	private JLabel name_label = null;
	
	private static PurchasesManageView purchasesManageView = null;
	private static SalesManageView salesManageView = null;
	private static CustomerManageView customerManageView = null;
	
	public SalesManView(User user) {
		LogUtil.getInstance().log(Level.INFO, "SalesManView---constructor");
		this.user = user;
		initView();
	}
	
	private void initView() {

		this.setLayout(null);
			
		//初始化顶部
		top_panel = new JPanel();
		top_panel.setBounds(0, 0, ConstantsUtil.FRAME_WIDTH, ConstantsUtil.FRAME_HEIGHT/10);
		top_panel.setLayout(null);
		
		JLabel title_lable = new JLabel("WarehouseSystem",JLabel.CENTER);
		title_lable.setFont(new Font(null, Font.BOLD, 20));
		title_lable.setBounds(0, 0, top_panel.getWidth()/5, top_panel.getHeight());
		top_panel.add(title_lable);
		
		type_label = new JLabel(user.getType() + ":",JLabel.RIGHT);
		type_label.setFont(new Font("楷体", Font.BOLD, 15));
		type_label.setBounds(5*top_panel.getWidth()/8, 0, top_panel.getWidth()/8, top_panel.getHeight());
		top_panel.add(type_label);
		
		name_label = new JLabel(user.getName(),JLabel.LEFT);
		name_label.setFont(new Font("楷体", Font.ITALIC, 14));
		name_label.setBounds(3*top_panel.getWidth()/4, 0, top_panel.getWidth()/8, top_panel.getHeight());
		top_panel.add(name_label);
		
		this.add(top_panel);
		
		//初始化左侧
		left_panel = new JPanel();
		left_panel.setBounds(0, ConstantsUtil.FRAME_HEIGHT/10, ConstantsUtil.FRAME_WIDTH/5, 9*ConstantsUtil.FRAME_HEIGHT/10);
		left_panel.setLayout(null);
		
		purchases_button = new JButton("进货管理");
		purchases_button.setBounds(left_panel.getWidth()/20, left_panel.getHeight()/6,
				9*left_panel.getWidth()/10, left_panel.getHeight()/12);
		left_panel.add(purchases_button);
		
		sales_button = new JButton("销售管理");
		sales_button.setBounds(left_panel.getWidth()/20, left_panel.getHeight()/3,
				9*left_panel.getWidth()/10, left_panel.getHeight()/12);
		left_panel.add(sales_button);
		
		customer_button = new JButton("用户管理");
		customer_button.setBounds(left_panel.getWidth()/20, left_panel.getHeight()/2,
				9*left_panel.getWidth()/10, left_panel.getHeight()/12);
		left_panel.add(customer_button);
		
		this.add(left_panel);
		
		//初始化主要部分
		main_panel = new JPanel();
		main_panel.setBounds(ConstantsUtil.FRAME_WIDTH/5, ConstantsUtil.FRAME_HEIGHT/10, 
				4*ConstantsUtil.FRAME_WIDTH/5, 9*ConstantsUtil.FRAME_HEIGHT/10);
		main_panel.setLayout(null);
		this.add(main_panel);
		
		purchasesManageView = new PurchasesManageView();
		main_panel.add(purchasesManageView);
		
		this.repaint();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		Operation operation = (Operation)arg;
		
		main_panel.removeAll();
		
		if(Operation.PurchasesManage == operation) {
			if(purchasesManageView == null) {
				purchasesManageView = new PurchasesManageView();	
			}
			
			main_panel.add(purchasesManageView);
			
			LogUtil.getInstance().log(Level.INFO, "SalesManView:change to purchasesManageView");
		} else if(Operation.SalesManage == operation) {
			if(salesManageView == null) {
				salesManageView = new SalesManageView();
			}
			
			main_panel.add(salesManageView);
			
			LogUtil.getInstance().log(Level.INFO, "SalesManView:change to salesManageView");
		} else {
			if(customerManageView == null) {
				customerManageView = new CustomerManageView();
			}
			
			main_panel.add(customerManageView);
			
			LogUtil.getInstance().log(Level.INFO, "SalesManView:change to customerManageView");
		}
		
		main_panel.repaint();
		
	}

	public JPanel getLeft_panel() {
		return left_panel;
	}

	public JPanel getMain_panel() {
		return main_panel;
	}

	public JButton getPurchases_button() {
		return purchases_button;
	}

	public JButton getSales_button() {
		return sales_button;
	}

	public JButton getCustomer_button() {
		return customer_button;
	}

	public JLabel getType_label() {
		return type_label;
	}

	public JLabel getName_label() {
		return name_label;
	}

	

	public static SalesManageView getSalesManageView() {
		return salesManageView;
	}

	public static PurchasesManageView getPurchasesManageView() {
		return purchasesManageView;
	}

	public static CustomerManageView getCustomerManageView() {
		return customerManageView;
	}

	public void addActionListener(ActionListener listener) {
		this.purchases_button.addActionListener(listener);
		this.customer_button.addActionListener(listener);
		this.sales_button.addActionListener(listener);
	}
}
