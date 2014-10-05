package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseOrderdetailDAO;


public class OrderdetailDAO extends BaseOrderdetailDAO implements com.wifihi.persistence.dao.iface.OrderdetailDAO {

	public OrderdetailDAO () {}
	
	public OrderdetailDAO (Session session) {
		super(session);
	}


}