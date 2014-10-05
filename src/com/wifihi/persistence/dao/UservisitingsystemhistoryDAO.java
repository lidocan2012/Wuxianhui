package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseUservisitingsystemhistoryDAO;


public class UservisitingsystemhistoryDAO extends BaseUservisitingsystemhistoryDAO implements com.wifihi.persistence.dao.iface.UservisitingsystemhistoryDAO {

	public UservisitingsystemhistoryDAO () {}
	
	public UservisitingsystemhistoryDAO (Session session) {
		super(session);
	}


}