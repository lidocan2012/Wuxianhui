package com.wifihi.terminalServerAction;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.json.JSONObject;

import com.wifihi.persistence.Wspuser;
import com.wifihi.terminalServerService.WspShowInfo;
import com.opensymphony.xwork2.ActionSupport;

public class ShowMenu extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;
	private WspShowInfo info;
	
	public WspShowInfo getInfo() {
		return info;
	}

	public void setInfo(WspShowInfo info) {
		this.info = info;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {   
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	
	public String wspShowMenu(){
	
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
	
		JSONObject json = new JSONObject(this.reqContent);
		long wspid = json.getLong("wspid");
		Wspuser wspuser = new Wspuser();
		info = new WspShowInfo();
		Session session = sf.openSession();
		try{
			session.beginTransaction();
			Query q = session.createQuery("from Wspuser where wspuser=:wspid"); 
			q.setParameter("wspid",wspid);
			wspuser = (Wspuser) q.uniqueResult();
			if(wspuser==null){
				info.setInfo("error");
				System.out.println(info.getInfo());
			}
			else
			{
				
				info.setInfo(wspuser.getShowinfo());
				System.out.println(info.getInfo());
			}
		}catch(Exception e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
			return SUCCESS;
	}
}

