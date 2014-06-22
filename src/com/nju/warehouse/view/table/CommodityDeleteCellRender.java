package com.nju.warehouse.view.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import com.nju.warehouse.controller.administrator.DeleteCommodityController;
import com.nju.warehouse.model.Commodity;
import com.nju.warehouse.view.administratot.AdministratorView;

@SuppressWarnings("serial")
public class CommodityDeleteCellRender  extends AbstractCellEditor 
	implements TableCellRenderer, TableCellEditor,ActionListener{
	
	
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text= "Delete";
	
	
	public CommodityDeleteCellRender(JTable table, int column)
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
	
	    renderButton.setText( (value == null) ? "" : "Delete" );
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
	    
	    Double purchasePrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 5).toString());
	    Double sellingPrice = Double.parseDouble(table.getValueAt(table.getSelectedRow(), 6).toString());
	    
	    if(purchasePrice != 0 || sellingPrice != 0) {
	    	JOptionPane.showMessageDialog(table, "本商品已经进过货或者销售过，不可删除！","删除提示",JOptionPane.ERROR_MESSAGE);
	    } else {
	    	String name = table.getValueAt(table.getSelectedRow(), 0).toString();
	    	String type = table.getValueAt(table.getSelectedRow(), 1).toString();
	    	new DeleteCommodityController(AdministratorView.getCommodityManageView(), name, type);
	    	JOptionPane.showMessageDialog(table, "删除成功！","删除提示",JOptionPane.CLOSED_OPTION);
	    }
	   
	}

}
