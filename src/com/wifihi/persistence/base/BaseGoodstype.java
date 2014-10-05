package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the goodstype table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="goodstype"
 */

public abstract class BaseGoodstype  implements Serializable {

	public static String REF = "Goodstype";
	public static String PROP_EXTENDS = "Extends";
	public static String PROP_GOODS_TYPE_DESCRIBE = "GoodsTypeDescribe";
	public static String PROP_W_S_P_USER = "WSPUser";
	public static String PROP_TYPE_I_D = "TypeID";
	public static String PROP_GOODS_TYPE = "GoodsType";


	// constructors
	public BaseGoodstype () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseGoodstype (java.lang.Long typeID) {
		this.setTypeID(typeID);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseGoodstype (
		java.lang.Long typeID,
		java.lang.String goodsType) {

		this.setTypeID(typeID);
		this.setGoodsType(goodsType);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long typeID;

	// fields
	private java.lang.String goodsType;
	private java.lang.String goodsTypeDescribe;
	private java.lang.String m_extends;

	// many to one
	private com.wifihi.persistence.Wspuser wSPUser;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="identity"
     *  column="TypeID"
     */
	public java.lang.Long getTypeID () {
		return typeID;
	}

	/**
	 * Set the unique identifier of this class
	 * @param typeID the new ID
	 */
	public void setTypeID (java.lang.Long typeID) {
		this.typeID = typeID;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: GoodsType
	 */
	public java.lang.String getGoodsType () {
		return goodsType;
	}

	/**
	 * Set the value related to the column: GoodsType
	 * @param goodsType the GoodsType value
	 */
	public void setGoodsType (java.lang.String goodsType) {
		this.goodsType = goodsType;
	}



	/**
	 * Return the value associated with the column: GoodsTypeDescribe
	 */
	public java.lang.String getGoodsTypeDescribe () {
		return goodsTypeDescribe;
	}

	/**
	 * Set the value related to the column: GoodsTypeDescribe
	 * @param goodsTypeDescribe the GoodsTypeDescribe value
	 */
	public void setGoodsTypeDescribe (java.lang.String goodsTypeDescribe) {
		this.goodsTypeDescribe = goodsTypeDescribe;
	}



	/**
	 * Return the value associated with the column: Extends
	 */
	public java.lang.String getExtends () {
		return m_extends;
	}

	/**
	 * Set the value related to the column: Extends
	 * @param m_extends the Extends value
	 */
	public void setExtends (java.lang.String m_extends) {
		this.m_extends = m_extends;
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




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.wifihi.persistence.Goodstype)) return false;
		else {
			com.wifihi.persistence.Goodstype goodstype = (com.wifihi.persistence.Goodstype) obj;
			if (null == this.getTypeID() || null == goodstype.getTypeID()) return false;
			else return (this.getTypeID().equals(goodstype.getTypeID()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getTypeID()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getTypeID().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}