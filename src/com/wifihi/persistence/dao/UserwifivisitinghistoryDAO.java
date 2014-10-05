package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseUserwifivisitinghistoryDAO;


public class UserwifivisitinghistoryDAO extends BaseUserwifivisitinghistoryDAO implements com.wifihi.persistence.dao.iface.UserwifivisitinghistoryDAO {

	public UserwifivisitinghistoryDAO () {}
	
	public UserwifivisitinghistoryDAO (Session session) {
		super(session);
	}


}