package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseMenuuser;



public class Menuuser extends BaseMenuuser {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Menuuser () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Menuuser (
		com.wifihi.persistence.Wspuser userID2,
		com.wifihi.persistence.Menu menuID) {

		super (
			userID2,
			menuID);
	}

/*[CONSTRUCTOR MARKER END]*/


}