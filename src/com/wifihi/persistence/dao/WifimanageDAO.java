package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseWifimanageDAO;


public class WifimanageDAO extends BaseWifimanageDAO implements com.wifihi.persistence.dao.iface.WifimanageDAO {

	public WifimanageDAO () {}
	
	public WifimanageDAO (Session session) {
		super(session);
	}


}