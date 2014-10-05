package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the menu table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="menu"
 */

public abstract class BaseMenu  implements Serializable {

	public static String REF = "Menu";
	public static String PROP_MENU_NAME = "MenuName";
	public static String PROP_ID = "Id";
	public static String PROP_ADDR = "Addr";
	public static String PROP_PARENT_I_D = "ParentID";


	// constructors
	public BaseMenu () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMenu (java.lang.Short id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseMenu (
		java.lang.Short id,
		java.lang.String menuName) {

		this.setId(id);
		this.setMenuName(menuName);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Short id;

	// fields
	private java.lang.String menuName;
	private java.lang.String addr;
	private java.lang.Short parentID;

	// collections
	private java.util.Set<com.wifihi.persistence.Wspuser> wspusers;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="MenuID"
     */
	public java.lang.Short getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Short id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: MenuName
	 */
	public java.lang.String getMenuName () {
		return menuName;
	}

	/**
	 * Set the value related to the column: MenuName
	 * @param menuName the MenuName value
	 */
	public void setMenuName (java.lang.String menuName) {
		this.menuName = menuName;
	}



	/**
	 * Return the value associated with the column: Addr
	 */
	public java.lang.String getAddr () {
		return addr;
	}

	/**
	 * Set the value related to the column: Addr
	 * @param addr the Addr value
	 */
	public void setAddr (java.lang.String addr) {
		this.addr = addr;
	}



	/**
	 * Return the value associated with the column: ParentID
	 */
	public java.lang.Short getParentID () {
		return parentID;
	}

	/**
	 * Set the value related to the column: ParentID
	 * @param parentID the ParentID value
	 */
	public void setParentID (java.lang.Short parentID) {
		this.parentID = parentID;
	}



	/**
	 * Return the value associated with the column: Wspusers
	 */
	public java.util.Set<com.wifihi.persistence.Wspuser> getWspusers () {
		return wspusers;
	}

	/**
	 * Set the value related to the column: Wspusers
	 * @param wspusers the Wspusers value
	 */
	public void setWspusers (java.util.Set<com.wifihi.persistence.Wspuser> wspusers) {
		this.wspusers = wspusers;
	}

	public void addToWspusers (com.wifihi.persistence.Wspuser wspuser) {
		if (null == getWspusers()) setWspusers(new java.util.TreeSet<com.wifihi.persistence.Wspuser>());
		getWspusers().add(wspuser);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Menu)) return false;
		else {
			com.wifihi.persistence.Menu menu = (com.wifihi.persistence.Menu) obj;
			if (null == this.getId() || null == menu.getId()) return false;
			else return (this.getId().equals(menu.getId()));
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