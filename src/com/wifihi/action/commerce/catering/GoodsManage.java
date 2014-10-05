package com.wifihi.action.commerce.catering;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.wifihi.persistence.Goodsmanage;
import com.wifihi.persistence.Goodstype;
import com.wifihi.persistence.Wspuser;
import com.wifihi.persistence.dao.GoodsmanageDAO;
import com.wifihi.persistence.dao.GoodstypeDAO;
import com.wifihi.persistence.dao.TablemanageDAO;
import com.wifihi.persistence.dao._RootDAO;



import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.opensymphony.xwork2.ActionSupport;

public class GoodsManage {
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
	private String goodsset;

	private String goodsid;
	private String goodsname;
	private String typeid;
	private String descripe;
	private String price;
	private String unit;
	private String priceunit;
	private String picture;

	private String goodsname_sr;
	private String goodstype_sr;
	private String descripe_sr;

    private String uploadPath = "E:\\Wuxianhui\\Wuxianhui\\WebContent\\commerce\\catering\\img"; // 上传文件的目录
    private String tempPath = "d:\\temp\\buffer\\"; // 临时文件目录
    File tempPathFile;
    private String filename;

    public String fileupload() throws IOException, ServletException {      	
		JSONObject json = new JSONObject();
    	try {
    	   System.out.println("in fileupload");
    	   HttpServletRequest request = ServletActionContext.getRequest();
   	       MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;   
           File file = wrapper.getFiles("myfile")[0];   
           String fileName = wrapper.getFileNames("myfile")[0];  
           
            // Create a factory for disk-based file items
           DiskFileItemFactory factory = new DiskFileItemFactory();
           System.out.println("**********filename="+fileName);
           // Set factory constraints
           factory.setSizeThreshold(4096000); // 设置缓冲区大小，这里是4kb
           factory.setRepository(tempPathFile);// 设置缓冲区目录
           System.out.print("0000000000000000");

           // Create a new file upload handler
           ServletFileUpload upload = new ServletFileUpload(factory);

           // Set overall request size constraint
           //upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB
           System.out.print("66666666666666");
           
           InputStream in = new FileInputStream(file);    
           File uploadFile = new File(uploadPath, fileName);    
           OutputStream out = new FileOutputStream(uploadFile);    
           System.out.print("1111111111111111111");
           
           byte[] buffer = new byte[1024 * 1024];    
           int length;    
           while ((length = in.read(buffer)) > 0) {   
               System.out.print("44444444444");
               out.write(buffer, 0, length);   
                System.out.print("2222222222222");
           }    
           System.out.print("------------------");

           in.close();    
           out.close();  
   		   json.put("filename", fileName);

           System.out.print("upload succeed ****111****");
       } catch (Exception e) {
  		   json.put("errorMsg", "file upload failed");
           e.printStackTrace();
       }
       goodsset = json.toString();// 将json对象转化为String对象传输
	   System.out.println(goodsset);		
       return "success";
    }

    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public String filedelete() {    
    	System.out.println("in filedelete,filename="+this.getFilename());
    	JSONObject json = new JSONObject();
        File file = new File(uploadPath, this.getFilename());  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) { 
        	System.out.println("被删除的文件不为空");
            file.delete();  
            json.put("deleteresult", "true"); 
        }  
        else{
        	json.put("errorMsg", "删除失败"); 
        }
        goodsset = json.toString();// 将json对象转化为String对象传输
 	    System.out.println(goodsset);		
        return "success";  
    }  

	public String newrecord() {
		System.out.println("in method newrecord");

		System.out.println("this.getGoodsname()=" + this.getGoodsname());
		System.out.println("this.getDescripe()=" + this.getDescripe());
		System.out.println("this.getTypeid()=" + this.getTypeid());

		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String) session.get("username");
		String wspuserid = (String) session.get("wspuserid");
		System.out.println("*********username=" + username+",wspuserid="+wspuserid);
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		//id = new Long(74);
		wuser.setUserID2(id);
		wuser.setUserName(username);
		System.out.println("#############");
		Long typeidl = Long.valueOf(this.getTypeid());
		Goodstype gt = new Goodstype();
		gt.setTypeID(Long.valueOf(typeidl));

		GoodsmanageDAO gdao = new GoodsmanageDAO();

		System.out.println("go to save goods");
		Goodsmanage tm = new Goodsmanage();
		tm.setGoodsType(gt);
		tm.setWSPUser(wuser);
		tm.setGoodsName(this.getGoodsname());
		tm.setDescription(this.getDescripe());
		tm.setPriceUnit(this.getPriceunit());
		System.out.println("#############,price="+this.getPrice());		
		long p;
		if(this.getPrice().equals("")){
			p=0;
		}
		else{
			p = Long.valueOf(this.getPrice());
		}
		System.out.println("price="+p);
		java.math.BigDecimal pri = java.math.BigDecimal.valueOf(p);
		tm.setPrice(pri);
		tm.setUnit(this.getUnit());
		//tm.setPicture(uploadPath+'\\'+this.getFilename());
		tm.setPicture(this.getFilename());
		System.out.println("#############,filename="+this.getFilename());

		_RootDAO.initialize();

		Long re = gdao.save(tm);
		System.out.println("save result, goodsid=" + re);
		searchrecord();
		return "success";

	}

	public String updaterecord() {
		System.out.println("in updaterecord");
		ActionContext actionContext = ActionContext.getContext();
		Map session = actionContext.getSession();
		String username = (String) session.get("username");
		System.out.println("*********username=" + username);
		String wspuserid = (String) session.get("wspuserid");
		Long id = Long.valueOf(wspuserid);
		Wspuser wuser = new Wspuser();
		wuser.setUserID2(id);
		wuser.setUserName(username);
		System.out.println("--------------");
		Long typeidl = Long.valueOf(this.getTypeid());
		Goodstype gt = new Goodstype();
		gt.setTypeID(Long.valueOf(typeidl));
		System.out.println("--------------"+this.getGoodsid());
		Goodsmanage tm = new Goodsmanage();
		tm.setId(Long.valueOf(this.getGoodsid()));
		System.out.println("--------------");
		tm.setGoodsType(gt);
		tm.setWSPUser(wuser);
		tm.setGoodsName(this.getGoodsname());
		tm.setDescription(this.getDescripe());
		tm.setPriceUnit(this.getPriceunit());
		System.out.println("#############,price="+this.getPrice());		
		long p;
		if(this.getPrice().equals("")){
			p=0;
		}
		else{
			p = Long.valueOf(this.getPrice());
		}
		System.out.println("price="+p+",Picture="+this.getFilename());
		java.math.BigDecimal pri = java.math.BigDecimal.valueOf(p);
		tm.setPrice(pri);
		tm.setUnit(this.getUnit());
		//tm.setPicture(uploadPath+'\\'+this.getFilename());
		tm.setPicture(this.getFilename());
		_RootDAO.initialize();
		GoodsmanageDAO gdao = new GoodsmanageDAO();
		gdao.update(tm);
		System.out.println("&&&&&&&&&&&");
		searchrecord();
		return "success";
	}

	public String deleterecord() {
		System.out.println("in deleterecord");

		_RootDAO.initialize();
		GoodsmanageDAO gdao = new GoodsmanageDAO();
		System.out.println("before delete,id=" + Long.valueOf(this.getGoodsid()));
		gdao.delete(Long.valueOf(this.getGoodsid()));
		searchrecord();
		return "success";
	}

	public String searchrecord() {
		System.out.println("in searchrecord");
		ActionContext actionContext = ActionContext.getContext();
		Map sess = actionContext.getSession();
		String wspuserid = (String) sess.get("wspuserid");
		Wspuser wuser = new Wspuser();
		Long id = Long.valueOf(wspuserid);
		wuser.setUserID2(id);

		// 当前页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: this.getPage());
		// 每页显示条数
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: this.getRows());
		// 每页的开始记录 第一页为1 第二页为number +1
		int start = (intPage - 1) * number;

		System.out.println("the index of Page = " + intPage
				+ ",the number of record in a page = " + number);

		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction trans = session.beginTransaction();
		String hql = "from Goodsmanage ";
		// 判断查询条件，并采用模糊查询
		System.out.println(this.getGoodstype_sr());
		String search_condi = "";
		String str = null;
		if (id != null && !id.equals("")) {
			System.out.println("11111111111");
			str = " and WSPUser = '" + id + "'";
			search_condi = search_condi.concat(str);
		}
		if (this.getGoodsname_sr() != null
				&& !this.getGoodsname_sr().equals("")) {
			System.out.println("11111111111");
			str = " and GoodsName like '%" + this.getGoodsname_sr() + "%'";
			search_condi = search_condi.concat(str);
		}
		if(this.getGoodstype_sr()!=null&&!this.getGoodstype_sr().equals("")){
			System.out.println("11111111111");
			str = " and GoodsType like '%"+this.getGoodstype_sr()+"%'";	
			search_condi = search_condi.concat(str);
		}
		if (this.getDescripe_sr() != null && !this.getDescripe_sr().equals("")) {
			System.out.println("22222222222");
			str = " and Description like '%" + this.getDescripe_sr()
					+ "%'";
			search_condi = search_condi.concat(str);
		}
		if (!search_condi.equals("")) {
			System.out.println("9999999999");
			search_condi = search_condi.substring(4);
			search_condi = "where " + search_condi;
			hql = hql.concat(search_condi);
		}
		System.out.println("hql=" + hql);
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(number);
		List tables = query.list();

		// 获取记录总数，用于前台分页工具栏中的记录总数
		String totalhql = "select count(*) from Goodsmanage ";
		if (!search_condi.equals("")) {
			System.out.println("111111111111");
			totalhql = totalhql.concat(search_condi);
		}
		System.out.println(totalhql);
		Query querytotal = session.createQuery(totalhql);
		int total = ((Number) querytotal.uniqueResult()).intValue();
		System.out.println("----the total number of record= " + total);

		trans.commit();
		if (session.isOpen()) {
			session.close();
		}
		// 将查询结果转化为Json对象以传到前台
		JSONArray jsonArray = new JSONArray();
		System.out.println("tables.size=" + tables.size());
		for (int i = 0; i < tables.size(); i++) {
			Goodsmanage tm = (Goodsmanage) tables.get(i);
			JSONObject json = new JSONObject();
			json.put("goodsid", tm.getId());
			json.put("goodsname", tm.getGoodsName());
			json.put("goodstype", tm.getGoodsType().getGoodsType());
			json.put("typeid", tm.getGoodsType().getTypeID());
			json.put("descripe", tm.getDescription());
			json.put("unit", tm.getUnit());
			json.put("price", tm.getPrice());
			json.put("priceunit", tm.getPriceUnit());
			json.put("picture", tm.getPicture());
			jsonArray.put(json);
		}
		JSONObject json = new JSONObject();
		json.put("total", total);// 设置记录总数
		json.put("rows", jsonArray);
		goodsset = json.toString();// 将json对象转化为String对象传输

		System.out.println(goodsset);

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



	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getGoodstype_sr() {
		return goodstype_sr;
	}

	public void setGoodstype_sr(String goodstype_sr) {
		this.goodstype_sr = goodstype_sr;
	}

	public String getGoodsname() {
		return goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getGoodsname_sr() {
		return goodsname_sr;
	}

	public void setGoodsname_sr(String goodsname_sr) {
		this.goodsname_sr = goodsname_sr;
	}

	public String getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(String goodsid) {
		this.goodsid = goodsid;
	}


	public String getGoodsset() {
		return goodsset;
	}


	public void setGoodsset(String goodsset) {
		this.goodsset = goodsset;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getPriceunit() {
		return priceunit;
	}

	public void setPriceunit(String priceunit) {
		this.priceunit = priceunit;
	}
	
	

}
