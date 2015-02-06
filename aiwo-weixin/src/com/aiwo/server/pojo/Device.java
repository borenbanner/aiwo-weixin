package com.aiwo.server.pojo;

public class Device {
	
	
	private int deviceId;
	private String mac;
	private String wd;
	private String sd;
	private String lux;
	private String pm;
	private String deviceName ;
	private String macId ;
	
	
	
	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getSd() {
		return sd;
	}

	public void setSd(String sd) {
		this.sd = sd;
	}

	public String getLux() {
		return lux;
	}

	public void setLux(String lux) {
		this.lux = lux;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}


	public Device(int deviceId, String mac, String wd, String sd, String lux,
			String pm, String deviceName, String macId) {
		super();
		this.deviceId = deviceId;
		this.mac = mac;
		this.wd = wd;
		this.sd = sd;
		this.lux = lux;
		this.pm = pm;
		this.deviceName = deviceName;
		this.macId = macId;
	}

	public Device() {
		super();
	}
	
}
