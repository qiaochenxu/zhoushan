package com.yinmei.myd.api;

import javax.servlet.http.HttpServletResponse;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class CrossDomainInterceptor implements Interceptor {

	@Override
	public void intercept(Invocation inv) {
		handler(inv.getController().getResponse());
			inv.invoke();
		
	}
	private void handler(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers",
				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
	}
}
