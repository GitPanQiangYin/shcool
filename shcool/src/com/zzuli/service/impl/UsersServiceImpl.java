package com.zzuli.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzuli.mapper.UsersMapper;
import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Comment;
import com.zzuli.pojo.Message;
import com.zzuli.pojo.Users;
import com.zzuli.service.UsersService;
@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersMapper usersMapper;
	@Override
	public void addUser(Users user) throws Exception {
		// TODO Auto-generated method stub
		usersMapper.addUser(user);

	}
	@Override
	public Users login(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		Users user = usersMapper.login(email, password);
		return user;
	}
	@Override
	public Users findUserByEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Users checkEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		Users user = usersMapper.checkEmail(email);
		return user;
	}
/*	@Override
	public List<Assignment> MyAssignment(int user_id) throws Exception {
		// TODO Auto-generated method stub
		List<Assignment> list = new ArrayList<Assignment>();
		list=usersMapper.MyAssignment(user_id);
		return list;
	}*/
	@Override
	public List<Assignment> MyReceiveAssignment(int user_id) throws Exception {
		// TODO Auto-generated method stub
		List<Assignment> list = new ArrayList<Assignment>();
		list = usersMapper.MyReceiveAssignment(user_id);
		return list;
	}
	@Override
	public void DeductMoney(Assignment assignment) throws Exception {
		// TODO Auto-generated method stub
		usersMapper.DeductMoney(assignment);
		
	}
	@Override
	public void AddMoney(Assignment assignment) throws Exception {
		// TODO Auto-generated method stub
		usersMapper.AddMoney(assignment);
		
	}
	@Override
	public int SearchAssignment(int receiver_id) throws Exception {
		// TODO Auto-generated method stub
		int num = usersMapper.SearchAssignment(receiver_id);
		return num;
	}
	@Override
	public int SearchCredit(int receiver_id) throws Exception {
		// TODO Auto-generated method stub
		int num = 0 ;
		num = usersMapper.SearchCredit(receiver_id);
		return num;
	}
	@Override
	public void ReduceCredit(int receiver_id, int credit) throws Exception {
		// TODO Auto-generated method stub
		usersMapper.ReduceCredit(receiver_id, credit);
	}
	@Override
	public Users SearchPersonInfo(int user_id) throws Exception {
		// TODO Auto-generated method stub
		Users user = new Users();
		user = usersMapper.SearchPersonInfo(user_id);
		return user;
	}
	@Override
	public void ChangePassword(int user_id, String password) throws Exception {
		// TODO Auto-generated method stub
		usersMapper.ChangePassword(user_id, password);
	}
	@Override
	public int messageCount(int user_id) throws Exception {
		int num = usersMapper.messageCount(user_id);
		return num;
	}
	@Override
	public void changePersonInfo(Users user) throws Exception {
		usersMapper.changePersonInfo(user);
		
	}
	@Override
	public int MyAssignment(int user_id) throws Exception {
		int num = usersMapper.MyAssignment(user_id);
		return num;
	}
	@Override
	public List<Assignment> MyAssignmentByPage(int startPos,int pageSize,int user_id) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = usersMapper.MyAssignmentByPage(startPos, pageSize, user_id);
		return list;
	}
	@Override
	public int canceledAssignment(int user_id) throws Exception {
		int num = usersMapper.canceledAssignment(user_id);
		return 0;
	}
	@Override
	public List<Assignment> canceledAssignmentByPage(int startPos, int pageSize, int user_id) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = usersMapper.canceledAssignmentByPage(startPos, pageSize, user_id);
		return list;
	}
	@Override
	public List<Message> myReceiveMessageByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize,@Param(value="user_id")int user_id) throws Exception {
		List<Message> list = new ArrayList<Message>();
		list = usersMapper.myReceiveMessageByPage(startPos, pageSize, user_id);
		return list;
	}
	@Override
	public Message queryMessageById(int message_id) throws Exception {
		Message message = new Message();
		message = usersMapper.queryMessageById(message_id);
		return message;
	}
	@Override
	public void signMessage(int message_id) throws Exception {
		usersMapper.signMessage(message_id);
		
	}
	@Override
	public int queryMyFinishedAssignment(int user_id) throws Exception {
		int num = usersMapper.queryMyFinishedAssignment(user_id);
		return 0;
	}
	@Override
	public List<Assignment> queryMyFinishedAssignmentByPage(int startPos,int pageSize,int user_id) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = usersMapper.queryMyFinishedAssignmentByPage(startPos, pageSize, user_id);
		return list;
	}
	@Override
	public void publishComment(Comment comment) throws Exception {
		usersMapper.publishComment(comment);
		
	}

}
