package com.yinmei.myd.api;

import cn.hutool.http.HttpRequest;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

@Before(CrossDomainInterceptor.class)
public class SeachController extends Controller {
    /**
     * 全部
     */
    public void seachAll(){

        String findkey = getPara("findkey", "");
        Integer max = getParaToInt("max");
        Integer offset = getParaToInt("offset");
        String domain = "http://47.110.243.129/api/pc/list?max="+max+"&offset="+offset+"&sort=id&order=desc&search="+findkey+"";
        String res = HttpRequest.post(domain).timeout(20000).execute().body();
        renderJson(res);
    }

    /**
     * 咨询检索
     */
    public void seachConsulting(){
        Integer pageNumber = getParaToInt("pageNumber",1);

        Integer pageSize = getParaToInt("pageSize",10);
        String keyword = getPara("keyword", "");
        String catid = getPara("catid", "");
//
       //新闻资讯
        if (!catid.isEmpty() && catid.equals("24")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize,"SELECT x.id,x.title,d.zhengwen,x.thumb"," from v9_xinwenzixun x,v9_xinwenzixun_data d where x.id=d.id and x.catid="+catid+" and x.title like '%"+keyword+"%' and x.status='99' ORDER BY  x.inputtime desc");
            set("paginate",paginate);
            //活动公告
        }else if (!catid.isEmpty() && "25".equals(catid)){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT h.id,h.title,d.zhengwen,h.thumb", " from v9_huodonggonggao h,v9_huodonggonggao_data d where h.id=d.id and h.catid=" + catid + " and h.title like '%" + keyword + "%' and h.status='99' ORDER BY h.inputtime desc");
            set("paginate",paginate);
            //馆务公开
        }else if (!catid.isEmpty() && catid.equals("26")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT g.id,g.title,d.zhengwen,g.thumb", " from v9_guanwugongkai g,v9_guanwugongkai_data d where g.id=d.id and g.catid=? and g.title like '%" + keyword + "%' and g.status='99' ORDER BY g.inputtime desc",catid);
            set("paginate",paginate);
            //文博动态
        }else if (!catid.isEmpty() && catid.equals("45")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT w.id,w.title,d.zhengwen,w.thumb", "from v9_wenbodongtai w,v9_wenbodongtai_data d where w.id=d.id and w.catid=? and w.title like '%" + keyword + "%' and w.status ='99' ORDER BY w.inputtime desc", catid);
            set("paginate",paginate);
        }
        renderJson();
    }

    /**
     * 展览搜索
     */
    public void seachWxhibition(){
        Integer pageNumber = getParaToInt("pageNumber",1);
        Integer pageSize = getParaToInt("pageSize",10);
        String keyword = getPara("keyword", "");
        String catid = getPara("catid", "");
        //基本陈列
        if (!catid.isEmpty() && catid.equals("27")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "select j.id,j.title,d.zhengwen,j.thumb ",  "from v9_jibenchenglie j,v9_jibenchenglie_data d where j.id=d.id and j.catid=? and j.title like '%" + keyword + "%' and j.status='99' ORDER BY j.inputtime desc", catid);
            set("paginate",paginate);
            //临时展览
        }else if (!catid.isEmpty() && catid.equals("55")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT l.id,l.title,d.zhengwen,l.thumb ", " FROM v9_linshizhanlan l,v9_linshizhanlan_data d where l.id=d.id and l.catid=? and l.title like '%" + keyword + "%' and l.status='99' ORDER BY l.inputtime desc", catid);
            set("paginate",paginate);
            //网上展厅
        }else if (!catid.isEmpty() && catid.equals("56")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT w.id,w.title,d.zhengwen,w.thumb ", " FROM v9_wangshangzhanting w,v9_wangshangzhanting_data d where w.id=d.id and w.catid=? and w.title like '%"+keyword+"%' and w.status='99' ORDER BY w.inputtime desc", catid);
            set("paginate",paginate);
        }
        renderJson();

    }

    /**
     * 活动检索
     */
    public void seachActivity(){
        Integer pageNumber = getParaToInt("pageNumber",1);
        Integer pageSize = getParaToInt("pageSize",10);
        String keyword = getPara("keyword", "");
        String catid = getPara("catid", "");
        //活动预告
        if (!catid.isEmpty() && catid.equals("33")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT h.id,h.title,d.zhengwen,h.thumb ", "FROM v9_huodongyugao h,v9_huodongyugao_data d where h.id=d.id and h.catid=? and h.title like '%" + keyword + "%' and h.status='99' ORDER BY h.inputtime desc", catid);
            set("paginate",paginate);
            //文博课堂
        }else if (!catid.isEmpty() && catid.equals("34")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT w.id,w.title,d.zhengwen,w.thumb ", "FROM v9_wenboketang w,v9_wenboketang_data d where w.id=d.id and w.catid=? and w.title like '%" + keyword + "%' and w.status='99' ORDER BY w.inputtime desc", catid);
            set("paginate",paginate);
            //文博讲座
        }else if(!catid.isEmpty() && catid.equals("35")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT w.id,w.title,d.zhengwen,w.thumb ", "FROM v9_wenbojiangzuo w,v9_wenbojiangzuo_data d where w.id=d.id and w.catid=? and w.title like '%" + keyword + "%' and w.status='99' ORDER BY w.inputtime desc", catid);
            set("paginate",paginate);
            //青少年活动
        }else if (!catid.isEmpty() && catid.equals("36")){
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, "SELECT q.id,q.title,d.zhengwen,q.thumb ", " FROM v9_qingshaonianhuodong q,v9_qingshaonianhuodong_data d where q.id=d.id and q.catid=? and q.title like '%" + keyword + "%' and q.status='99' ORDER BY q.inputtime desc", catid);
            set("paginate",paginate);
        }
        renderJson();
    }


}
