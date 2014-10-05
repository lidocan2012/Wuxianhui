package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseMenu;



public class Menu extends BaseMenu {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public Menu () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public Menu (java.lang.Short id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public Menu (
		java.lang.Short id,
		java.lang.String menuName) {

		super (
			id,
			menuName);
	}

/*[CONSTRUCTOR MARKER END]*/


}