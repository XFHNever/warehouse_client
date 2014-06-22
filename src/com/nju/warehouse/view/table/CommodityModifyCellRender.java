package com.nju.warehouse.view.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.nju.warehouse.controller.administrator.ModifyCommodityController;
import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.model.administrator.ModifyCommodity;
import com.nju.warehouse.view.administratot.ModifyCommodityView;

@SuppressWarnings("serial")
public class CommodityModifyCellRender  extends AbstractCellEditor 
	implements TableCellRenderer, TableCellEditor,ActionListener{

	JTable table;
	JButton renderButton;
	JButton editButton;
	String text= "Modify";
	
	
	public CommodityModifyCellRender(JTable table, int column)
	{
	    super();
	    this.table = table;
	    renderButton = new JButton("°´Å¥1");
	
	    editButton = new JButton("°´Å¥2");
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
	
	    renderButton.setText( (value == null) ? "" : "Modify" );
	    return renderButton;
	}
	
	public Component getTableCellEditorComponent(
	    JTable table, Object value, boolean isSelected, int row, int column)
	{
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
	    
	    String name = table.getValueAt(table.getSelectedRow(), 0).toString();
	    String type = table.getValueAt(table.getSelectedRow(), 1).toString();
	    Integer quantity = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 2).toString());
	    Double defaultPurchasePrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 3).toString());
	    Double defaultSellingPrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 4).toString());
	    Double lastPurchasePrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 5).toString());
	    Double lastSellingPrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 6).toString());
	    
	    Commodity commodity = new Commodity(name, type, quantity, defaultPurchasePrice, defaultSellingPrice, lastPurchasePrice, lastSellingPrice);
	    
	    new ModifyCommodityController(new ModifyCommodityView(commodity), new ModifyCommodity(commodity));
	}

}
