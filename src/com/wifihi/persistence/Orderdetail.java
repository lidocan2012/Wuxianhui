package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseOrderdetail;



public class Orderdetail extends BaseOrderdetail {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Orderdetail () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Orderdetail (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Orderdetail (
		java.lang.Long id,
		java.lang.Integer number) {

		super (id,number);
	}
}