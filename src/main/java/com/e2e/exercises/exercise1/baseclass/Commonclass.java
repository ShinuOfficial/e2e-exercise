package com.e2e.exercises.exercise1.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;


import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class Commonclass {
	
	public static void writeFile(String filepath, String data) {
		File f = null;
		FileWriter fo=null;
        try {
        	f= new File("/Users/sds-shahul.h01/e2e-exercise/src/main/resourse");
        	f.mkdirs();
        	if (f.exists()) {
        		f= new File(filepath);
				f.createNewFile();
				fo = new FileWriter(f);
				fo.write(data);
				fo.close();
			}
            
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static JSONObject readFile(String filepath) throws IOException, ParseException {
		File f = new File(filepath);
		 JSONObject jsonObject = null;
		try {
			FileReader reader = new FileReader(f);
			 JSONParser parser = new JSONParser();
	            jsonObject = (JSONObject) parser.parse(reader);
	            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
		
	}	

	public static String tostring(JSONObject jobject) {
		return jobject.toJSONString();
		
	}
	
	public static String propReader(String data) throws Exception {
	    FileReader reader = null;
		try {
			reader = new FileReader("pathfile.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	      
	    Properties p=new Properties();  
	    p.load(reader);
	    String pData = p.getProperty(data);
		return pData;  
	}
	
	public static JSONObject jsonObject() {
		JSONObject jobject = new JSONObject();
		jobject.put("device-code", "HZ096YKML");
		jobject.put("device-name", "JBL T160BT Bluetooth Headset");
		jobject.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");
		JSONArray jArrColorCodes = new JSONArray();
		jArrColorCodes.add("red");
		jArrColorCodes.add("black");
		jArrColorCodes.add("white");
		jobject.put("color-codes", jArrColorCodes);
		jobject.put("stock", "Instock");
		jobject.put("serviceable", true);
		jobject.put("available-quantities", 100);
		return jobject;
	}
	
}
