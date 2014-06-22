package com.nju.warehouse.util;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

public class BeautyEyeUtil {
	/**
	 * ����BeautyEye������á�
	 */
	public static void launch() {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			//�������ð�ť
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ���崰�ڱ߿����͡�
	 * @param style �߿���ʽ������:ϵͳĬ�ϱ߿�(osLookAndFeelDecorated)��
	 * ǿ����а�͸���߿�(translucencyAppleLike)��������а�͸���߿�(translucencySmallShadow)��
	 * ��ͨ��͸���߿�(generalNoTranslucencyshadow)
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
	 * ����Ĭ�ϰ�ť
	 * @param button JButton����
	 */
	public static void setNormalButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.normal));
	}
	
	/**
	 * ������ɫ��ť
	 * @param button JButton����
	 */
	public static void setGreenButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.green));
	}
	
	/**
	 * ������ɫ��ť
	 * @param button JButton����
	 */
	public static void setBlueButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.blue));
	}
	
	/**
	 * ���õ���ɫ��ť
	 * @param button JButton����
	 */
	public static void setLightBlueButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.lightBlue));
	}

	/**
	 * ���ú�ɫ��ť
	 * @param button JButton����
	 */
	public static void setRedButton(JButton button) {
		button.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
	}
}
