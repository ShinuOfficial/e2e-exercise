package com.e2e.exercises.jsonperformer;

import static org.testng.AssertJUnit.assertArrayEquals;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import com.e2e.exercises.jsondefiner.DeviceDetails;


public class DeviceDetailsJSONReader {

	public static final String JSON_FILE="src/main/resources/Devices.json";
	
	public static void DeviceJSONReader(String[] args) throws IOException {
		InputStream fis = new FileInputStream(JSON_FILE);
		
		//create JsonReader object
		JsonReader jsonReader = Json.createReader(fis);

		//get JsonObject from JsonReader
		JsonObject jsonObject = jsonReader.readObject();
		
		//Closing JsonReader
		jsonReader.close();
		fis.close();
		
		//Retrieve data from JsonObject and create Device bean
		DeviceDetails device = new DeviceDetails();
		
		device.setDevicecode(jsonObject.getString("device-code"));
		device.setDevicename(jsonObject.getString("device-name"));
		device.setDeviceinfo(jsonObject.getString("device-info"));
		
		//reading arrays from json
		JsonArray jsonArray = jsonObject.getJsonArray("color-codes");
		
		String[] color = new String[jsonArray.size()];
		int index = 0;
		for(JsonValue value :jsonArray){
			color[index++] = value.toString();
		}
		device.setColorcodes(color);
		device.setStock(jsonObject.getString("stock"));
		device.setServiceable(jsonObject.getBoolean("serviceable"));
		device.setQuantities(jsonObject.getInt("available-quantities"));
		//print device bean information
		System.out.println("Device details - JSON String read from the file"+device);
		
		//Read Assertion 
		assertTrue(device.getDevicecode().matches("^[A-Z0-9]{0,9}$"));
		assertTrue(device.getDevicename().matches("JBL T160BT Bluetooth Headset"));
		assertTrue(device.getDeviceinfo().matches("JBL T160BT Bluetooth Headset - Black, In the Ear"));
		assertTrue(device.getStock().matches("Instock"));
		assertTrue(device.isServiceable());
		assertEquals(100,device.getQuantities());
		assertEquals(3, jsonArray.size());
		assertArrayEquals(device.getColorcodes(),color);
	}

}
