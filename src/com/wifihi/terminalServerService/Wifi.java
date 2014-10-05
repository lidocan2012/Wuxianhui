package com.wifihi.terminalServerService;
import java.io.Serializable;

public class Wifi implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mac;
	private String passwd;
	private boolean Authorise;
	private String wifiname;
	private String wspusername;
	private String displayname;
	private long wspId;
	
	public String getWspusername() {
		return wspusername;
	}

	public void setWspusername(String wspusername) {
		this.wspusername = wspusername;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getWifiname() {
		return wifiname;
	}

	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}

	public boolean isAuthorise() {
		return Authorise;
	}

	public void setAuthorise(boolean authorise) {
		Authorise = authorise;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Wifi["+this.wspusername+","+this.wifiname+","+this.mac+","+this.passwd+","+this.Authorise+"'"+this.displayname+"]";
		
	}

	public long getWspId() {
		return wspId;
	}

	public void setWspId(long wspId) {
		this.wspId = wspId;
	}

}
