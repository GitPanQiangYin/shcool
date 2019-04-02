package com.zzuli.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzuli.pojo.Admin;
import com.zzuli.pojo.Assignment;
import com.zzuli.pojo.Message;
import com.zzuli.pojo.Users;

public interface AdminMapper {
	
	//注册管理员
	public void addAdmin(Admin admin) throws Exception;
	
	//登录
	public Admin login(@Param(value="email")String email,@Param(value="password")String password) throws Exception;
	
	//查询全部用户数量
	public int searchAllUsers()  throws Exception;
	
	//分页查询全部用户
	public List<Users> searchUsersByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;
	
	//查询全部用户
	public List<Users> searchUsers() throws Exception;
	
	//查询全部任务数量
	public int searchAllAssignment() throws Exception;
	//分页查询全部任务
	public List<Assignment> searchAssignmentByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;
	
	//查询低信誉用户数量
	public int searchLowCreditUsers()  throws Exception;
	//分页查询低信誉用户
	public List<Users> searchLowCreditUsersByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;

	//查询被封用户数量
	public int searchBannedUsers()  throws Exception;
	//分页查询被封用户
	public List<Users> searchBannedUsersByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;
	
	//解封用户
	public void deblockUser(int user_id) throws Exception;
	//发送低信誉警告信息
	public void sendWarningMessage(Message message) throws Exception;
	//查询提示修改信息的用户
	public int searchPleaseChangeUsers()  throws Exception;
	//分页查询提示修改信息的用户
	public List<Users> searchPleaseChangeUsersByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;
	
	//查询需要修改的任务数量
	public int queryWarningAssignment() throws Exception;
	//分页查询需要修改的任务
	public List<Assignment> queryWarningAssignmentByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;

	//根据ID封停用户
	public void banUser(int user_id) throws Exception;
	//根据ID修改用户状态为请修改信息
	public void pleaseChangeInfo(Users user) throws Exception;
	//向用户发送提示修改信息消息
	public void sendMessage(Message message) throws Exception;
	
	//根据ID查询任务详情
	public Assignment queryAssignmentById(int assignment_id) throws Exception;
	//根据ID下架任务
	public void repealAssignmentById(@Param(value="reason")String reason,@Param(value="assignment_id")int assignment_id) throws Exception;
	//向用户发送下架任务消息
	public void sendMessageOfRepealAssignment(Message message) throws Exception;
	
	//查询下架的任务数量
	public int searchRepealedAssignment() throws Exception;
	//分页查询需要下架的任务
	public List<Assignment> queryRepealedAssignmentByPage(@Param(value="startPos")int startPos,@Param(value="pageSize")int pageSize) throws Exception;
}
