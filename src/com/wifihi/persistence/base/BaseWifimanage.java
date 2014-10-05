package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the wifimanage table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="wifimanage"
 */

public abstract class BaseWifimanage  implements Serializable {

	public static String REF = "Wifimanage";
	public static String PROP_M_A_C_ADDRESS = "MACAddress";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_WIFI_NAME = "WifiName";
	public static String PROP_ID = "Id";
	public static String PROP_W_S_P_USER = "WSPUser";
	public static String PROP_AUTHORISE = "Authorise";
	public static String PROP_DISPLAY_NAME = "DisplayName";


	// constructors
	public BaseWifimanage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseWifimanage (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseWifimanage (
		java.lang.Long id,
		java.lang.String wifiName,
		java.lang.String mACAddress,
		java.lang.String password,
		boolean authorise,
		java.lang.String displayName) {

		this.setId(id);
		this.setWifiName(wifiName);
		this.setMACAddress(mACAddress);
		this.setPassword(password);
		this.setAuthorise(authorise);
		this.setDisplayName(displayName);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String wifiName;
	private java.lang.String mACAddress;
	private java.lang.String password;
	private boolean authorise;
	private java.lang.String displayName;

	// many to one
	private com.wifihi.persistence.Wspuser wSPUser;

	// collections
	private java.util.Set<com.wifihi.persistence.Userwifivisitinghistory> userwifivisitinghistories;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="WifiID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: WifiName
	 */
	public java.lang.String getWifiName () {
		return wifiName;
	}

	/**
	 * Set the value related to the column: WifiName
	 * @param wifiName the WifiName value
	 */
	public void setWifiName (java.lang.String wifiName) {
		this.wifiName = wifiName;
	}



	/**
	 * Return the value associated with the column: MACAddress
	 */
	public java.lang.String getMACAddress () {
		return mACAddress;
	}

	/**
	 * Set the value related to the column: MACAddress
	 * @param mACAddress the MACAddress value
	 */
	public void setMACAddress (java.lang.String mACAddress) {
		this.mACAddress = mACAddress;
	}



	/**
	 * Return the value associated with the column: Password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: Password
	 * @param password the Password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
	}



	/**
	 * Return the value associated with the column: Authorise
	 */
	public boolean isAuthorise () {
		return authorise;
	}

	/**
	 * Set the value related to the column: Authorise
	 * @param authorise the Authorise value
	 */
	public void setAuthorise (boolean authorise) {
		this.authorise = authorise;
	}



	/**
	 * Return the value associated with the column: DisplayName
	 */
	public java.lang.String getDisplayName () {
		return displayName;
	}

	/**
	 * Set the value related to the column: DisplayName
	 * @param displayName the DisplayName value
	 */
	public void setDisplayName (java.lang.String displayName) {
		this.displayName = displayName;
	}



	/**
	 * Return the value associated with the column: WSPUser
	 */
	public com.wifihi.persistence.Wspuser getWSPUser () {
		return wSPUser;
	}

	/**
	 * Set the value related to the column: WSPUser
	 * @param wSPUser the WSPUser value
	 */
	public void setWSPUser (com.wifihi.persistence.Wspuser wSPUser) {
		this.wSPUser = wSPUser;
	}



	/**
	 * Return the value associated with the column: Userwifivisitinghistories
	 */
	public java.util.Set<com.wifihi.persistence.Userwifivisitinghistory> getUserwifivisitinghistories () {
		return userwifivisitinghistories;
	}

	/**
	 * Set the value related to the column: Userwifivisitinghistories
	 * @param userwifivisitinghistories the Userwifivisitinghistories value
	 */
	public void setUserwifivisitinghistories (java.util.Set<com.wifihi.persistence.Userwifivisitinghistory> userwifivisitinghistories) {
		this.userwifivisitinghistories = userwifivisitinghistories;
	}

	public void addToUserwifivisitinghistories (com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory) {
		if (null == getUserwifivisitinghistories()) setUserwifivisitinghistories(new java.util.TreeSet<com.wifihi.persistence.Userwifivisitinghistory>());
		getUserwifivisitinghistories().add(userwifivisitinghistory);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Wifimanage)) return false;
		else {
			com.wifihi.persistence.Wifimanage wifimanage = (com.wifihi.persistence.Wifimanage) obj;
			if (null == this.getId() || null == wifimanage.getId()) return false;
			else return (this.getId().equals(wifimanage.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}