package com.e2e.exercises.exercise1;

import java.io.FileNotFoundException;
import java.io.IOException;


import org.testng.annotations.Test;

import com.e2e.exercises.jsonperformer.DeviceDetailsJSONReader;
import com.e2e.exercises.jsonperformer.DeviceDetailsJSONWriter;



public class BaseTest {

	@Test
	public void test1() throws FileNotFoundException {
		
		//Calling Json Writer Class & Its Assertion
		DeviceDetailsJSONWriter.DeviceJSONWriter(null);
		
	}
	
	@Test(dependsOnMethods = "test1")
	public void test2() throws IOException {
		
		//Calling Json Reader Class & Its Assertion
		DeviceDetailsJSONReader.DeviceJSONReader(null);
		
	}
}
