package com.nju.warehouse.view.administratot;

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

@SuppressWarnings("serial")
public class AdministratorView extends JPanel implements Observer {
	private User user = null;
	
	private JPanel top_panel = null;
	private JPanel left_panel = null;
	private JPanel main_panel = null;
	
	private JButton commodiy_button = null;
	private JButton storage_button = null;
	
	private JLabel type_label = null;
	private JLabel name_label = null;
	
	
	private static CommodityManageView commodityManageView = null;
	private static StorageManageView storageManageView = null;
	
	
	public AdministratorView(User user) {
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
		
		commodiy_button = new JButton("商品管理");
		commodiy_button.setBounds(left_panel.getWidth()/20, left_panel.getHeight()/4,
				9*left_panel.getWidth()/10, left_panel.getHeight()/10);
		left_panel.add(commodiy_button);
		
		storage_button = new JButton("库存管理");
		storage_button.setBounds(left_panel.getWidth()/20, 7*left_panel.getHeight()/16,
				9*left_panel.getWidth()/10, left_panel.getHeight()/10);
		left_panel.add(storage_button);
		
		this.add(left_panel);
		
		//初始化主要部分
		main_panel = new JPanel();
		main_panel.setBounds(ConstantsUtil.FRAME_WIDTH/5, ConstantsUtil.FRAME_HEIGHT/10, 
				4*ConstantsUtil.FRAME_WIDTH/5, 9*ConstantsUtil.FRAME_HEIGHT/10);
		main_panel.setLayout(null);
		this.add(main_panel);
		
		commodityManageView = new CommodityManageView();
		
		main_panel.add(commodityManageView);
		this.repaint();
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		Operation operation = (Operation)arg;
		
		main_panel.removeAll();
		
		if (Operation.CommodityManage == operation) {
			if(getCommodityManageView() == null) {
				commodityManageView = new CommodityManageView();	
			}
			
			main_panel.add(commodityManageView);
			
			LogUtil.getInstance().log(Level.INFO, "AdministratorView:change to commodityManageView");
		} else {
			if(getStorageManageView() == null) {
				storageManageView = new StorageManageView();
			}
			
			main_panel.add(storageManageView);
			
			LogUtil.getInstance().log(Level.INFO, "AdministratorView:change to storageManageView");
		}
		
		main_panel.repaint();
		
	}

	public JPanel getLeft_panel() {
		return left_panel;
	}

	public JPanel getMain_panel() {
		return main_panel;
	}

	public JButton getCommodiy_button() {
		return commodiy_button;
	}

	public JButton getStorage_button() {
		return storage_button;
	}

	public JLabel getType_label() {
		return type_label;
	}

	public JLabel getName_label() {
		return name_label;
	}

	public static CommodityManageView getCommodityManageView() {
		return commodityManageView;
	}

	public static StorageManageView getStorageManageView() {
		return storageManageView;
	}
	
	
	@SuppressWarnings("static-access")
	public void setCommodityManageView(CommodityManageView commodityManageView) {
		this.commodityManageView = commodityManageView;
	}

	@SuppressWarnings("static-access")
	public void setStorageManageView(StorageManageView storageManageView) {
		this.storageManageView = storageManageView;
	}

	public void addActionListener(ActionListener listener) {
		this.commodiy_button.addActionListener(listener);
		this.storage_button.addActionListener(listener);
	}
	
}
