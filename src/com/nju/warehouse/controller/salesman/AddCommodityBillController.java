package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.model.salesman.AddCommodityBill;
import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.result.CommodityBillType;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.salesman.AddCommodityBillView;
import com.nju.warehouse.view.salesman.SalesManView;

public class AddCommodityBillController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AddCommodityBillView view = null;
	private AddCommodityBill model = null;
	
	
	public AddCommodityBillController(AddCommodityBillView view, AddCommodityBill addCommodityBill) {
		LogUtil.getInstance().log(Level.INFO, "AddCommodityBillController---constructor");
		
		this.view = view;
		this.model = addCommodityBill;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			AddResult result = null;
			CommodityBill bill = null;
			
			String customer = view.getCustomer();
			String name = view.getName();
			String type = view.getType();
			String qunatity = view.getQuantity();
			String price = view.getPrice();
			
			
			if(customer.length() == 0 || name.length() == 0 || type.length() ==0 || 
					qunatity.length() == 0 || price.length() == 0) {
				result = AddResult.信息没有填写完全;
			} else {
				if(!NumberUtil.isNumber(qunatity) || !NumberUtil.isNumber(price)) {
					result = AddResult.添加失败价格请输入数字;
				} else {
					Calendar d = Calendar.getInstance();
					Date nowDate = d.getTime(); 
					int amount = Integer.parseInt(qunatity);
					double p = Double.parseDouble(price);
					double total = p * amount;
					
					bill = new CommodityBill(nowDate, CommodityBillType.ADD, customer, name, type, amount, p, total,false);
					
					try {
						result = service.addNewCommodityBill(bill);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			model.add(bill, result);
			
			CommodityBillList commodityBillList = new CommodityBillList();
			commodityBillList.addObserver(SalesManView.getPurchasesManageView());
			try {
				commodityBillList.setCommodityBills((service.getAllCommofityBill()));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	public AddCommodityBillView getView() {
		return view;
	}
	
	

	
}
