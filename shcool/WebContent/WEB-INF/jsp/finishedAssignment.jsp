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
<script language="javascript" type="text/javascript" src="<%=basePath %>js/WdatePicker.js">
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>校园跑腿人系统</title>
<link rel="stylesheet" href="<%=basePath %>styles/common.css"/>
<link rel="stylesheet" href="<%=basePath %>styles/window.css"/>
<link rel="stylesheet" href="<%=basePath %>styles/window1.css"/>
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
						$("#promulgator_id").val(data.promulgator_id);
						$("#brokerage").val(data.brokerage);
						$("#receiver_id").val(data.receiver_id);
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
function over(){
	debugger;
	var assignment_id = document.getElementById("assignment_id").value;
	var promulgator_id = document.getElementById("promulgator_id").value;
	var brokerage = document.getElementById("brokerage").value;
	var receiver_id = document.getElementById("receiver_id").value;
	$.ajax({
		url:"<%=basePath%>assignment/FinishAssignment.action",
		data:{"assignment_id":assignment_id,"promulgator_id":promulgator_id,"brokerage":brokerage,"receiver_id":receiver_id},
		type:"get",
		success:function(data){
			if(data==1){
				alert("任务已完成！");
				window.location.reload();
			}else if(data==0){
				alert("任务已完成,等待确认完成！");
				window.location.reload();
			}
		},
	});
}

