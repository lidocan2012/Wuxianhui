package com.wifihi.terminalServerAction;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.persistence.User;
import com.wifihi.terminalServerService.FindPassResult;
import com.wifihi.terminalServerService.IDString;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;   
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;   
import org.dom4j.Element; 

public class LoginAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;
	private IDString id;
	private FindPassResult findPassResult;


	@Override
	public void setServletRequest(HttpServletRequest arg0) {   //閼惧嘲绶辨潻娆愵偧鐠囬攱鐪伴惃鍓卐quest鐎电钖�
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	
	public String userLoginCheck(){
		//Configuration conf = new Configuration().configure();
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		JSONObject json = new JSONObject(this.reqContent);
		String tel=json.getString("tel");
		String passwd=json.getString("passwd");
		User user = new User(); 
		Session session = sf.openSession();
		id = new IDString();
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
					System.out.println(id.getId());
				}
				else if(!user.getPassword().equals(passwd)){
					id.setId("err10002");
				}
		}catch(Exception e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		
	//	System.out.println(id.getId());
		return SUCCESS;
	}
	public String findPassword(){
		//Configuration conf = new Configuration().configure();
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		JSONObject json = new JSONObject(this.reqContent);
		String tel=json.getString("tel");
		User user = new User(); 
		Session session = sf.openSession();
		findPassResult = new FindPassResult();
		try{
				session.beginTransaction();
			
				Query q = session.createQuery("from User where phonenumber=:tel"); 
				q.setParameter("tel",tel);
				user = (User) q.uniqueResult();
				if(user==null){
					findPassResult.setResult("err10003");
				}
				String password = user.getPassword();
				sendsms(tel,password);
				findPassResult.setResult("success");
		}catch(Exception e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		
	//	System.out.println(id.getId());
		return SUCCESS;
	}
	public void sendsms(String phonenumber , String password){
		String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";
		org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient(); 
		PostMethod method = new PostMethod(Url); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
		//System.out.println(mobile);
		
	    String content = new String("您的验证码是：" + password + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {//提交短信
			    new NameValuePair("account", "cf_lidocane"), 
			    new NameValuePair("password", "529193429"), //密码可以使用明文密码或使用32位MD5加密
			    //new NameValuePair("password", StringUtil.MD5Encode("529193429")),
			    new NameValuePair("mobile", phonenumber), 
			    new NameValuePair("content", content),
		};
		method.setRequestBody(data);
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			String code = root.elementText("code");	
			String msg = root.elementText("msg");	
			String smsid = root.elementText("smsid");	
			
			
			System.out.println(code);
			System.out.println(msg);
			System.out.println(smsid);
						
			if(code == "2"){
				System.out.println("短信提交成功");
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public IDString getId() {
		return id;
	}

	public void setId(IDString id) {
		this.id = id;
	}

	public FindPassResult getFindPassResult() {
		return findPassResult;
	}

	public void setFindPassResult(FindPassResult findPassResult) {
		this.findPassResult = findPassResult;
	}
	
}
