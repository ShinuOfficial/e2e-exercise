package com.e2e.exercises.exercise1;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestDeserialized {
	
@JsonProperty("device-code")
private String devicecode;

@JsonProperty("device-name")
private String devicename;

@JsonProperty("device-info")
private String deviceinfo;

@JsonProperty("color-codes")
private List<String> colorlist;

@JsonProperty("stock")
private String stock;

@JsonProperty("serviceable")
private boolean serviceable;

@JsonProperty("available-quantities")
private int availablequantities;

public String getDevicecode() {
	return devicecode;
}

public void setDevicecode(String devicecode) {
	this.devicecode = devicecode;
}

public String getDevicename() {
	return devicename;
}

public void setDevicename(String devicename) {
	this.devicename = devicename;
}

public String getDeviceinfo() {
	return deviceinfo;
}

public void setDeviceinfo(String deviceinfo) {
	this.deviceinfo = deviceinfo;
}

public List<String> getColorlist() {
	return colorlist;
}

public void setColorlist(List<String> colorlist) {
	this.colorlist = colorlist;
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

public int getAvailablequantitties() {
	return availablequantities;
}

public void setAvailablequantitties(int availablequantitties) {
	this.availablequantities = availablequantitties;
}

public TestDeserialized()
    {
     super();
    }

public TestDeserialized(String devicecode, String devicename, String deviceinfo, List<String> colorlist, String stock,
		boolean serviceable, int availablequantitties) {
	  super();
	this.devicecode = devicecode;
	this.devicename = devicename;
	this.deviceinfo = deviceinfo;
	this.colorlist = colorlist;
	this.stock = stock;
	this.serviceable = serviceable;
	this.availablequantities = availablequantitties;
}

@Override
public String toString() {
	return "Pojo [devicecode=" + devicecode + ", devicename=" + devicename + ", deviceinfo=" + deviceinfo
			+ ", colorlist=" + colorlist + ", stock=" + stock + ", serviceable=" + serviceable
			+ ", availablequantitties=" + availablequantities + "]";
}
	
	
}
