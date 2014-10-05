package com.wifihi.action.usermanage;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.WspuserDAO;
import com.wifihi.persistence.dao._RootDAO;

public class WspUserRegistrate {
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String wspuserset;
    private String userID2;
	private String username;
	private String password;
	private String phonenumber;
	private String email;
	private String detailaddress;
	private String province;
	private String city;
	private String district;
	private String businesstype;
	private String showinfo;

	private String username_sr;
	private String phonenumber_sr;
	private String email_sr;
	private String detailaddress_sr;
	private String province_sr;
	private String city_sr;
	private String district_sr;
	private String businesstype_sr;

	public String insertwspuser(){
		System.out.println("in method insertwspuser");

		java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
		java.sql.Time t = new java.sql.Time(System.currentTimeMillis());
		System.out.println("this.getUsername()="+this.getUsername());
		System.out.println("this.getPassword()="+this.getPassword());
		System.out.println("this.getBusinesstype()="+this.getBusinesstype());
		boolean exist = exitUser(this.getUsername());
		if(exist)
			return "existuser";
		
		Wspuser wspu = new Wspuser();
        wspu.setUserName(this.getUsername());
        wspu.setPassword(this.getPassword());
        wspu.setEmail(this.getEmail());
        wspu.setPhoneNumber(this.getPhonenumber());
        wspu.setProvince(this.getProvince());
        wspu.setCity(this.getCity());
        wspu.setDistrict(this.getDistrict());
        wspu.setDetailAddress(this.getDetailaddress());
        wspu.setRegistrateDate(d);
        wspu.setRegistrateTime(t);
        wspu.setBusinessType(this.getBusinesstype());
        wspu.setShowinfo(this.getShowinfo());
        
		_RootDAO.initialize();
        WspuserDAO wdao = new WspuserDAO();
        java.lang.Long re = wdao.save(wspu);
        System.out.println("save result="+re);
        if(re>0)
            return "success";
        else
        	return "error";
  		
	}
	/**
	 * wsp用户自注册
	 * @return
	 */
	public String registrate(){
		String result = insertwspuser();
		return result;
	}
	/**
	 * 管理员创建新的wsp用户
	 * @return
	 */
	public String newwspuser(){
		String result = insertwspuser();
		System.out.println("the result in newwspuser="+result);
		if(result.equals("existuser")){
			JSONObject json = new JSONObject();
			json.put("errorMsg", "用户名已经存在！");
			wspuserset = json.toString();// 将json对象转化为String对象传输
			System.out.println(wspuserset);		
		}
		else{
			searchwspuser();			
		}
		return "success";
	}
	
	public String updatewspuser(){
		System.out.println("in updatewspuser");
		System.out.println("province="+this.getProvince()+",city="+this.getCity()+",district="+this.getDistrict());
		Wspuser wuser = new Wspuser();
		Long id = Long.valueOf(this.getUserID2());
		wuser.setUserID2(id);
		wuser.setUserName(this.getUsername());
		wuser.setBusinessType(this.getBusinesstype());
		wuser.setPassword(this.getPassword());
		wuser.setPhoneNumber(this.getPhonenumber());
		wuser.setEmail(this.getEmail());
		wuser.setProvince(this.getProvince());
		wuser.setCity(this.getCity());
		wuser.setDistrict(this.getDistrict());
		wuser.setDetailAddress(this.getDetailaddress());
		wuser.setShowinfo(this.getShowinfo());

		_RootDAO.initialize();
		WspuserDAO mdao = new WspuserDAO();
        mdao.update(wuser);
        searchwspuser();
		return "success";		
	}
	public String maintainwspuser(){
		System.out.println("in updatewspuser");
		System.out.println("province="+this.getProvince()+",city="+this.getCity()+",district="+this.getDistrict());
		Wspuser wuser = new Wspuser();
		Long id = Long.valueOf(this.getUserID2());
		wuser.setUserID2(id);
		wuser.setUserName(this.getUsername());
		wuser.setBusinessType(this.getBusinesstype());
		wuser.setPassword(this.getPassword());
		wuser.setPhoneNumber(this.getPhonenumber());
		wuser.setEmail(this.getEmail());
		wuser.setProvince(this.getProvince());
		wuser.setCity(this.getCity());
		wuser.setDistrict(this.getDistrict());
		wuser.setDetailAddress(this.getDetailaddress());
		wuser.setShowinfo(this.getShowinfo());

		_RootDAO.initialize();
		WspuserDAO mdao = new WspuserDAO();
        mdao.update(wuser);
        
		JSONObject json = new JSONObject();
		json.put("result","success");//设置记录总数
		wspuserset = json.toString();// 将json对象转化为String对象传输
        
		return "success";		
	}
	
