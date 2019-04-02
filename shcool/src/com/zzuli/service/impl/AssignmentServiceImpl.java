package com.zzuli.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzuli.mapper.AssignmentMapper;
import com.zzuli.pojo.Assignment;
import com.zzuli.service.AssignmentService;
@Service
public class AssignmentServiceImpl implements AssignmentService {
	
	@Autowired
	private AssignmentMapper assignmentmapper;
	@Override
	public void PublishAssignment(Assignment assignment) throws Exception {
		// TODO Auto-generated method stub
		assignmentmapper.PublishAssignment(assignment);

	}
	@Override
	public List<Assignment> QueryAllAssignment() throws Exception {
		// TODO Auto-generated method stub
		List<Assignment> list = new ArrayList<Assignment>();
		list = assignmentmapper.QueryAllAssignment();
		return list;
	}
	@Override
	public Assignment FindAssignmentById(int assignment_id) throws Exception {
		// TODO Auto-generated method stub
		Assignment assignment = new Assignment();
		assignment=assignmentmapper.FindAssignmentById(assignment_id);
		return assignment;
	}
	@Override
	public void ReceiveAssignment(int assignment_id,int receiver_id) throws Exception {
		// TODO Auto-generated method stub
		assignmentmapper.ReceiveAssignment(assignment_id, receiver_id);
		
	}
	@Override
	public int FindAllAssignment() throws Exception {
		int totalCount = assignmentmapper.FindAllAssignment();
		return totalCount;
	}
	@Override
	public List<Assignment> QueryAssignmentByPage(int startPos, int pageSize) throws Exception {
		List<Assignment> list = new ArrayList<Assignment>();
		list = assignmentmapper.QueryAssignmentByPage(startPos, pageSize);
		return list;
	}
	@Override
	public void AssignmentFinished(int assignment_id) throws Exception {
		// TODO Auto-generated method stub
		assignmentmapper.AssignmentFinished(assignment_id);
		
	}
	@Override
	public void FinishAssignment(int assignment_id) throws Exception {
		// TODO Auto-generated method stub
		assignmentmapper.FinishAssignment(assignment_id);
	}
	@Override
	public void AbandonAssignment(int assignment_id) throws Exception {
		// TODO Auto-generated method stub
		assignmentmapper.AbandonAssignment(assignment_id);
		
	}
	@Override
	public void updateAssignment(Assignment assignment) throws Exception {
		assignmentmapper.updateAssignment(assignment);
		
	}
	@Override
	public String queryAssignmentStatus(int assignment_id) throws Exception {
		String status = assignmentmapper.queryAssignmentStatus(assignment_id);
		return status;
	}
	@Override
	public void cancelAssignment(int assignment_id) throws Exception {
		assignmentmapper.cancelAssignment(assignment_id);
		
	}
	@Override
	public void republishAssignment(Assignment assignment) throws Exception {
		assignmentmapper.republishAssignment(assignment);
		
	}

}
