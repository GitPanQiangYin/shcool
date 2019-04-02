package com.zzuli.pojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Assignment {
	
	private int assignment_id;
	private int promulgator_id;
	private int receiver_id;
	private String assignment_name;
	private String detail;
	private double brokerage;
	private Date starttime;
	private Date endtime;
	private String status;
	public int getAssignment_id() {
		return assignment_id;
	}
	public void setAssignment_id(int assignment_id) {
		this.assignment_id = assignment_id;
	}
	public int getPromulgator_id() {
		return promulgator_id;
	}
	public void setPromulgator_id(int promulgator_id) {
		this.promulgator_id = promulgator_id;
	}
	public int getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getAssignment_name() {
		return assignment_name;
	}
	public void setAssignment_name(String assignment_name) {
		this.assignment_name = assignment_name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
	public double getBrokerage() {
		return brokerage;
	}
	public void setBrokerage(double brokerage) {
		this.brokerage = brokerage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	
	

}
