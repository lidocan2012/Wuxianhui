package com.wifihi.persistence.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the tablemanage table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="tablemanage"
 */

public abstract class BaseTablemanage  implements Serializable {

	public static String REF = "Tablemanage";
	public static String PROP_EXTENDS = "Extends";
	public static String PROP_ID = "Id";


	// constructors
	public BaseTablemanage () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseTablemanage (com.wifihi.persistence.TablemanagePK id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private com.wifihi.persistence.TablemanagePK id;

	// fields
	private java.lang.String m_extends;

	// collections
	private java.util.Set<com.wifihi.persistence.Ordermanage> ordermanages;


	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     */
	public com.wifihi.persistence.TablemanagePK getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (com.wifihi.persistence.TablemanagePK id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
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
		if (!(obj instanceof com.wifihi.persistence.Tablemanage)) return false;
		else {
			com.wifihi.persistence.Tablemanage tablemanage = (com.wifihi.persistence.Tablemanage) obj;
			if (null == this.getId() || null == tablemanage.getId()) return false;
			else return (this.getId().equals(tablemanage.getId()));
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