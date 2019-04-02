package com.zzuli.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Page;
import com.zzuli.service.AssignmentService;
import com.zzuli.service.UsersService;

@Controller
@RequestMapping("assignment")
public class AssignmentController {
	@Autowired
	private AssignmentService assignmenntServ;
	@Autowired
	private UsersService userServ;
	
	@RequestMapping("publishAssignment")
	public String PublishAssignment(HttpServletRequest request,Assignment assignment){
		HttpSession session = request.getSession();
		int promulgator_id=(int) session.getAttribute("user_id");
		assignment.setPromulgator_id(promulgator_id);
		try {
			assignmenntServ.PublishAssignment(assignment);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:QueryAssignmentByPage.action";
	}
/*	@RequestMapping("QueryAllAssignment")
	
	public String QueryAllAssignment(HttpServletRequest request){
		List<Assignment> list = new ArrayList<Assignment>();
		try {
			list = assignmenntServ.QueryAllAssignment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", list);	
		System.out.println(list);
		return "main";
	}*/
	
	@RequestMapping("FindAssignmentById")
	@ResponseBody
	public Assignment FindAssignmentById(int assignment_id){
		Assignment assignment = new Assignment(); 
		try {
			assignment=assignmenntServ.FindAssignmentById(assignment_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return assignment;
	}
	@RequestMapping("ReceiveAssignment")
	@ResponseBody
	public int  ReceiveAssignment(int assignment_id,HttpServletRequest request ){
		HttpSession session = request.getSession();
		int receiver_id = (int) session.getAttribute("user_id"); 
		int num = 0;	
		try {
			num = userServ.SearchAssignment(receiver_id);	//查询当前用户已接多少任务，超过两个无法继续接任务
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  				
		if(num <2){
			try {
				assignmenntServ.ReceiveAssignment(assignment_id, receiver_id);
				num=1;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			num = -1;
		}
		
		return num;
	}
	//分页查询显示主页
	@RequestMapping("QueryAssignmentByPage")
	 public String QueryAssignmentByPage(HttpServletRequest request){
		 String pageNow = request.getParameter("pageNow");
		 int user_id=(int) request.getSession().getAttribute("user_id");
		 int messageCount = 0;
		 Page page = null;
		 int totalCount = 0;
		 List<Assignment> list = new ArrayList<Assignment>();
		 try {
			 messageCount = userServ.messageCount(user_id);
			 totalCount = assignmenntServ.FindAllAssignment();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(pageNow!=null){
			 page = new Page(totalCount, Integer.parseInt(pageNow)); //????
			 try {
				list = assignmenntServ.QueryAssignmentByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }else{
			 page = new Page(totalCount, 1);
			 try {
				list = assignmenntServ.QueryAssignmentByPage(page.getStartPos(), page.getPageSize());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 request.setAttribute("list", list);
		 request.setAttribute("page", page);
		 if(messageCount!=0){
			 System.out.println(messageCount);
			 String count = "("+messageCount+")";
			 request.setAttribute("count", count);
			 System.out.println(count);
		 }
		 return "main";
	 }
	//完成任务
	@RequestMapping("FinishAssignment")
	@ResponseBody
	public int FinishAssignment(HttpServletRequest request,int assignment_id,int promulgator_id,double brokerage,int receiver_id){
		HttpSession session = request.getSession();
		Assignment assignment = new Assignment();
		int user_id=(int) session.getAttribute("user_id");
		assignment.setAssignment_id(assignment_id);
		assignment.setBrokerage(brokerage);
		assignment.setPromulgator_id(promulgator_id);
		assignment.setReceiver_id(receiver_id);
		int num = -1;
		if(user_id==promulgator_id){
			try {
				num = 1;
				assignmenntServ.AssignmentFinished(assignment_id);
				userServ.DeductMoney(assignment);
				userServ.AddMoney(assignment);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				num = 0;
				assignmenntServ.FinishAssignment(assignment_id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  num;
	}
	
	
	//放弃任务
	@RequestMapping("AbandonAssignment")
	@ResponseBody
	public int AbandonAssignment(int assignment_id,int receiver_id){
		int credit = 0;
		try {
			credit = userServ.SearchCredit(receiver_id);		//查询此任务接受者的当前信用积分
			assignmenntServ.AbandonAssignment(assignment_id); 	//放弃任务	
			userServ.ReduceCredit(receiver_id, credit); 		//放弃任务后任务接受者信誉降低
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return 0;
	}
	@RequestMapping("test1")
	public String test1(){
		return "main";
	}
	//修改任务
	@RequestMapping("updateAssignment")
	@ResponseBody
	public int updateAssignment(HttpServletRequest request){
		int num = 0;
		//java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String starttime1 = request.getParameter("starttime");
		String endtime1 = request.getParameter("endtime");
		java.util.Date starttime = null;
		java.util.Date endtime = null;
		try {
			starttime =  formatter.parse(starttime1);
			endtime =   formatter.parse(endtime1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assignment assignment = new Assignment();
		String assignment_name = request.getParameter("assignment_name"); 
		Double brokerage =Double.parseDouble(request.getParameter("brokerage")) ;
		String detail = request.getParameter("detail");
		int assignment_id = Integer.parseInt(request.getParameter("assignment_id1"));
		assignment.setAssignment_name(assignment_name);
		assignment.setBrokerage(brokerage);
		assignment.setStarttime(starttime);
		assignment.setEndtime(endtime);
		assignment.setDetail(detail);
		assignment.setAssignment_id(assignment_id);
		try {
			assignmenntServ.updateAssignment(assignment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; 
	}
	//根据任务ID查询任务状态
	@RequestMapping("queryAssignmentStatus")
	@ResponseBody
	public int queryAssignmentStatus(int assignment_id){
		int num = 0;
		String status = null;
		try {
			status = assignmenntServ.queryAssignmentStatus(assignment_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status.equals("等待确认完成")||status.equals("已被接受")){
			num=1;
		}else{
			num=0;
		}
		System.out.println(num);
		return num;
	}
	//下架任务
	@RequestMapping("cancelAssignment")
	@ResponseBody
	public int cancelAssignment(int assignment_id){
		try {
			assignmenntServ.cancelAssignment(assignment_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//重新发布任务
		@RequestMapping("republishAssignment")
		@ResponseBody
		public int republishAssignment(HttpServletRequest request){
			int num = 0;
			//java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			String starttime1 = request.getParameter("starttime");
			String endtime1 = request.getParameter("endtime");
			java.util.Date starttime = null;
			java.util.Date endtime = null;
			try {
				starttime =  formatter.parse(starttime1);
				endtime =   formatter.parse(endtime1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assignment assignment = new Assignment();
			String assignment_name = request.getParameter("assignment_name"); 
			Double brokerage =Double.parseDouble(request.getParameter("brokerage")) ;
			String detail = request.getParameter("detail");
			int assignment_id = Integer.parseInt(request.getParameter("assignment_id1"));
			assignment.setAssignment_name(assignment_name);
			assignment.setBrokerage(brokerage);
			assignment.setStarttime(starttime);
			assignment.setEndtime(endtime);
			assignment.setDetail(detail);
			assignment.setAssignment_id(assignment_id);
			try {
				assignmenntServ.republishAssignment(assignment);;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0; 
		}
	//评论任务
		@RequestMapping("commentAssignment")
		public String commentAssignment(HttpServletRequest request,int assignment_id){
			Assignment assignment = new Assignment();
			try {
				assignment = assignmenntServ.FindAssignmentById(assignment_id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("assignment", assignment);
			return "commentAssignment";
		}
}
