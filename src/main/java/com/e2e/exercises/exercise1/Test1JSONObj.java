package com.e2e.exercises.exercise1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class Test1JSONObj {

	public String CreateTest1JSONObj() {

		String test1Input;
		JSONObject syndicate = new JSONObject();
		syndicate.put("device-code", "HZ096YKML");
		syndicate.put("device-name", "JBL T160BT Bluetooth Headset");
		syndicate.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");

		JSONArray colorcode = new JSONArray();
		colorcode.add("red");
		colorcode.add("black");
		colorcode.add("white");

		syndicate.put("color-codes", colorcode);

		syndicate.put("stock", "Instock");
		syndicate.put("serviceable", true);
		syndicate.put("available-quantities", 100);

		test1Input = syndicate.toString();

		try {

			String filePath = System.getProperty("user.dir") + "//src//main//resources//test1Input.json";

			System.out.println(filePath);

			FileWriter test1Inputf1 = new FileWriter(filePath);
			test1Inputf1.write(syndicate.toJSONString());
			test1Inputf1.close();
		} catch (IOException e) {
			System.out.println("Error in File Operation");

		}

		return test1Input;
	}

}
