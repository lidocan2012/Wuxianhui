package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the userwifivisitinghistory table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="userwifivisitinghistory"
 */

public abstract class BaseUserwifivisitinghistory  implements Serializable {

	public static String REF = "Userwifivisitinghistory";
	public static String PROP_WIFI_VISITING_DATE = "WifiVisitingDate";
	public static String PROP_AUTHORIZED_RESULT = "AuthorizedResult";
	public static String PROP_USER_I_D = "UserID";
	public static String PROP_ID = "Id";
	public static String PROP_END_TIME = "EndTime";
	public static String PROP_WIFI_I_D = "WifiID";
	public static String PROP_START_TIME = "StartTime";


	// constructors
	public BaseUserwifivisitinghistory () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUserwifivisitinghistory (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private boolean authorizedResult;
	private java.util.Date wifiVisitingDate;
	private java.util.Date startTime;
	private java.util.Date endTime;

	// many to one
	private com.wifihi.persistence.User userID;
	private com.wifihi.persistence.Wifimanage wifiID;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="WifiVisitingID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: AuthorizedResult
	 */
	public boolean isAuthorizedResult () {
		return authorizedResult;
	}

	/**
	 * Set the value related to the column: AuthorizedResult
	 * @param authorizedResult the AuthorizedResult value
	 */
	public void setAuthorizedResult (boolean authorizedResult) {
		this.authorizedResult = authorizedResult;
	}



	/**
	 * Return the value associated with the column: WifiVisitingDate
	 */
	public java.util.Date getWifiVisitingDate () {
		return wifiVisitingDate;
	}

	/**
	 * Set the value related to the column: WifiVisitingDate
	 * @param wifiVisitingDate the WifiVisitingDate value
	 */
	public void setWifiVisitingDate (java.util.Date wifiVisitingDate) {
		this.wifiVisitingDate = wifiVisitingDate;
	}



	/**
	 * Return the value associated with the column: StartTime
	 */
	public java.util.Date getStartTime () {
		return startTime;
	}

	/**
	 * Set the value related to the column: StartTime
	 * @param startTime the StartTime value
	 */
	public void setStartTime (java.util.Date startTime) {
		this.startTime = startTime;
	}



	/**
	 * Return the value associated with the column: EndTime
	 */
	public java.util.Date getEndTime () {
		return endTime;
	}

	/**
	 * Set the value related to the column: EndTime
	 * @param endTime the EndTime value
	 */
	public void setEndTime (java.util.Date endTime) {
		this.endTime = endTime;
	}



	/**
	 * Return the value associated with the column: UserID
	 */
	public com.wifihi.persistence.User getUserID () {
		return userID;
	}

	/**
	 * Set the value related to the column: UserID
	 * @param userID the UserID value
	 */
	public void setUserID (com.wifihi.persistence.User userID) {
		this.userID = userID;
	}



	/**
	 * Return the value associated with the column: WifiID
	 */
	public com.wifihi.persistence.Wifimanage getWifiID () {
		return wifiID;
	}

	/**
	 * Set the value related to the column: WifiID
	 * @param wifiID the WifiID value
	 */
	public void setWifiID (com.wifihi.persistence.Wifimanage wifiID) {
		this.wifiID = wifiID;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Userwifivisitinghistory)) return false;
		else {
			com.wifihi.persistence.Userwifivisitinghistory userwifivisitinghistory = (com.wifihi.persistence.Userwifivisitinghistory) obj;
			if (null == this.getId() || null == userwifivisitinghistory.getId()) return false;
			else return (this.getId().equals(userwifivisitinghistory.getId()));
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