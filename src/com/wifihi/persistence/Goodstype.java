package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseGoodstype;



public class Goodstype extends BaseGoodstype {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Goodstype () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Goodstype (java.lang.Long typeID) {
		super(typeID);
	}

	/**
	 * Constructor for required fields
	 */
	public Goodstype (
		java.lang.Long typeID,
		java.lang.String goodsType) {

		super (
			typeID,
			goodsType);
	}

/*[CONSTRUCTOR MARKER END]*/


}