package com.nju.warehouse.view.salesman;

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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.nju.warehouse.controller.salesman.PurchasesManageController;
import com.nju.warehouse.controller.salesman.SalesManageController;
import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.model.salesman.SalesBillList;
import com.nju.warehouse.result.DeleteResult;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.table.DeleteSalesBillCellRender;

@SuppressWarnings("serial")
public class SalesManageView extends JPanel implements Observer{
	private JTable bill_table = null;
	private JButton add_btn = null;
	private JTextField search_textField = null;
	private JButton search_btn = null;
	
	private SalesBillList salesBillList = null;
	
	public static final String[] tableHeads = {"日期","清单类型", "客户名", "商品","型号","数量","单价","总价","销售退货"};
	private Object[][] object = null;
	
	public SalesManageView() {		
		LogUtil.getInstance().log(Level.INFO, "SalesManageView --- contruction");
		
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
		
		bill_table = new JTable();
		scrollPane.setViewportView(bill_table);
		
		updateTabel(null);		
		
		
		add_btn = new JButton("销售");
		add_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(add_btn);
		
		this.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LogUtil.getInstance().log(Level.INFO, "SalesManageView---update");
		
		if(arg == null) {
			JOptionPane.showMessageDialog(this, "您搜索的日期没有销售单，请重新搜索", "搜索销售单提示", JOptionPane.ERROR_MESSAGE);
		} else if(arg instanceof DeleteResult){
			JOptionPane.showMessageDialog(this, (DeleteResult)arg, "销售退货提示", JOptionPane.INFORMATION_MESSAGE);
		} else {
			ArrayList<SalesBill> list = (ArrayList<SalesBill>)arg;
			
			if(list.size() == 0) {
				JOptionPane.showMessageDialog(this, "您搜索的日期没有销售单，请重新搜索", "搜索销售单提示", JOptionPane.WARNING_MESSAGE);
			} else {
				updateTabel(list);
			}
			

		}
	}
	
	private void updateTabel(ArrayList<SalesBill> salesBills) {
		
		if(salesBills != null) {
			int size = salesBills.size();
			object = new Object[size][9];
			
			for(int i=0; i<size; i++) {
				object[i][0] = new SimpleDateFormat("yyyy-MM-dd").format(salesBills.get(i).getDate());
				object[i][1] = salesBills.get(i).getType();
				object[i][2] = salesBills.get(i).getCustomer();
				object[i][3] = salesBills.get(i).getCommodityName();
				object[i][4] = salesBills.get(i).getCommodityType();
				object[i][5] = salesBills.get(i).getAmount();
				object[i][6] = salesBills.get(i).getPrice();
				object[i][7] = salesBills.get(i).getTotal();
				object[i][8] = salesBills.get(i).isDelete();
				
			}
						
		} else {
			object = new Object[0][9];
		}
		
		bill_table.setModel(new DefaultTableModel(object, tableHeads));
		
		new DeleteSalesBillCellRender(bill_table, 8);
		
		
	}
	
	public void addListener(SalesManageController controller) {
		
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


	public SalesBillList getSalesBillList() {
		return salesBillList;
	}


	public void setSalesBillList(SalesBillList salesBillList) {
		this.salesBillList = salesBillList;
	}

	
}
