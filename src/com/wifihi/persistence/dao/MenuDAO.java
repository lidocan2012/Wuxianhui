package com.wifihi.persistence.dao;

import java.util.List;

import org.hibernate.Session;

import com.wifihi.persistence.Menu;
import com.wifihi.persistence.base.BaseMenuDAO;



public class MenuDAO extends BaseMenuDAO implements com.wifihi.persistence.dao.iface.MenuDAO {

	public MenuDAO () {}
	
	public MenuDAO (Session session) {
		super(session);
	}

	
	
    public void insertAdminMenu(){
		Menu menu1 = new Menu((short) 1, "系统管理");
		Menu menu11 = new Menu((short) 11, "菜单管理");
		Menu menu12 = new Menu((short) 12, "菜单分配");
		
		menu1.setParentID((short)0);
		menu1.setAddr("");
		
		menu11.setParentID((short)1);
		menu11.setAddr("admin/menumanage.html");

		menu12.setParentID((short)1);
		menu12.setAddr("admin/menuassign.html");

		_RootDAO.initialize();
		
		MenuDAO mdao = new MenuDAO();
		mdao.saveOrUpdate(menu1);
		mdao.saveOrUpdate(menu11);
		mdao.saveOrUpdate(menu12);
    	
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuDAO mdao = new MenuDAO();
		mdao.insertAdminMenu();		
        List result = mdao.findAll();
        System.out.println(result.size());
	}

}