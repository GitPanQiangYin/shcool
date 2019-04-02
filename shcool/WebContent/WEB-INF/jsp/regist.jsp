<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>校园跑腿人系统</title>
    <link rel="stylesheet" href="<%=basePath %>styles/common.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(function(){
	$("#email").blur(function(){
		debugger;
		var email=$("#email").val();
		$.ajax({
			url:"<%=basePath%>user/checkEmail.action",
			data:{"email":email},
			type:"post",
			success:function(data){
				$("#span").html("")
				if(data==0){
					$("#span").html("该用户名可以使用");
				}else if(data==-1){
					$("#span").html("用户名不能为空！");
				}else{
					$("#span").html("该用户名已存在");
				}
			},
		});
	});
	
});
function send(){
	var phone = document.getElementById("phone");
	debugger;
	$.ajax({
		
		url:"sendMsgSvl",
		data:{"phone":phone},
		success:function(){
			alert(1);
		}
	});
}


</script>
</head>
<body>
	<div class="page-header">
            <div class="header-banner">
                <img src="images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问校园跑腿人系统
            </div>
            <div class="header-quicklink">
              <strong>${uname }</strong>
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                </div>
                <div class="sidebar-menugroup">
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="<%=basePath %>login.action">返回</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    	注册新用户	
                </div>
                <form action="<%=basePath %>user/regist.action" method="post" align="center">
                    <fieldset>
                        <legend>用户信息</legend>
                        <table class="formtable" style="width:50%">
                            <tr>
                                <td>姓名：</td>
                                <td>
                                    <input type="text" id="username" name="user_name" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>性别：</td>
                                <td>
                                <select name="gender" id="gender">
                                 	<option value="男">男</option>
                                 	<option value="女">女</option>
                                </select>
                                </td>
                            </tr>
                            <tr>
                                <td>密码：</td>
                                <td>
                                    <input type="password" id="password" name="password" maxlength="20" placeholder="请输入6位以上的密码"/>
                                </td>
                            </tr>
                            <tr>
                                <td>确认密码：</td>
                                <td>
                                    <input type="password" id="confirm" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>联系电话：</td>
                                <td>
                                    <input type="text" id="phone" name="phone" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>电子邮件：</td>
                                <td>
                                    <input type="text" id="email" name="email" maxlength="20"/><span id="span"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>住址：</td>
                                <td>
                                    <input type="text" id="address" name="address" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
							<td>所在学校：</td>
                                <td>
                                    <select name="school" id="deptid">  
                                     	<option value="郑州轻工业大学">郑州轻工业大学</option>
                                     	<option value="郑州轻工业大学1">郑州轻工业大学1</option>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                            </tr>
                            <tr>
                            	<td>${info }</td>
                            </tr>
                            <tr>
                                <td colspan="6" class="command">
                                    <input type="submit" class="clickbutton" value="注册"/>
                                    <input type="reset" class="clickbutton" value="重置"/>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </form>
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            <img src="<%=basePath %>images/footer.png" alt="CoolMeeting"/>
        </div>
</body>
</html>