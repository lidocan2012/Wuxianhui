package com.wifihi.action.admin;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.Menu;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.MenuDAO;
import com.wifihi.persistence.dao._RootDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MenuMaintaince extends ActionSupport {
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    
	private String menuid_sr;
	private String menuname_sr;
	
	private String menuset;
	private String menuid;
	private String menuname;
	private String parentid;
	private String urladdress;

	public String addmenu() {
		System.out.println("in addmenu");
		Menu menu = new Menu();
        menu.setId((short)new Integer(this.getMenuid()).intValue());
        menu.setMenuName(this.getMenuname());
        menu.setParentID((short)new Integer(this.getParentid()).intValue());
        menu.setAddr(this.getUrladdress());
		_RootDAO.initialize();
		MenuDAO mdao = new MenuDAO();
        mdao.save(menu);
        System.out.println("save is finished");
        searchmenu();

		return "success";
	}

	public String updatemenu() {
		System.out.println("in updatemenu");
		Menu menu = new Menu();
        menu.setId((short)new Integer(this.getMenuid()).intValue());
        menu.setMenuName(this.getMenuname());
        menu.setParentID((short)new Integer(this.getParentid()).intValue());
        menu.setAddr(this.getUrladdress());
		_RootDAO.initialize();
		MenuDAO mdao = new MenuDAO();
        mdao.update(menu);
        System.out.println("update is finished");
        searchmenu();
		return "success";		
	}

	public String deletemenu() {
		System.out.println("in deletemenu");
		_RootDAO.initialize();
		MenuDAO mdao = new MenuDAO();
		System.out.println("before delete");

		short id = (short)new Integer(this.getMenuid()).intValue();
        mdao.delete(id);
        System.out.println("delete is finished");
        searchmenu();
		return "success";
	}

	/**
	 * 下面json数据的key名称应符合zTree对key的命名要求
	 * 
	 * @return
	 */
	public String getmenutree() {

		_RootDAO.initialize();
		
		System.out.println("in getmenutree");
		
		MenuDAO mdao = new MenuDAO();
		List<Menu> menus = null;
		try {
			menus = mdao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获得登陆的wsp类型
		ActionContext actionContext = ActionContext.getContext();
		Map sess = actionContext.getSession();
		String username = (String)sess.get("username");
		System.out.println("username=" + username);
		
		JSONArray jsonArray = new JSONArray();
		System.out.println("menus.size=" + menus.size());
		List<Menu> filteredmenus = new ArrayList();
		if(!username.equals("admin")){
			filteredmenus = filterMenu(menus);			
		}
		else
			filteredmenus = menus;
		for (int i = 0; i < filteredmenus.size(); i++) {
			Menu me = (Menu) filteredmenus.get(i);
			JSONObject json = new JSONObject();
			json.put("id", me.getId());
			json.put("name", me.getMenuName());
			json.put("pId", me.getParentID());
			if (!me.getAddr().equalsIgnoreCase("0")){
				json.put("addr", me.getAddr());
			}
			else
				json.put("open", true);
			jsonArray.put(json);
		}
		
		menuset = jsonArray.toString();// 将json对象转化为String对象传输

		System.out.println(menuset);

		return "success";
	}
	
	public List<Menu> filterMenu(List<Menu> menus){
		System.out.println("in the method filterMenu***");
		List<Menu> finalmenus = new ArrayList();
		
		ActionContext actionContext = ActionContext.getContext();
		Map sess = actionContext.getSession();
		String showinfo = (String)sess.get("showinfo");
		System.out.println("showinfo="+showinfo);
		List<Menu> currentlevel = new ArrayList();
		//获得wsp用户注册时选定的子系统首级菜单
		for (int i = 0; i < menus.size(); i++) {
			Menu me = (Menu) menus.get(i);
			System.out.println("me.getMenuName="+me.getMenuName()+",ParentID="+me.getParentID());
			if(me.getParentID()==0){
				//System.out.println("****************");
				if(showinfo==null||showinfo==""){
					System.out.println("&&&&&&&&");
					if(me.getMenuName().contains("Wifi管理")){
						 currentlevel.add(me);
						 finalmenus.add(me);
						 menus.remove(i);
						 i--;
					}									
					if(me.getMenuName().contains("系统设置")){
						 currentlevel.add(me);
						 finalmenus.add(me);
						 menus.remove(i);
						 i--;
					}									
				}
				else{
					if((showinfo.contains("门户")&&me.getMenuName().contains("门户"))||
					   (showinfo.contains("商务")&&me.getMenuName().contains("商务"))||
						me.getMenuName().contains("Wifi管理")||
						me.getMenuName().contains("系统设置")){						
							currentlevel.add(me);
							finalmenus.add(me);
							menus.remove(i);
							i--;
				    }										
				}
			}			
		}
		System.out.println("currentlevle.size="+currentlevel.size());
		//根据子系统首级菜单获得所以下属菜单
		List<Menu> sonlevel = new ArrayList();
		while(menus.size()>0) {
			for(int i=0;i<currentlevel.size();i++){
				Menu fmu = (Menu) currentlevel.get(i);
				//System.out.println("fmu.getMenuName="+fmu.getMenuName()+",ParentID="+fmu.getParentID());
				for(int j=0;j<menus.size();j++){
					Menu me = (Menu) menus.get(j);
					//System.out.println("me.getMenuName="+me.getMenuName()+",ParentID="+me.getParentID());
					if(me.getParentID().equals(fmu.getId())){
						//System.out.println("33333333333");
						sonlevel.add(fmu);
						finalmenus.add(me);
						menus.remove(j);
						j--;
					}
				}
				currentlevel.remove(i);
				i--;
			}
			if(sonlevel.size()>0){
				currentlevel = sonlevel;
			}
			else
				break;
		}
		System.out.println("finalmenus.size="+finalmenus.size());
		return finalmenus;
	}


	/**
	 * 下面json数据的key名称应符合zTree对key的命名要求
	 * 
	 * @return
	 */
	public String searchmenu() {
		System.out.println("in searchmenu");
		System.out.println("in searchmenu, menuid_sr="+this.getMenuid_sr()+",menuname_sr="+this.getMenuname_sr());
		
        //当前页 
        int intPage = Integer.parseInt((page == null || page == "0") ? "1":this.getPage()); 
        //每页显示条数 
        int number = Integer.parseInt((rows == null || rows == "0") ? "10":this.getRows()); 
        //每页的开始记录  第一页为1  第二页为number +1  
        int start = (intPage-1)*number;  
        
        System.out.println("the index of Page = "+intPage+",the number of record in a page = "+number);
        
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Menu";
		//判断查询条件，并采用模糊查询
		boolean condi = false;
		String str = null;
		if(this.getMenuid_sr()!=null&&!this.getMenuid_sr().equals("")){
			short id = (short)(new Integer(this.getMenuid_sr()).intValue());
			System.out.println("id="+id);
			str = " where Id like '%"+id+"%'";	
			condi = true;
		}
		if(this.getMenuname_sr()!=null&&!this.getMenuname_sr().equals("")){
			String con = null;
			if(condi){
				con = " and MenuName like '%"+this.getMenuname_sr()+"%'";
			}
			else{
				con = " where MenuName like '%"+this.getMenuname_sr()+"%'";
			}
			str = str.concat(con);										
		}			
		if(str!=null)
			hql = hql.concat(str);
		System.out.println("hql="+hql);
        Query query = session.createQuery(hql);
        query.setFirstResult(start);   
        query.setMaxResults(number);
        List menus = query.list();     
        
        //获取记录总数，用于前台分页工具栏中的记录总数
        String totalhql = "select count(*) from Menu";
        if(str!=null)
        	totalhql = totalhql.concat(str);
        System.out.println("totalhql="+totalhql);
        Query querytotal = session.createQuery(totalhql);        
        int total = ((Number)querytotal.uniqueResult()).intValue();       
        System.out.println("----the total number of record= "+total);
        
		trans.commit();
		if(session.isOpen()){
			session.close();
		}       
		//将查询结果转化为Json对象以传到前台
		JSONArray jsonArray = new JSONArray();
		System.out.println("menus.size=" + menus.size());
		for (int i = 0; i < menus.size(); i++) {
			Menu me = (Menu) menus.get(i);
			JSONObject json = new JSONObject();
			json.put("menuid", me.getId());
			json.put("menuname", me.getMenuName());
			json.put("parentid", me.getParentID());
			json.put("urladdress", me.getAddr());

			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		
		menuset = json.toString();// 将json对象转化为String对象传输

		System.out.println(menuset);

		return "success";
	}

	public String getMenuset() {
		return menuset;
	}

	public void setMenuset(String menuset) {
		this.menuset = menuset;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getUrladdress() {
		return urladdress;
	}

	public void setUrladdress(String urladdress) {
		this.urladdress = urladdress;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getMenuid_sr() {
		return menuid_sr;
	}

	public void setMenuid_sr(String menuid_sr) {
		this.menuid_sr = menuid_sr;
	}

	public String getMenuname_sr() {
		return menuname_sr;
	}

	public void setMenuname_sr(String menuname_sr) {
		this.menuname_sr = menuname_sr;
	}

}
