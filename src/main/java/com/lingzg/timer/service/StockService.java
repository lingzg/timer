package com.lingzg.timer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lingzg.timer.dao.BaseDao;

@Service
public class StockService {

	@Autowired
	private BaseDao dao;
	
	public boolean save(String[] arr){
		if("0".equals(arr[2])){
			return true;
		}
		String sql = "select count(1) from t_shares_data where s_code=? and s_date=? and s_time=?";
		int count = (int) dao.get(sql, Integer.class, arr[0], arr[31], arr[32]);
		if(count>0){
			return true;
		}
		sql = "insert into t_shares_data(s_date,s_time,s_name,s_code,jrkp,zrsp,dqjg,zd,zdf,jrzg,jrzd,jrbd,jrbf,cjsl,cjje,jrsp) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] params = {arr[31], arr[32],arr[1],arr[0],arr[2],arr[3],arr[4],null,null,arr[5],arr[6],null,null,arr[9],arr[10],null};
		Double zrsp = Double.parseDouble(arr[3]);
		params[7] = Double.parseDouble(arr[4])-zrsp;
		params[8] = (double) params[7]/zrsp*100;
		params[11] = Double.parseDouble(arr[5])-Double.parseDouble(arr[6]);
		params[12] = (double) params[11]/zrsp*100;
		if("15:00:00".compareTo(arr[32])<=0){
			params[15] = arr[4];
		}
		long id = dao.save(sql, params);
		return true;
	}
}
