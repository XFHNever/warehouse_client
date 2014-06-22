package com.nju.warehouse.view.salesman;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.nju.warehouse.controller.salesman.CustomerManageController;
import com.nju.warehouse.model.Customer;
import com.nju.warehouse.model.salesman.CustomerList;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.table.CustomerDeleteCellRender;
import com.nju.warehouse.view.table.CustomerModifyCellRender;

@SuppressWarnings("serial")
public class CustomerManageView extends JPanel implements Observer{
	private JTable customer_table = null;
	private JButton add_btn = null;
	private JTextField search_textField = null;
	private JButton search_btn = null;
	
	private CustomerList customerList = null;
	
	public static final String[] tableHeads = {"姓名","联系方式", "应收账款", "应付账款","合计","删除","修改"};
	private Object[][] object = null;
	
	public CustomerManageView() {		
		LogUtil.getInstance().log(Level.INFO, "CustomerManageView --- contruction");
		
		initView();
	}
	
	
	private void initView() {
		this.setSize(3*ConstantsUtil.FRAME_WIDTH/4, 9*ConstantsUtil.FRAME_HEIGHT/10);
		this.setLayout(null);
		
		search_textField = new JTextField();
		search_textField.setBounds(2*ConstantsUtil.FRAME_WIDTH/5, 10, 150, 40);
		this.add(search_textField);
		
		search_btn = new JButton("Search");
		search_btn.setBounds(2*ConstantsUtil.FRAME_WIDTH/5 + 160, 10, 80, 40);
		this.add(search_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 3*ConstantsUtil.FRAME_WIDTH/4 -30, 9*ConstantsUtil.FRAME_HEIGHT/10 - 200);
		this.add(scrollPane);
		
		customer_table = new JTable();
		scrollPane.setViewportView(customer_table);
		
		updateTabel(null);
		
		
		
		add_btn = new JButton("添加");
		add_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(add_btn);
		
		this.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LogUtil.getInstance().log(Level.INFO, "CustomerManageView---update");
		
		if(arg == null) {
			JOptionPane.showMessageDialog(this, "您搜索的用户不存在，请重新搜索", "搜索用户提示", JOptionPane.ERROR_MESSAGE);
		} else {
			ArrayList<Customer> list = (ArrayList<Customer>)arg;
			
			if(list.size() == 0) {
				JOptionPane.showMessageDialog(this, "您搜索的用户不存在，请重新搜索", "搜索用户提示", JOptionPane.WARNING_MESSAGE);
			} else {
				updateTabel(list);
			}
			

		}
	}
	
	private void updateTabel(ArrayList<Customer> customers) {
		if(customers != null) {
			int size = customers.size();
			object = new Object[size][7];
			
			for(int i=0; i<size; i++) {
				object[i][0] = customers.get(i).getName();
				object[i][1] = customers.get(i).getPhone();
				object[i][2] = customers.get(i).getReceiveAccount();
				object[i][3] = customers.get(i).getPayAccount();
				object[i][4] = customers.get(i).getTotalAccount();
				object[i][5] = "Delete";
				object[i][6] = "Modify";
				
			}
						
		} else {
			object = new Object[0][7];
		}
		
		customer_table.setModel(new DefaultTableModel(object, tableHeads));
		
		new CustomerDeleteCellRender(customer_table, 5);
		new CustomerModifyCellRender(customer_table, 6);
		
	}
	
	public void addListener(CustomerManageController controller) {
		
		this.add_btn.addActionListener(controller);
		this.search_btn.addActionListener(controller);
	}
	
	public String getSearchText() {
		return this.search_textField.getText();
	}


	public JButton getAdd_btn() {
		return add_btn;
	}


	public JButton getSearch_btn() {
		return search_btn;
	}


	public CustomerList getCustomerList() {
		return customerList;
	}


	public void setCustomerList(CustomerList customerList) {
		this.customerList = customerList;
	}
}
