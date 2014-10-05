<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta charset="UTF-8">

<!--引入easyui的css和js包 -->
<link rel="stylesheet" type="text/css" href="css/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="css/themes/icon.css">
<link rel="stylesheet" type="text/css" href="css/themes/color.css">
<link rel="stylesheet" type="text/css" href="demo/demo.css">
<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/ProJson.js"></script>
<script type="text/javascript" src="js/CityJson.js"></script>
<script type="text/javascript" src="js/DistrictJson.js"></script>


<title>无标题文档</title>


<body>
<p>
<p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p><p>
	<div id="box"
		style="width: 800px; height: 450px; margin: 0 auto 0 auto;"
		align="center">
		<div id="bar" class="easyui-panel" title="企业类型确认"
			style="width: 400px; padding: 10px 15px; margin: auto;">
			<form id="loginfm" name="loginfm" method="post" novalidate>
				<div>
				<p><p><p><p>
				<font color="blue">企业类型不同，所进入的功能系统有所差异，请选择正确的企业类型；如果所选择的企业类型与注册时填写的企业类型不同，则以所选择的企业类型为准。</font>
				</div>
				<div style="margin: auto;">
					<select id="businesstype" name="businesstype" style="width: 200px;height: 25px;"
						data-options="required:true">
						<%
						String type = (String)session.getAttribute("wsptype");
						out.println(type);
						if(type.equals("餐饮娱乐")){
						%>
						<option value="餐饮娱乐" selected="selected">餐饮娱乐</option>
						<option value="旅游">旅游</option>
						<option value="停车场">停车场</option>
						<option value="其它">其它</option>
						<%
						}
						else if(type.equals("旅游")){
						%>
						<option value="餐饮娱乐">餐饮娱乐</option>
						<option value="旅游" selected="selected">旅游</option>
						<option value="停车场">停车场</option>
						<option value="其它">其它</option>
						<%
						}
						else if(type.equals("停车场")){
						%>
						<option value="餐饮娱乐">餐饮娱乐</option>
						<option value="旅游">旅游</option>
						<option value="停车场" selected="selected">停车场</option>
						<option value="其它">其它</option>
						<%
						}
						else {
						%>
						<option value="餐饮娱乐">餐饮娱乐</option>
						<option value="旅游">旅游</option>
						<option value="停车场">停车场</option>
						<option value="其它" selected="selected">其它</option>
						<%
						}
						%>
					</select>
				</div>
				<p><p>
				<div style="margin: auto;">
				    <a href="javascript:void(0)" class="easyui-linkbutton"
						style="width: 70px; height: 25px; color: #80F;" onclick="upstep()">上一步</a>
						&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
					<a href="javascript:void(0)" class="easyui-linkbutton"
						style="width: 70px; height: 25px; color: #80F;"
						onclick="submitForm()">下一步</a>
				</div>
				<div>
				<p><p><p><p>
				</div>
			</form>
		</div>
	</div>


	<script>

	function submitForm(){
		   var url = "wsptypesel.action";
		   document.loginfm.action = url;
		   document.loginfm.submit();
    }
	function upstep(){
		window.location.href="login.html";
	} 
	</script>

</body>
</html>