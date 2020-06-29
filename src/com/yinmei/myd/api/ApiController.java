package com.yinmei.myd.api;
import java.io.*;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;


import javax.servlet.http.HttpServletResponse;

@Before(CrossDomainInterceptor.class) //接口都可以跨域访问
public class ApiController extends Controller {
	
	public void list() {
		
		String modelName = getPara(0);//模型名称
		int pageNumber=getParaToInt(1,1);//第几页，默认第一页
		int pageSize = getParaToInt(2,10);//每页显示数目，默认每页10条
		
		Page<Record> page= Db.paginate(pageNumber, pageSize, "select x_data.*,x.title,x.thumb,x.vthumb,x.keywords,x.description ","from v9_"+modelName+"_data x_data,v9_"+modelName+" x  where x.id=x_data.id and x.status=99");
		set("page",page);
		renderJson();
	}
	
	public void detail() {
		String modelName = getPara(0);
		String id = getPara(1,"0");
		List<Record> info = Db.find("select x_data.*,x.title,x.thumb,x.vthumb,x.keywords,x.description from v9_"+modelName+"_data x_data,v9_"+modelName+" x  where x.id=x_data.id and x.status=99 and x_data.id=?",id);
		set("info",info);
		renderJson();
	}

	/**
	 * 时间线
	 */
	public void timeline(){
		String year = getPara("year");
			List<Record> info = Db.find("SELECT x_data.*,x.title,x.thumb,x.inputtime,x.updatetime FROM v9_linshizhanlan x,v9_linshizhanlan_data x_data WHERE x.id=x_data.id AND status='99' AND YEAR(FROM_UNIXTIME(x.inputtime))=?",year);
			set("info",info);
		renderJson();
	}

//	/**
//	 * 首页轮播图
//	 */
//	public void slideshow(){
//		String id = getPara("id");
//		List<Record> info = Db.find("Select * from 表 where id=?",id);
//		set("info",info);
//		renderJson();
//
//	}



}