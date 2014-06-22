package com.nju.warehouse.net;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import com.nju.warehouse.model.Account;
import com.nju.warehouse.model.AccountBill;
import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.CommodityBill;
import com.nju.warehouse.model.Customer;
import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.model.Storage;
import com.nju.warehouse.model.User;
import com.nju.warehouse.result.AddCustomerResult;
import com.nju.warehouse.result.AddResult;
import com.nju.warehouse.result.DeleteResult;
import com.nju.warehouse.result.ModifyResult;

public interface IDataRemoteService extends Remote{
	public String hello(String name) throws RemoteException;
	public User login(String name, String password, String type) throws RemoteException;
	
	//Commodity
	public ArrayList<Commodity> getAllCommodity() throws RemoteException;
	public ArrayList<Commodity> getCommodityByParam(String param) throws RemoteException;
	public AddResult addNewCommodity(Commodity commodity) throws RemoteException;

	public void deleteCommodity(String name, String type) throws RemoteException;
	
	public ModifyResult modifyCommodity(Commodity commodity) throws RemoteException;
	
	//Customer
	public ArrayList<Customer> getAllCustomer() throws RemoteException;
	public ArrayList<Customer> getCustomerByParam(String param) throws RemoteException;
	public AddCustomerResult addNewCustomer(Customer customer) throws RemoteException;

	public void deleteCustomer(String name) throws RemoteException;
	
	public ModifyResult modifyCustomer(Customer customer) throws RemoteException;
	
	//Storage
	public ArrayList<Storage> getAllStorage() throws RemoteException;
	
	//CommodityBill
	public ArrayList<CommodityBill> getAllCommofityBill() throws RemoteException;
	public ArrayList<CommodityBill> getCommodityBillsByDate(Date date) throws RemoteException;
	public AddResult addNewCommodityBill(CommodityBill commodityBill) throws RemoteException;
	public DeleteResult deleteCommodityBill(CommodityBill commodityBill) throws RemoteException;
	
	//SalesBill
	public ArrayList<SalesBill> getAllSalesBill() throws RemoteException;
	public ArrayList<SalesBill> getSalesBillsByDate(Date date) throws RemoteException;
	public AddResult addNewSalesBill(SalesBill salesBill) throws RemoteException;
	public DeleteResult deleteSalesBill(SalesBill salesBill) throws RemoteException;
	
	//AccountBill
	public ArrayList<AccountBill> getAllAccountBill() throws RemoteException;
	public AddResult addNewAccountBill(AccountBill accountBill) throws RemoteException;
	//Account
	public Account getAccount() throws RemoteException;
	public void initAccount() throws RemoteException;
	
}
