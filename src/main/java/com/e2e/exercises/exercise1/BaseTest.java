package com.e2e.exercises.exercise1;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

	public static class stockResponse {
		@JsonProperty("device-code")
		private String deviceCode;

		@JsonProperty("device-name")
		private String deviceName;

		@JsonProperty("device-info")
		private String deviceInfo;

		@JsonProperty("color-codes")
		private List color = new ArrayList<>();

		private String stock;

		private boolean serviceable;

		@JsonProperty("available-quantities")
		private int qty;

		public String getDeviceCode() {
			return deviceCode;
		}

		public void setDeviceCode(String deviceCode) {
			this.deviceCode = deviceCode;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public List getColor() {
			return color;
		}

		public void setColor(List color) {
			this.color = color;
		}

		public String getDeviceInfo() {
			return deviceInfo;
		}

		public void setDeviceInfo(String deviceInfo) {
			this.deviceInfo = deviceInfo;
		}

		public String getStock() {
			return stock;
		}

		public void setStock(String stock) {
			this.stock = stock;
		}

		public boolean isServiceable() {
			return serviceable;
		}

		public void setServiceable(boolean serviceable) {
			this.serviceable = serviceable;
		}

		public int getQty() {
			return qty;
		}

		public void setQty(int qty) {
			this.qty = qty;
		}

		@Override
		public String toString() {
			return "[device-code=" + deviceCode + ", device-name=" + deviceName + ", " + "device-info=" + deviceInfo
					+ "," + "color-codes=" + color + "," + "stock=" + stock + "," + "serviceable=" + serviceable + ","
					+ "available-quantities=" + qty + "]";
		}
	}

	@Test
	public void test1() {

		JSONObject obj = new JSONObject();
		obj.put("device-code", "HZ096YKML");
		obj.put("device-name", "JBL T160BT Bluetooth Headset");
		obj.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");

		JSONArray arr = new JSONArray();
		arr.add("red");
		arr.add("black");
		arr.add("white");

		obj.put("color-codes", arr);
		obj.put("stock", "Instock");
		obj.put("serviceable", true);
		obj.put("available-quantities", 100);
		System.out.println(obj);

		try {

			FileWriter f1 = new FileWriter(
					"/Users/sds-r.ashok/automation/local/exercices/e2e-exercise/src/main/resources/write.json");
			f1.write(obj.toJSONString());
			f1.close();
		} catch (IOException e) {
			System.out.println("Error.");

		}

		SoftAssert softAssert = new SoftAssert();

		softAssert.assertEquals(arr.get(0), "red", "Color is NOT RED");
		softAssert.assertTrue(arr.contains("black"), "Color is Not as Expected");
		softAssert.assertNotEquals(obj.getAsString("device-name"), "JL T160BT Bluetooth Headset");
		softAssert.assertEquals(obj.getAsString("device-info"), "JBL T160BT Bluetooth Headset - Black, In the Ear");
		softAssert.assertEquals(obj.containsKey("stock"), true);
		softAssert.assertEquals(obj.getAsNumber("available-quantities"), 100);

		softAssert.assertAll();

	}

	@Test
	public void test2() {

		// Object Mapper from Jackson Library
		ObjectMapper mapper = new ObjectMapper();
		stockResponse s = new stockResponse();
		try {
			// Read file and convert JSON to JAVA object
			s = mapper.readValue(
					new File(
							"/Users/sds-r.ashok/automation/local/exercices/e2e-exercise/src/main/resources/write.json"),
					stockResponse.class);
			System.out.println(s);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Assertions
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(s.qty, 100, "qty is NOT 100");
		softAssert.assertTrue(s.serviceable, "Serviceable is false");
		softAssert.assertEquals(s.isServiceable(), true, "Serviceable is false");
		softAssert.assertNotNull(s.color, "Color object is Empty");
		softAssert.assertEquals(s.color.get(0), "red");
		softAssert.assertAll();
	}

}
