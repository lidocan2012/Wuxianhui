package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseUserDAO;


public class UserDAO extends BaseUserDAO implements com.wifihi.persistence.dao.iface.UserDAO {

	public UserDAO () {}
	 
	public UserDAO (Session session) {
		super(session);
	}


}