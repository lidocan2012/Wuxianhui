package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseWspuser;



public class Wspuser extends BaseWspuser {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Wspuser () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Wspuser (java.lang.Long userID2) {
		super(userID2);
	}

	/**
	 * Constructor for required fields
	 */
	public Wspuser (
		java.lang.Long userID2,
		java.lang.String phoneNumber) {

		super (
			userID2,
			phoneNumber);
	}

/*[CONSTRUCTOR MARKER END]*/


}