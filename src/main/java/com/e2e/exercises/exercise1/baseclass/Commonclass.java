package com.e2e.exercises.exercise1.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class Commonclass {
	
	public static void writeFile(String filepath, String data) {
		File f = null;
		FileWriter fo=null;
        try {
        	f= new File("/Users/sds-shahul.h01/e2e-exercise/src/main/resourse");
        	f.mkdirs();
        	if (f.exists()) {
        		//f= new File("/Users/sds-shahul.h01/e2e-exercise/src/main/resourse/mytext.txt");
        		f= new File(filepath);
				f.createNewFile();
				fo = new FileWriter(f, true);
				fo.write(data);
				fo.close();
			}
            
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@SuppressWarnings("deprecation")
	public static String readFile(String filepath) {
		File f = new File(filepath);
		String readFileToString = null;
		try {
			readFileToString = FileUtils.readFileToString(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return readFileToString;
	}	

	public static String tostring(JSONObject jobject) {
		String string = jobject.toString();
		return string;
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
	
}
