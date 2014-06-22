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
import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.result.DeleteResult;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.table.DeleteCommodifyBillCellRender;

@SuppressWarnings("serial")
public class PurchasesManageView extends JPanel implements Observer{
	private JTable bill_table = null;
	private JButton add_btn = null;
	private JTextField search_textField = null;
	private JButton search_btn = null;
	
	private CommodityBillList commodityBillList = null;
	
	public static final String[] tableHeads = {"日期","进退货", "客户名", "商品","型号","数量","单价","总价","退货"};
	private Object[][] object = null;
	
	public PurchasesManageView() {		
		LogUtil.getInstance().log(Level.INFO, "PurchasesManageView --- contruction");
		
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
		
		
		add_btn = new JButton("进货");
		add_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(add_btn);
		
		this.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LogUtil.getInstance().log(Level.INFO, "PurchasesManageView---update");
		
		if(arg == null) {
			JOptionPane.showMessageDialog(this, "您搜索的日期没有货单，请重新搜索", "搜索货单提示", JOptionPane.ERROR_MESSAGE);
		} else if(arg instanceof DeleteResult){
			JOptionPane.showMessageDialog(this, (DeleteResult)arg, "退货提示", JOptionPane.INFORMATION_MESSAGE);
		} else {
			ArrayList<CommodityBill> list = (ArrayList<CommodityBill>)arg;
			
			if(list.size() == 0) {
				JOptionPane.showMessageDialog(this, "您搜索的日期没有货单，请重新搜索", "搜索货单提示", JOptionPane.WARNING_MESSAGE);
			} else {
				updateTabel(list);
			}
			

		}
	}
	
	private void updateTabel(ArrayList<CommodityBill> commodityBills) {
		
		if(commodityBills != null) {
			int size = commodityBills.size();
			object = new Object[size][9];
			
			for(int i=0; i<size; i++) {
				object[i][0] = new SimpleDateFormat("yyyy-MM-dd").format(commodityBills.get(i).getDate());
				object[i][1] = commodityBills.get(i).getType();
				object[i][2] = commodityBills.get(i).getCustomer();
				object[i][3] = commodityBills.get(i).getCommodityName();
				object[i][4] = commodityBills.get(i).getCommodityType();
				object[i][5] = commodityBills.get(i).getAmount();
				object[i][6] = commodityBills.get(i).getPrice();
				object[i][7] = commodityBills.get(i).getTotal();
				object[i][8] = commodityBills.get(i).isDelete();
				
			}
						
		} else {
			object = new Object[0][9];
		}
		
		bill_table.setModel(new DefaultTableModel(object, tableHeads));
		
		new DeleteCommodifyBillCellRender(bill_table, 8);
		
		
	}
	
	public void addListener(PurchasesManageController controller) {
		
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


	public CommodityBillList getCommodityBillList() {
		return commodityBillList;
	}


	public void setCommodityBillList(CommodityBillList commodityBillList) {
		this.commodityBillList = commodityBillList;
	}
}
