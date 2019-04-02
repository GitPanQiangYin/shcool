package com.zzuli.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zzuli.pojo.Assignment;

@Controller
public class HtmlController {
	
	@RequestMapping("regist")
	public String regist(){
		return "regist";
	}
	@RequestMapping("bookMeeting")
	public String bookMeeting(){
		return "bookmeeting";
	}
	
	@RequestMapping("login")
	public String login(){
		
		return "login";
	}
	@RequestMapping("adminlogin")
	public String adminlogin(){
		
		return "adminlogin";
	}
	@RequestMapping("main")
	public String main(){
		
		return "main";
	}
	
	@RequestMapping("test")
	public String test(){
		return "test";
	}
	@RequestMapping("publishAssignment")
	public String publishAssignment(){
		return "publishAssignment";
	}
	@RequestMapping("PersonInfo")
	public String PersonInfo(){
		return "personInfo";
	}
	@RequestMapping("ChangePassword")
	public String ChangePassword(){
		return "changePassword";
	}
	//注册管理员、登录
	@RequestMapping("addAdmin")
	public String addAdmin(){
		return "registAdmin";
	}
}
