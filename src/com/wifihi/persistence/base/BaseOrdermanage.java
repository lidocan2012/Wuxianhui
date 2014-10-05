package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the ordermanage table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="ordermanage"
 */

public abstract class BaseOrdermanage  implements Serializable {

	public static String REF = "Ordermanage";
	public static String PROP_ORDER_TIME = "OrderTime";
	public static String PROP_STATUS = "Status";
	public static String PROP_ORDER_DATE = "OrderDate";
	public static String PROP_EXPENDITURE = "Expenditure";
	public static String PROP_USER_I_D = "UserID";
	public static String PROP_ID = "Id";
	public static String PROP_W_S_P_USER = "WSPUser";
	public static String PROP_TABLE_I_D = "TableID";


	// constructors
	public BaseOrdermanage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseOrdermanage (java.lang.String id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.String id;

	// fields
	private java.util.Date orderDate;
	private java.util.Date orderTime;
	private java.math.BigDecimal expenditure;
	private java.lang.String status;

	// many to one
	private com.wifihi.persistence.Wspuser wSPUser;
	private com.wifihi.persistence.User userID;
	private com.wifihi.persistence.Tablemanage tableID;

	// collections
	private java.util.Set<com.wifihi.persistence.Orderdetail> orderdetails;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="assigned"
     *  column="OrderId"
     */
	public java.lang.String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: OrderDate
	 */
	public java.util.Date getOrderDate () {
		return orderDate;
	}

	/**
	 * Set the value related to the column: OrderDate
	 * @param orderDate the OrderDate value
	 */
	public void setOrderDate (java.util.Date orderDate) {
		this.orderDate = orderDate;
	}



	/**
	 * Return the value associated with the column: OrderTime
	 */
	public java.util.Date getOrderTime () {
		return orderTime;
	}

	/**
	 * Set the value related to the column: OrderTime
	 * @param orderTime the OrderTime value
	 */
	public void setOrderTime (java.util.Date orderTime) {
		this.orderTime = orderTime;
	}



	/**
	 * Return the value associated with the column: Expenditure
	 */
	public java.math.BigDecimal getExpenditure () {
		return expenditure;
	}

	/**
	 * Set the value related to the column: Expenditure
	 * @param expenditure the Expenditure value
	 */
	public void setExpenditure (java.math.BigDecimal expenditure) {
		this.expenditure = expenditure;
	}



	/**
	 * Return the value associated with the column: Status
	 */
	public java.lang.String getStatus () {
		return status;
	}

	/**
	 * Set the value related to the column: Status
	 * @param status the Status value
	 */
	public void setStatus (java.lang.String status) {
		this.status = status;
	}



	/**
	 * Return the value associated with the column: WSPUser
	 */
	public com.wifihi.persistence.Wspuser getWSPUser () {
		return wSPUser;
	}

	/**
	 * Set the value related to the column: WSPUser
	 * @param wSPUser the WSPUser value
	 */
	public void setWSPUser (com.wifihi.persistence.Wspuser wSPUser) {
		this.wSPUser = wSPUser;
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
	 * Return the value associated with the column: Wspuser
	 */
	public com.wifihi.persistence.Tablemanage getTableID () {
		return tableID;
	}

	/**
	 * Set the value related to the column: Wspuser
	 * @param tableID the Wspuser value
	 */
	public void setTableID (com.wifihi.persistence.Tablemanage tableID) {
		this.tableID = tableID;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Ordermanage)) return false;
		else {
			com.wifihi.persistence.Ordermanage ordermanage = (com.wifihi.persistence.Ordermanage) obj;
			if (null == this.getId() || null == ordermanage.getId()) return false;
			else return (this.getId().equals(ordermanage.getId()));
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