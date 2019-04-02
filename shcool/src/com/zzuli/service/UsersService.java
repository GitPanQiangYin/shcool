package com.zzuli.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Comment;
import com.zzuli.pojo.Message;
import com.zzuli.pojo.Users;

public interface UsersService {
	//注册新用户
	public void addUser(Users user) throws Exception;
	//注册用户时检查邮箱是否已经被注册
	public Users checkEmail(String email) throws Exception;
	//登录
	public Users login(String email,String password) throws Exception;
	//根据邮箱查询用户信息
	public Users findUserByEmail(String email) throws Exception;
/*	//查询我发布的任务
	public List<Assignment> MyAssignment(int user_id) throws Exception;*/
	//查询我接受的任务
	public List<Assignment> MyReceiveAssignment(int user_id) throws Exception;
	//任务已完成，扣除佣金
	public void DeductMoney(Assignment assignment) throws Exception;
	//将佣金给完成者
	public void AddMoney(Assignment assignment) throws Exception;
	//查询当前用户已接多少任务，超过两个无法继续接任务
	public int SearchAssignment(int receiver_id)  throws Exception;
	//查询任务接受者的信誉积分
	public int SearchCredit(int receiver_id) throws Exception;
	//放弃任务后任务接受者信誉降低
	public void ReduceCredit(int receiver_id,int credit) throws Exception;
	//根据用户id查询用户详细信息
	public Users SearchPersonInfo(int user_id) throws Exception;
	//修改密码
	public void ChangePassword(@Param(value="user_id")int user_id,@Param(value="password")String password) throws Exception;
	
	//查询我接受的消息数量
	public int messageCount(int user_id) throws Exception;
	//修改个人信息
	public void changePersonInfo(Users user) throws Exception;
	
	//查询我发布的任务数量
	public int MyAssignment(int user_id) throws Exception;
	//分页查询我发布的任务
	public List<Assignment> MyAssignmentByPage(int startPos,int pageSize,int user_id) throws Exception;
	//查询我下架的任务数量
	public int canceledAssignment(int user_id) throws Exception;
	//分页查询我发布的任务
	public List<Assignment> canceledAssignmentByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize,@Param(value="user_id")int user_id) throws Exception;
	//分页查询我接受的未读消息
	public List<Message> myReceiveMessageByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize,@Param(value="user_id")int user_id) throws Exception;
	//根据消息ID查询消息详情
	public Message queryMessageById(int message_id) throws Exception;
	//标记消息为已读
	public void signMessage(int message_id) throws Exception;
	//查询我的已完成任务的数量
	public int queryMyFinishedAssignment(int user_id) throws Exception;
	//分页查询我的已完成的任务
	public List<Assignment> queryMyFinishedAssignmentByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize,@Param(value="user_id")int user_id) throws Exception;
	//发表评论
	public void publishComment(Comment comment)  throws Exception;
}
