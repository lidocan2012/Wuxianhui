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
import com.wifihi.persistence.Tablemanage;
import com.wifihi.persistence.TablemanagePK;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.TablemanageDAO;
import com.wifihi.persistence.dao._RootDAO;

public class TableManage {
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String tableset;
    
	private String tableid;
	private String descripe;

	private String tableid_sr;
	private String descripe_sr;

	public String newtable(){
		System.out.println("in method newtable");

		System.out.println("this.getTableid()="+this.getTableid());
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
		Integer tableid = Integer.valueOf(this.getTableid());
		TablemanagePK tpk = new TablemanagePK();
		tpk.setTableID(tableid);
		tpk.setWSPUser(wuser);		
	    TablemanageDAO tdao = new TablemanageDAO();
	    
	    Tablemanage ex = tdao.get(tpk);
		if(ex!=null){
			JSONObject json = new JSONObject();
			json.put("errorMsg", "此桌位已经存在！");
			tableset = json.toString();// 将json对象转化为String对象传输
			System.out.println(tableset);		
			return "success";			
		}		
		System.out.println("go to save table");
		Tablemanage tm = new Tablemanage();
		tm.setId(tpk);
		tm.setExtends(this.getDescripe());

		_RootDAO.initialize();
 
        TablemanagePK re = tdao.save(tm);
        System.out.println("save result, tableid="+re.getTableID());
        searchtable();
        return "success";
  		
	}

	
	public String updatetable(){
		System.out.println("in updatetable");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		wuser.setUserID2(id);
		wuser.setUserName(username);
		
		TablemanagePK tpk = new TablemanagePK();
		tpk.setTableID(Integer.valueOf(this.getTableid()));
		tpk.setWSPUser(wuser);
		
		Tablemanage tm = new Tablemanage();

		tm.setId(tpk);
		tm.setExtends(this.getDescripe());

		_RootDAO.initialize();
		System.out.println("&&&&&&&&&&&");
		TablemanageDAO udao = new TablemanageDAO();
		System.out.println("&&&&&&&&&&&");
		udao.update(tm);
		System.out.println("&&&&&&&&&&&");
        searchtable();
		return "success";		
	}
	
	public String deletetable() {
		System.out.println("in deletetable");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String)session.get("username");
		System.out.println("*********username="+username);
		String wspuserid = (String)session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		wuser.setUserID2(id);
		wuser.setUserName(username);
		Integer tableid = Integer.valueOf(this.getTableid());
		TablemanagePK tpk = new TablemanagePK();
		tpk.setTableID(Integer.valueOf(this.getTableid()));
		tpk.setWSPUser(wuser);		
		
		Tablemanage tm = new Tablemanage();
		tm.setId(tpk);
		
		_RootDAO.initialize();
		TablemanageDAO udao = new TablemanageDAO();
		System.out.println("before delete,id="+id);
		udao.delete(tm);
        searchtable();
		return "success";
	}	
	

	public String searchtable() {
		System.out.println("in searchtable");
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
		String hql = "from Tablemanage ";
		//判断查询条件，并采用模糊查询
		System.out.println(this.getTableid_sr());
		String search_condi="";
		String str = null;
		if(id!=null&&!id.equals("")){
			System.out.println("11111111111");
			str = " and WSPUser = '"+id+"'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getTableid_sr()!=null&&!this.getTableid_sr().equals("")){
			System.out.println("11111111111");
			str = " and TableID like '%"+this.getTableid_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if(this.getDescripe_sr()!=null&&!this.getDescripe_sr().equals("")){
			System.out.println("22222222222");
			str = " and Extends like '%"+this.getDescripe_sr()+"%'";		
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
        String totalhql = "select count(*) from Tablemanage ";
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
			Tablemanage tm = (Tablemanage) tables.get(i);
			JSONObject json = new JSONObject();
			json.put("tableid", tm.getId().getTableID());
			json.put("descripe", tm.getExtends());
			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total",total);//设置记录总数
		json.put("rows",jsonArray);
		tableset = json.toString();// 将json对象转化为String对象传输

		System.out.println(tableset);

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
	public String getTableset() {
		return tableset;
	}
	public void setTableset(String tableset) {
		this.tableset = tableset;
	}
	public String getTableid() {
		return tableid;
	}
	public void setTableid(String tableid) {
		this.tableid = tableid;
	}
	public String getDescripe() {
		return descripe;
	}
	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}
	public String getTableid_sr() {
		return tableid_sr;
	}
	public void setTableid_sr(String tableid_sr) {
		this.tableid_sr = tableid_sr;
	}
	public String getDescripe_sr() {
		return descripe_sr;
	}
	public void setDescripe_sr(String descripe_sr) {
		this.descripe_sr = descripe_sr;
	}




	
}
