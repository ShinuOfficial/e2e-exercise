package com.e2e.exercises.jsondefiner;

import java.util.Arrays;

public class DeviceDetails {

	private String devicecode;
	private String devicename;
	private String deviceinfo;
	private String[] colorcodes;
	private String stock;
	private boolean serviceable;
	private int quantities;
	
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
	public String[] getColorcodes() {
		return colorcodes;
	}
	public void setColorcodes(String[] colorcodes) {
		this.colorcodes = colorcodes;
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
	public int getQuantities() {
		return quantities;
	}
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***** Device Details *****\n");
		sb.append("device-code="+getDevicecode()+"\n");
		sb.append("device-name="+getDevicename()+"\n");
		sb.append("device-info="+getDeviceinfo()+"\n");
		sb.append("color-codes="+Arrays.toString(getColorcodes())+"\n");
		sb.append("stock="+getStock()+"\n");
		sb.append("serviceable="+isServiceable()+"\n");
		sb.append("available-quantities="+getQuantities());
		sb.append("\n***** Details Complete *****");
		
		return sb.toString();
	}
}
