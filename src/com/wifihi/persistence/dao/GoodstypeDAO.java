package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseGoodstypeDAO;


public class GoodstypeDAO extends BaseGoodstypeDAO implements com.wifihi.persistence.dao.iface.GoodstypeDAO {

	public GoodstypeDAO () {}
	
	public GoodstypeDAO (Session session) {
		super(session);
	}


}