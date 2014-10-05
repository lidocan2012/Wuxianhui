package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseWspuserDAO;


public class WspuserDAO extends BaseWspuserDAO implements com.wifihi.persistence.dao.iface.WspuserDAO {

	public WspuserDAO () {}
	
	public WspuserDAO (Session session) {
		super(session);
	}


}