package com.nju.warehouse.controller.salesman;

import java.rmi.RemoteException;

import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.model.salesman.DeleteCommodityBill;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.DeleteResult;
import com.nju.warehouse.view.salesman.PurchasesManageView;

public class DeleteCommodityBillController {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();

	private PurchasesManageView view = null;
	private DeleteCommodityBill model = null;
	
	public DeleteCommodityBillController(PurchasesManageView view, DeleteCommodityBill deleteCommodityBill) {
		this.view = view;
		this.model = deleteCommodityBill;
		
		this.model.addObserver(this.view);	
		
		
		CommodityBillList commodityBillList = new CommodityBillList();
		try {
			
			DeleteResult result = service.deleteCommodityBill(this.model.getCommodityBill());
			
			model.delete(deleteCommodityBill.getCommodityBill(), result);
			
			commodityBillList.addObserver(this.view);
			
			commodityBillList.setCommodityBills(service.getAllCommofityBill());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


}
