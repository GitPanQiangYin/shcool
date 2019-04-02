package com.zzuli.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzuli.pojo.*;
import com.zzuli.service.AdminService;
import com.zzuli.service.UsersService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private UsersService userServ;
	
	//注册管理员
	@RequestMapping("addAdmin")
	public String AddAdmin( HttpServletRequest request, Admin admin){
		String username = request.getParameter("username");
		System.out.println(username);
		admin.setUsername(username);
		try {
			adminServ.addAdmin(admin);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return "adminlogin";
	}
	//登录
	@RequestMapping("adminlogin")
	public String AdminLogin(HttpServletRequest request, HttpSession session,String username,String password){
		request.getSession().invalidate();
		System.out.println(username);
		Admin admin = new Admin();
		try {
			admin = adminServ.login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(admin==null){
			request.setAttribute("Info", "用户名或密码错误");
			System.out.println();
			return "adminlogin";
		}else{
			request.getSession().setAttribute("admin_name", admin.getUsername());
			request.getSession().setAttribute("admin_id", admin.getUser_id());
			return "redirect:/admin/searchAllUsers.action";
		}
	}
	//分页查询全部用户
	@RequestMapping("searchAllUsers")
	public String searchAllUsers(HttpServletRequest request){
		 String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Users> list = new ArrayList<Users>();
		try {
			System.out.println("11");
			totalCount = adminServ.searchAllUsers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = adminServ.searchUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				list = adminServ.searchUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		request.setAttribute("list", list);
		 request.setAttribute("page", page);
		 return "adminMain";
	}
	
	//分页查询低信誉的用户
	@RequestMapping("searchLowCreditUsers")
	public String searchLowCreditUsers(HttpServletRequest request){
		 String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Users> list = new ArrayList<Users>();
		try {
			totalCount = adminServ.searchLowCreditUsers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = adminServ.searchLowCreditUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = adminServ.searchLowCreditUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		request.setAttribute("list", list);
		 request.setAttribute("page", page);
		 return "lowcredit";
	}
	
	//发送警告信息
	@RequestMapping("sendWarningMessage")
	@ResponseBody
	public int sendWarningMessage(HttpServletRequest request,int receiver_id,String message_detail){
		int sender_id = (int) request.getSession().getAttribute("admin_id");
		Message mes = new Message();
		mes.setMessage_detail(message_detail);
		mes.setMessage_name("低信誉警告");
		mes.setReceiver_id(receiver_id);
		mes.setSender_id(sender_id);
		try {
			adminServ.sendWarningMessage(mes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//分页查询被封的用户
	@RequestMapping("searchBannedUsers")
	public String searchBannedUsers(HttpServletRequest request){
		 String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Users> list = new ArrayList<Users>();
		try {
			totalCount = adminServ.searchBannedUsers();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = adminServ.searchBannedUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = adminServ.searchBannedUsersByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		request.setAttribute("list", list);
		 request.setAttribute("page", page);
		 return "bannedusers";
	}
		//分页查询提示修改信息的用户
		@RequestMapping("searchPleaseChangeUsers")
		public String searchPleaseChangeUsers(HttpServletRequest request){
			 String pageNow = request.getParameter("pageNow");
			 Page page = null;
			 int totalCount = 0;
			List<Users> list = new ArrayList<Users>();
			try {
				totalCount = adminServ.searchPleaseChangeUsers();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(pageNow!=null){
				 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
				 try {
					list = adminServ.searchPleaseChangeUsersByPage(page.getStartPos(), page.getPageSize());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }else{
				 page = new Page(totalCount, 1);
				 try {
					 list = adminServ.searchPleaseChangeUsersByPage(page.getStartPos(), page.getPageSize());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			request.setAttribute("list", list);
			 request.setAttribute("page", page);
			 return "pleasechangeinfo";
		}
		
	//分页查询全部任务
	@RequestMapping("queryAllAssignment")
	public String queryAllAssignment(HttpServletRequest request){
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			totalCount = adminServ.searchAllAssignment();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = adminServ.searchAssignmentByPage(page.getStartPos(),page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				list = adminServ.searchAssignmentByPage(page.getStartPos(),page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
		return "allAssignment";
	}
	
	//分页查询需要修改的任务
		@RequestMapping("queryWarningAssignment")
		public String queryWarningAssignment(HttpServletRequest request){
			String pageNow = request.getParameter("pageNow");
			 Page page = null;
			 int totalCount = 0;
			List<Assignment> list = new ArrayList<Assignment>();
			try {
				totalCount = adminServ.queryWarningAssignment();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(pageNow!=null){
				 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
				 try {
					list = adminServ.queryWarningAssignmentByPage(page.getStartPos(),page.getPageSize());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }else{
				 page = new Page(totalCount, 1);
				 try {
					 list = adminServ.queryWarningAssignmentByPage(page.getStartPos(),page.getPageSize());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
				request.setAttribute("list", list);
				request.setAttribute("page", page);
			return "warningAssignment";
		}
	
	
	//根据ID查询用户详细信息
	@RequestMapping("queryUserInfo")
	public String queryUserInfo(HttpServletRequest request,int user_id){
			Users user = new Users();
			try {
				user = userServ.SearchPersonInfo(user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			return "userInfo";
		}
	//根据ID查询用户详细信息
	@RequestMapping("queryUserInfo1")
	@ResponseBody
	public Users queryUserInfo1(HttpServletRequest request,int user_id){
			Users user = new Users();
			try {
				user = userServ.SearchPersonInfo(user_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			return user;
		}
	//根据ID封停用户
	@RequestMapping("banUser")
	@ResponseBody
	public int banUser(int user_id){
		try {
			adminServ.banUser(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	//通知用户修改信息
	@RequestMapping("pleaseChangeInfo")
	@ResponseBody
	public int pleaseChangeInfo(HttpServletRequest request,int user_id,String reason,String message_name){
		Users user = new Users();
		Message mes = new Message();
		user.setUser_id(user_id);
		user.setReason(reason);
		int sender_id = (int) request.getSession().getAttribute("admin_id");
		mes.setSender_id(sender_id);
		mes.setReceiver_id(user_id);
		mes.setMessage_detail(reason);
		mes.setMessage_name(message_name);
		try {
			adminServ.pleaseChangeInfo(user);
			adminServ.sendMessage(mes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0 ;
	}
	
	//根据ID查询任务信息
	@RequestMapping("queryAssignment")
	public String queryAssignment(HttpServletRequest request){
		int assignment_id = Integer.parseInt(request.getParameter("assignment_id"));
		Assignment assignment = new Assignment();
		try {
			assignment = adminServ.queryAssignmentById(assignment_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("assignment", assignment);
		return "repealassignment";
	}
	
	//下架任务
	@RequestMapping("repealAssignment")
	@ResponseBody
	public int repealAssignment(HttpServletRequest request,String reason,int assignment_id,int receiver_id){
		int sender_id = (int) request.getSession().getAttribute("admin_id");
		
		Message mes = new Message();
		mes.setAssignment_id(assignment_id);
		mes.setMessage_detail(reason);
		mes.setMessage_name("下架任务");
		mes.setSender_id(sender_id);
		mes.setReceiver_id(receiver_id);
		try {
			adminServ.repealAssignmentById(reason,assignment_id);
			adminServ.sendMessageOfRepealAssignment(mes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return 1;
	}
	
	//解封用户
	@RequestMapping("deblockUser")
	@ResponseBody
	public int deblockUser(int user_id){
		try {
			adminServ.deblockUser(user_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	//分页查询下架的任务
	@RequestMapping("queryRepealedAssignment")
	public String queryRepealedAssignment(HttpServletRequest request){
		String pageNow = request.getParameter("pageNow");
		 Page page = null;
		 int totalCount = 0;
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			totalCount = adminServ.searchRepealedAssignment();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = adminServ.queryRepealedAssignmentByPage(page.getStartPos(),page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				 list = adminServ.queryRepealedAssignmentByPage(page.getStartPos(),page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
			request.setAttribute("list", list);
			request.setAttribute("page", page);
		return "repealedassignment";
	}
}
