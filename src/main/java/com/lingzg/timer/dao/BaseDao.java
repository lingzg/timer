package com.lingzg.timer.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public long save(String sql, Object...args){
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator preparedStatementCreator = con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(args != null && args.length>0){
            	for(int i=0;i<args.length;i++){
            		ps.setObject(i+1, args[i]);
            	}
            }
            return ps;
        };
        jdbcTemplate.update(preparedStatementCreator, keyHolder);
        return keyHolder.getKey().longValue();
	}
	
	public int update(String sql, Object...args){
		return jdbcTemplate.update(sql, args);
	}
	
	public List<Map<String, Object>> query(String sql, Object...args){
		return jdbcTemplate.queryForList(sql, args);
	}
	
	public List<?> query(String sql, Class<?> cls, Object...args){
		return jdbcTemplate.queryForList(sql, cls, args);
	}
	
	public Object get(String sql, Class<?> cls, Object...args){
		return jdbcTemplate.queryForObject(sql, cls, args);
	}
	
	public Map<String, Object> get(String sql, Object...args){
		return jdbcTemplate.queryForMap(sql, args);
	}

	public void executeBatch(String sql, List<Object[]> batchArgs) {
		jdbcTemplate.batchUpdate(sql, batchArgs);
		
	}
}
