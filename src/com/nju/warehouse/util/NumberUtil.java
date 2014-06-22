package com.nju.warehouse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
	
	 /**
	  * �ж��ַ����Ƿ��Ǹ�����
	  */
	 public static boolean isDouble(String value) {
		  try {
			  Double.parseDouble(value);
			  if (value.contains("."))
				  return true;
			  return false;
		  } catch (NumberFormatException e) {
			  return false;
		  }
	 }
	 
	 /**
	  * �ж��ַ����Ƿ�������
	  */
	 public static boolean isInteger(String value) {
		  try {
			  Integer.parseInt(value);
			  return true;
		  } catch (NumberFormatException e) {
			  return false;
		  }
	 }
	 
	 /**
	  * �ж��ַ����Ƿ�������
	  */
	 public static boolean isNumber(String value) {
		 return isInteger(value) || isDouble(value);
	 }
	 
	 public static boolean isPhone(String phone) {
		 Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		 Matcher m = p.matcher(phone);
		 return m.matches();
	 }
}
