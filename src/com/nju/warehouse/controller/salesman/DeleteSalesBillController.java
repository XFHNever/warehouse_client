package com.nju.warehouse.controller.salesman;

import java.rmi.RemoteException;

import com.nju.warehouse.model.salesman.CommodityBillList;
import com.nju.warehouse.model.salesman.DeleteCommodityBill;
import com.nju.warehouse.model.salesman.DeleteSalesBill;
import com.nju.warehouse.model.salesman.SalesBillList;
import com.nju.warehouse.net.IDataRemoteService;
import com.nju.warehouse.net.LaunchClient;
import com.nju.warehouse.result.DeleteResult;
import com.nju.warehouse.view.salesman.PurchasesManageView;
import com.nju.warehouse.view.salesman.SalesManageView;

public class DeleteSalesBillController {
	private IDataRemoteService service = LaunchClient.getDatabaseFactory();

	private SalesManageView view = null;
	private DeleteSalesBill model = null;
	
	public DeleteSalesBillController(SalesManageView view, DeleteSalesBill deleteSalesBill) {
		this.view = view;
		this.model = deleteSalesBill;
		
		this.model.addObserver(this.view);	
		
		
		SalesBillList salesBillList = new SalesBillList();
		try {
			
			DeleteResult result = service.deleteSalesBill(this.model.getSalesBill());
			
			model.delete(deleteSalesBill.getSalesBill(), result);
			
			salesBillList.addObserver(this.view);
			
			salesBillList.setList(service.getAllSalesBill());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}


}

