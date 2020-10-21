package com.lingzg.timer.task;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lingzg.timer.service.StockService;
import com.lingzg.timer.util.HttpUtil;

@Component
public class StockTask {

	@Autowired
	private StockService service;
	
//	@Scheduled(cron = "0 0/5 9-15 * * MON-FRI")
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
	
	@Scheduled(cron = "0 1/5 9-15 * * MON-FRI")
	public void batchCollect(){
		System.out.println("-----------batchCollect at "+LocalDateTime.now());
		LocalTime now = LocalTime.now();
		if(now.isBefore(LocalTime.of(9, 30)) || now.isAfter(LocalTime.of(15, 5))){
			return;
		}
		List<Map<String, Object>> stocks = service.queryStock();
		System.out.println("-----------stocks size: "+stocks.size());
		int size=100;
		int count = stocks.size();
		int page = count%size==0?count/size:count/size+1;
		String url="http://hq.sinajs.cn/list=";
		for(int i=0;i<page;i++){
			String list = stocks.stream().skip(i*size).limit(size).map(x -> x.get("s_code").toString()).reduce("", (x,y) -> x+","+y);
			String result = HttpUtil.sendGet(url+list.substring(1));
			String[] results = result.replaceFirst(";$", "").split(";");
			service.saveBatch(results);
		}
	}
	
	@Scheduled(cron = "0 45 15 * * MON-FRI")
	public void deleteRepeat(){
		System.out.println("-----------deleteRepeat--------------");
		service.deleteRepeat();
	}
	
}
