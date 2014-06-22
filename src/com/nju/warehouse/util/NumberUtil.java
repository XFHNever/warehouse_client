package com.nju.warehouse.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtil {
	
	 /**
	  * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÊÇ¸¡µãÊý
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
	  * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÊÇÕûÊý
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
	  * ÅÐ¶Ï×Ö·û´®ÊÇ·ñÊÇÊý×Ö
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
