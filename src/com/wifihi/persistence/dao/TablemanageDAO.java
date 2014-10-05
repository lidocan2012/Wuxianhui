package com.wifihi.persistence.dao;

import org.hibernate.Session;

import com.wifihi.persistence.base.BaseTablemanageDAO;


public class TablemanageDAO extends BaseTablemanageDAO implements com.wifihi.persistence.dao.iface.TablemanageDAO {

	public TablemanageDAO () {}
	
	public TablemanageDAO (Session session) {
		super(session);
	}


}