package com.wifihi.action.usermanage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.opensymphony.xwork2.ActionContext;
import com.wifihi.persistence.Wspuser;

public class Login {
	
	private String username;
	private String password;
	private String businesstype;
	
	public String doLogin(){
		System.out.println(this.getUsername()+","+this.getPassword());
		
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Wspuser where UserName='"+this.getUsername()+"' and Password='"+this.getPassword()+"'";
		System.out.println("hql="+hql);
        Query query = session.createQuery(hql);
        List user = query.list();  
		trans.commit();
		if(session.isOpen()){
			session.close();
		}    
		if(user.size()==1){
			Wspuser wuser = (Wspuser)user.get(0);
			ActionContext actionContext = ActionContext.getContext();
			Map httpsession = actionContext.getSession();
			httpsession.put("username", this.getUsername());  
			System.out.println("------------------UserID2="+wuser.getUserID2());
			httpsession.put("wspuserid", wuser.getUserID2().toString());
			httpsession.put("businesstype", wuser.getBusinessType().toString());
			httpsession.put("showinfo", wuser.getShowinfo());
			httpsession.put("country", wuser.getCountry());
			httpsession.put("province", wuser.getProvince());
			httpsession.put("city", wuser.getCity());
			httpsession.put("district", wuser.getDistrict());
			httpsession.put("password", wuser.getPassword());
			httpsession.put("email", wuser.getEmail());
			httpsession.put("detailaddress", wuser.getDetailAddress());
			httpsession.put("phonenumber", wuser.getPhoneNumber());
			System.out.println("province="+wuser.getProvince()+",city="+wuser.getCity()+",district="+wuser.getDistrict()+",busitype="+wuser.getBusinessType());
			System.out.println("finish dologin");
			return "success";
		}
        else 
        	return "error";		
	}
	
	public String wsptypesel(){
		System.out.println("------------------wsptype="+this.getBusinesstype());
		ActionContext actionContext = ActionContext.getContext();
		Map httpsession = actionContext.getSession();
		httpsession.put("busitype", this.getBusinesstype());  
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

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
	
	

}
