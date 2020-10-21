package com.lingzg.timer.task;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lingzg.timer.service.StockService;
import com.lingzg.timer.util.HttpUtil;

@Component
public class StockTask {

	@Autowired
	private StockService service;
	
	@Scheduled(cron = "0 0/5 9-15 * * MON-FRI")
	public void collect(){
		System.out.println(LocalDateTime.now());
		String url="http://hq.sinajs.cn/list=sh601222,sh600522,sz002080,sz002600,sh600312,sz002027,sz002106,sz002617,sz002661,sh600089,sz002075";
		String result = HttpUtil.sendGet(url);
		String[] results = result.replaceFirst(";$", "").split(";");
		for(String str : results){
			str = str.replace("var hq_str_", "").replace("=", ",").replace("\"", "");
			String[] arr = str.split(",");
			service.save(arr);
		}
	}
	
}
