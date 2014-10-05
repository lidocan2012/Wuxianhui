package com.wifihi.action.commerce.catering;

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
import com.wifihi.persistence.Goodstype;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.GoodstypeDAO;
import com.wifihi.persistence.dao._RootDAO;

public class GoodstypeManage {
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String goodstypeset;
    private String typeid;
	private String goodstype;
	private String descripe;
	private String extendsdesc;

	private String goodstype_sr;
	private String descripe_sr;

	public String newgoodstype(){
		System.out.println("in method newgoodstype");

		System.out.println("this.getgoodstype()="+this.getGoodstype());
		System.out.println("this.getDescripe()="+this.getDescripe());

		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		wuser.setUserID2(id);
		wuser.setUserName(username);
		boolean existf = exitgoodstype(this.getGoodstype(), id);

	    System.out.println("get goodstype");
		if(existf){
			JSONObject json = new JSONObject();
			json.put("errorMsg", "此商品类型已经存在！");
			goodstypeset = json.toString();// 将json对象转化为String对象传输
			System.out.println(goodstypeset);		
			return "success";			
		}		
		
		System.out.println("go to save goodstype");
		Goodstype tm = new Goodstype();
		tm.setGoodsType(this.getGoodstype());
		tm.setWSPUser(wuser);
		tm.setExtends(this.getExtendsdesc());
		tm.setGoodsTypeDescribe(this.getDescripe());

		_RootDAO.initialize();
		GoodstypeDAO tdao = new GoodstypeDAO();
		Long re = tdao.save(tm);
        System.out.println("save result, goodstype="+re);
        searchgoodstype();
        return "success";
  		
	}

	public boolean exitgoodstype(String typename, Long wspuserid){
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Goodstype where GoodsType='"+typename+"' and WSPUser="+wspuserid;
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
	
	public String updategoodstype(){
		System.out.println("in updategoodstype");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		wuser.setUserID2(id);
		wuser.setUserName(username);

		
		Goodstype tm = new Goodstype();
		String idstr = this.getTypeid();
		tm.setTypeID(Long.valueOf(idstr));
		tm.setGoodsType(this.getGoodstype());
		tm.setWSPUser(wuser);
		tm.setExtends(this.getExtendsdesc());
		tm.setGoodsTypeDescribe(this.getDescripe());

		_RootDAO.initialize();
		System.out.println("&&&&&&&&&&&");
		GoodstypeDAO udao = new GoodstypeDAO();
		System.out.println("&&&&&&&&&&&");
		udao.update(tm);
		System.out.println("&&&&&&&&&&&");
		searchgoodstype();
		return "success";		
	}
	
	public String deletegoodstype() {
		System.out.println("in deletegoodstype");
		String idstr = this.getTypeid();

		_RootDAO.initialize();
		GoodstypeDAO udao = new GoodstypeDAO();
		System.out.println("before delete,typeid="+idstr);
		udao.delete(Long.valueOf(idstr));
		searchgoodstype();
		return "success";
	}	
	

	public String searchgoodstype() {
		System.out.println("in searchgoodstype");
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
		String hql = "from Goodstype ";
		//判断查询条件，并采用模糊查询
		System.out.println(this.getGoodstype_sr());
		String search_condi="";
		String str = null;
		if(id!=null&&!id.equals("")){
			System.out.println("11111111111");
			str = " and WSPUser = '"+id+"'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getGoodstype_sr()!=null&&!this.getGoodstype_sr().equals("")){
			System.out.println("11111111111");
			str = " and GoodsType like '%"+this.getGoodstype_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getDescripe_sr()!=null&&!this.getDescripe_sr().equals("")){
			System.out.println("22222222222");
			str = " and GoodsTypeDescribe like '%"+this.getDescripe_sr()+"%'";		
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
        List tables = query.list();     
        
        //获取记录总数，用于前台分页工具栏中的记录总数
        String totalhql = "select count(*) from Goodstype ";
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
		System.out.println("tables.size=" + tables.size());
		for (int i = 0; i < tables.size(); i++) {
			Goodstype tm = (Goodstype) tables.get(i);
			JSONObject json = new JSONObject();
			json.put("typeid", tm.getTypeID());
			json.put("goodstype", tm.getGoodsType());
			json.put("descripe", tm.getGoodsTypeDescribe());
			json.put("extendsdesc", tm.getExtends());
			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		goodstypeset = json.toString();// 将json对象转化为String对象传输

		System.out.println(goodstypeset);

		return "success";
	}

	/*
	 * 用于商品管理时给出所有商品类型
	 */
	public String obtaingoodstype() {
		System.out.println("in getgoodstype");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);

		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session ss = sf.openSession();
		Transaction trans = ss.beginTransaction();
		String hql = "from Goodstype where WSPUser="+id;
		System.out.println("hql="+hql);
        Query query = ss.createQuery(hql);
        List goodtypes = query.list();  

		//将查询结果转化为Json对象以传到前台
		JSONArray jsonArray = new JSONArray();
		System.out.println("goodtypes.size=" + goodtypes.size());
		for (int i = 0; i < goodtypes.size(); i++) {
			Goodstype tm = (Goodstype) goodtypes.get(i);
			JSONObject json = new JSONObject();
			json.put("typeid", tm.getTypeID());
			json.put("goodstype", tm.getGoodsType());
			jsonArray.put(json);
		}
		goodstypeset = jsonArray.toString();// 将json对象转化为String对象传输
		System.out.println(goodstypeset);
		return "success";
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
	public String getDescripe() {
		return descripe;
	}
	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}
	public String getDescripe_sr() {
		return descripe_sr;
	}
	public void setDescripe_sr(String descripe_sr) {
		this.descripe_sr = descripe_sr;
	}


	public String getGoodstypeset() {
		return goodstypeset;
	}


	public void setGoodstypeset(String goodstypeset) {
		this.goodstypeset = goodstypeset;
	}


	public String getGoodstype() {
		return goodstype;
	}


	public void setGoodstype(String goodstype) {
		this.goodstype = goodstype;
	}


	public String getExtendsdesc() {
		return extendsdesc;
	}


	public void setExtendsdesc(String extendsdesc) {
		this.extendsdesc = extendsdesc;
	}


	public String getGoodstype_sr() {
		return goodstype_sr;
	}


	public void setGoodstype_sr(String goodstype_sr) {
		this.goodstype_sr = goodstype_sr;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}




	
}
