package com.nju.warehouse.view.administratot;

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

import com.nju.warehouse.controller.administrator.CommodityManageController;
import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.CommodityList;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.table.CommodityDeleteCellRender;
import com.nju.warehouse.view.table.CommodityModifyCellRender;

@SuppressWarnings("serial")
public class CommodityManageView extends JPanel implements Observer{
	private JTable commodity_table = null;
	private JButton add_btn = null;
	private JTextField search_textField = null;
	private JButton search_btn = null;
	
	private CommodityList commodityList = null;
	
	public static final String[] tableHeads = {"名称","型号", "数量", "默认进价","默认售价","最近进价","最近售价","删除","修改"};
	private Object[][] object = null;
	
	public CommodityManageView() {		
		LogUtil.getInstance().log(Level.INFO, "CommodityManageView --- contruction");
		
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
		
		commodity_table = new JTable();
		scrollPane.setViewportView(commodity_table);
		
		updateTabel(null);
		
		
		
		add_btn = new JButton("添加");
		add_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(add_btn);
		
		this.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		LogUtil.getInstance().log(Level.INFO, "CommodityManageView---update");
		
		if(arg == null) {
			JOptionPane.showMessageDialog(this, "您搜索的商品不存在，请重新搜索", "搜索商品提示", JOptionPane.ERROR_MESSAGE);
		} else {
			ArrayList<Commodity> list = (ArrayList<Commodity>)arg;
			
			if(list.size() == 0) {
				JOptionPane.showMessageDialog(this, "您搜索的商品不存在，请重新搜索", "搜索商品提示", JOptionPane.WARNING_MESSAGE);
			} else {
				updateTabel(list);
			}
			

		}
	}
	
	private void updateTabel(ArrayList<Commodity> commodityList) {
		if(commodityList != null) {
			int size = commodityList.size();
			object = new Object[size][9];
			
			for(int i=0; i<size; i++) {
				object[i][0] = commodityList.get(i).getName();
				object[i][1] = commodityList.get(i).getType();
				object[i][2] = commodityList.get(i).getQuantity();
				object[i][3] = commodityList.get(i).getDefaultPurchasePrice();
				object[i][4] = commodityList.get(i).getDefaultSellingPrice();
				object[i][5] = commodityList.get(i).getLastPurchasePrice();
				object[i][6] = commodityList.get(i).getLastSellingPrice();
				object[i][7] = "Delete";
				object[i][8] = "Modify";
				
			}
						
		} else {
			object = new Object[0][9];
		}
		
		commodity_table.setModel(new DefaultTableModel(object, tableHeads));
		
		new CommodityDeleteCellRender(commodity_table, 7);
		new CommodityModifyCellRender(commodity_table, 8);
		
	}
	
	public void addListener(CommodityManageController controller) {
		
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


	public CommodityList getCommodityList() {
		return commodityList;
	}


	public void setCommodityList(CommodityList commodityList) {
		this.commodityList = commodityList;
	}
}
