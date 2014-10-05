<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

</head>
<body>

<p>
<p><p><p>
<div id="headeright" style="width:100%;margin-top: 10px;margin-bottom: 10px;text-align:right;">
<font  style="size:10;face:arial; color:red;font-weight:bold;font-style:italic;">无线慧 汇无线 &nbsp &nbsp &nbsp </font>
<a href="javascript:void(0)" style="width: 100px; height: 20px" onclick="logout()">退出</a> &nbsp 
<span>Hi,<%= session.getAttribute("username")%></span>&nbsp &nbsp
</div>
<script type="text/javascript">
   function logout(){
	   //session.removeAttribute("username");
	   //session.invalidate();
	   if(confirm("确定要注销吗？"))
	   {
		   window.location.href = "login.html";
	   }

   }
</script>

</body>
</html>
