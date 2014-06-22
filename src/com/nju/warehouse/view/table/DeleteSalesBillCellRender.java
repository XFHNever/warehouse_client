package com.nju.warehouse.view.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.nju.warehouse.controller.salesman.DeleteSalesBillController;
import com.nju.warehouse.model.SalesBill;
import com.nju.warehouse.model.salesman.DeleteSalesBill;
import com.nju.warehouse.result.CommodityBillType;
import com.nju.warehouse.result.SalesBillType;
import com.nju.warehouse.view.salesman.SalesManView;

@SuppressWarnings("serial")
public class DeleteSalesBillCellRender  extends AbstractCellEditor 
	implements TableCellRenderer, TableCellEditor,ActionListener{
		
		
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text= "退货";
	
	
	public DeleteSalesBillCellRender(JTable table, int column)
	{
	    super();
	    this.table = table;
	    renderButton = new JButton("按钮1");
	
	    editButton = new JButton("按钮2");
	    editButton.setFocusPainted( false );
	    editButton.addActionListener( this );
	
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(column).setCellRenderer( this );
	    columnModel.getColumn(column).setCellEditor( this );
	    renderButton.addActionListener(this);
	}
	
	public Component getTableCellRendererComponent(
	    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if(CommodityBillType.valueOf(table.getValueAt(row, 1).toString()) == CommodityBillType.DEL 
				|| Boolean.parseBoolean(table.getValueAt(row, 8).toString())) {
			renderButton.setVisible(false);
			return null;
		}
		
	    if (hasFocus)
	    {
	        renderButton.setForeground(table.getForeground());
	        renderButton.setBackground(UIManager.getColor("Button.background"));
	    }
	    else if (isSelected)
	    {
	        renderButton.setForeground(table.getSelectionForeground());
	        renderButton.setBackground(table.getSelectionBackground());
	    }
	    else
	    {
	        renderButton.setForeground(table.getForeground());
	        renderButton.setBackground(UIManager.getColor("Button.background"));
	    }
	
	    renderButton.setText( (value == null) ? "" : "退货" );
	    return renderButton;
	}
	
	public Component getTableCellEditorComponent(
	    JTable table, Object value, boolean isSelected, int row, int column)
	{
		if(CommodityBillType.valueOf(table.getValueAt(row, 1).toString()) == CommodityBillType.DEL
				|| Boolean.parseBoolean(table.getValueAt(row, 8).toString())) {
			editButton.setVisible(false);
			return null;
		}
		
	    text = (value == null) ? "" : value.toString();
	    editButton.setText( text );
	    return editButton;
	}
	
	public Object getCellEditorValue()
	{
	    return text;
	}
	
	public void actionPerformed(ActionEvent e)
	{
	    fireEditingStopped();
	    
	    CommodityBillType type = CommodityBillType.valueOf(table.getValueAt(table.getSelectedRow(), 1).toString());
	    
	    if(type == CommodityBillType.DEL) {
	    	JOptionPane.showMessageDialog(table, "这是销售退货单。。乃还想退给谁！","销售退货提示",JOptionPane.ERROR_MESSAGE);
	    } else if(Boolean.parseBoolean(table.getValueAt(table.getSelectedRow(), 8).toString())) {
	    	JOptionPane.showMessageDialog(table, "已经退过了。。不要再退了！","销售退货提示",JOptionPane.ERROR_MESSAGE);
		}else{
	    	String dateStr = table.getValueAt(table.getSelectedRow(), 0).toString();
	    	Date date = null;
	    	try {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
	    	String customer = table.getValueAt(table.getSelectedRow(), 2).toString().trim();
	    	String name = table.getValueAt(table.getSelectedRow(), 3).toString();
	    	String CommodityType = table.getValueAt(table.getSelectedRow(), 4).toString();
	    	int amount = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
	    	double price = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 6).toString());
	    	double total = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 7).toString());
	    
	    	SalesBill bill = new SalesBill(date, SalesBillType.DEL, customer, name, CommodityType, amount, price, total,false);
	    	
	    	new DeleteSalesBillController(SalesManView.getSalesManageView(), new DeleteSalesBill(bill));
	    	
	    	
	    	
	    }
	   
	}

}
