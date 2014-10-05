package com.wifihi.action.commerce.catering;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.wifihi.util.JDBCOpera;


public class OrderManage {
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
	private String orderset;
    
	private String username_sr;
	private String tableid_sr;
	private String status_sr;
    private String fromdate_sr;   
    private String todate_sr; 
    private String orderid;
    
    
    public String orderdetail(){
    	System.out.println("in orderdetail");
		try{
	        //当前页 
	        int intPage = Integer.parseInt((page == null || page == "0") ? "1":this.getPage()); 
	        //每页显示条数 
	        int number = Integer.parseInt((rows == null || rows == "0") ? "10":this.getRows()); 
	        //每页的开始记录  第一页为1  第二页为number +1  
	        int start = (intPage-1)*number;  

	        
			Connection con = JDBCOpera.CreateConn();	
			String querysql = "select od.DetailID,od.OrderId,gm.GoodsName,gm.Price,gm.Unit,od.Number from orderdetail od join goodsmanage gm on od.GoodsID=gm.GoodsID ";
			System.out.println("sql="+querysql);
			//判断查询条件，并采用模糊查询
	        System.out.println(this.getOrderid());
			String search_condi="";
			String str = null;
			if(this.getOrderid()!=null&&!this.getOrderid().equals("")){
				System.out.println("11111111111");
				str = " and od.OrderId = '"+this.getOrderid()+"'";	
				search_condi = search_condi.concat(str);
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
				System.out.println("goodsname="+rs.getString("GoodsName")+",detailid=" +rs.getInt("DetailID"));
				BigDecimal price = rs.getBigDecimal("Price");
				float xx = price.floatValue();
				int numberd = Integer.valueOf(rs.getInt("Number")).intValue();
				float sumprice = xx*numberd;
				JSONObject json = new JSONObject();
				json.put("orderid", rs.getInt("OrderId"));
				json.put("detailid", rs.getInt("DetailID"));
				json.put("goodsname", rs.getString("GoodsName"));
				json.put("price", rs.getBigDecimal("Price"));
				json.put("unit", rs.getString("Unit"));
				json.put("number", rs.getInt("Number"));
				json.put("sumprice", sumprice);
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
			orderset = json.toString();// 将json对象转化为String对象传输
			System.out.println(orderset);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
    	
    	return "success";
    }
    
	public String searchorder() {
		System.out.println("in searchorder");
		try{
	        //当前页 
	        int intPage = Integer.parseInt((page == null || page == "0") ? "1":this.getPage()); 
	        //每页显示条数 
	        int number = Integer.parseInt((rows == null || rows == "0") ? "10":this.getRows()); 
	        //每页的开始记录  第一页为1  第二页为number +1  
	        int start = (intPage-1)*number;  

			ActionContext actionContext = ActionContext.getContext();
			Map sess = actionContext.getSession();
			String wspuserid_str = (String) sess.get("wspuserid");
			Integer wspuserid = Integer.valueOf(wspuserid_str);
	        
	        
			Connection con = JDBCOpera.CreateConn();	
			String querysql = "select om.OrderId,om.TableID,om.OrderDate,om.OrderTime,om.Expenditure,om.Status,us.UserName from ordermanage om join user us on om.UserID=us.UserID ";
			System.out.println("sql="+querysql);
			//判断查询条件，并采用模糊查询
			System.out.println(this.getUsername_sr());
	        System.out.println(this.getTableid_sr());
	        System.out.println(this.getFromdate_sr());
	        System.out.println(this.getTodate_sr());
	        System.out.println(this.getStatus_sr());
			String search_condi="";
			String str = null;
			if(this.getUsername_sr()!=null&&!this.getUsername_sr().equals("")){
				System.out.println("11111111111");
				str = " and us.UserName like '%"+this.getUsername_sr()+"%'";	
				search_condi = search_condi.concat(str);
			}
	
			if(this.getTableid_sr()!=null&&!this.getTableid_sr().equals("")){
				System.out.println("11111111111");
				str = " and om.TableID like '%"+this.getTableid_sr()+"%'";	
				search_condi = search_condi.concat(str);
			}
		
			if(this.getStatus_sr()!=null&&!this.getStatus_sr().equals("")){
				System.out.println("11111111111");
				str = " and om.Status like '%"+this.getStatus_sr()+"%'";	
				search_condi = search_condi.concat(str);
			}
			
			if(wspuserid!=null){
				System.out.println("222222222222");
				str = " and om.WSPUser = "+wspuserid;	
				search_condi = search_condi.concat(str);
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt1;
			Date dt2;
			if(this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals("")&&
			   !(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and om.OrderDate >= '"+this.getFromdate_sr()+"'";
				search_condi = search_condi.concat(str);
		        try {
		             dt1 = df.parse(getFromdate_sr());
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
			}
			else if(!(this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals(""))&&
				(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and om.OrderDate <= '"+this.getTodate_sr()+"'";
				search_condi = search_condi.concat(str);
		        try {
		             dt2 = df.parse(getTodate_sr());
		        } catch (Exception exception) {
		            exception.printStackTrace();
		        }
			}
			else if((this.getFromdate_sr()!=null&&!this.getFromdate_sr().equals(""))&&
					(this.getTodate_sr()!=null&&!this.getTodate_sr().equals(""))){
				str = " and om.OrderDate between '"+this.getFromdate_sr()+"' and '"+this.getTodate_sr()+"'";
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
				System.out.println("username="+rs.getString("UserName")+",tableid=" +rs.getInt("TableID"));
				JSONObject json = new JSONObject();
				json.put("orderid", rs.getInt("OrderId"));
				json.put("tableid", rs.getInt("TableID"));
				json.put("username", rs.getString("UserName"));
				json.put("orderdate", rs.getDate("OrderDate"));
				json.put("ordertime", rs.getTime("OrderTime"));
				json.put("expenditure", rs.getFloat("Expenditure"));
				json.put("status", rs.getString("Status"));
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
			orderset = json.toString();// 将json对象转化为String对象传输
			System.out.println(orderset);
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

	public String getUsername_sr() {
		return username_sr;
	}

	public void setUsername_sr(String username_sr) {
		this.username_sr = username_sr;
	}

	public String getTableid_sr() {
		return tableid_sr;
	}

	public void setTableid_sr(String tableid_sr) {
		this.tableid_sr = tableid_sr;
	}

	public String getStatus_sr() {
		return status_sr;
	}

	public void setStatus_sr(String status_sr) {
		this.status_sr = status_sr;
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

	public String getOrderset() {
		return orderset;
	}

	public void setOrderset(String orderset) {
		this.orderset = orderset;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}


	
	

}
