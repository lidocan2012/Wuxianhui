package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the wspuser table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="wspuser"
 */

public abstract class BaseWspuser  implements Serializable {

	public static String REF = "Wspuser";
	public static String PROP_USER_I_D2 = "UserID2";
	public static String PROP_REGISTRATE_TIME = "RegistrateTime";
	public static String PROP_BUSINESS_TYPE = "BusinessType";
	public static String PROP_PASSWORD = "Password";
	public static String PROP_DETAIL_ADDRESS = "DetailAddress";
	public static String PROP_PHONE_NUMBER = "PhoneNumber";
	public static String PROP_USER_NAME = "UserName";
	public static String PROP_COUNTRY = "Country";
	public static String PROP_PROVINCE = "Province";
	public static String PROP_CITY = "City";
	public static String PROP_EMAIL = "Email";
	public static String PROP_DISTRICT = "District";
	public static String PROP_SHOWINFO = "Showinfo";
	public static String PROP_RID = "Rid";
	public static String PROP_Q_Q_NUMBER = "QQNumber";
	public static String PROP_REGISTRATE_DATE = "RegistrateDate";


	// constructors
	public BaseWspuser () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseWspuser (java.lang.Long userID2) {
		this.setUserID2(userID2);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseWspuser (
		java.lang.Long userID2,
		java.lang.String phoneNumber) {

		this.setUserID2(userID2);
		this.setPhoneNumber(phoneNumber);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long userID2;

	// fields
	private java.lang.String phoneNumber;
	private java.lang.Long qQNumber;
	private java.lang.String userName;
	private java.util.Date registrateDate;
	private java.util.Date registrateTime;
	private java.lang.String email;
	private java.lang.String country;
	private java.lang.String province;
	private java.lang.String city;
	private java.lang.String district;
	private java.lang.String detailAddress;
	private java.lang.String password;
	private java.lang.String businessType;
	private java.lang.String showinfo;
	private java.lang.String rid;

	// collections
	private java.util.Set<com.wifihi.persistence.Goodsmanage> goodsmanages;
	private java.util.Set<com.wifihi.persistence.Wifimanage> wifimanages;
	private java.util.Set<com.wifihi.persistence.Wspuservisithistory> wspuservisithistories;
	private java.util.Set<com.wifihi.persistence.Orderdetail> orderdetails;
	private java.util.Set<com.wifihi.persistence.Tablemanage> tablemanages;
	private java.util.Set<com.wifihi.persistence.Ordermanage> ordermanages;
	private java.util.Set<com.wifihi.persistence.Menu> menus;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="WSPUser"
     */
	public java.lang.Long getUserID2 () {
		return userID2;
	}

	/**
	 * Set the unique identifier of this class
	 * @param userID2 the new ID
	 */
	public void setUserID2 (java.lang.Long userID2) {
		this.userID2 = userID2;
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
	public java.lang.Long getQQNumber () {
		return qQNumber;
	}

	/**
	 * Set the value related to the column: QQNumber
	 * @param qQNumber the QQNumber value
	 */
	public void setQQNumber (java.lang.Long qQNumber) {
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
	 * Return the value associated with the column: Email
	 */
	public java.lang.String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: Email
	 * @param email the Email value
	 */
	public void setEmail (java.lang.String email) {
		this.email = email;
	}



	/**
	 * Return the value associated with the column: Country
	 */
	public java.lang.String getCountry () {
		return country;
	}

	/**
	 * Set the value related to the column: Country
	 * @param country the Country value
	 */
	public void setCountry (java.lang.String country) {
		this.country = country;
	}



	/**
	 * Return the value associated with the column: Province
	 */
	public java.lang.String getProvince () {
		return province;
	}

	/**
	 * Set the value related to the column: Province
	 * @param province the Province value
	 */
	public void setProvince (java.lang.String province) {
		this.province = province;
	}



	/**
	 * Return the value associated with the column: City
	 */
	public java.lang.String getCity () {
		return city;
	}

	/**
	 * Set the value related to the column: City
	 * @param city the City value
	 */
	public void setCity (java.lang.String city) {
		this.city = city;
	}



	/**
	 * Return the value associated with the column: District
	 */
	public java.lang.String getDistrict () {
		return district;
	}

	/**
	 * Set the value related to the column: District
	 * @param district the District value
	 */
	public void setDistrict (java.lang.String district) {
		this.district = district;
	}



	/**
	 * Return the value associated with the column: DetailAddress
	 */
	public java.lang.String getDetailAddress () {
		return detailAddress;
	}

	/**
	 * Set the value related to the column: DetailAddress
	 * @param detailAddress the DetailAddress value
	 */
	public void setDetailAddress (java.lang.String detailAddress) {
		this.detailAddress = detailAddress;
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
	 * Return the value associated with the column: BusinessType
	 */
	public java.lang.String getBusinessType () {
		return businessType;
	}

	/**
	 * Set the value related to the column: BusinessType
	 * @param businessType the BusinessType value
	 */
	public void setBusinessType (java.lang.String businessType) {
		this.businessType = businessType;
	}



	/**
	 * Return the value associated with the column: Showinfo
	 */
	public java.lang.String getShowinfo () {
		return showinfo;
	}

	/**
	 * Set the value related to the column: Showinfo
	 * @param showinfo the Showinfo value
	 */
	public void setShowinfo (java.lang.String showinfo) {
		this.showinfo = showinfo;
	}



	/**
	 * Return the value associated with the column: RID
	 */
	public java.lang.String getRid () {
		return rid;
	}

	/**
	 * Set the value related to the column: RID
	 * @param rid the RID value
	 */
	public void setRid (java.lang.String rid) {
		this.rid = rid;
	}



	/**
	 * Return the value associated with the column: Goodsmanages
	 */
	public java.util.Set<com.wifihi.persistence.Goodsmanage> getGoodsmanages () {
		return goodsmanages;
	}

	/**
	 * Set the value related to the column: Goodsmanages
	 * @param goodsmanages the Goodsmanages value
	 */
	public void setGoodsmanages (java.util.Set<com.wifihi.persistence.Goodsmanage> goodsmanages) {
		this.goodsmanages = goodsmanages;
	}

	public void addToGoodsmanages (com.wifihi.persistence.Goodsmanage goodsmanage) {
		if (null == getGoodsmanages()) setGoodsmanages(new java.util.TreeSet<com.wifihi.persistence.Goodsmanage>());
		getGoodsmanages().add(goodsmanage);
	}



	/**
	 * Return the value associated with the column: Wifimanages
	 */
	public java.util.Set<com.wifihi.persistence.Wifimanage> getWifimanages () {
		return wifimanages;
	}

	/**
	 * Set the value related to the column: Wifimanages
	 * @param wifimanages the Wifimanages value
	 */
	public void setWifimanages (java.util.Set<com.wifihi.persistence.Wifimanage> wifimanages) {
		this.wifimanages = wifimanages;
	}

	public void addToWifimanages (com.wifihi.persistence.Wifimanage wifimanage) {
		if (null == getWifimanages()) setWifimanages(new java.util.TreeSet<com.wifihi.persistence.Wifimanage>());
		getWifimanages().add(wifimanage);
	}



	/**
	 * Return the value associated with the column: Wspuservisithistories
	 */
	public java.util.Set<com.wifihi.persistence.Wspuservisithistory> getWspuservisithistories () {
		return wspuservisithistories;
	}

	/**
	 * Set the value related to the column: Wspuservisithistories
	 * @param wspuservisithistories the Wspuservisithistories value
	 */
	public void setWspuservisithistories (java.util.Set<com.wifihi.persistence.Wspuservisithistory> wspuservisithistories) {
		this.wspuservisithistories = wspuservisithistories;
	}

	public void addToWspuservisithistories (com.wifihi.persistence.Wspuservisithistory wspuservisithistory) {
		if (null == getWspuservisithistories()) setWspuservisithistories(new java.util.TreeSet<com.wifihi.persistence.Wspuservisithistory>());
		getWspuservisithistories().add(wspuservisithistory);
	}



	/**
	 * Return the value associated with the column: Orderdetails
	 */
	public java.util.Set<com.wifihi.persistence.Orderdetail> getOrderdetails () {
		return orderdetails;
	}

	/**
	 * Set the value related to the column: Orderdetails
	 * @param orderdetails the Orderdetails value
	 */
	public void setOrderdetails (java.util.Set<com.wifihi.persistence.Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public void addToOrderdetails (com.wifihi.persistence.Orderdetail orderdetail) {
		if (null == getOrderdetails()) setOrderdetails(new java.util.TreeSet<com.wifihi.persistence.Orderdetail>());
		getOrderdetails().add(orderdetail);
	}



	/**
	 * Return the value associated with the column: Tablemanages
	 */
	public java.util.Set<com.wifihi.persistence.Tablemanage> getTablemanages () {
		return tablemanages;
	}

	/**
	 * Set the value related to the column: Tablemanages
	 * @param tablemanages the Tablemanages value
	 */
	public void setTablemanages (java.util.Set<com.wifihi.persistence.Tablemanage> tablemanages) {
		this.tablemanages = tablemanages;
	}

	public void addToTablemanages (com.wifihi.persistence.Tablemanage tablemanage) {
		if (null == getTablemanages()) setTablemanages(new java.util.TreeSet<com.wifihi.persistence.Tablemanage>());
		getTablemanages().add(tablemanage);
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



	/**
	 * Return the value associated with the column: Menus
	 */
	public java.util.Set<com.wifihi.persistence.Menu> getMenus () {
		return menus;
	}

	/**
	 * Set the value related to the column: Menus
	 * @param menus the Menus value
	 */
	public void setMenus (java.util.Set<com.wifihi.persistence.Menu> menus) {
		this.menus = menus;
	}

	public void addToMenus (com.wifihi.persistence.Menu menu) {
		if (null == getMenus()) setMenus(new java.util.TreeSet<com.wifihi.persistence.Menu>());
		getMenus().add(menu);
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Wspuser)) return false;
		else {
			com.wifihi.persistence.Wspuser wspuser = (com.wifihi.persistence.Wspuser) obj;
			if (null == this.getUserID2() || null == wspuser.getUserID2()) return false;
			else return (this.getUserID2().equals(wspuser.getUserID2()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getUserID2()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getUserID2().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}