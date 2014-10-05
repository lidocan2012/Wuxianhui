package com.wifihi.terminalServerAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.APIConnectionException;
import cn.jpush.api.common.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.persistence.Wifimanage;
import com.wifihi.persistence.Wspuser;
import com.wifihi.terminalServerService.IDString;
//import com.wifihi.persistence.wspu;
import com.wifihi.terminalServerService.Wifi;

public class WifiAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;
	private Wifi wifi;  //返回passwd的
	private List<Wifi> wifiList = new ArrayList<Wifi>(); //返回的List
	private IDString id;
	
	//鉴权
	private static final String appKey="63bf6dbe60ad541c4c83d155";
	private static final String masterSecret="df5fafb42ab511848d9c4f3d";
	private static int timeToLive =  60 * 60 * 24; 
	public static final String ALERT = "鉴权";
	public  JPushClient jpush= null;
	private String passwd="";
	//public static final String MSG_CONTENT ="";
	public String WspAuthorise() throws APIConnectionException, APIRequestException
	{
		JSONObject json = new JSONObject(this.reqContent);
		JPushClient jpush= null;
		String result = json.getString("result");
		String mac = json.getString("mac");
		String WifiRID = json.getString("WifiRID");
		if(result.equals("agree"))
		{
			SessionFactory sf = HibernateSessionFactory.getSessionFactory();
			Session session = sf.openSession();//同意授权，允许wifi用户连接wifi
			IDString	id = new IDString();
			Wifimanage macAdd = new Wifimanage();
			session.beginTransaction();
			Query q = session.createQuery("from Wifimanage  where MacAddress like :mac");
			q.setParameter("mac", "%"+mac+"%");
			macAdd = (Wifimanage)q.uniqueResult();
			
			passwd = macAdd.getPassword();
			System.out.println("同意");
		}
		else{
			//拒接授权，不允许用户 连接wifi
		    passwd= "";
		    System.out.println("拒绝");
		    
		}
		jpush = new JPushClient(masterSecret, appKey, timeToLive); 
		System.out.println("创建JPush");
		System.out.println("密码："+passwd);
		PushPayload payload = buildPushObject_all_all_alert(WifiRID,passwd);
		System.out.println("即将推送");
		PushResult result1 = jpush.sendPush(payload);//向RgisterID为RID的wifi客户端推送消息
		
		System.out.println(result1);
		id.setId("10001");//消息推送成功
		//唤醒rtnAuthorise()进程
		return SUCCESS;
		
	}
	public String rtnAuthorise(){
		JSONObject json = new JSONObject(this.reqContent);
		String mac = json.getString("mac");
		String username =json.getString("username");
		String wifiRID = json.getString("WifiRID");
		//Configuration conf = new Configuration().configure();
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		wifi = new Wifi();
		Wifimanage macAdd = new Wifimanage();
		try{
				session.beginTransaction();
				Query q = session.createQuery("from Wifimanage  where MacAddress like :mac");
				q.setParameter("mac", "%"+mac+"%");
				macAdd = (Wifimanage)q.uniqueResult();
				if(macAdd!=null){
					if(macAdd.isAuthorise()==false)
					{
						wifi.setWifiname(macAdd.getWifiName());
						wifi.setPasswd(macAdd.getPassword());
						wifi.setAuthorise(macAdd.isAuthorise());
					}
					else{ 
						String hqlwsp = "from Wspuser  where wspuser = :wspid";
						Wspuser wspuser = new Wspuser(); 
						q = session.createQuery(hqlwsp);
						q.setParameter("wspid",macAdd.getWSPUser().getUserID2());
						wspuser = (Wspuser)q.uniqueResult();
						String RID = wspuser.getRid();
						System.out.println("向WSPRID为："+RID);
						StringBuilder msg= new StringBuilder();
						msg.append(username+","+mac+","+wifiRID);
						System.out.println("msg："+msg.toString());
					
					
						jpush = new JPushClient(masterSecret, appKey, timeToLive); 
						System.out.println("1");
						PushPayload payload = buildPushObject_all_all_alert(RID,msg.toString());
						System.out.println("2");
						PushResult result = jpush.sendPush(payload);//向注册id为RID的WSP客户端推送消息
						System.out.println(result);
						
						
						//线程阻塞，等待WSP客户端的HTTP请求发来的信息，由WSPAuthorise()唤醒
						
					
						wifi.setWifiname(macAdd.getWifiName());
						wifi.setPasswd("");
						wifi.setAuthorise(macAdd.isAuthorise());
				}
				
			}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("wifii"+wifi.toString());
		return SUCCESS;
	}

	public PushPayload buildPushObject_all_all_alert(String registrationId,String MSG_CONTENT) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.all())
				.setAudience(Audience.registrationId(registrationId))
			.setMessage(Message.newBuilder().setMsgContent(MSG_CONTENT)
					.build())
				.build();
	}
    //返回用户名密码
	public String rtnWifi(){
		JSONObject json = new JSONObject(this.reqContent);
		String mac = json.getString("mac");
		String hql = "from Wifimanage where MacAddress like :mac";
		String hqlwsp = "from Wspuser  where wspuser = :wspid";

		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		wifi = new Wifi();
		Wifimanage macAdd = new Wifimanage();
		Wspuser wspuser = new Wspuser();   
		try{
				session.beginTransaction();
				Query q = session.createQuery(hql);
				q.setParameter("mac", "%"+mac+"%");
				macAdd = (Wifimanage)q.uniqueResult();
				if(macAdd!=null){
					wifi.setMac(mac); 
					//wifi.setPasswd(macAdd.getPassword());
					wifi.setDisplayname(macAdd.getDisplayName().toString());
					q = session.createQuery(hqlwsp);
					q.setParameter("wspid", macAdd.getWSPUser().getUserID2());
					System.out.println("wspid:"+macAdd.getWSPUser().getUserID2());
					wspuser = (Wspuser)q.uniqueResult();
					wifi.setWspusername(wspuser.getUserName());
					wifi.setWspId(wspuser.getUserID2());
				}
				else
					wifi.setPasswd("err10004");
			}catch(Exception e){
				System.out.println("Something Wrong in select wifiPassword.");
		}
		return SUCCESS;
	}
	
	//return wifiList
	public String rtnWifiList(){
	
		JSONArray jsonArray = new JSONArray(this.reqContent);
		String[] macs = new String[jsonArray.length()];
		for(int i = 0; i < jsonArray.length(); i++){
			macs[i] = jsonArray.getJSONObject(i).getString("mac"); 
		}
		//建立连接
		System.out.println("jsonarray:"+jsonArray.toString());
		//Configuration conf = new Configuration().configure();
		//SessionFactory sf = conf.buildSessionFactory();
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		
		String hql = "from Wifimanage  where MACAddress like :mac"; //区分大小写
		//String hqlwsp = "from Wspuser  where wspuser = :wspid";
		String hqlwsp = "from Wspuser  where wspuser =:wspid";
		Wifimanage maci = new Wifimanage();   //查询时使用
		
		Wspuser wspuser = new Wspuser();             //添加对象
		
		for(int i = 0; i < macs.length; i++){
			try{
				Wifi wifii = new Wifi(); 
				session.beginTransaction();
				Query q = session.createQuery(hql); //查找
				q.setParameter("mac", "%"+macs[i]+"%");
			  	maci = (Wifimanage) q.uniqueResult(); //获取当前对象
				if(maci==null) continue;               //若为空则继续
				else{
					wifii.setMac(macs[i]); 
					//设置该mac
					wifii.setWifiname(maci.getWifiName().toString());
					wifii.setDisplayname(maci.getDisplayName().toString());
					q = session.createQuery(hqlwsp);
					q.setParameter("wspid", maci.getWSPUser().getUserID2());
					System.out.println("wspid:"+maci.getWSPUser().getUserID2());
					wspuser = (Wspuser)q.uniqueResult();
					wifii.setWspusername(wspuser.getUserName());
					wifii.setWspId(wspuser.getUserID2());
					wifiList.add(wifii);
					System.out.println("wifiList:"+wifiList.toString());
					System.out.println("wifii"+wifii.toString());
				}//添加wifii对象
			}catch(Exception e){
				session.getTransaction().rollback();
			}
			finally{
				//session.close();//如果用c3p0好像不用关闭..
			}
		}
		System.out.println("wifiList:"+wifiList.toString());
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.reqContent = GetRequestAction.getJsonContent(arg0);
		System.out.println("reqContent: " + reqContent);//多个json对象
	}
	public Wifi getWifi() {
		return wifi;
	}

	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}
	public List<Wifi> getWifiList() {
		return wifiList;
	}

	public void setWifi(List<Wifi> wifiList) {
		this.wifiList = wifiList;
	}
	public IDString getId()
	{
		return this.id;
	}
	public void setId(IDString id)
	{
		this.id = id;
		

	}
}
