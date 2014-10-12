package com.wifihi.terminalServerAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import com.wifihi.terminalServerService.DBOperate;

public class HandleCommitOrdersAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 1L;
	private String reqContent = null;

	public void setServletRequest(HttpServletRequest arg0) {
		this.reqContent = GetRequestAction.getJsonContent(arg0);  //static method getJsonContent no new
		System.out.println("reqContent: " + reqContent);
	}
	public String handleCommitOrders(){
		try{
			String sql1 = "insert into ordermanage(WSPuser,UserId,TableId,OrderDate,OrderTime,Expenditure,Status)values(?,?,?,?,?,?,?)";
			String sql2 = "insert into orderdetail (OrderId,GoodsId,WSPUser,Number) values(?,?,?,?)";
			PreparedStatement pstmt = DBOperate.getDBOperate().getConn().prepareStatement(sql1,PreparedStatement.RETURN_GENERATED_KEYS);
			JSONObject json = new JSONObject(this.reqContent);
			Date date = new Date();
			String tableId = json.getString("tableId");
			long userId = json.getLong("userId");
			long wspId = json.getLong("wspId");
			double totalSum = json.getDouble("totalSum");
			JSONArray goodsIdArray = json.getJSONArray("goodsIdArray");
			JSONArray numberArray = json.getJSONArray("numberArray");
			
			pstmt.setLong(1, wspId);
			pstmt.setLong(2, userId);
			pstmt.setLong(3, Long.parseLong(tableId));
			pstmt.setDate(4, new java.sql.Date(date.getTime()));
			pstmt.setTime(5, new java.sql.Time(date.getTime()));
			pstmt.setDouble(6, totalSum);
			pstmt.setString(7, "未结账");
			pstmt.execute();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			long orderId=-1;
			if(rs.next()){
				orderId = rs.getLong(1);
				pstmt=DBOperate.getDBOperate().getConn().prepareStatement(sql2);
				pstmt.setLong(1,orderId);
				pstmt.setLong(3, wspId);
				for(int i=0;i<goodsIdArray.length();i++){
					pstmt.setLong(2, goodsIdArray.getLong(i));
					pstmt.setInt(4, numberArray.getInt(i));
					pstmt.execute();
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		}
		return SUCCESS;
	}
}
