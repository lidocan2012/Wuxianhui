package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseOrdermanageDAO;


public class OrdermanageDAO extends BaseOrdermanageDAO implements com.wifihi.persistence.dao.iface.OrdermanageDAO {

	public OrdermanageDAO () {}
	
	public OrdermanageDAO (Session session) {
		super(session);
	}


}