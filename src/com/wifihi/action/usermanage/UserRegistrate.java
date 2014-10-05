package com.wifihi.action.usermanage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.wifihi.persistence.User;
import com.wifihi.persistence.dao.UserDAO;
import com.wifihi.persistence.dao._RootDAO;

public class UserRegistrate {
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String userset;
    private String userid;
    
	private String username;
	private String password;
	private String phonenumber;

	private String username_sr;
	private String phonenumber_sr;

	public String insertuser(){
		System.out.println("in method insertuser");

		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		java.sql.Time t = new java.sql.Time(System.currentTimeMillis());
		System.out.println("this.getUsername()="+this.getUsername());
		System.out.println("this.getPassword()="+this.getPassword());
		System.out.println("this.getPhonenumber()="+this.getPhonenumber());

		boolean exist = exitUser(this.getPhonenumber());
		if(exist)
			return "existuser";
		System.out.println("go to save user");
		User us = new User();
		us.setUserName(this.getUsername());
		us.setPassword(this.getPassword());

		us.setPhoneNumber(this.getPhonenumber());

        us.setRegistrateDate(d);
        us.setRegistrateTime(t);

		_RootDAO.initialize();
        UserDAO udao = new UserDAO();

        java.lang.Long re = udao.save(us);
        System.out.println("save result="+re);
        return "success";
  		
	}
	/**
	 * 用户自注册
	 * @return
	 */
	public String registrate(){
		String result = insertuser();
		return result;
	}
	/**
	 * 管理员创建新的用户
	 * @return
	 */
	public String newuser(){
		String result = insertuser();
		System.out.println("the result in newuser="+result);
		if(result.equals("existuser")){
			JSONObject json = new JSONObject();
			json.put("errorMsg", "用户名已经存在！");
			userset = json.toString();// 将json对象转化为String对象传输
			System.out.println(userset);		
		}
		else{
			searchuser();			
		}
		return "success";
	}
	
	public String updateuser(){
		System.out.println("in updateuser");
		User us = new User();
		String nid = this.getUserid();
		System.out.println("&&&&&&&&&&&, id="+nid);
		us.setId(Long.valueOf(nid));
		System.out.println("&&&&&&&&&&&,username="+this.getUsername());
		us.setUserName(this.getUsername());
		System.out.println("&&&&&&&&&&&,password="+this.getPassword());
		us.setPassword(this.getPassword());
		System.out.println("&&&&&&&&&&&,phonenumber="+this.getPhonenumber());
		us.setPhoneNumber(this.getPhonenumber());
		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		java.sql.Time t = new java.sql.Time(System.currentTimeMillis());
        us.setRegistrateDate(d);
        us.setRegistrateTime(t);

		_RootDAO.initialize();
		System.out.println("&&&&&&&&&&&");
		UserDAO udao = new UserDAO();
		System.out.println("&&&&&&&&&&&");
		udao.update(us);
		System.out.println("&&&&&&&&&&&");
        searchuser();
		return "success";		
	}
	
	public String deleteuser() {
		System.out.println("in deleteuser");
		_RootDAO.initialize();
		UserDAO udao = new UserDAO();
		Long id = Long.valueOf(this.getUserid());
		System.out.println("before delete,id="+id);
		udao.delete(id);
        searchuser();
		return "success";
	}	
	
	public boolean exitUser(String phonenumber){
		System.out.println("in exitUser");
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from User where PhoneNumber="+phonenumber;
		System.out.println("hql="+hql);
        Query query = session.createQuery(hql);
        List user = query.list();  
		trans.commit();
		if(session.isOpen()){
			session.close();
		}    
		if(user.size()>=1)
        	return true;
        else 
        	return false;		
	}
	public String searchuser() {
		System.out.println("in searchuser");
		
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
		String hql = "from User ";
		//判断查询条件，并采用模糊查询
		System.out.println(this.getUsername_sr());
		String search_condi="";
		String str = null;
		if(this.getUsername_sr()!=null&&!this.getUsername_sr().equals("")){
			System.out.println("11111111111");
			str = " and UserName like '%"+this.getUsername_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getPhonenumber_sr()!=null&&!this.getPhonenumber_sr().equals("")){
			System.out.println("22222222222");
			str = " and PhoneNumber like '%"+this.getPhonenumber_sr()+"%'";		
			search_condi = search_condi.concat(str);
		}
		if(!search_condi.equals("")){
			System.out.println("9999999999");
			search_condi = search_condi.substring(4);
			search_condi = "where "+search_condi;
			hql = hql.concat(search_condi);
		}
		System.out.println("hql="+hql);
        Query query = session.createQuery(hql);
        query.setFirstResult(start);   
        query.setMaxResults(number);
        List user = query.list();     
        
        //获取记录总数，用于前台分页工具栏中的记录总数
        String totalhql = "select count(*) from User ";
		if(!search_condi.equals("")){
			System.out.println("111111111111");
			totalhql = totalhql.concat(search_condi);
		}
		System.out.println(totalhql);
        Query querytotal = session.createQuery(totalhql);        
        int total = ((Number)querytotal.uniqueResult()).intValue();       
        System.out.println("----the total number of record= "+total);
        
		trans.commit();
		if(session.isOpen()){
			session.close();
		}       
		//将查询结果转化为Json对象以传到前台
		JSONArray jsonArray = new JSONArray();
		System.out.println("User.size=" + user.size());
		for (int i = 0; i < user.size(); i++) {
			User us = (User) user.get(i);
			JSONObject json = new JSONObject();
			json.put("userid", us.getId());
			json.put("username", us.getUserName());
			json.put("password", us.getPassword());
			json.put("phonenumber", us.getPhoneNumber());
			json.put("registratedate", us.getRegistrateDate());
			json.put("registratetime", us.getRegistrateTime());

			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		userset = json.toString();// 将json对象转化为String对象传输

		System.out.println(userset);

		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getUserset() {
		return userset;
	}
	public void setUserset(String userset) {
		this.userset = userset;
	}
	public String getUsername_sr() {
		return username_sr;
	}
	public void setUsername_sr(String username_sr) {
		this.username_sr = username_sr;
	}
	public String getPhonenumber_sr() {
		return phonenumber_sr;
	}
	public void setPhonenumber_sr(String phonenumber_sr) {
		this.phonenumber_sr = phonenumber_sr;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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


	
}
