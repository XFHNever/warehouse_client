package com.nju.warehouse.view.accountant;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nju.warehouse.model.Account;
import com.nju.warehouse.util.ConstantsUtil;

@SuppressWarnings("serial")
public class AccountAllView extends JPanel implements Observer{
	private JLabel total_label;
	private JLabel receive_label;
	private JLabel pay_label;
	private JButton init_btn;
	
	
	
	public AccountAllView() {
		initView();
	}
	
	private void initView() {
		this.setSize(3*ConstantsUtil.FRAME_WIDTH/4, 9*ConstantsUtil.FRAME_HEIGHT/10);
		this.setLayout(null);
		
		JPanel inner = new JPanel();
		inner.setLayout(new GridLayout(3, 2, 10, 20));
		inner.setBounds(100, 100, 200, 200);
		
		inner.add(new JLabel("总额",JLabel.CENTER));
		
		total_label = new JLabel();
		inner.add(total_label);
		
		inner.add(new JLabel("应收金额",JLabel.CENTER));
		
		receive_label = new JLabel();
		inner.add(receive_label);
		
		inner.add(new JLabel("应付金额",JLabel.CENTER));
		
		pay_label = new JLabel();
		inner.add(pay_label);
		
		this.add(inner);
		
		init_btn = new JButton("初始化");
		init_btn.setBounds(150, 9*ConstantsUtil.FRAME_HEIGHT/10 - 130, 100, 40);
		this.add(init_btn);
		
		this.updateUI();
	}
	@Override
	public void update(Observable o, Object arg) {
		Account account = (Account)arg;
		if(account != null){
			total_label.setText(account.getTotal() + "");
			receive_label.setText(account.getReceiveMoney() + "");
			pay_label.setText(account.getPayMoney() + "");
		}		
	}

	public void addListener(ActionListener listener) {
		init_btn.addActionListener(listener);
	}
	
	
}
