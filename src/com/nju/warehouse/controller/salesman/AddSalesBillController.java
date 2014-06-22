package com.nju.warehouse.controller.salesman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.model.salesman.AddSalesBill;
import com.nju.warehouse.model.salesman.SalesBillList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.result.SalesBillType;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.util.NumberUtil;
import com.nju.warehouse.view.salesman.AddSalesBillView;
import com.nju.warehouse.view.salesman.SalesManView;

public class AddSalesBillController implements ActionListener{
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();
	
	private AddSalesBillView view = null;
	private AddSalesBill model = null;
	
	
	public AddSalesBillController(AddSalesBillView view, AddSalesBill addBill) {
		LogUtil.getInstance().log(Level.INFO, "AddSalesBillController---constructor");
		
		this.view = view;
		this.model = addBill;
		model.addObserver(view);
		this.view.addListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == view.getCancel_btn()) {
			model.complete();
		} else {
			AddResult result = null;
			SalesBill bill = null;
			
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
					
					bill = new SalesBill(nowDate, SalesBillType.ADD, customer, name, type, amount, p, total,false);
					
					try {
						result = service.addNewSalesBill(bill);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
			}
			
			model.add(bill, result);
			
			SalesBillList salesBillList = new SalesBillList();
			salesBillList.addObserver(SalesManView.getSalesManageView());
			try {
				salesBillList.setList((service.getAllSalesBill()));
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		

	}
	
	public AddSalesBillView getView() {
		return view;
	}
	
	

	
}
