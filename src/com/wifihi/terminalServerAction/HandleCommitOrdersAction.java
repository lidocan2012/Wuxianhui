package com.wifihi.terminalServerAction;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.Goodsmanage;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.persistence.Orderdetail;
import com.wifihi.persistence.Ordermanage;
import com.wifihi.persistence.Tablemanage;
import com.wifihi.persistence.TablemanagePK;
import com.wifihi.persistence.User;
import com.wifihi.persistence.Wspuser;

public class HandleCommitOrdersAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;

	public void setServletRequest(HttpServletRequest arg0) {
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	public String handleCommitOrders(){
		SessionFactory sf = HibernateSessionFactory.getSessionFactory();
		Session session = sf.openSession();
		JSONObject json = new JSONObject(this.reqContent);
		try{
			Date date = new Date();
			int tableId = Integer.parseInt(json.getString("tableId"));
			long userId = json.getLong("userId");
			long wspId = json.getLong("wspId");
			double totalSum = json.getDouble("totalSum");
			JSONArray goodsIdArray = json.getJSONArray("goodsIdArray");
			JSONArray numberArray = json.getJSONArray("numberArray");
			Transaction trans = session.beginTransaction();
			Ordermanage orderManage = new Ordermanage();
			
			
			User user = new User(userId);
			orderManage.setUserID(user);
			Wspuser wspUser = new Wspuser(wspId);
			orderManage.setWSPUser(wspUser);
			TablemanagePK pk = new TablemanagePK(tableId,wspUser);
			Tablemanage tableManage = new Tablemanage(pk);
			orderManage.setTableID(tableManage);
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");	
			orderManage.setOrderDate(sdf1.parse(sdf1.format(date)));
			orderManage.setOrderTime(sdf2.parse(sdf2.format(date)));
			Set<Orderdetail> orderDetailSet = orderManage.getOrderdetails();
			if(orderDetailSet==null) {
				orderDetailSet = new HashSet<Orderdetail>();
			}
			orderManage.setExpenditure(new BigDecimal(totalSum));
			orderManage.setStatus("δ����");
			
			for(int i=0;i<goodsIdArray.length();i++){
				Orderdetail orderDetail = new Orderdetail();
				orderDetail.setWSPUser(wspUser);
				orderDetail.setOrder(orderManage);
				Goodsmanage goodsManage = new Goodsmanage(goodsIdArray.getLong(i));
				Set<Goodsmanage> goodsManages = new HashSet<Goodsmanage>();
				goodsManages.add(goodsManage);
				orderDetail.setGoodsmanages(goodsManages);
				orderDetail.setGoodsID(goodsManage);
				orderDetail.setNumber(numberArray.getInt(i));
				session.save(orderDetail);
				orderDetailSet.add(orderDetail);
			}
			session.save(orderManage);
			trans.commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
		return SUCCESS;
	}
}
