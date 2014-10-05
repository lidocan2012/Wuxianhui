package com.wifihi.terminalServerAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.persistence.Wspuser;
import com.wifihi.terminalServerService.IDString;

public class WspLoginAction extends ActionSupport implements ServletRequestAware{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String reqContent = null;
private IDString id;
@Override
public void setServletRequest(HttpServletRequest arg0) {
	// TODO Auto-generated method stub
	this.reqContent = GetRequestAction.getJsonContent(arg0);
	System.out.println("reqContent: " + reqContent);
}


public String wspLoginCheck()
{   SessionFactory sf = HibernateSessionFactory.getSessionFactory();
    Session session = sf.openSession();
     JSONObject json = new JSONObject(this.reqContent);
     String wspname = json.getString("wspname");
     String passwd = json.getString("passwd");
     String RegisterID = json.getString("RID");
     Wspuser wspuser = new Wspuser();
     
     id = new IDString();
     try
     {
    	 Transaction trans = session.beginTransaction();
    	 Query q = session.createQuery("from Wspuser where UserName=:userName");
    	 q.setParameter("userName", wspname);
    	 wspuser =(Wspuser) q.uniqueResult();
    	 if(wspuser==null)
    	 {
    		 id.setId("err10003");//用户不存在
    	 }
    	 else if(wspuser.getPassword().equals(passwd))
    	 {
    		 id.setId(wspuser.getUserID2().toString());
    		 wspuser.setRid(RegisterID);
    		session.save(wspuser);
    		trans.commit();
    		 
    		 
    	 }else 
    	 {
    		 id.setId("err10002");
    	 }
    	 
     }catch(Exception e){
 		session.getTransaction().rollback();
 	}finally{
 		session.close();
 	}
	/*Configuration conf = new Configuration().configure();
	SessionFactory sf = conf.buildSessionFactory();
	JSONObject json = new JSONObject(this.reqContent);
	String tel=json.getString("tel");
	String passwd=json.getString("passwd");
	User user = new User(); //pojo闇�鎸佷箙鍖栫殑绫�
	Session session = sf.openSession();
	id = new IDString();//浼犺緭鐨刯son瀵硅薄
	try{
			session.beginTransaction();
		
			Query q = session.createQuery("from User where phonenumber=:tel"); 
			q.setParameter("tel",tel);
			user = (User) q.uniqueResult();
			if(user==null){
				id.setId("err10003");
			}
		//	user = (User)session.createQuery("from user where phonenumber='"+tel+"'");
			
			if(user.getPassword().equals(passwd)){
				id.setId(user.getId().toString());
			}
			else if(!user.getPassword().equals(passwd)){
				id.setId("err10002");
			}
	}catch(Exception e){
		session.getTransaction().rollback();
	}finally{
		session.close();
	}
	
//	System.out.println(id.getId());*/
     System.out.println("wsp:"+wspuser);
	return SUCCESS;

	}
public IDString getId() {
	return id;
}

public void setId(IDString id) {
	this.id = id;
}
}
