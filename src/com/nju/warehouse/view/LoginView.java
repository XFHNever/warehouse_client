package com.nju.warehouse.view;

import java.awt.GridLayout;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.nju.warehouse.controller.LoginController;
import com.nju.warehouse.util.BeautyEyeUtil;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;

@SuppressWarnings("serial")
public class LoginView extends JPanel{
	private JPanel content_panel;
	private JButton ok_btn ;
	private JButton cancel_btn;
	private JLabel name_label,password_label,typeLabel;
	private JTextField name_textField;
	private JPasswordField password_textField;
	private JComboBox type_comboBox;
	
	public LoginView() {
		initView();
	}
	
	public void initView() {
		this.setLayout(null);
		
		content_panel = new JPanel();
		content_panel.setBorder(new TitledBorder("Login"));
		content_panel.setSize(ConstantsUtil.FRAME_WIDTH/3, ConstantsUtil.FRAME_HEIGHT/3);
		content_panel.setLocation(ConstantsUtil.FRAME_WIDTH/4,ConstantsUtil.FRAME_HEIGHT/4);
		content_panel.setLayout(new GridLayout(4, 2,40,20));
		
		name_label = new JLabel("用户名",JLabel.CENTER);
		content_panel.add(name_label);
		
		name_textField = new JTextField();
		content_panel.add(name_textField);
		
		password_label = new JLabel("密      码",JLabel.CENTER);
		content_panel.add(password_label);
		
		password_textField = new JPasswordField();
		content_panel.add(password_textField);
		
		typeLabel = new JLabel("用户类型",JLabel.CENTER);
		content_panel.add(typeLabel);
		
		type_comboBox = new JComboBox(ConstantsUtil.user_types);
		content_panel.add(type_comboBox);
		
		ok_btn = new JButton("Login");
		content_panel.add(ok_btn);
		
		cancel_btn = new JButton("Cancel");
		content_panel.add(cancel_btn);
		
		this.add(content_panel);
		
		LogUtil.getInstance().log(Level.INFO, "LoginView construction is invoked");
	}
	
	public JButton getOk_btn() {
		return ok_btn;
	}

	public JButton getCancel_btn() {
		return cancel_btn;
	}

	public JTextField getName_textField() {
		return name_textField;
	}

	public JPasswordField getPassword_textField() {
		return password_textField;
	}

	public JComboBox getType_comboBox() {
		return type_comboBox;
	}

	public void addActionListener(LoginController controller) {
		ok_btn.addActionListener(controller);
		cancel_btn.addActionListener(controller);
		LogUtil.getInstance().log(Level.INFO, "LoginView addActionListener is invoked");
	}
	
	public void clear() {
		name_textField.setText("");
		password_textField.setText("");
		type_comboBox.setSelectedItem(ConstantsUtil.user_types[0]);
	}
}
