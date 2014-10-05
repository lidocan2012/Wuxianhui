package com.wifihi.persistence.dao;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.wifihi.persistence.base.BaseMenuuserDAO;
import com.wifihi.persistence.Menu;
import com.wifihi.persistence.Menuuser;
import com.wifihi.persistence.Wspuser;

public class MenuuserDAO extends BaseMenuuserDAO implements
		com.wifihi.persistence.dao.iface.MenuuserDAO {

	public MenuuserDAO() {
	}

	public MenuuserDAO(Session session) {
		super(session);
	}

	/**
	 * assign all menus to admin user
	 */
	public void assignMenustoAdmin() {
		System.out.println("111");
		_RootDAO.initialize();
		System.out.println("222");
		Calendar cdate = Calendar.getInstance();
		cdate.set(2014, 07, 04);
		Calendar ctime = Calendar.getInstance();
		ctime.set(2014, 07, 04, 20, 02, 30);

		Wspuser admin = new Wspuser("admin", "18782211943", cdate, ctime, "中国",
				"四川省", "成都市", "成华区", "建设路");
		WspuserDAO wspudao = new WspuserDAO();
		wspudao.saveOrUpdate(admin);

		ArrayList<Menu> alist = new ArrayList();
		MenuDAO mdao = new MenuDAO();
		MenuuserDAO mudao = new MenuuserDAO();
		alist = (ArrayList<Menu>) mdao.findAll();

		for (int i = 0; i < alist.size(); i++) {
			Menu menu = (Menu) alist.get(i);
			Menuuser mu = new Menuuser(admin, menu);
			mudao.saveOrUpdate(mu);
		}
		System.out.println("end");

		// add a temp user

		wspudao.saveOrUpdate(temp);
		Menu menu = (Menu) alist.get(0);
		Menuuser mu = new Menuuser(temp, menu);
		mudao.saveOrUpdate(mu);

	}

	public List getMenusbyUser(String username) {
		String adminmenuhql = "select me.Id, me.MenuName, me.Level, me.ParentID, me.IsLeaf from Menu me where me.Id in (select mu.MenuID.Id from Menuuser mu where mu.UserID2.Id='"
				+ username + "')";//
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();

		List list = session.createQuery(adminmenuhql).list();

		for (Iterator it = list.iterator(); it.hasNext();) {
			Object[] obj = (Object[]) it.next();
			Short menuid = (Short) obj[0];
			String menuname = (String) obj[1];
			Short level = (Short) obj[2];
			Short parentID = (Short) obj[3];
			System.out.println(menuid + "," + menuname + "," + level + ","
					+ parentID);
		}
		trans.commit();
		session.close();
		return list;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MenuuserDAO mudao = new MenuuserDAO();
		mudao.assignMenustoAdmin();
		mudao.getMenusbyUser("admin");
	}

}