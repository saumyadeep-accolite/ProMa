package com.au.proma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	
	public ArrayList<ProjectDTO> extractProjectsUnderBU(String buname)
	{
		String query="select p.clientname,p.projectname,u.username,"+
"p.resourceworking,p.startdate,p.enddate from dbo.bu as bu,dbo.project as p, dbo.users"+
"as u where bu.buname='"+buname+ 
"' and bu.buid=p.buid" +
"and p.projectmanagerid=u.userid";
		return jdbcTemplate.query(query, new ResultSetExtractor< ArrayList<ProjectDTO> >() {

			public ArrayList<ProjectDTO> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				
				ArrayList<ProjectDTO> temp=new ArrayList<ProjectDTO>();
				while (rs.next()){
				String clientname=rs.getString("clientname");
				String projectname=rs.getString("projectname");
				String managername=rs.getString("username");
				int resourceworking=rs.getInt("resourceworking");
				Date startdate=rs.getDate("startdate");
				Date enddate=rs.getDate("enddate");
				ProjectDTO obj=new ProjectDTO(clientname, projectname, managername, resourceworking, startdate, enddate);
				temp.add(obj);
				}
				return temp;
			}
		});
	}
	
	
	public int updateProject(ProjectDTO pobj)
	{
		UserDao udao=new UserDao();
		Integer projectmanagerid =udao.getUserId(pobj.getManagername());
		String query="update dbo.project set projectmanagerid='"+projectmanagerid.toString()+"' , resourceworking='"+pobj.getResourceworking()+"' ,startdate='"+ pobj.getStartdate().toString()+"' ,enddate='"+pobj.getEnddate().toString()+"' where projectname='"+pobj.getProjectname()+"'and clientname='"+pobj.getClientname()+"'";
		return jdbcTemplate.update(query);
	}
	
	public int insertProject(ProjectDTO pobj,int buid)
	{
		UserDao udao=new UserDao();
		Integer id=udao.getUserId(pobj.getManagername());
		String query="insert into dbo.Project(projectname,clientname,projectmanagerid,buid,resourceworking,startdate,enddate)"+
                       "values('"+pobj.getProjectname()+"','"+pobj.getClientname()+"','"+id.toString()+"','"+buid+"','"+pobj.getResourceworking()
                       +"','"+pobj.getStartdate().toString()+"','"+pobj.getEnddate()+"')";
		return jdbcTemplate.update(query);
	}
	
	
	
	
}
