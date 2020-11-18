package com.e2e.exercises.exercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	static String filePath = System.getProperty("user.dir") + "//src//main//resources//test1Input.json";
	Path path;
	static String fromFile, jsoninput;

	@Test
	public void test1() throws IOException {

		System.out.println("FIRST TEST");

		Test1JSONObj testObj = new Test1JSONObj();

		System.out.println(testObj.CreateTest1JSONObj());

		path = Paths.get(filePath);

		fromFile = Files.readString(path);

		System.out.println(fromFile);

		Assert.assertEquals(fromFile, testObj.CreateTest1JSONObj());

	}

	@Test
	public void test2() throws FileNotFoundException {

		File file1 = new File(filePath);

		ObjectMapper mp = new ObjectMapper();
		mp.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			Test1DesData Object = mp.readValue(file1, Test1DesData.class);

			System.out.println(Object.getDevicecode());
			System.out.println(Object.getDevicename());
			System.out.println(Object.getDeviceinfo());

			System.out.println(Object.getColorlist().get(0).toString());
			System.out.println(Object.getColorlist().get(1).toString());
			System.out.println(Object.getColorlist().get(2).toString());
			System.out.println(Object.getStock());
			System.out.println(Object.isServiceable());
			System.out.println(Object.getAvailablequantitties());

			Assert.assertEquals("HZ096YKML", Object.getDevicecode());
			Assert.assertEquals("JBL T160BT Bluetooth Headset", Object.getDevicename());
			Assert.assertEquals("JBL T160BT Bluetooth Headset - Black, In the Ear", Object.getDeviceinfo());

			Assert.assertEquals("red", Object.getColorlist().get(0).toString());
			Assert.assertEquals("black", Object.getColorlist().get(1).toString());
			Assert.assertEquals("white", Object.getColorlist().get(2).toString());

			Assert.assertEquals("Instock", Object.getStock());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
