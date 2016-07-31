package com.au.proma.service;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.au.proma.dao.ProjectDao;
import com.au.proma.model.Project;

public class Test {
	
public static void main(String[] args) {
	Date sd=new Date(26, 7, 4);
	Date ed=new Date(26,7,27);
	Project pr=new Project("fedxemployee", "projectmanagement", "prem", 5, sd, ed);
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	ProjectDao pd = (ProjectDao) applicationContext.getBean("pdao");
	
	for(Project p: pd.getAllProjects()){
		System.out.println(p.getProjectname());
	}
	
}
}
