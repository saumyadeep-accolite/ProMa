package com.au.proma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

@Repository
public class BuDao {
	@Autowired
	private JdbcTemplate jdbctemplate;
	public Integer getBuId(String buname)
	{
		String query ="select buid from dbo.bu where buname='"+buname+"'";
		return jdbctemplate.query(query, new ResultSetExtractor< Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				
				
				Integer temp=0;
				while (rs.next()){
				temp=rs.getInt("buid");
				
				}
				return temp;
			}
		});
	}
}