function cancel(){
	var assignment_id = document.getElementById("assignment_id").value;
	var promulgator_id = document.getElementById("promulgator_id").value;
	var brokerage = document.getElementById("brokerage").value;
	var receiver_id = document.getElementById("receiver_id").value;
	$.ajax({
		url:"<%=basePath%>assignment/queryAssignmentStatus.action",
		data:{"assignment_id":assignment_id},
		type:"get",
		success:function(data){
			if(data==1){
				var mes = "该任务已被接受，确定要下架么？";
			}else{
				var mes = "该任务未被接受，确定要下架么？"
			}
			if(confirm(mes)){
				$.post("<%=basePath%>assignment/cancelAssignment.action",{"assignment_id":assignment_id},function(data){
					alert("任务下架成功！");
					window.location.reload();
				});
			}
		},
	});
};
function abandon(){
	var assignment_id = document.getElementById("assignment_id").value;
	var receiver_id = document.getElementById("receiver_id").value;
	if(confirm('放弃任务后信誉将降低,确实要放弃任务吗?')){
		debugger;
		$.ajax({
			url:"<%=basePath%>assignment/AbandonAssignment.action",
			data:{"assignment_id":assignment_id,"receiver_id":receiver_id},
			type:"get",
			success:function(data){
				alert("任务放弃成功！");
				window.location.reload();
			},
		});
	}
}
function change(){
	$.post("<%=basePath%>assignment/updateAssignment.action",$("#changeassignment").serialize(),function(data){
		alert("任务修改成功！");
		window.location.reload();
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
                    个人中心 > 我发布的任务
                </div>
                <div class="pager-header">
                        <div class="header-info">
                        <font size="2">共 1 页</font>
                        <font size="2">第 ${page.pageNow} 页</font>
                        </div>
                        <div class="header-nav">
                            <a href="<%=basePath%>user/MyAssignment.action?pageNow=1">首页</a>  
        <c:choose>  
            <c:when test="${page.pageNow - 1 > 0}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.pageNow - 1}">上一页</a>  
            </c:when>  
            <c:when test="${page.pageNow - 1 <= 0}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=1">上一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.pageNow}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 < page.totalPageCount}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.pageNow + 1}">下一页</a>  
            </c:when>  
            <c:when test="${page.pageNow + 1 >= page.totalPageCount}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.totalPageCount}">下一页</a>  
            </c:when>  
        </c:choose>  
        <c:choose>  
            <c:when test="${page.totalPageCount==0}">  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.pageNow}">尾页</a>  
            </c:when>  
            <c:otherwise>  
                <a href="<%=basePath%>user/MyAssignment.action?pageNow=${page.totalPageCount}">尾页</a>  
            </c:otherwise>  
        </c:choose> 
                            跳到第<input type="text" id="pageNow" name="pageNow" class="nav-number"/>页
                 <a href="<%=basePath%>user/MyAssignment.action?.action">跳转</a>
                 </div>
                    </div>
                <table class="listtable">
                    <caption>
                    </caption>
                    <tr class="listheader">
                        <th style="width:200px">任务名称</th>
                        <th>佣金</th>
                        <th>起始时间</th>
                        <th>结束时间</th>
                        <th>任务状态</th>
                        <th style="width:100px">操作</th>
                        <th>评论任务</th>
                    </tr>
                    <c:forEach var="list" items="${list }">
                    <tr class="tr">
                        <td>${list.assignment_name }</td>
                        <td>${list.brokerage }</td>
                        <td><fmt:formatDate value="${list.starttime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td><fmt:formatDate value="${list.endtime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        <td>${list.status }</td>
                        <td>
                            <a id="${list.assignment_id }" class="clickbutton1" href="javascript:void(0)">查看详情</a>
                         </td>
                         <td>
                            <a id="${list.assignment_id }"  href="<%=basePath%>assignment/commentAssignment.action?assignment_id=${list.assignment_id }">评论</a>
                         </td>
                    </tr>
                    </c:forEach>
                </table>
                
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
									    		<td height=30px; width=200px; align="right">任务名称:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="assignment_name"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">佣金:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="brokerage"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">开始时间:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="starttime"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">结束时间:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="endtime"></td>
									    	</tr>
									    	
									    	<tr>
									    		<td height=30px; width=200px; align="right">任务详情:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id="detail"></td>
									    	</tr>
									    </table>
									    <input id="assignment_id" name="assignment_id" type="hidden" />
									    <input id="promulgator_id" name="promulgator_id" type="hidden" />
									    <input id="brokerage" name="brokerage" type="hidden" />
									    <input id="receiver_id" name="receiver_id" type="hidden" />
									    </form>
									  </div>
									  <div class="modal-footer" align="center">
									   </div>
									  </div>
									</div>
									
									<div id="myModal1" class="modal">
									<div class="modal-content">
									  <div class="modal-header">
									    <span class="close1">&times;</span>
									    <h2></h2>
									  </div>
									  <div class="modal-body">
									    <form id="changeassignment">
									    <table class="tableDetail">
									    	<tr>
									    		<td height=30px; width=200px; align="right">任务名称:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; id=""><input id="assignment_name1" name="assignment_name" type="text" /></td> 
									    	</tr>
									    	<tr>
									    	<td height=30px; width=200px; align="right">佣金:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; ><input id="brokerage1" name="brokerage" type="text" /></td>
									    	</tr>
									    	<tr>
									    		<td height=30px; width=200px; align="right">开始时间:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; ><input id="starttime1" name="starttime" class="Wdate" type="text" onClick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
									    	</tr>
									    	<tr>
									    	<td height=30px; width=200px; align="right">结束时间:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; ><input id="endtime1" name="endtime" class="Wdate" type="text"  onClick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})"></td>
									    	</tr>
									    	<tr>
									    		<td height=30px; width=200px; align="right">任务详情:&nbsp;&nbsp;</td>
									    		<td height=30px; width=600px; ><textarea id="detail1" name="detail" rows="5"></textarea></td>
									    	</tr>
									    </table>
									    <input id="assignment_id1" name="assignment_id1" type="hidden" />
									    </form>
									  </div>
									  <div class="modal-footer" align="center">
									   <a  href="javascript:void(0)" style="color: white" onclick="change()">确认修改&nbsp;&nbsp;&nbsp;</a>
									   <a  href="javascript:void(0)" style="color: white" onclick="window.location.reload();">取消</a>
									  </div>
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