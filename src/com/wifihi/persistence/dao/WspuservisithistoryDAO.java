package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseWspuservisithistoryDAO;


public class WspuservisithistoryDAO extends BaseWspuservisithistoryDAO implements com.wifihi.persistence.dao.iface.WspuservisithistoryDAO {

	public WspuservisithistoryDAO () {}
	
	public WspuservisithistoryDAO (Session session) {
		super(session);
	}


}