package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the goodsmanage table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="goodsmanage"
 */

public abstract class BaseGoodsmanage  implements Serializable {

	public static String REF = "Goodsmanage";
	public static String PROP_PICTURE = "Picture";
	public static String PROP_DESCRIPTION = "Description";
	public static String PROP_PRICE = "Price";
	public static String PROP_ID = "Id";
	public static String PROP_W_S_P_USER = "WSPUser";
	public static String PROP_CONSUMED_TIMES = "ConsumedTimes";
	public static String PROP_GOODS_TYPE = "GoodsType";
	public static String PROP_DETAIL_I_D = "DetailID";
	public static String PROP_UNIT = "Unit";
	public static String PROP_GOODS_NAME = "GoodsName";
	public static String PROP_PRICE_UNIT = "PriceUnit";


	// constructors
	public BaseGoodsmanage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGoodsmanage (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
	private java.lang.String goodsName;
	private java.lang.String unit;
	private java.math.BigDecimal price;
	private java.lang.String description;
	private java.lang.String picture;
	private java.lang.String priceUnit;
	private java.lang.Integer consumedTimes;

	// many to one
	private com.wifihi.persistence.Wspuser wSPUser;
	private com.wifihi.persistence.Goodstype goodsType;
	private com.wifihi.persistence.Orderdetail detailID;

	// collections
	private java.util.Set<com.wifihi.persistence.Orderdetail> orderdetails;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="GoodsID"
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
	 * Return the value associated with the column: GoodsName
	 */
	public java.lang.String getGoodsName () {
		return goodsName;
	}

	/**
	 * Set the value related to the column: GoodsName
	 * @param goodsName the GoodsName value
	 */
	public void setGoodsName (java.lang.String goodsName) {
		this.goodsName = goodsName;
	}



	/**
	 * Return the value associated with the column: Unit
	 */
	public java.lang.String getUnit () {
		return unit;
	}

	/**
	 * Set the value related to the column: Unit
	 * @param unit the Unit value
	 */
	public void setUnit (java.lang.String unit) {
		this.unit = unit;
	}



	/**
	 * Return the value associated with the column: Price
	 */
	public java.math.BigDecimal getPrice () {
		return price;
	}

	/**
	 * Set the value related to the column: Price
	 * @param price the Price value
	 */
	public void setPrice (java.math.BigDecimal price) {
		this.price = price;
	}



	/**
	 * Return the value associated with the column: Description
	 */
	public java.lang.String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: Description
	 * @param description the Description value
	 */
	public void setDescription (java.lang.String description) {
		this.description = description;
	}



	/**
	 * Return the value associated with the column: Picture
	 */
	public java.lang.String getPicture () {
		return picture;
	}

	/**
	 * Set the value related to the column: Picture
	 * @param picture the Picture value
	 */
	public void setPicture (java.lang.String picture) {
		this.picture = picture;
	}



	/**
	 * Return the value associated with the column: ConsumedTimes
	 */
	public java.lang.Integer getConsumedTimes () {
		return consumedTimes;
	}

	/**
	 * Set the value related to the column: ConsumedTimes
	 * @param consumedTimes the ConsumedTimes value
	 */
	public void setConsumedTimes (java.lang.Integer consumedTimes) {
		this.consumedTimes = consumedTimes;
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
	 * Return the value associated with the column: Wspuser
	 */
	public com.wifihi.persistence.Goodstype getGoodsType () {
		return goodsType;
	}

	/**
	 * Set the value related to the column: Wspuser
	 * @param goodsType the Wspuser value
	 */
	public void setGoodsType (com.wifihi.persistence.Goodstype goodsType) {
		this.goodsType = goodsType;
	}



	/**
	 * Return the value associated with the column: DetailID
	 */
	public com.wifihi.persistence.Orderdetail getDetailID () {
		return detailID;
	}

	/**
	 * Set the value related to the column: DetailID
	 * @param detailID the DetailID value
	 */
	public void setDetailID (com.wifihi.persistence.Orderdetail detailID) {
		this.detailID = detailID;
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


	public java.lang.String getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(java.lang.String priceUnit) {
		this.priceUnit = priceUnit;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Goodsmanage)) return false;
		else {
			com.wifihi.persistence.Goodsmanage goodsmanage = (com.wifihi.persistence.Goodsmanage) obj;
			if (null == this.getId() || null == goodsmanage.getId()) return false;
			else return (this.getId().equals(goodsmanage.getId()));
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