package com.nju.warehouse.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;


public class FileUtil {
	public static void saveToFile(String filePath, Object object) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
			out.writeObject(object);
			out.close();
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object readFromFile(String filePath) {
		FileInputStream fileInputStream = null;
		Object result = null;
		
		try {
			fileInputStream = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileInputStream);
			result = in.readObject();
			in.close();
			fileInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		LogUtil.getInstance().log(Level.INFO, "readFromFile from" + filePath);
		return result;
	}
	
	
	public static void commonWrite(String filePath, String object) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filePath);
			
			fileOutputStream.write(object.getBytes("utf-8"));
		
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String ReadIP(String filePath) {
		String temp=null;
	    StringBuffer sb=new StringBuffer();
		
		File file=new File(filePath);
        
        BufferedReader br;
		try {
			if(!file.exists()||file.isDirectory())
	            throw new FileNotFoundException();
			
			br = new BufferedReader(new FileReader(file));
			
			 temp=br.readLine();
			 while(temp!=null){
	            sb.append(temp+" ");
	            temp=br.readLine();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
      
       
        return sb.toString().split("/")[1];
	}

}
