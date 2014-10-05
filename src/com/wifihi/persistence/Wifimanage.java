package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseWifimanage;



public class Wifimanage extends BaseWifimanage {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Wifimanage () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Wifimanage (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Wifimanage (
		java.lang.Long id,
		java.lang.String wifiName,
		java.lang.String mACAddress,
		java.lang.String password,
		boolean authorise,
		java.lang.String displayName) {

		super (
			id,
			wifiName,
			mACAddress,
			password,
			authorise,
			displayName);
	}

/*[CONSTRUCTOR MARKER END]*/


}