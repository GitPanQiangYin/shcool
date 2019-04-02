package com.zzuli.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Comment;
import com.zzuli.pojo.Message;
import com.zzuli.pojo.Page;
import com.zzuli.pojo.Users;
import com.zzuli.service.UsersService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UsersService userService;
	
	@RequestMapping("checkEmail")
	@ResponseBody
	public int checkEmail(HttpServletRequest request){
		String email = request.getParameter("email");
		int num=1;
		if(email==null||email==""){
			num=-1;
		}else{
			Users user = new Users();
			try {
				user = userService.checkEmail(email);
				if(user==null){
					num=0;
				}else{
					num=1;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return num;
	}
	
	@RequestMapping("login")
	@ResponseBody
	public int login(HttpServletRequest request, HttpSession session,String email,String password){
		request.getSession().invalidate();
		if(email==null||password==null){
			return -1;
		}else{
			Users user = new Users();
			try {
				user= userService.login(email, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(user==null){
					request.setAttribute("info", "用户名或密码错误");
					return 0;
				}else{
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("user_name", user.getUser_name());
					request.getSession().setAttribute("user_id", user.getUser_id());
					return 1;
				}
		}
		
		
	}
	@RequestMapping("/regist")
		public String User(HttpServletRequest request,Users user) {
		if(user.getUser_name()==""||user.getEmail()==""||user.getPassword()==""){
			request.setAttribute("info", "请填入正确的信息");
			return "regist";
		}else{
			try {
				this.userService.addUser(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "login";
		}
		}
	//分页查询我发布的任务
	@RequestMapping("MyAssignment")
	public String MyAssignment(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			totalCount = userService.MyAssignment(user_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = userService.MyAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = userService.MyAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
		return "myAssignment";
	}
	
	@RequestMapping("MyReceiveAssignment")
	public String MyReceiveAssignment(HttpServletRequest request){
		HttpSession session = request.getSession();
		List<Assignment> list = new ArrayList<Assignment>();
		int user_id=(int) session.getAttribute("user_id");
		try {
			list = userService.MyReceiveAssignment(user_id);
			request.setAttribute("list1", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null==list||list.size()==0){
			return "noAssignment";
		}else{
			return "myReceiveAssignment";
		}
	}
	
	//退出
	@RequestMapping("LoginOut")
	public String LoginOut(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
	
	//修改密码
	@RequestMapping("ChangePassword")
	public String ChangePassword(HttpServletRequest request,String password,String password1){
		String s = request.getParameter("password");
		String s2 = request.getParameter("password1");
		int user_id = (int) request.getSession().getAttribute("user_id");
		try {
			userService.ChangePassword(user_id, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("msg", "密码修改成功，请重新登录");
		return "login";
	}
	
	//查看个人信息
	@RequestMapping("SearchPersonInfo")
	public String SearchPersonInfo(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		Users user = new Users();
		try {
			user = userService.SearchPersonInfo(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		return "personInfo";
	}
	//查看个人信息
		@RequestMapping("SearchPersonInfo1")
		public String SearchPersonInfo1(HttpServletRequest request){
			int user_id = (int) request.getSession().getAttribute("user_id");
			Users user = new Users();
			try {
				user = userService.SearchPersonInfo(user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			return "changePersonInfo";
		}
	//修改个人信息
	@RequestMapping("changePersonInfo")
	@ResponseBody
	public int changePersonInfo(HttpServletRequest request,String user_name,String phone,String address,String school,String gender){
		Users user = new Users();
		int user_id = (int) request.getSession().getAttribute("user_id");
		user.setUser_id(user_id);
		user.setAddress(address);
		user.setGender(gender);
		user.setPhone(phone);
		user.setSchool(school);
		user.setUser_name(user_name);
		try {
			userService.changePersonInfo(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	//分页查询我下架的任务数量
	@RequestMapping("canceledAssignment")
	public String  canceledAssignment(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			totalCount = userService.canceledAssignment(user_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = userService.canceledAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = userService.canceledAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
		return "canceledAssignment";
	}
	//分页查询接受的消息
	@RequestMapping("myReceiveMessage")
	public String  myReceiveMessage(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Message> list = new ArrayList<Message>();
		try {
			totalCount = userService.messageCount(user_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = userService.myReceiveMessageByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = userService.myReceiveMessageByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			System.out.println(list);
		return "mymessage";
	}
	//根据消息ID查询消息详情
	@RequestMapping("queryMessageById")
	@ResponseBody
	public Message queryMessageById(HttpServletRequest request, int message_id){
		Message message = new Message();
		try {
			message = userService.queryMessageById(message_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		return message;
	}
	//标记消息为已读
	@RequestMapping("signMessage")
	@ResponseBody
	public int signMessage(HttpServletRequest request, int message_id){
		
		try {
			 userService.signMessage(message_id);;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	//分页查询我的已完成的任务
	@RequestMapping("myFinishedAssignment")
	public String  myFinishedAssignment(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			totalCount = userService.queryMyFinishedAssignment(user_id);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = userService.queryMyFinishedAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = userService.queryMyFinishedAssignmentByPage(page.getStartPos(),page.getPageSize(), user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
			System.out.println(list);
		return "finishedAssignment";
	}
	//发布评论
	@RequestMapping("publishComment")
	@ResponseBody
	public Comment publishComment(HttpServletRequest request,int assignment_id,String comment_detail){
		int promulgator_id = (int) request.getSession().getAttribute("user_id");
		Comment comment = new Comment();
		comment.setAssignment_id(assignment_id);
		comment.setPromulgator_id(promulgator_id);
		comment.setComment_detail(comment_detail);
		try {
			userService.publishComment(comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment ;
	}
}
