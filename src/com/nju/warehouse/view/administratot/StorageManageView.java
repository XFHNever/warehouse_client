package com.nju.warehouse.view.administratot;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.nju.warehouse.model.Storage;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class StorageManageView extends JPanel implements Observer{
	private JTable storage_table = null;
	
	public static final String[] tableHeads = {"名称","型号", "进货数量", "进货平均单价","进货总价","销售数量","销售平均单价",
		"销售总价","库存数量","库存平均单价","库存总价"};
	private Object[][] object = null;
	
	public StorageManageView() {
		LogUtil.getInstance().log(Level.INFO, "StorageManageView --- contruction");
		initView();
	}
	
	
	private void initView() {
		this.setSize(3*ConstantsUtil.FRAME_WIDTH/4, 9*ConstantsUtil.FRAME_HEIGHT/10);
		this.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 3*ConstantsUtil.FRAME_WIDTH/4 -30, 9*ConstantsUtil.FRAME_HEIGHT/10 - 100);
		this.add(scrollPane);
		
		storage_table = new JTable();
		scrollPane.setViewportView(storage_table);
		
		updateTable(null);
		
		this.updateUI();
	}
	
	private void updateTable(ArrayList<Storage> storages) {
		if(storages != null) {
			int size = storages.size();
			object = new Object[size][11];
			
			for(int i=0; i<size; i++) {
				object[i][0] = storages.get(i).getName();
				object[i][1] = storages.get(i).getType();
				object[i][2] = storages.get(i).getPurchasesQuantity();
				object[i][3] = storages.get(i).getAveragePurchasesPrice();
				object[i][4] = storages.get(i).getTotalPurchasesPrice();
				object[i][5] = storages.get(i).getSaleQuantity();
				object[i][6] = storages.get(i).getAverageSaleQuantity();
				object[i][7] = storages.get(i).getTatalSaleQuantity();
				object[i][8] = storages.get(i).getStorageQuanttiy();
				object[i][9] = storages.get(i).getAverageStoragePrice();
				object[i][10] = storages.get(i).getTotalStoragePrice();
				
			}
						
		} else {
			object = new Object[0][11];
		}
		
		storage_table.setModel(new DefaultTableModel(object, tableHeads));
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Storage> storages = (ArrayList<Storage>)arg;
		
		updateTable(storages);
	}
}
