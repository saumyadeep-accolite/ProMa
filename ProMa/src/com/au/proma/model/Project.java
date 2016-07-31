package com.au.proma.model;

import java.util.Date;

public class Project{
	private String clientname;
	private String projectname;
	private String managername;
	private int resourceworking;
	private Date startdate;
	private Date enddate;
	private int status;
	
	
	public Project() {
		super();
	}
	
	
	public Project(String clientname, String projectname, String managername, int resourceworking, Date startdate,
			Date enddate) {
		super();
		this.clientname = clientname;
		this.projectname = projectname;
		this.managername = managername;
		this.resourceworking = resourceworking;
		this.startdate = startdate;
		this.enddate = enddate;
	}


	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public int getResourceworking() {
		return resourceworking;
	}
	public void setResourceworking(int resourceworking) {
		this.resourceworking = resourceworking;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}