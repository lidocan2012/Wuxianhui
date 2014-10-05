package com.wifihi.action.wifimanage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.persistence.Userwifivisitinghistory;
import com.wifihi.persistence.Wifimanage;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.WifimanageDAO;
import com.wifihi.persistence.dao.WspuserDAO;
import com.wifihi.persistence.dao._RootDAO;
import com.wifihi.util.JDBCOpera;

/**
 * 根据Struts2框架，定义成员变量来获取前台传过来的值，一个需要获取数据的前台标签对应一个相同名字的成员变量，且需要定义get/set方法以获取标签值
 * @author su
 *
 */
public class WifiVisitingHistory extends ActionSupport{
	private String rows;//每页显示的记录数    
    private String page;//当前第几页  
    private String wifihistoryset;

	private String wifiname_sr;
	private String username_sr;
	private String authorizedresult_sr;
    private String fromdate_sr;   
    private String todate_sr; 
    

	public String searchhistory() {
		try{
	        //当前页 
	        int intPage = Integer.parseInt((page == null || page == "0") ? "1":this.getPage()); 
	        //每页显示条数 
	        int number = Integer.parseInt((rows == null || rows == "0") ? "10":this.getRows()); 
	        //每页的开始记录  第一页为1  第二页为number +1  
	        int start = (intPage-1)*number;  
	        
			Connection con = JDBCOpera.CreateConn();	
			String querysql = "select us.UserName, wm.WifiName, uv.AuthorizedResult, uv.WifiVisitingDate,uv.StartTime, uv.EndTime from userwifivisitinghistory uv join user us on uv.UserID=us.UserID join wifimanage wm on uv.WifiID=wm.WifiID ";
			System.out.println("sql="+querysql);
			//判断查询条件，并采用模糊查询
			System.out.println(this.getUsername_sr());
	        System.out.println(this.getWifiname_sr());
	        System.out.println(this.getFromdate_sr());
	        System.out.println(this.getTodate_sr());
	        System.out.println(this.getAuthorizedresult_sr());
			String search_condi="";
			String str = null;
			if(this.getUsername_sr()!=null&&!this.getUsername_sr().equals("")){
				System.out.println("11111111111");
				str = " and us.UserName like '%"+this.getUsername_sr()+"%'";	
				search_condi = search_condi.concat(str);
			}
	
			if(this.getWifiname_sr()!=null&&!this.getWifiname_sr().equals("")){
				System.out.println("11111111111");
				str = " and wm.WifiName like '%"+this.getWifiname_sr()+"%'";	
				search_condi = search_condi.concat(str);
			}
		
			if(this.getAuthorizedresult_sr()!=null&&!this.getAuthorizedresult_sr().equals("")){
				System.out.println("11111111111");
				if(this.getAuthorizedresult_sr().equals("1"))
				    str = " and uv.AuthorizedResult = 1";
				else
					str = " and uv.AuthorizedResult = 0";
				search_condi = search_condi.concat(str);
			}
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt1;
			Date dt2;
			if(this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals("")&&
			   !(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and uv.WifiVisitingDate >= '"+this.getFromdate_sr()+"'";
				search_condi = search_condi.concat(str);
		        try {
		             dt1 = df.parse(getFromdate_sr());
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
			}
			else if(!(this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals(""))&&
				(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and uv.WifiVisitingDate <= '"+this.getTodate_sr()+"'";
				search_condi = search_condi.concat(str);
		        try {
		             dt2 = df.parse(getTodate_sr());
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
			}
			else if((this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals(""))&&
					(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and uv.WifiVisitingDate between '"+this.getFromdate_sr()+"' and '"+this.getTodate_sr()+"'";
				search_condi = search_condi.concat(str);
			        try {
			             dt2 = df.parse(getTodate_sr());
			        } catch (Exception exception) {
			            exception.printStackTrace();
			        }
				}
			else{
				
			}
			if(!search_condi.equals("")){
				System.out.println("9999999999");
				search_condi = search_condi.substring(4);
				search_condi = "where "+search_condi;
				querysql = querysql.concat(search_condi);
			}
			String querysql_l = querysql.concat(" LIMIT "+start+","+number);
			
			System.out.println("querysql_l="+querysql_l);
			java.sql.Statement stm = con.createStatement();
			java.sql.ResultSet rs = stm.executeQuery(querysql_l);
			JSONArray jsonArray = new JSONArray();
			while (rs.next()) {
				System.out.print(rs.getString("UserName")+" " +rs.getString("WifiName"));
				System.out.println();
				
				JSONObject json = new JSONObject();
				json.put("wifiname", rs.getString("UserName"));
				json.put("username", rs.getString("WifiName"));
				json.put("visitdate", rs.getDate("WifiVisitingDate"));
				json.put("starttime", rs.getTime("StartTime"));
				json.put("endtime", rs.getTime("EndTime"));
				if(rs.getInt("AuthorizedResult")==1)
					json.put("authorizedresult", "通过");
				else
					json.put("authorizedresult", "拒绝");
				jsonArray.put(json);
			}
			
			//查询总数
			java.sql.ResultSet rsx = stm.executeQuery(querysql);
			int total = 0;
			while (rsx.next()) {
				total++;
			}
			
			JSONObject json = new JSONObject();
			json.put("total",total);//设置记录总数
			json.put("rows",jsonArray);
			wifihistoryset = json.toString();// 将json对象转化为String对象传输
			System.out.println(wifihistoryset);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
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


	public String getWifihistoryset() {
		return wifihistoryset;
	}


	public void setWifihistoryset(String wifihistoryset) {
		this.wifihistoryset = wifihistoryset;
	}


	public String getWifiname_sr() {
		return wifiname_sr;
	}


	public void setWifiname_sr(String wifiname_sr) {
		this.wifiname_sr = wifiname_sr;
	}


	public String getUsername_sr() {
		return username_sr;
	}


	public void setUsername_sr(String username_sr) {
		this.username_sr = username_sr;
	}


	public String getAuthorizedresult_sr() {
		return authorizedresult_sr;
	}


	public void setAuthorizedresult_sr(String authorizedresult_sr) {
		this.authorizedresult_sr = authorizedresult_sr;
	}


	public String getFromdate_sr() {
		return fromdate_sr;
	}


	public void setFromdate_sr(String fromdate_sr) {
		this.fromdate_sr = fromdate_sr;
	}


	public String getTodate_sr() {
		return todate_sr;
	}


	public void setTodate_sr(String todate_sr) {
		this.todate_sr = todate_sr;
	}
	

	
}
