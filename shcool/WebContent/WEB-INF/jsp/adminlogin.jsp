<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>校园跑腿人系统</title> 
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#username").blur(function(){
		var username=$("#username").val();
		$.ajax({
			url:"<%=basePath%>main.action",
			data:{"username":username},
			type:"get",
			success:function(data){
				$("#span").html("")
				if(data==0){
					$("#span").html("用户名不存在！");
				}else if(data==-1){
					$("#span").html("用户名不能为空！");
				}else if(data==-2){
					$("#span").html("该用户名尚未审批通过");
				}else if(data==-3){
					$("#span").html("该用户名已被关闭");
				}else{
					$("#span").html("用户名正确");
			}
			}
		});
	});
	
});
function reload(){  
    document.getElementById("image").src="<%=basePath %>verification/imageServlet.action?date="+new Date().getTime();  
    $("#checkcode").val("");   // 将验证码清空  
} 

</script>
<link rel="stylesheet" href="<%=basePath %>styles/common.css"/>
</head>
<body>
	 <div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt=""/>
            </div>
            <div class="header-title">
                欢迎访问校园跑腿人系统
            </div>
            <div class="header-quicklink">
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle"></div>
                    <ul class="sidebar-menu">
                    </ul>
                </div>
            </div> 
            <div class="page-content">
                <div class="content-nav">
                    管理员登录
                </div>
                <form action="<%=basePath %>admin/adminlogin.action" method="get">
                    <fieldset>
                        <legend>登录信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>用户名:</td>
                                <td>
                                    <input id="username" name="username" placeholder="输入管理员账号" type="text" /><span id="span"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>密码:</td>
                                <td>
                                    <input id="new" name="password" placeholder="输入密码" type="password" /><span>${Info }</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    <button type="submit"  class="clickbutton" />登录</button>
                                    <input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="command">
                                    ${msg }
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            <img src="images/footer.png" alt=""/>
        </div>
</body>
</html>