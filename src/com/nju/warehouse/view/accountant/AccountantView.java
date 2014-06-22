package com.nju.warehouse.view.accountant;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.warehouse.model.User;
import com.nju.warehouse.result.Operation;
import com.nju.warehouse.util.ConstantsUtil;
import com.nju.warehouse.util.LogUtil;
import com.nju.warehouse.view.administratot.CommodityManageView;
import com.nju.warehouse.view.administratot.StorageManageView;

@SuppressWarnings("serial")
public class AccountantView extends JPanel implements Observer {
	private User user = null;
	
	private JPanel top_panel = null;
	private JPanel left_panel = null;
	private JPanel main_panel = null;
	
	private JButton all_button = null;
	private JButton detail_button = null;
	
	private JLabel type_label = null;
	private JLabel name_label = null;
	
	private static AccountAllView accountAllView = null;
	private static AccountDetailView accountDetailView = null;
	
	
	public AccountantView(User user) {
		this.user = user;
		initView();
	}
	
	private void initView() {

		this.setLayout(null);
			
		//初始化顶部
		top_panel = new JPanel();
		top_panel.setBounds(0, 0, ConstantsUtil.FRAME_WIDTH, ConstantsUtil.FRAME_HEIGHT/10);
		top_panel.setLayout(null);
		
		JLabel title_lable = new JLabel("WarehouseSystem",JLabel.CENTER);
		title_lable.setFont(new Font(null, Font.BOLD, 20));
		title_lable.setBounds(0, 0, top_panel.getWidth()/5, top_panel.getHeight());
		top_panel.add(title_lable);
		
		type_label = new JLabel(user.getType() + ":",JLabel.RIGHT);
		type_label.setFont(new Font("楷体", Font.BOLD, 15));
		type_label.setBounds(5*top_panel.getWidth()/8, 0, top_panel.getWidth()/8, top_panel.getHeight());
		top_panel.add(type_label);
		
		name_label = new JLabel(user.getName(),JLabel.LEFT);
		name_label.setFont(new Font("楷体", Font.ITALIC, 14));
		name_label.setBounds(3*top_panel.getWidth()/4, 0, top_panel.getWidth()/8, top_panel.getHeight());
		top_panel.add(name_label);
		
		this.add(top_panel);
		
		//初始化左侧
		left_panel = new JPanel();
		left_panel.setBounds(0, ConstantsUtil.FRAME_HEIGHT/10, ConstantsUtil.FRAME_WIDTH/5, 9*ConstantsUtil.FRAME_HEIGHT/10);
		left_panel.setLayout(null);
		
		all_button = new JButton("账目总览");
		all_button.setBounds(left_panel.getWidth()/20, left_panel.getHeight()/4,
				9*left_panel.getWidth()/10, left_panel.getHeight()/10);
		left_panel.add(all_button);
		
		detail_button = new JButton("账目明细");
		detail_button.setBounds(left_panel.getWidth()/20, 7*left_panel.getHeight()/16,
				9*left_panel.getWidth()/10, left_panel.getHeight()/10);
		left_panel.add(detail_button);
		
		this.add(left_panel);
		
		//初始化主要部分
		main_panel = new JPanel();
		main_panel.setBounds(ConstantsUtil.FRAME_WIDTH/5, ConstantsUtil.FRAME_HEIGHT/10, 
				4*ConstantsUtil.FRAME_WIDTH/5, 9*ConstantsUtil.FRAME_HEIGHT/10);
		main_panel.setLayout(null);
		this.add(main_panel);
		
		accountAllView = new AccountAllView();
		
		main_panel.add(accountAllView);
		this.repaint();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		Operation operation = (Operation)arg;
		
		main_panel.removeAll();
		
		if(Operation.AccountAll == operation) {
			if(getAccountAllView() == null) {
				accountAllView = new AccountAllView();	
			}
			
			main_panel.add(accountAllView);
			
			LogUtil.getInstance().log(Level.INFO, "AccountantView:change to accountAllView");
		} else {
			if(getAccountDetailView() == null) {
				accountDetailView = new AccountDetailView();
			}
			
			main_panel.add(accountDetailView);
			
			LogUtil.getInstance().log(Level.INFO, "AccountantView:change to accountDetailView");
		}
		
		main_panel.repaint();
		
	}

	public JPanel getLeft_panel() {
		return left_panel;
	}

	public JPanel getMain_panel() {
		return main_panel;
	}

	public JButton getAll_button() {
		return all_button;
	}

	public void setAll_button(JButton all_button) {
		this.all_button = all_button;
	}

	public JButton getDetail_button() {
		return detail_button;
	}

	public void setDetail_button(JButton detail_button) {
		this.detail_button = detail_button;
	}

	public static AccountAllView getAccountAllView() {
		return accountAllView;
	}

	public static void setAccountAllView(AccountAllView accountAllView) {
		AccountantView.accountAllView = accountAllView;
	}

	public static AccountDetailView getAccountDetailView() {
		return accountDetailView;
	}

	public static void setAccountDetailView(AccountDetailView accountDetailView) {
		AccountantView.accountDetailView = accountDetailView;
	}



	public void addActionListener(ActionListener listener) {
		this.all_button.addActionListener(listener);
		this.detail_button.addActionListener(listener);
	}
}
