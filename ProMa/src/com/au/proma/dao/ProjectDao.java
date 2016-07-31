package com.au.proma.dao;
import com.au.proma.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDao {
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}


	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	public ArrayList<Project> extractProjectsUnderBU(String buname)
	{
		String query="select p.clientname,p.projectname,u.username,"+
"p.resourceworking,p.startdate,p.enddate from dbo.bu as bu,dbo.project as p, dbo.users"+
"as u where bu.buname='"+buname+ 
"' and bu.buid=p.buid" +
"and p.projectmanagerid=u.userid";
		return jdbcTemplate.query(query, new ResultSetExtractor< ArrayList<Project> >() {

			public ArrayList<Project> extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				
				ArrayList<Project> temp=new ArrayList<Project>();
				while (rs.next()){
				String clientname=rs.getString("clientname");
				String projectname=rs.getString("projectname");
				String managername=rs.getString("username");
				int resourceworking=rs.getInt("resourceworking");
				Date startdate=rs.getDate("startdate");
				Date enddate=rs.getDate("enddate");
				Project obj=new Project(clientname, projectname, managername, resourceworking, startdate, enddate);
				temp.add(obj);
				}
				return temp;
			}
		});
	}
	
	
	public int updateProject(Project pobj)
	{
		UserDao udao=new UserDao();
		Integer projectmanagerid =udao.getUserId(pobj.getManagername());
		String query="update dbo.project set projectmanagerid='"+projectmanagerid.toString()+"' , resourceworking='"+pobj.getResourceworking()+"' ,startdate='"+ pobj.getStartdate().toString()+"' ,enddate='"+pobj.getEnddate().toString()+"' where projectname='"+pobj.getProjectname()+"'and clientname='"+pobj.getClientname()+"'";
		return jdbcTemplate.update(query);
	}
	
	public int insertProject(Project pobj,int buid)
	{
		UserDao udao=new UserDao();
		Integer id=udao.getUserId(pobj.getManagername());
		String query="insert into dbo.Project(projectname,clientname,projectmanagerid,buid,resourceworking,startdate,enddate)"+
                       "values('"+pobj.getProjectname()+"','"+pobj.getClientname()+"','"+id.toString()+"','"+buid+"','"+pobj.getResourceworking()
                       +"','"+pobj.getStartdate().toString()+"','"+pobj.getEnddate()+"')";
		return jdbcTemplate.update(query);
	}
	
	public List<Project> getAllProjects(){
		
		String sql="select * from dbo.Project";
		
		return jdbcTemplate.query(sql, new RowMapper<Project>(){

			@Override
			public Project mapRow(ResultSet arg0, int arg1) throws SQLException {
				Project p = new Project();
				p.setClientname(arg0.getString("clientname"));
				p.setProjectname(arg0.getString("projectname"));
				return p;
			}
			
		});
		
	}
	
	
}
