package com.zzuli.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzuli.mapper.AdminMapper;
import com.zzuli.pojo.Admin;
import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Message;
import com.zzuli.pojo.Users;
import com.zzuli.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminMapper adminmapper;
	
	@Override
	public void addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("add");
		adminmapper.addAdmin(admin);
	}

	@Override
	public Admin login(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		Admin admin = adminmapper.login(email, password);
		return admin;
	}

	@Override
	public List<Users> searchUsers() throws Exception {
		List<Users> list = new ArrayList<Users>();
		list = adminmapper.searchUsers();
		return null;
	}

	@Override
	public int searchAllUsers() throws Exception {
		int num = adminmapper.searchAllUsers();
		return num;
	}

	@Override
	public List<Users> searchUsersByPage(int startPos, int pageSize) throws Exception {
		List<Users> list = new ArrayList<Users>();
		list = adminmapper.searchUsersByPage(startPos, pageSize);
		return list;
	}

	@Override
	public int searchAllAssignment() throws Exception {
		int num = adminmapper.searchAllAssignment();
		return num;
	}

	@Override
	public List<Assignment> searchAssignmentByPage(int startPos, int pageSize) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = adminmapper.searchAssignmentByPage(startPos, pageSize);
		return list;
	}

	@Override
	public int searchLowCreditUsers() throws Exception {
		int num = adminmapper.searchLowCreditUsers();
		return num;
	}

	@Override
	public List<Users> searchLowCreditUsersByPage(int startPos, int pageSize) throws Exception {
		List<Users> list = new ArrayList<Users>();
		list = adminmapper.searchLowCreditUsersByPage(startPos, pageSize);
		return list;
	}

	@Override
	public int searchBannedUsers() throws Exception {
		int num = adminmapper.searchBannedUsers();
		return num;
	}

	@Override
	public List<Users> searchBannedUsersByPage(int startPos, int pageSize) throws Exception {
		List<Users> list = new ArrayList<Users>();
		list = adminmapper.searchBannedUsersByPage(startPos, pageSize);
		return list;
	}

	@Override
	public int queryWarningAssignment() throws Exception {
		int num = adminmapper.queryWarningAssignment();
		return 0;
	}

	@Override
	public List<Assignment> queryWarningAssignmentByPage(int startPos, int pageSize) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = adminmapper.queryWarningAssignmentByPage(startPos, pageSize);
		return list;
	}

	@Override
	public void banUser(int user_id) throws Exception {
		adminmapper.banUser(user_id);
	}

	@Override
	public void pleaseChangeInfo(Users user) throws Exception {
		adminmapper.pleaseChangeInfo(user);
		
	}

	@Override
	public void sendMessage(Message message) throws Exception {
		adminmapper.sendMessage(message);
		
	}

	@Override
	public int searchRepealedAssignment() throws Exception {
		int num = adminmapper.searchRepealedAssignment();
		return 0;
	}

	@Override
	public List<Assignment> queryRepealedAssignmentByPage(int startPos, int pageSize) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = adminmapper.queryRepealedAssignmentByPage(startPos, pageSize);
		return list;
	}

	@Override
	public Assignment queryAssignmentById(int assignment_id) throws Exception {
		Assignment assignment = new Assignment();
		assignment= adminmapper.queryAssignmentById(assignment_id);
		return assignment;
	}

	@Override
	public void repealAssignmentById( String reason,int assignment_id) throws Exception {
		adminmapper.repealAssignmentById(reason,assignment_id);
	
		
	}

	@Override
	public void sendMessageOfRepealAssignment(Message message) throws Exception {
		adminmapper.sendMessageOfRepealAssignment(message);
		
	}

	@Override
	public int searchPleaseChangeUsers() throws Exception {
		int num = adminmapper.searchPleaseChangeUsers();
		return num;
	}

	@Override
	public List<Users> searchPleaseChangeUsersByPage(int startPos, int pageSize) throws Exception {
		List<Users> list = new ArrayList<Users>();
		list = adminmapper.searchPleaseChangeUsersByPage(startPos, pageSize);
		return list;
	}

	@Override
	public void deblockUser(int user_id) throws Exception {
		adminmapper.deblockUser(user_id);
		
	}

	@Override
	public void sendWarningMessage(Message message) throws Exception {
		adminmapper.sendWarningMessage(message);
		
	}

}
