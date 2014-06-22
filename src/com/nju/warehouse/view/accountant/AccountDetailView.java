package com.nju.warehouse.view.accountant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nju.warehouse.controller.accountant.AccountDetailController;
import com.nju.warehouse.model.AccountBill;
import com.nju.warehouse.model.accountant.AccountBillList;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class AccountDetailView extends JPanel implements Observer{
	private JTable bill_table = null;
	private JButton add_btn = null;
	
	private AccountBillList accountBillList = null;
	
	public static final String[] tableHeads = {"日期","类别", "名字", "金额"};
	private Object[][] object = null;
	
	public AccountDetailView() {		
		LogUtil.getInstance().log(Level.INFO, "AccountDetailView --- contruction");
		
		initView();
	}
	
	
	private void initView() {
		this.setSize(3*ConstantsUtil.FRAME_WIDTH/4, 9*ConstantsUtil.FRAME_HEIGHT/10);
		this.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 3*ConstantsUtil.FRAME_WIDTH/4 -10, 9*ConstantsUtil.FRAME_HEIGHT/10 - 200);
		this.add(scrollPane);
		
		bill_table = new JTable();
		scrollPane.setViewportView(bill_table);
		
		updateTabel(null);
		
		
		
		add_btn = new JButton("添加");
		add_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(add_btn);
		
		this.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LogUtil.getInstance().log(Level.INFO, "AccountDetailView---update");
		
		if(arg != null) {
			ArrayList<AccountBill> accountBills = (ArrayList<AccountBill>) arg;
			updateTabel(accountBills);
		}
		
		
	}
	
	private void updateTabel(ArrayList<AccountBill> accountBills) {
		if(accountBills != null) {
			int size = accountBills.size();
			object = new Object[size][4];
			
			for(int i=0; i<size; i++) {
				object[i][0] = new SimpleDateFormat("yyyy-MM-dd").format(accountBills.get(i).getDate());
				object[i][1] = accountBills.get(i).getType();
				object[i][2] = accountBills.get(i).getName();
				object[i][3] = accountBills.get(i).getMoney();

			}
						
		} else {
			object = new Object[0][4];
		}
		
		bill_table.setModel(new DefaultTableModel(object, tableHeads));
		
	}
	
	public void addListener(AccountDetailController controller) {
		
		this.add_btn.addActionListener(controller);
	}


	public void setAccountBillList(AccountBillList accountBillList) {
		this.accountBillList = accountBillList;
	}


	public AccountBillList getAccountBillList() {
		return accountBillList;
	}
	
	
}
