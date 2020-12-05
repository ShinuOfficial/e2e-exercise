package com.e2e.exercises.jsonperformer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;


import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import com.e2e.exercises.jsondefiner.DeviceDetails;




public class DeviceDetailsJSONWriter {

	public static void DeviceJSONWriter(String[] args) throws FileNotFoundException {

		DeviceDetails device = createDeviceDetails();
		
		//Json Builder
		JsonObjectBuilder deviceBuilder = Json.createObjectBuilder();
		//Json Array Builder
		JsonArrayBuilder colorBuilder = Json.createArrayBuilder();

		for (String color : device.getColorcodes()) {
			colorBuilder.add(color);
		}		
		deviceBuilder.add("device-code", device.getDevicecode())
					.add("device-name", device.getDevicename())
						.add("device-info", device.getDeviceinfo())
							.add("color-codes", colorBuilder)
								.add("stock", device.getStock())
									.add("serviceable", device.isServiceable())
										.add("available-quantities", device.getQuantities());
		
				
		JsonObject deviceJsonObject = deviceBuilder.build();
		
		System.out.println("Device details - JSON String to Write in file\n"+deviceJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("src/main/resources/Devices.json");
		JsonWriter jsonWriter = Json.createWriter(os);
		jsonWriter.writeObject(deviceJsonObject);
		jsonWriter.close();
		
		//Write Assertion 
		try {
			JSONAssert.assertEquals(
					"{device-code:\"HZ096YKML\",device-name:\"JBL T160BT Bluetooth Headset\",device-info:\"JBL T160BT Bluetooth Headset - Black, In the Ear\",color-codes:[\"red\",\"black\",\"white\"],serviceable:true,stock:\"Instock\",available-quantities:100}",
					 deviceJsonObject.toString(), JSONCompareMode.STRICT);
		} catch (JSONException e) {
			e.getCause();
		}
	}
	

	public static DeviceDetails createDeviceDetails() {

		DeviceDetails device = new DeviceDetails();
		device.setDevicecode("HZ096YKML");
		device.setDevicename("JBL T160BT Bluetooth Headset");
		device.setDeviceinfo("JBL T160BT Bluetooth Headset - Black, In the Ear");
		device.setColorcodes(new String[] { "red", "black", "white" });
		device.setStock("Instock");
		device.setServiceable(true);
		device.setQuantities(100);

		return device;
	}


}
