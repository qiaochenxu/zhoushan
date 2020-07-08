package com.yinmei.myd.common.config;

import com.jfinal.config.Routes;
import com.yinmei.myd.api.*;

public class AdminRoutes extends Routes {

	@Override
	public void config() {
		//针对一组路由配置baseViewPath
		//this.setBaseViewPath("/_view/_admin");
		//针对一组路由配置单独的拦截器
		//this.addInterceptor(new AdminAuthInterceptor());
		//针对后台管理系统配置路由+controller
		this.add("/api", ApiController.class);
		this.add("/seachs", SeachController.class);
		this.add("/download", DownloadController.class);
		this.add("/vol", VolunteerController.class);
	}

}