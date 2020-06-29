package com.yinmei.myd.api;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(CrossDomainInterceptor.class)
public class DownloadController extends Controller {
    /**
     * 下载文件
     */
    public void downloads() {
        String fileName = getPara("fileName","");
        if (!fileName.isEmpty()){
            renderFile(fileName);
        }else {
            renderJson("文件不存在");
        }
    }
}
