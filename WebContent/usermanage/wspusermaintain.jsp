<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<meta charset="UTF-8">

<!--引入easyui的css和js包 -->
<link rel="stylesheet" type="text/css" href="../css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../css/themes/color.css">
<link rel="stylesheet" type="text/css" href="../demo/demo.css">
<script type="text/javascript" src="../js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/ProJson.js"></script>
<script type="text/javascript" src="../js/CityJson.js"></script>
<script type="text/javascript" src="../js/DistrictJson.js"></script>


<title>无标题文档</title>

<body>

<h3 align="center">请维护信息</h3>
<div id="box" style="width: 800px; height: 400px; margin: 0 auto 0 auto;"  align="center">
<div id="bar" class="easyui-panel" title="请维护以下信息" style="width: 550px; padding: 10px 15px;margin: auto;">
<form id="registrate" name="registrate" method="post" novalidate>
	<table width="510" height="350" border ="0px" cellspacing="6px" align="center">
		<tr>
			<td width="291">
			    <input id="userID2" name="userID2" type="hidden" value=<%=session.getAttribute("wspuserid")%>> 
				<div style=" margin: auto;">
					<span class="text">用户名:</span>&nbsp <input
					    name="username" class="easyui-validatebox textbox" data-options="required:true,validType:'length[3,15]'"
						style="width: 150px; height: 15px" value=<%=session.getAttribute("username")%>>
				</div>
			</td>
		</tr>
		<tr>
			<td width="104">
				<div style=" margin: auto;">
					<span class="text">请输入密码:</span>&nbsp<input
						id="password" name="password" type="password" class="easyui-validatebox textbox" data-options="required:true,validType:'length[6,20]'" 
						style="width: 150px; height: 15px" value=<%= session.getAttribute("password")%>>
				</div>
			</td>
		</tr>
		<tr>
			<td width="104">
				<div style=" margin: auto;">
					<span class="text">再次输入密码:</span>&nbsp<input
						id="repassword" name="repassword" type=password class="easyui-validatebox textbox" data-options="required:true,validType:'length[6,20]'" 
						style="width: 150px; height: 15px" value=<%= session.getAttribute("password")%>>
				</div>
			</td>
		</tr>
		<tr>
			<td width="104">
				<div style=" margin: auto;">
					<span class="text">电话号码:</span>&nbsp<input
					    name="phonenumber" class="easyui-validatebox textbox" data-options="required:true" 
						style="width: 150px; height: 15px" value=<%= session.getAttribute("phonenumber")%>>
				</div>
			</td>
		</tr>
		<tr>
			<td width="104">
				<div style=" margin: auto;">
					<span class="text">电子邮件:</span>&nbsp<input
						name="email" class="easyui-validatebox textbox" data-options="required:true,validType:'email'"
						style="width: 150px; height: 15px" value=<%= session.getAttribute("email")%>><font color="#FF0000">&nbsp(请准确填写可用于找回密码)</font>
				</div>
			</td>
		</tr>		
		<tr>
		    <td width="104">
		       <div style=" margin: auto;">
				<select id="selProvince">
					<option value="0">--请选择省份--</option>
				</select> <select id="selCity" >
					<option value="0">--请选择城市--</option>
				</select> <select id="selDistrict">
					<option value="0">--请选择区/县--</option>
	             <!-- 下面定义这三个隐藏input标签为了传输js选择的省市区名称使用 -->
	             <input type="hidden" id="province" name="province" value=<%= session.getAttribute("province")%>>
	             <input type="hidden" id="city" name="city"/ value=<%= session.getAttribute("city")%>>
	             <input type="hidden" id="district" name="district" value=<%= session.getAttribute("district")%>>
	           </div>
	        </td>	
		</tr>
		<tr>
			<td width="104">
				<div style="margin: auto;">
					<span class="text">详细地址:</span>&nbsp<input
						id="detailaddress"  name="detailaddress" class="easyui-validatebox textbox" data-options="required:true" 
						style="width: 280px; height: 15px" value=<%= session.getAttribute("detailaddress")%>><font color="#FF0000">&nbsp(省市县/区不再重复填写)</font>
				</div>
			</td>
		</tr>		
		<tr>
		    <td width="104">
		       <div style=" margin: auto;">
		          <input type="hidden" id="busitype" name="busitype" value=<%= session.getAttribute("businesstype")%>>
                  <span class="text">业务类型:</span>&nbsp
                  <select id="businesstype" name="businesstype" style="width: 100px" data-options="required:true">
                     <option value ="餐饮娱乐">餐饮娱乐</option>                
                     <option value="旅游">旅游</option>
                     <option value="停车场">停车场</option>
                     <option value="其它">其它</option>
                  </select>
	           </div>
	        </td>	
		</tr>
		
		<tr>
		    <td width="104">
		       <div style=" margin: auto;">
		             <input type="hidden" id="showinfo" name="showinfo" value= <%= session.getAttribute("showinfo")%>/>
                  <span class="text">系统功能:</span>&nbsp
                     <input type="checkbox" id="sysfuns1" name="sysfuns" value="门户">门户 &nbsp&nbsp&nbsp
                     <input type="checkbox" id="sysfuns2" name="sysfuns" value="商务"/>商务
	           </div>
	        </td>	
		</tr>
		<tr>
		    <td width="250">
		        <div style="margin: auto;">
		           &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <a href="javascript:void(0)" class="easyui-linkbutton" style="width: 200px; height: 20px" onclick="submitForm()">提交</a>
                      
 		        </div>
            </td>
		</tr>
			
	</table>
          
	</form>
