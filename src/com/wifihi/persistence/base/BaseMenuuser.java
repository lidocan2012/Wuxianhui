package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the menuuser table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="menuuser"
 */

public abstract class BaseMenuuser  implements Serializable {

	public static String REF = "Menuuser";


	// constructors
	public BaseMenuuser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseMenuuser (
		com.wifihi.persistence.Wspuser userID2,
		com.wifihi.persistence.Menu menuID) {

		this.setUserID2(userID2);
		this.setMenuID(menuID);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key

	private com.wifihi.persistence.Wspuser userID2;

	private com.wifihi.persistence.Menu menuID;



	/**
     * @hibernate.property
     *  column=UserID2
	 * not-null=true
	 */
	public com.wifihi.persistence.Wspuser getUserID2 () {
		return this.userID2;
	}

	/**
	 * Set the value related to the column: UserID2
	 * @param userID2 the UserID2 value
	 */
	public void setUserID2 (com.wifihi.persistence.Wspuser userID2) {
		this.userID2 = userID2;
		this.hashCode = Integer.MIN_VALUE;
	}

	/**
     * @hibernate.property
     *  column=MenuID
	 * not-null=true
	 */
	public com.wifihi.persistence.Menu getMenuID () {
		return this.menuID;
	}

	/**
	 * Set the value related to the column: MenuID
	 * @param menuID the MenuID value
	 */
	public void setMenuID (com.wifihi.persistence.Menu menuID) {
		this.menuID = menuID;
		this.hashCode = Integer.MIN_VALUE;
	}





	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Menuuser)) return false;
		else {
			com.wifihi.persistence.Menuuser menuuser = (com.wifihi.persistence.Menuuser) obj;
			if (null != this.getUserID2() && null != menuuser.getUserID2()) {
				if (!this.getUserID2().equals(menuuser.getUserID2())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getMenuID() && null != menuuser.getMenuID()) {
				if (!this.getMenuID().equals(menuuser.getMenuID())) {
					return false;
				}
			}
			else {
				return false;
			}
			return true;
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			StringBuilder sb = new StringBuilder();
			if (null != this.getUserID2()) {
				sb.append(this.getUserID2().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getMenuID()) {
				sb.append(this.getMenuID().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}