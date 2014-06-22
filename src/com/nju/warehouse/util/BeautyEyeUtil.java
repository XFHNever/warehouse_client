package com.nju.warehouse.util;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class BeautyEyeUtil {
	/**
	 * 启动BeautyEye外观设置。
	 */
	public static void launch() {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			//隐藏设置按钮
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 定义窗口边框类型。
	 * @param style 边框样式，包含:系统默认边框(osLookAndFeelDecorated)、
	 * 强立体感半透明边框(translucencyAppleLike)、弱立体感半透明边框(translucencySmallShadow)、
	 * 普通不透明边框(generalNoTranslucencyshadow)
	 */
	public static void editFrameBorder(FrameBorderStyle style) {
		BeautyEyeLNFHelper.frameBorderStyle = style;
	}
	
	
	public static void setUIManager() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置默认按钮
	 * @param button JButton对象
	 */
	public static void setNormalButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
	}
	
	/**
	 * 设置绿色按钮
	 * @param button JButton对象
	 */
	public static void setGreenButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
	}
	
	/**
	 * 设置蓝色按钮
	 * @param button JButton对象
	 */
	public static void setBlueButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
	}
	
	/**
	 * 设置淡蓝色按钮
	 * @param button JButton对象
	 */
	public static void setLightBlueButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
	}

	/**
	 * 设置红色按钮
	 * @param button JButton对象
	 */
	public static void setRedButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
	}
}
