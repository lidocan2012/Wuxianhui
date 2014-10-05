package com.wifihi.terminalServerAction;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.Goodsmanage;
import com.wifihi.persistence.HibernateSessionFactory;
import com.wifihi.terminalServerService.Goods;

public class GetDishesAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;
	private List<Goods> goodsList = new ArrayList<Goods>();
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goods) {
		this.goodsList = goods;
	}
	public void setServletRequest(HttpServletRequest arg0) {
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	@SuppressWarnings("unchecked")
	public String getDishes(){
		SessionFactory sf = com.wifihi.persistence.HibernateSessionFactory.getSessionFactory();
		JSONObject json = new JSONObject(this.reqContent);
		long wspid=json.getLong("wspid");
		Session session = sf.openSession();
		try{
			session.beginTransaction();
			Query q = session.createQuery("from Goodsmanage where wspuser=:wspid"); 
			q.setParameter("wspid",wspid);
			List<Goodsmanage> goodsManages = (List<Goodsmanage>) q.list();
			if(goodsManages==null){
				System.out.println("error");
			}
			for(int i=0;i<goodsManages.size();i++){
				Goodsmanage goodsmanage = goodsManages.get(i);
				Goods goods = new Goods();
				goods.setGoodsType(goodsmanage.getGoodsType().getGoodsType());
				goods.setGoodsName(goodsmanage.getGoodsName());
				goods.setImageUrl(goodsmanage.getPicture());
				goods.setPrice(goodsmanage.getPrice().doubleValue());
				goods.setGoodsId(goodsmanage.getId());
				goodsList.add(goods);
			}
		}catch(Exception e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
			return SUCCESS;
	}
}
