package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the user table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="user"
 */

public abstract class BaseUser  implements Serializable {

	public static String REF = "User";
	public static String PROP_REGISTRATE_TIME = "RegistrateTime";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_Q_Q_NUMBER = "QQNumber";
	public static String PROP_ID = "Id";
	public static String PROP_USER_NAME = "UserName";
	public static String PROP_PHONE_NUMBER = "PhoneNumber";
	public static String PROP_REGISTRATE_DATE = "RegistrateDate";


	// constructors
	public BaseUser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUser (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseUser (
		java.lang.Long id,
		java.lang.String phoneNumber,
		java.util.Date registrateDate,
		java.util.Date registrateTime) {

		this.setId(id);
		this.setPhoneNumber(phoneNumber);
		this.setRegistrateDate(registrateDate);
		this.setRegistrateTime(registrateTime);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String phoneNumber;
	private java.lang.Integer qQNumber;
	private java.lang.String userName;
	private java.lang.String password;
	private java.util.Date registrateDate;
	private java.util.Date registrateTime;

	// collections
	private java.util.Set<com.wifihi.persistence.Uservisitingsystemhistory> uservisitingsystemhistories;
	private java.util.Set<com.wifihi.persistence.Ordermanage> ordermanages;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="UserID"
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
	 * Return the value associated with the column: PhoneNumber
	 */
	public java.lang.String getPhoneNumber () {
		return phoneNumber;
	}

	/**
	 * Set the value related to the column: PhoneNumber
	 * @param phoneNumber the PhoneNumber value
	 */
	public void setPhoneNumber (java.lang.String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	/**
	 * Return the value associated with the column: QQNumber
	 */
	public java.lang.Integer getQQNumber () {
		return qQNumber;
	}

	/**
	 * Set the value related to the column: QQNumber
	 * @param qQNumber the QQNumber value
	 */
	public void setQQNumber (java.lang.Integer qQNumber) {
		this.qQNumber = qQNumber;
	}



	/**
	 * Return the value associated with the column: UserName
	 */
	public java.lang.String getUserName () {
		return userName;
	}

	/**
	 * Set the value related to the column: UserName
	 * @param userName the UserName value
	 */
	public void setUserName (java.lang.String userName) {
		this.userName = userName;
	}



	/**
	 * Return the value associated with the column: Password
	 */
	public java.lang.String getPassword () {
		return password;
	}

	/**
	 * Set the value related to the column: Password
	 * @param password the Password value
	 */
	public void setPassword (java.lang.String password) {
		this.password = password;
	}



	/**
	 * Return the value associated with the column: RegistrateDate
	 */
	public java.util.Date getRegistrateDate () {
		return registrateDate;
	}

	/**
	 * Set the value related to the column: RegistrateDate
	 * @param registrateDate the RegistrateDate value
	 */
	public void setRegistrateDate (java.util.Date registrateDate) {
		this.registrateDate = registrateDate;
	}



	/**
	 * Return the value associated with the column: RegistrateTime
	 */
	public java.util.Date getRegistrateTime () {
		return registrateTime;
	}

	/**
	 * Set the value related to the column: RegistrateTime
	 * @param registrateTime the RegistrateTime value
	 */
	public void setRegistrateTime (java.util.Date registrateTime) {
		this.registrateTime = registrateTime;
	}



	/**
	 * Return the value associated with the column: Uservisitingsystemhistories
	 */
	public java.util.Set<com.wifihi.persistence.Uservisitingsystemhistory> getUservisitingsystemhistories () {
		return uservisitingsystemhistories;
	}

	/**
	 * Set the value related to the column: Uservisitingsystemhistories
	 * @param uservisitingsystemhistories the Uservisitingsystemhistories value
	 */
	public void setUservisitingsystemhistories (java.util.Set<com.wifihi.persistence.Uservisitingsystemhistory> uservisitingsystemhistories) {
		this.uservisitingsystemhistories = uservisitingsystemhistories;
	}

	public void addToUservisitingsystemhistories (com.wifihi.persistence.Uservisitingsystemhistory uservisitingsystemhistory) {
		if (null == getUservisitingsystemhistories()) setUservisitingsystemhistories(new java.util.TreeSet<com.wifihi.persistence.Uservisitingsystemhistory>());
		getUservisitingsystemhistories().add(uservisitingsystemhistory);
	}



	/**
	 * Return the value associated with the column: Ordermanages
	 */
	public java.util.Set<com.wifihi.persistence.Ordermanage> getOrdermanages () {
		return ordermanages;
	}

	/**
	 * Set the value related to the column: Ordermanages
	 * @param ordermanages the Ordermanages value
	 */
	public void setOrdermanages (java.util.Set<com.wifihi.persistence.Ordermanage> ordermanages) {
		this.ordermanages = ordermanages;
	}

	public void addToOrdermanages (com.wifihi.persistence.Ordermanage ordermanage) {
		if (null == getOrdermanages()) setOrdermanages(new java.util.TreeSet<com.wifihi.persistence.Ordermanage>());
		getOrdermanages().add(ordermanage);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.User)) return false;
		else {
			com.wifihi.persistence.User user = (com.wifihi.persistence.User) obj;
			if (null == this.getId() || null == user.getId()) return false;
			else return (this.getId().equals(user.getId()));
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