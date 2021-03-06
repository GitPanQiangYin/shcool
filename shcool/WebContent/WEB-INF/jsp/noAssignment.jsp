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
				var assignment_id = $(this).attr("id");
				 $.ajax({
					url:"<%=basePath%>assignment/FindAssignmentById.action",
					data:{"assignment_id":assignment_id},
					type:"get",
					success:function(data){
						$("#assignment_name").html(data.assignment_name);
						$("#brokerage").html(data.brokerage);
						$("#starttime").html(data.starttime);
						$("#endtime").html(data.endtime);
						$("#detail").html(data.detail);
						$("#assignment_id").val(data.assignment_id);
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
function rec(){
	debugger;
	var assignment_id = document.getElementById("assignment_id").value;
	$.ajax({
		url:"<%=basePath%>assignment/ReceiveAssignment.action",
		data:{"assignment_id":assignment_id},
		type:"get",
		success:function(data){
			if(data==1){
				alert("任务接收成功！");
				window.location.reload();
			}
		},
	});
}

</script>

</head>
<body>
	 <div class="page-header">
            <div class="header-banner">
                <img src="<%=basePath %>images/header.png" alt="CoolMeeting"/>
            </div>
            <div class="header-title">
                欢迎访问校园跑腿人系统
            </div>
            <div class="header-quicklink">
                欢迎您，<strong>${user.user_name }</strong>
                <a href="<%=basePath %>changepassword.jsp">[修改密码]</a>
                <a href="<%=basePath %>user/LoginOut.action">退出</a>
            </div>
        </div>
        <div class="page-body">
            <div class="page-sidebar">
                <div class="sidebar-menugroup">
                	<div class="sidebar-grouptitle"><a href="<%=basePath %>assignment/QueryAssignmentByPage.action">首页</a></div>
                    <div class="sidebar-grouptitle">个人中心</div>
                    <ul class="sidebar-menu">
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/MyAssignment.action">我发布的任务</a></li>
                     	<li class="sidebar-menuitem active"><a href="<%=basePath %>user/MyReceiveAssignment.action">我接受的任务</a></li> 
                        
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
                    个人中心 
                </div>    
            </div>
        </div>
        <h2>这里还是空的哦</h2>
        <div class="page-footer">
            <hr/>
            <img src="<%=basePath %>images/footer.png" alt="CoolMeeting"/>
        </div>
</body>
</html>