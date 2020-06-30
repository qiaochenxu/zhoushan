package com.yinmei.myd.api;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;
@Before(CrossDomainInterceptor.class)
public class VolunteerController extends Controller {
    /**
     * 志愿者招募详情页
     */
    public void recruit(){
        String catid = getPara("catid","");
        String id = getPara("id","");
        List<Record> list = Db.find("select z.id,z.catid,z.title name,z.thumb,d.zhengwen,FROM_UNIXTIME(z.inputtime) releaseTime from v9_zhiyuanzhezhaomu z,v9_zhiyuanzhezhaomu_data d where z.id=d.id and z.status='99' and z.id=? and z.catid=?;", id,catid);
        set("list",list);
        renderJson();
    }

    /**
     * 志愿者风采详情页
     */
    public void mien(){
        String catid = getPara("catid","");
        String id = getPara("id","");
        List<Record> list = Db.find("select z.id,z.catid,z.title name,z.thumb,d.zhengwen,FROM_UNIXTIME(z.inputtime) releaseTime from v9_zhiyuanzhefengcai z,v9_zhiyuanzhefengcai_data d where z.id=d.id and z.status='99' and z.id=? and z.catid=?;", id, catid);
        set("list",list);
        renderJson();

    }

    /**
     * 征集捐赠详情页
     */
    public void collect(){
        String catid = getPara("catid","");
        String id = getPara("id","");
        List<Record> list = Db.find("select z.id,z.catid,z.title name,z.thumb,d.zhengwen,FROM_UNIXTIME(z.inputtime) releaseTime from v9_zhengjijuanzeng z,v9_zhengjijuanzeng_data d where z.id=d.id and z.status='99' and z.id=? and z.catid=?;", id, catid);
        set("list",list);
        renderJson();
    }
}
