package com.zzuli.service;

import java.util.List;

import com.zzuli.pojo.Assignment;

public interface AssignmentService {
	//发布任务
	public void PublishAssignment(Assignment assignment) throws Exception;
	//查询所有发布的任务
	public List<Assignment> QueryAllAssignment() throws Exception;
	//根据assignment_id查询时间详情
	public Assignment FindAssignmentById(int assignment_id) throws Exception;
	//接受任务
	public void ReceiveAssignment(int assignment_id,int receiver_id) throws Exception;
	//查询所有未完成的任务
	public int FindAllAssignment() throws Exception;
	//分页查询
	public List<Assignment> QueryAssignmentByPage(int startPos,int pageSize) throws Exception;
	//发布者确认任务已完成
	public void AssignmentFinished(int assignment_id) throws Exception;
	//接受者确认任务已完成
	public void FinishAssignment(int assignment_id) throws Exception;
	//接受者放弃接受任务
	public void AbandonAssignment(int assignment_id) throws Exception;
	//修改任务
	public void updateAssignment(Assignment assignment) throws Exception;
	//根据任务ID查询任务状态
	public String queryAssignmentStatus(int assignment_id) throws Exception;
	//下架任务
	public void cancelAssignment(int assignment_id) throws Exception;
	//重新发布任务
	public void republishAssignment(Assignment assignment) throws Exception;
}
