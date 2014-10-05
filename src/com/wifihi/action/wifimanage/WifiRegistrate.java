package com.wifihi.action.wifimanage;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.Wifimanage;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.WifimanageDAO;
import com.wifihi.persistence.dao.WspuserDAO;
import com.wifihi.persistence.dao._RootDAO;

/**
 * 根据Struts2框架，定义成员变量来获取前台传过来的值，一个需要获取数据的前台标签对应一个相同名字的成员变量，且需要定义get/set方法以获取标签值
 * @author su
 *
 */
public class WifiRegistrate extends ActionSupport{
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String wifiset;
	private String wspusername;
	private String id;
	private String wifiname;
	private String wifipassword;
	private String macaddress;
	private String authorize;
    private String displayname;  
    
	private String wifiname_sr;
	private String macaddress_sr;
	private String authorize_sr;
    private String displayname_sr;   
    
    
	public String searchwifi(){
		
		System.out.println("in the method searchwifi");
		ActionContext actionContext = ActionContext.getContext();
		Map sess = actionContext.getSession();
		String wspuserid = (String)sess.get("wspuserid");
		Wspuser wuser = new Wspuser();
		Long id = Long.valueOf(wspuserid);
		wuser.setUserID2(id);
		
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
		String hql = "from Wifimanage ";
		//判断查询条件，并采用模糊查询
		System.out.println(this.getWspusername());
        System.out.println(this.getWifiname_sr());
		String search_condi="";
		String str = null;
		if(id!=null&&!id.equals("")){
			System.out.println("11111111111");
			str = " and WSPUser = '"+id+"'";	
			search_condi = search_condi.concat(str);
		}

		if(this.getWifiname_sr()!=null&&!this.getWifiname_sr().equals("")){
			System.out.println("11111111111");
			str = " and wifiName like '%"+this.getWifiname_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getMacaddress_sr()!=null&&!this.getMacaddress_sr().equals("")){
			System.out.println("22222222222");
			str = " and mACAddress like '%"+this.getMacaddress_sr()+"%'";		
			search_condi = search_condi.concat(str);
		}
		if(this.getAuthorize_sr()!=null&&!this.getAuthorize_sr().equals("")){
			System.out.println("33333333");
			str = " and authorise like '%"+this.getAuthorize_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getDisplayname_sr()!=null&&!this.getDisplayname_sr().equals("")){
			System.out.println("44444444444");
			str = " and displayName like '%"+this.getDisplayname_sr()+"%'";				
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
        List wifilist = query.list();     
        
        //获取记录总数，用于前台分页工具栏中的记录总数
        String totalhql = "select count(*) from Wifimanage ";
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
		System.out.println("Wifimanage.size=" + wifilist.size());
		for (int i = 0; i < wifilist.size(); i++) {
			Wifimanage wifim = (Wifimanage) wifilist.get(i);
			JSONObject json = new JSONObject();
			json.put("id", wifim.getId());
			json.put("wifiname", wifim.getWifiName());
			json.put("macaddress", wifim.getMACAddress());
			json.put("wifipassword", wifim.getPassword());
			if(wifim.isAuthorise())
				json.put("authorize", "是");
			else
				json.put("authorize", "否");
			json.put("displayname", wifim.getDisplayName());
			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		wifiset = json.toString();// 将json对象转化为String对象传输
		System.out.println(wifiset);

		return "success";
				
	}
	
	public String addwifi(){
		System.out.println("in method addwifi");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
//		System.out.println("*********username="+username+",UserID2="+userID2);
		boolean exist = exitWifi(this.getMacaddress());
		System.out.println("&&&&&&&&&&&&");
		if(exist){
			JSONObject json = new JSONObject();
			json.put("errorMsg", "此Wifi已经存在！");
			wifiset = json.toString();// 将json对象转化为String对象传输
			System.out.println(wifiset);		
		}
		else{
			Wifimanage wm = new Wifimanage();
			Wspuser wuser = new Wspuser();
			Long id = Long.valueOf(wspuserid);
			wuser.setUserID2(id);
			wuser.setUserName(username);
			
			String macaddr = this.getMacaddress();
			String wifiname = this.getWifiname();
			String wifipassword = this.getWifipassword();
			String autho = this.getAuthorize();
			String displayName = this.getDisplayname();
	        System.out.println(id+","+wifiname+","+wifipassword+","+autho+","+displayname+","+macaddr);		
			wm.setWSPUser(wuser);
			wm.setWifiName(wifiname);
			wm.setPassword(wifipassword);
			wm.setMACAddress(macaddr);
			wm.setAuthorise(autho.equals("1")?true:false);
			wm.setDisplayName(displayName);
			_RootDAO.initialize();
	        WifimanageDAO wdao = new WifimanageDAO();
	        wdao.save(wm);
			
			searchwifi();			
		}
		return "success";		
	}

	public String updatewifi(){
		System.out.println("in the method updatewifi");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String wspuserid = (String)session.get("wspuserid");
		Wspuser wuser = new Wspuser();
		Long id = Long.valueOf(wspuserid);
		wuser.setUserID2(id);
		
		String idstr = this.getId();
		String macaddr = this.getMacaddress();
		String wifiname = this.getWifiname();
		String wifipassword = this.getWifipassword();
		String autho = this.getAuthorize();
		String displayName = this.getDisplayname();
        System.out.println(idstr+","+wifiname+","+wifipassword+","+autho+","+displayname+","+macaddr);		
		Wifimanage wm = new Wifimanage();
		wm.setId(Long.valueOf(idstr));
		wm.setWSPUser(wuser);
		wm.setWifiName(wifiname);
		wm.setPassword(wifipassword);
		wm.setMACAddress(macaddr);
		wm.setAuthorise(autho.equals("1")?true:false);
		wm.setDisplayName(displayName);

		_RootDAO.initialize();
        WifimanageDAO wdao = new WifimanageDAO();
        
        wdao.update(wm);
        
        searchwifi();		
 		return "success";		
	}
	
	public boolean exitWifi(String mACAddress){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Wifimanage where mACAddress='"+mACAddress+"'";
		System.out.println("hql="+hql);
        Query query = session.createQuery(hql);
        List wifis = query.list();  
		trans.commit();
		if(session.isOpen()){
			session.close();
		}    
		if(wifis.size()>=1)
        	return true;
        else 
        	return false;		
	}	
	
	public String deletewifi(){
		System.out.println("in deletewifi");
		_RootDAO.initialize();
		WifimanageDAO wdao = new WifimanageDAO();

		String id = this.getId();
		System.out.println("before delete,id="+id);
		
        wdao.delete(Long.valueOf(id));
        searchwifi();
		return "success";
	}	
	public String getWspusername() {
		return wspusername;
	}

	public void setWspusername(String wspusername) {
		this.wspusername = wspusername;
	}

	public String getWifiname() {
		return wifiname;
	}

	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}

	public String getWifipassword() {
		return wifipassword;
	}

	public void setWifipassword(String wifipassword) {
		this.wifipassword = wifipassword;
	}

	public String getMacaddress() {
		return macaddress;
	}

	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}

	public String getAuthorize() {
		return authorize;
	}

	public void setAuthorize(String authorize) {
		this.authorize = authorize;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getWifiset() {
		return wifiset;
	}

	public void setWifiset(String wifiset) {
		this.wifiset = wifiset;
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

	public String getWifiname_sr() {
		return wifiname_sr;
	}

	public void setWifiname_sr(String wifiname_sr) {
		this.wifiname_sr = wifiname_sr;
	}

	public String getMacaddress_sr() {
		return macaddress_sr;
	}

	public void setMacaddress_sr(String macaddress_sr) {
		this.macaddress_sr = macaddress_sr;
	}

	public String getAuthorize_sr() {
		return authorize_sr;
	}

	public void setAuthorize_sr(String authorize_sr) {
		this.authorize_sr = authorize_sr;
	}

	public String getDisplayname_sr() {
		return displayname_sr;
	}

	public void setDisplayname_sr(String displayname_sr) {
		this.displayname_sr = displayname_sr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}




	
}
