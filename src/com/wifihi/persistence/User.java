package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseUser;



public class User extends BaseUser {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public User () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public User (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public User (
		java.lang.Long id,
		java.lang.String phoneNumber,
		java.util.Date registrateDate,
		java.util.Date registrateTime) {

		super (
			id,
			phoneNumber,
			registrateDate,
			registrateTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}