</div>
</div>


<script type="text/javascript">
    //区域选择
	$(function() {
		//alert("mmmm");
        $("#selProvince").empty();
        $("#selCity").empty();
        $("#selDistrict").empty();
		$.each(province, function(k, p) {
			var option = "<option value='" + p.ProID + "'>" + p.ProName
					+ "</option>";
			$("#selProvince").append(option);
		});
		
		
		var provincename = document.getElementById("province").value;
		var cityname = document.getElementById("city").value;
		var districtname = document.getElementById("district").value;
		var busitype = document.getElementById("busitype").value;
		var showinfo = document.getElementById("showinfo").value;
		//alert(provincename+","+cityname+","+districtname+","+busitype+","+showinfo);
		//设置businesstype的选中项
		var options = document.getElementById("businesstype").options;		
		for(var i=0;i<options.length;i++){
			//alert(options[i].value+","+busitype);
			if(options[i].value==busitype){				    
				$("#businesstype").get(0).selectedIndex = i;
			}
		}
		//设置功能的选中项
		if(showinfo.indexOf("门户")!=-1){
			document.getElementById("sysfuns1").checked = true;
		}
		if(showinfo.indexOf("商务")!=-1){
			document.getElementById("sysfuns2").checked = true;
		}
		
        //设置省市区三级的选中项
		var city0 = 0;
		$.each(province, function(k, p) {
			if (p.ProName == provincename) {
			  $("#selProvince").get(0).selectedIndex = p.ProID-1;  
			  //alert(p.ProName+","+provincename+",ProID="+p.ProID);
			  //加载地市选项
			  if(city0==0){					  
			      var option = "<option value='0'>--请选择城市--</option>";
			      $("#selCity").append(option);
			      city0 = 1;
			  }
			  $.each(city, function(l, q) {
				 if (q.ProID == p.ProID) {
					  var option = "<option value='" + q.CityID + "'>"
									+ q.CityName + "</option>";
					  $("#selCity").append(option);
				 }
			 });
			  //判断地市选中项
			 var j=0;
				var district0 = 0;
			 $.each(city, function(k, r) {
				 if (r.ProID == p.ProID) {
					  j = j + 1;//j用于selectedIndex的索引，不是 p.ProID
					  //alert(j);
						 if (r.CityName == cityname) {
							 //alert(r.CityID+","+cityname+",j="+j);
							 $("#selCity").get(0).selectedIndex = j; 
							 //加载区县
							 if(district0==0){
						         var option = "<option value='0'>--请选择区/县--</option>";
						         $("#selDistrict").append(option);	
						         district0 = 1;
							 }
							 $.each(District, function(k, s) {
							     if (s.CityID == r.CityID) {
									 var option = "<option value='" + s.Id + "'>"
												+ s.DisName + "</option>";
									 $("#selDistrict").append(option);
							     }
						     });
							 //判断区县选中项
							 var w=0;
							 $.each(District, function(k, p) {
								 //alert(p.DisName+","+districtname);
								 if(p.CityID==r.CityID){
									 w = w + 1;
									 //alert(w);
									 if (p.DisName == districtname) {
										 $("#selDistrict").get(0).selectedIndex = w; 
										 //alert("w="+w+",districtname="+districtname);
										 return false;
									 }
								 }
							  });							 
							  return false;
						  }					 
				   }
			  });
              return false;
		    }
		});
				
		$("#selProvince").change(
				function() {
					var selValue = $(this).val();
					$("#selCity option:gt(0)").remove();

					$.each(city, function(k, p) {
						if (p.ProID == selValue) {
							var option = "<option value='" + p.CityID + "'>"
									+ p.CityName + "</option>";
							$("#selCity").append(option);
						}
					});

				});
		$("#selCity").change(
				function() {
					var selValue = $(this).val();
					$("#selDistrict option:gt(0)").remove();

					$.each(District, function(k, p) {
						if (p.CityID == selValue) {
							var option = "<option value='" + p.Id + "'>"
									+ p.DisName + "</option>";
							$("#selDistrict").append(option);
						}
				});
		});
	});
	
	function submitForm(){
		//alert("xxxxxxxxxx");
		var password = document.getElementById("password").value;
		var repassword = document.getElementById("repassword").value;
		if(password!=repassword){
			alert("两次输入的密码不一样，请重新输入密码！");
			return false;
		}
		//获取省市区选择框的值
		var provinceid = document.getElementById("selProvince").value;
		var cityid = document.getElementById("selCity").value;
		var districtid = document.getElementById("selDistrict").value;
		//alert(provinceid+","+cityid+","+districtid);		
		var provincename;
		var cityname;
		var districtname;		
		if(provinceid==0){
			provincename="";
		}
		else{
			provincename=province[provinceid-1].ProName;
		}
		if(cityid==0){
			cityname="";
		}
		else{
			cityname=city[cityid-1].CityName;
		}
		if(districtid==0){
			districtname="";
		}
		else{
			districtname=District[districtid-1].DisName;
		}		
		//alert(provincename+","+cityname+","+districtname);
		document.getElementById("province").value = provincename;
		document.getElementById("city").value = cityname;
		document.getElementById("district").value = districtname;
		//获取select的值
		var str="";  
		$('input:checkbox').each(function() {
	        if ($(this).attr('checked') ==true) {
	        	str+=$(this).val()+";";
	            //alert(str);
	        }
	    });
        document.getElementById("showinfo").value = str;
		//var url = "wspusermaintain.action";
		//document.registrate.action = url;
		//document.registrate.submit();

		$('#registrate').form('submit',	{
					url : "wspusermanage_maintainwspuser.action",
					onSubmit : function() {
						return $(this).form(
								'validate');
					},
					success : function(result) {
						var result = eval('('+ result + ')');
						result = JSON.parse(result);
						if (result.errorMsg) {
							$.messager.show({
								title : 'Error',
								msg : result.errorMsg
							});
						} else {
							$.messager.alert('操作提示', '更新完成,退出重新登录生效', "info");
						}
					}
				});
	
	}
	
</script>

</body>
</html>