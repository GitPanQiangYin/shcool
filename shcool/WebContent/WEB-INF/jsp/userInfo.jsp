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
	$(".clickbutton2").click(function(){
		var user_id = document.getElementById('user_id').value;
		 $.ajax({
				url:"<%=basePath%>admin/queryUserInfo1.action",
				data:{"user_id":user_id},
				type:"get",
				success:function(data){
					},
			}); 
		modal.style.display = "block";
	});
	// 点击 <span> (x), 关闭弹窗
	span.onclick = function() {
	    modal1.style.display = "none";
	}
	// 在用户点击其他地方时，关闭弹窗
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
});
$(function(){
	$("#banuser").click(function(){
		var user_id = document.getElementById('user_id').value;
		
		if(confirm('确实要封停此用户吗?')) {
			$.post("<%=basePath%>admin/banUser.action",{"user_id":user_id},function(data){
				alert("此用户已被封停！");
				window.location.reload();
			});
		}

	});
});

function over(){
	var user_id = document.getElementById('user_id').value;
	var reason = document.getElementById('detail').value;
	var message_name = document.getElementById('message').value;
	 $.ajax({
			url:"<%=basePath%>admin/pleaseChangeInfo.action",
			data:{"user_id":user_id,"reason":reason,"message_name":message_name},
			type:"get",
			success:function(data){
				alert("消息发送成功！");
				window.location.reload();

				},
		});
}
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
                    用户管理 > 查看用户信息
                </div>
		<div >
						</div>
						<div class="modal-body">
						<form action="" >
						<table class="tableDetail">
						<tr>
						<td height=30px; width=50px; align="right">用户名:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.user_name }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">性别:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.gender }</td>
						</tr>  	
						<tr>
						<td height=30px; width=50px; align="right">电话:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.phone }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">地址:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.address }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">学校:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.school }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">信誉积分:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.credit }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">邮箱:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.email }</td>
						</tr>
						<tr>
						<td height=30px; width=50px; align="right">账户余额:&nbsp;&nbsp;</td>
						<td height=30px; width=150px; >${user.money }</td>
						</tr>
						<tr>
						<td colspan="2" class="command" align="center">
						<a id="banuser" class="clickbutton1" style="color: green" href="javascript:void(0)">封停此账号</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
						
						<a id="" class="clickbutton2" style="color: green" href="javascript:void(0)">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示用户修改信息</button></a>
						</td>
						</tr>
						<input id="user_id" name="user_id" value="${user.user_id }" type="hidden" />
						</table>
						</form>
						</div>							  
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
									    <td colspan="2" class="command" align="center">
									    消息名：<input id="message" name ="message" />
									    </td>
									    </tr>
									    	<tr>
									    		<td height=30px; width=200px; align="right">需要修改的内容:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; ><textarea id="detail" name="detail" rows="5"></textarea></td>
									    	</tr>
									    </table>
									    <input id="assignment_id" name="assignment_id" type="hidden" />
									    </form>
									  </div>
									  <div class="modal-footer" align="center">
									   <a  href="javascript:void(0)" style="color: white" onclick="over()">确定&nbsp;&nbsp;&nbsp;</a>
									   <a  href="javascript:void(0)" style="color: white" onclick="">取消</a>
									  </div>
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