package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseUservisitingsystemhistory;



public class Uservisitingsystemhistory extends BaseUservisitingsystemhistory {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Uservisitingsystemhistory () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Uservisitingsystemhistory (java.lang.String id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Uservisitingsystemhistory (
		java.lang.String id,
		java.util.Date wSPVisitingDate,
		java.util.Date wSPVisitingTime) {

		super (
			id,
			wSPVisitingDate,
			wSPVisitingTime);
	}

/*[CONSTRUCTOR MARKER END]*/


}