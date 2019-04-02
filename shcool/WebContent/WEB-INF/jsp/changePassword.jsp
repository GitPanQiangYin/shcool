<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>校园跑腿人系统</title>
<link rel="stylesheet" href="<%=basePath %>styles/common.css"/>
<link rel="stylesheet" href="<%=basePath %>styles/window.css"/>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

</script>

</head>
<body>
	 <div class="page-header">
            <div class="header-banner">
                <img src="<%=basePath %>images/header.png" alt=""/>
            </div>
            <div class="header-title">
                欢迎访问校园跑腿人系统
            </div>
            <div class="header-quicklink">
                <strong></strong>
                <a href="<%=basePath %>changepassword.jsp">修改密码</a>
                <a href="<%=basePath %>user/LoginOut.action">退出</a>
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                	<div class="sidebar-grouptitle"><a href="<%=basePath %>assignment/QueryAssignmentByPage.action">首页</a></div>
                    <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/SearchPersonInfo.action">查看个人信息</a></li>
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/MyAssignment.action">我发布的任务</a></li>
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/MyReceiveAssignment.action">我接受的任务</a></li>
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/canceledAssignment.action">我下架的任务</a></li>  
 						<li class="sidebar-menuitem active"><a href="<%=basePath %>user/myReceiveMessage.action">最新消息<span style="color: red">${count}</span></a></li>	                       
                      
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">任务中心</div>
                    <ul class="sidebar-menu">
                   		<li class="sidebar-menuitem active"><a href="<%=basePath %>publishAssignment.action">发布任务</a></li>
                        <li class="sidebar-menuitem"><a href="<%=basePath %>assignment/QueryAssignmentByPage.action">查看全部任务</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 我的信息
                </div>
		<div >
						</div>
						<div class="modal-body">
						<form action="<%=basePath %>user/ChangePassword.action" method="get">
						<table class="formtable">
						<tr>
						<td height=25px; width=50px; align="right">请输入新密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td height=25px; width=150px; ><input style="height: 25px;width: 200px" id="new" name="password" placeholder="输入新密码" type="password" ></input></td>
						</tr>
						<tr>
						<td height=25px; width=50px; align="right">确认新密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td height=25px; width=150px; ><input style="height: 25px;width: 200px" id="new" name="password1" placeholder="确认密码" type="password" ></input></td>
						</tr>
						<tr>
						<td height=25px; width=50px; align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
						<td height=25px; width=150px; ></td>
						</tr>
                        <tr>
                            <td height=25px; width=50px; align="right">&nbsp;&nbsp;&nbsp;</td>
							<td height=25px; width=150px; ><button type="submit"  class="clickbutton" />确认</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="返回" class="clickbutton" onclick="window.history.back();"/></td>
                                    
                                    
                                
                            </tr>
						</table>
						</form>
						</div>							  
				</div>      
            </div>
        </div>
        <div class="page-footer">
            <hr/>
            <img src="<%=basePath %>images/footer.png" alt=""/>
        </div>
</body>
</html>