package com.wifihi.persistence.base;

import java.io.Serializable;


public abstract class BaseTablemanagePK implements Serializable {

	protected int hashCode = Integer.MIN_VALUE;

	private java.lang.Integer tableID;
	private com.wifihi.persistence.Wspuser wSPUser;


	public BaseTablemanagePK () {}
	
	public BaseTablemanagePK (
		java.lang.Integer tableID,
		com.wifihi.persistence.Wspuser wSPUser) {

		this.setTableID(tableID);
		this.setWSPUser(wSPUser);
	}


	/**
	 * Return the value associated with the column: TableID
	 */
	public java.lang.Integer getTableID () {
		return tableID;
	}

	/**
	 * Set the value related to the column: TableID
	 * @param tableID the TableID value
	 */
	public void setTableID (java.lang.Integer tableID) {
		this.tableID = tableID;
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
		if (!(obj instanceof com.wifihi.persistence.TablemanagePK)) return false;
		else {
			com.wifihi.persistence.TablemanagePK mObj = (com.wifihi.persistence.TablemanagePK) obj;
			if (null != this.getTableID() && null != mObj.getTableID()) {
				if (!this.getTableID().equals(mObj.getTableID())) {
					return false;
				}
			}
			else {
				return false;
			}
			if (null != this.getWSPUser() && null != mObj.getWSPUser()) {
				if (!this.getWSPUser().equals(mObj.getWSPUser())) {
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
			if (null != this.getTableID()) {
				sb.append(this.getTableID().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			if (null != this.getWSPUser()) {
				sb.append(this.getWSPUser().hashCode());
				sb.append(":");
			}
			else {
				return super.hashCode();
			}
			this.hashCode = sb.toString().hashCode();
		}
		return this.hashCode;
	}


}