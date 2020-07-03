package com.yinmei.myd.api;

import cn.hutool.http.HttpRequest;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

@Before(CrossDomainInterceptor.class)
public class SeachTestController extends Controller {
    /**
     * 藏品检索
     */
    public void seach(){
        String keyword = getPara("keyword"," ");
        String catid = getPara("catid","0");
        //第几页，默认第一页
        int pageNumber=getParaToInt(1,1);
        //每页显示数目，默认每页10条
        int pageSize = getParaToInt(2,10);
            Page<Record> page = Db.paginate(pageNumber,pageSize,"SELECT c.* FROM v9_cangpinjiansuo c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' or a.catid=?;",catid);
            set("page",page);
        renderJson();
    }
    /**
     * 咨询检索
     */
    public void seachConsulting(){
        //String modelName = getPara(0);//模型名称
        int pageNumber=getParaToInt(1,1);//第几页，默认第一页
        int pageSize = getParaToInt(2,25);//每页显示数目，默认每页10条
        String catid = getPara("catid","");
        String keyword = getPara("keyword","");



        if ("24".equals(catid)){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select c.*", " from v9_xinwenzixun c,v9_category a where a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid="+catid+";");
            //List<Record> info = Db.find("SELECT c.* FROM v9_xinwenzixun c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid="+catid+";");
            set("page",paginate);
            //活动公告
        }else if (catid.equals("25")){
            Page<Record> info = Db.paginate(pageNumber,pageSize,true,"SELECT c.title,d.zhengwen"," FROM v9_huodonggonggao c,v9_category a,v9_huodonggonggao_data d WHERE a.catid=c.catid and c.id=d.id and c.title like '%"+keyword+"%' or d.zhengwen like '%"+keyword+"%' and a.catid=? ORDER BY c.inputtime desc;",catid);
            set("info",info);
            //馆务公开
        }else if (catid.equals("26")){
            List<Record> info = Db.find("SELECT c.* FROM v9_guanwugongkai c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid="+catid+";");
            set("info",info);
            //文博动态
        }else if (catid.equals("45")){

            List<Record> info = Db.find("SELECT c.* FROM v9_wenbodongtai c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid="+catid+";");
            set("info",info);
        }
        renderJson();
    }

    /**
     * 展览搜索
     */
    public void seachWxhibition(){
        String keyword = getPara("keyword","");
        String catid = getPara("catid","0");
        //基本陈列
        if (catid.equals("27")){
            List<Record> info = Db.find("SELECT c.* FROM v9_jibenchenglie c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
            //临时展览
        }else if (catid.equals("55")){
            List<Record> info = Db.find("SELECT c.* FROM v9_linshizhanlan c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
            //网上展厅
        }else if (catid.equals("56")){
            List<Record> info = Db.find("SELECT c.* FROM v9_wangshangzhanting c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
        }
        renderJson();
    }

    /**
     * 活动检索
     */
    public void seachActivity(){
        String keyword = getPara("keyword","");
        String catid = getPara("catid","0");
        //活动预告
        if (catid.equals("33")){
            List<Record> info = Db.find("SELECT c.* FROM v9_huodongyugao c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
            //文博课堂
        }else if (catid.equals("34")){
            List<Record> info = Db.find("SELECT c.* FROM v9_wenboketang c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
            //文博讲座
        }else if (catid.equals("35")){
            List<Record> info = Db.find("SELECT c.* FROM v9_wenbojiangzuo c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
            //青少年活动
        }else if (catid.equals("36")){
            List<Record> info = Db.find("SELECT c.* FROM v9_qingshaonianhuodong c,v9_category a WHERE a.catid=c.catid and c.title like '%"+keyword+"%' and a.catid=?;",catid);
            set("info",info);
        }
        renderJson();

    }

    /**
     * 全部
     */
    public void seachAll(){
        String findkey = getPara("findkey", "");
        String domain = "http://47.110.243.129/api/pc/list?max=20&offset=0&sort=id&order=desc&search="+findkey+"";
        String res = HttpRequest.post(domain).timeout(20000).execute().body();
        renderJson(res);
    }

}
