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
$(function(){
	//获取弹窗
	var modal = document.getElementById('myModal');
	// 打开弹窗的按钮对象
	var btn = document.getElementById('myBtn');
	// 获取 <span> 元素，用于关闭弹窗 that closes the modal
	var span = document.getElementsByClassName("close")[0];
	// 点击按钮打开弹窗
	debugger;
		$(".tr").each(function(){
			$(this).find(".clickbutton1").click(function(){
				debugger;
				var user_id = $(this).attr("id");
				 $.ajax({
					url:"<%=basePath%>admin/queryUserInfo1.action",
					data:{"user_id":user_id},
					type:"get",
					success:function(data){
						$("#user_name").html(data.user_name);
						$("#gender").html(data.gender);
						$("#phone").html(data.phone);
						$("#school").html(data.school);
						$("#address").html(data.address);
						$("#credit").html(data.credit);
						$("#money").html(data.money);
						$("#user_id").val(data.user_id);
						},
				}); 
			    modal.style.display = "block";
			});  
		});
	
	// 点击 <span> (x), 关闭弹窗
	span.onclick = function() {
	    modal.style.display = "none";
	}
	// 在用户点击其他地方时，关闭弹窗
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
});
function deblock(){
	var user_id= $("#user_id").val();
	$.ajax({
		url:"<%=basePath%>admin/deblockUser.action",
		data:{"user_id":user_id},
		type:"get",
		success:function(data){
			alert("用户解封成功！")
			window.location.reload();
		},
	}); 
};
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
                <a href="<%=basePath %>ChangePassword.action">修改密码</a>
                <a href="<%=basePath %>user/LoginOut.action">退出</a>
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                	<div class="sidebar-grouptitle"><a href="<%=basePath %>admin/searchAllUsers.action">首页</a></div>
                    <div class="sidebar-grouptitle">用户管理</div>
                    <ul class="sidebar-menu">
                    	<li class="sidebar-menuitem active"><a href="<%=basePath %>admin/searchAllUsers.action">查看全部用户</a></li>
                    	<li class="sidebar-menuitem active"><a href="<%=basePath %>admin/searchLowCreditUsers.action">查看低信誉的用户</a></li>
                    	<li class="sidebar-menuitem active"><a href="<%=basePath %>admin/searchBannedUsers.action">查看被封用户</a></li>
                    	<li class="sidebar-menuitem active"><a href="<%=basePath %>admin/searchPleaseChangeUsers.action">查看提示修改的用户</a></li>
                    </ul>
                </div>
                <div class="sidebar-menugroup">
                    <div class="sidebar-grouptitle">任务管理</div>
                    <ul class="sidebar-menu">
                        <li class="sidebar-menuitem"><a href="<%=basePath %>admin/queryAllAssignment.action">查看全部任务</a></li>
                        <li class="sidebar-menuitem active"><a href="<%=basePath %>admin/queryWarningAssignment.action">查看提示修改的任务</a></li>
                        <li class="sidebar-menuitem active"><a href="<%=basePath %>admin/queryRepealedAssignment.action">查看下架的任务</a></li>
                    </ul>
                </div>
            </div>
            <div class="page-content">
                <div class="content-nav">
                    个人中心 > 最新任务
                </div>
                 <div class="pager-header">
                        <div class="header-info">
                        <font size="2">共 1 页</font>
                        <font size="2">第 ${page.pageNow} 页</font>
                        </div>
                        <div class="header-nav">
                            <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=1">首页</a>  
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.pageNow - 1}">上一页</a>  
            </c:when>  
            <c:when test="${page.pageNow - 1 <= 0}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=1">上一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.pageNow}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.pageNow + 1}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="<%=basePath%>assignment/QueryAssignmentByPage.action?pageNow=${page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose> 
                            跳到第<input type="text" id="pageNow" name="pageNow" class="nav-number"/>页
                 <a href="<%=basePath%>assignment/QueryAssignmentByPage.action">跳转</a>
                 </div>
                    </div>
                <table class="listtable">
                    <caption>
                    </caption>
                    <tr class="listheader">
                        <th>用户名</th>
                        <th>性别</th>
                        <th>电话</th>
                        <th>学校</th>
                        <th>用户状态</th>
                        <th style="width:100px">操作</th>
                    </tr>
                    <c:forEach var="list" items="${list }">
                    <tr class="tr">
                        <td>${list.user_name }</td>
                        <td>${list.gender }</td>
                        <td>${list.phone }</td>
                        <td>${list.school }</td>
                        <td>${list.status }</td>
                        <td>
                            <a id="${list.user_id }" class="clickbutton1" href="javascript:void(0)">解封</a>
                         </td>
                    </tr>
                    </c:forEach>
                </table> 
            </div>
            <div id="myModal" class="modal">
									<div class="modal-content">
									  <div class="modal-header">
									    <span class="close">&times;</span>
									    <h2></h2>
									  </div>
									  <div class="modal-body">
									    <form action="" >
									    <table class="tableDetail">
									    	<tr>
									    		<td height=30px; width=200px; align="right">用户名:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="user_name"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">性别:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="gender"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">电话:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="phone"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">学校:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="school"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">地址:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="address"></td>
									    	</tr>
									    	<tr>
									    		<td height=30px; width=200px; align="right">信誉分:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="credit"></td>
									    	</tr>
									    	<tr>
									    		<td height=30px; width=200px; align="right">余额:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="money"></td>
									    	</tr>
									    </table>
									    <input id="user_id" name="user_id" type="hidden" />
									    </form>
									  </div>
									  <div class="modal-footer" align="center">
									   <a  href="javascript:void(0)" style="color: white" onclick="deblock()">确定解封此账号</a>
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