	public String deletewspuser() {
		System.out.println("in deletewspuser");
		_RootDAO.initialize();
		WspuserDAO mdao = new WspuserDAO();
		System.out.println("before delete");
		Long id = Long.valueOf(this.getUserID2());
        mdao.delete(id);
        searchwspuser();
		return "success";
	}	
	public boolean exitUser(String username){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Wspuser where UserName='"+username+"'";
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
	public String searchwspuser() {
		System.out.println("in searchwspuser");
		
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
		String hql = "from Wspuser ";
		//判断查询条件，并采用模糊查询
		System.out.println(this.getUsername_sr());
        System.out.println(this.getProvince_sr());
        System.out.println(this.getBusinesstype_sr());
		String search_condi="";
		String str = null;
		if(this.getUsername_sr()!=null&&!this.getUsername_sr().equals("")){
			System.out.println("11111111111");
			str = " and userName like '%"+this.getUsername_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getPhonenumber_sr()!=null&&!this.getPhonenumber_sr().equals("")){
			System.out.println("22222222222");
			str = " and phoneNumber like '%"+this.getPhonenumber_sr()+"%'";		
			search_condi = search_condi.concat(str);
		}
		if(this.getEmail_sr()!=null&&!this.getEmail_sr().equals("")){
			System.out.println("33333333");
			str = " and email like '%"+this.getEmail_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getBusinesstype_sr()!=null&&!this.getBusinesstype_sr().equals("")){
			System.out.println("44444444444");
			str = " and businessType like '%"+this.getBusinesstype_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getProvince_sr()!=null&&!this.getProvince_sr().equals("")){
			System.out.println("555555555555");
			str = " and province like '%"+this.getProvince_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getCity_sr()!=null&&!this.getCity_sr().equals("")){
			System.out.println("66666666666");
			str = " and city like '%"+this.getCity_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getDistrict_sr()!=null&&!this.getDistrict_sr().equals("")){
			System.out.println("77777777777");
			str = " and district like '%"+this.getDistrict_sr()+"%'";				
			search_condi = search_condi.concat(str);
		}
		if(this.getDetailaddress_sr()!=null&&!this.getDetailaddress_sr().equals("")){
			System.out.println("888888888888");
			str = " and detailAddress like '%"+this.getDetailaddress_sr()+"%'";				
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
        List wspuser = query.list();     
        
        //获取记录总数，用于前台分页工具栏中的记录总数
        String totalhql = "select count(*) from Wspuser ";
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
		System.out.println("Wspuser.size=" + wspuser.size());
		for (int i = 0; i < wspuser.size(); i++) {
			Wspuser wuser = (Wspuser) wspuser.get(i);
			JSONObject json = new JSONObject();
			json.put("userID2", wuser.getUserID2());
			json.put("username", wuser.getUserName());
			json.put("password", wuser.getPassword());
			json.put("username", wuser.getUserName());
			json.put("phonenumber", wuser.getPhoneNumber());
			json.put("email", wuser.getEmail());
			json.put("province", wuser.getProvince());
			json.put("city", wuser.getCity());
			json.put("district", wuser.getDistrict());
			json.put("detailaddress", wuser.getDetailAddress());
			json.put("businesstype", wuser.getBusinessType());
			json.put("showinfo", wuser.getShowinfo());

			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		
		
		
		wspuserset = json.toString();// 将json对象转化为String对象传输

		System.out.println(wspuserset);

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDetailaddress() {
		return detailaddress;
	}

	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	
	public String getShowinfo() {
		return showinfo;
	}
	public void setShowinfo(String showinfo) {
		this.showinfo = showinfo;
	}
	
	public String getUserID2() {
		return userID2;
	}

	public void setUserID2(String userID2) {
		this.userID2 = userID2;
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

	public String getWspuserset() {
		return wspuserset;
	}

	public void setWspuserset(String wspuserset) {
		this.wspuserset = wspuserset;
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
	public String getEmail_sr() {
		return email_sr;
	}
	public void setEmail_sr(String email_sr) {
		this.email_sr = email_sr;
	}
	public String getDetailaddress_sr() {
		return detailaddress_sr;
	}
	public void setDetailaddress_sr(String detailaddress_sr) {
		this.detailaddress_sr = detailaddress_sr;
	}
	public String getProvince_sr() {
		return province_sr;
	}
	public void setProvince_sr(String province_sr) {
		this.province_sr = province_sr;
	}
	public String getCity_sr() {
		return city_sr;
	}
	public void setCity_sr(String city_sr) {
		this.city_sr = city_sr;
	}
	public String getDistrict_sr() {
		return district_sr;
	}
	public void setDistrict_sr(String district_sr) {
		this.district_sr = district_sr;
	}
	public String getBusinesstype_sr() {
		return businesstype_sr;
	}
	public void setBusinesstype_sr(String businesstype_sr) {
		this.businesstype_sr = businesstype_sr;
	}

	
}
