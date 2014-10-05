package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseGoodsmanageDAO;


public class GoodsmanageDAO extends BaseGoodsmanageDAO implements com.wifihi.persistence.dao.iface.GoodsmanageDAO {

	public GoodsmanageDAO () {}
	
	public GoodsmanageDAO (Session session) {
		super(session);
	}


}