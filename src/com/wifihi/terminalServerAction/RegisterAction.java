package com.wifihi.terminalServerAction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.persistence.User;
import com.wifihi.terminalServerService.IDString;



public class RegisterAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;
	private IDString id;

	public IDString getId() {
		return id;
	}

	public void setId(IDString id) {
		this.id = id;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {   //获得这次请求的request对象
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	
	public String registerPrivateUser() throws ParseException{
		//Configuration conf = new Configuration().configure();
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		JSONObject json = new JSONObject(this.reqContent);
		String passwd = json.getString("passwd");
		String telNum = json.getString("tel");
		User user = new User();
		user.setPassword(passwd);
		user.setPhoneNumber(telNum);
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		user.setRegistrateDate(sdf1.parse(sdf1.format(new Date())));
		user.setRegistrateTime(sdf2.parse(sdf2.format(new Date())));
		id= new IDString();
		Session session = sf.openSession();
		try{
			Transaction trans = session.beginTransaction();
			session.save(user);	
			id.setId(user.getId().toString());
			trans.commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}

		
		return SUCCESS;
	}
	
}
