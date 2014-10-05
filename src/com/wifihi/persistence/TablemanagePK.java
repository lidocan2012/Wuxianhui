package com.wifihi.persistence;

import com.wifihi.persistence.base.BaseTablemanagePK;

public class TablemanagePK extends BaseTablemanagePK {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public TablemanagePK () {}
	
	public TablemanagePK (
		java.lang.Integer tableID,
		com.wifihi.persistence.Wspuser wSPUser) {

		super (
			tableID,
			wSPUser);
	}
/*[CONSTRUCTOR MARKER END]*/


}