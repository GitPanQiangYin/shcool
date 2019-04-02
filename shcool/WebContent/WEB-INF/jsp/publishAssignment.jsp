<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
    <head>
<script language="javascript" type="text/javascript" src="<%=basePath %>js/WdatePicker.js">
</script>
        <meta charset = "utf-8"/>
        <title>校园跑腿人系统</title>
        <link rel="stylesheet" href="<%=basePath %>styles/common.css"/>
        <style type="text/css">
            #divfrom{
                float:left;
                width:150px;
            }
            #divto{
                float:left;
                width:150px;
            }
            #divoperator{
                float:left;
                width:50px;
                padding:60px 5px;
            }
            #divoperator input[type="button"]{
                margin:10px 0;
            }
            #selDepartments{
                display:block;
                width:100%;
            }
            #selEmployees{
                display:block;
                width:100%;
                height:200px;
            }
            #selSelectedEmployees{
                display:block;
                width:100%;
                height:225px;
            }
        </style>
    </head>
    <body onload="body_load()">
        <div class="page-header">
            <div class="header-banner">
                <img src="<%=basePath %>images/header.png" alt=""/>
            </div>
            <div class="header-title">
                欢迎访问校园跑腿人系统
            </div>
            <div class="header-quicklink">
               <strong></strong>
                <a href="<%=basePath %>ChangePassword.action">修改密码</a>
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
 						<li class="sidebar-menuitem active"><a href="<%=basePath %>user/myFinishedAssignment.action">我完成的任务</a></li> 
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
                    任务中心 > 发布任务
                </div>
                <form action="<%=basePath %>assignment/publishAssignment.action" method="post">
                    <fieldset>
                        <legend>任务信息</legend>
                        <table class="formtable">
                            <tr>
                                <td>任务名称：</td>
                                <td>
                                    <input type="text" id="assignment_name" name="assignment_name" maxlength="20"/>
                                </td>
                            </tr>
                            <tr>
                                <td>佣金：</td>
                                <td>
                                    <input type="text" id="brokerage" name="brokerage" />
                                </td>
                            </tr>
                            <tr>
                                <td>预计时间：</td>
                                <td>
                                     	开始时间<input name="starttime" class="Wdate" type="text" onClick="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMMdd HH:mm:ss'})">
  					  					结束时间<input name="endtime" class="Wdate" type="text"  onClick="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMMdd HH:mm:ss'})">
                                </td>
                            </tr>
							<tr>
                                <td>接受任务者性别：</td>
                                <td>
                                    <select name="gender">    
                                     	<option value="男">男</option>
                                     	<option value="女">女</option>
                                     </select>
                                </td>
                            </tr>
                            <tr>
                                <td>任务说明：</td>
                                <td>
                                    <textarea id="detail" name="detail" rows="5"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <td class="command" colspan="2">
                                    <input type="submit" class="clickbutton" value="发布任务"/>
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
            <img src="<%=basePath %>images/footer.png" alt=""/>
        </div>
    </body>
</html>