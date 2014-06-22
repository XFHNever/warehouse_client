package com.nju.warehouse.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogUtil {
	private static LogUtil logUtil;
	private static Logger logger;
	
	private LogUtil() {
		try {
			initLogger();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Logger getInstance() {
		if(logUtil == null) {
			logUtil = new LogUtil();
		}
		
		return logger;
	}
	
	private void initLogger() throws SecurityException, IOException {
		logger = Logger.getLogger("warehouse_client.log");
		FileHandler handler = new FileHandler("E:/code/ebay/warehouse_client/warehouse.log",true);
		logger.addHandler(handler);
		logger.setLevel(Level.ALL);
		SimpleFormatter formatter = new SimpleFormatter();
		handler.setFormatter(formatter);
	}
}
