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

import com.nju.warehouse.controller.salesman.DeleteCustomerController;
import com.nju.warehouse.view.salesman.SalesManView;

@SuppressWarnings("serial")
public class CustomerDeleteCellRender extends AbstractCellEditor 
	implements TableCellRenderer, TableCellEditor,ActionListener{
		
		
	JTable table;
	JButton renderButton;
	JButton editButton;
	String text= "Delete";
	
	
	public CustomerDeleteCellRender(JTable table, int column)
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
	   
    	String name = table.getValueAt(table.getSelectedRow(), 0).toString();

    	new DeleteCustomerController(SalesManView.getCustomerManageView(), name);
    	JOptionPane.showMessageDialog(table, "删除成功！","删除提示",JOptionPane.CLOSED_OPTION);
	    
	   
	}
}