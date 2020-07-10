package com.yinmei.myd.api;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sun.org.apache.bcel.internal.generic.Select;

/**
 * @author 乔晨旭
 */
@Before(CrossDomainInterceptor.class)
public class ManagemenController extends Controller {
    public void antique(){
        Integer pageNumber = getParaToInt("pageNumber",1);
        Integer pageSize = getParaToInt("pageSize",10);
        String fengpinfeilei = getPara("fengpinfeilei","");
        //瓷,陶器,雕塑、造像的分类id
        String porcelain = "8a7aef0958b37e280158b38d781e0033";
        //书法绘画,碑帖拓本的分类ID
        String calligraphy = "8a7aef0958b37e280158b39114d30057";
        //玉器的分类ID
        String jade = "8a7aef0958b37e280158b38e61b4003b";
        //古籍图书的分类ID
        String book = "8a7aef0958b37e280158b391666e005b";
        //标本化石的分类ID
        String specimen = "8a7aef0958b37e280158b38ffed8004b";
        //其他
        //其他中的其他和文件分类ID
        String rests = "8a7aef0958b37e280158b391b34d005f";
        //其他中的档案文书 家具的分类ID
        String record = "4028c2526174389e016182f203ca002c";
        //其他中的石器分类ID
        String stoneware = "8a7aef0958b37e280158b38ffed8004b";
        //其他中的铜器ID
        String brass = "8a7aef0958b37e280158b38fabab0047";
        //其他中的竹木雕和度量衡器的分类ID
        String wood = "8a7aef0958b37e280158b390a8290053";
        //玺印符牌 牙骨角器 金银器的分类ID
        String tool = "8a7aef0958b37e280158b38f0331003f";
        //其他中的钱币分类ID
        String coin = "8a7aef0958b37e280158b3933a730073";


        //瓷,陶器,雕塑、造像
        if (fengpinfeilei.equals(porcelain)){
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei='8a7aef0958b37e280158b38d781e0033' ORDER BY c.id desc";
            Page<Record> paginate = Db.paginate(pageNumber,pageSize,select,from);
            set("paginate",paginate);
            //法绘画,碑帖拓本
        }else if (fengpinfeilei.equals(calligraphy)){
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei='8a7aef0958b37e280158b39114d30057' ORDER BY c.id desc";
            Page<Record> paginate = Db.paginate(pageNumber,pageSize,select,from);
            set("paginate",paginate);
            //玉器的分类ID
        }else if (fengpinfeilei.equals(jade)){
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei='8a7aef0958b37e280158b38e61b4003b' ORDER BY c.id desc";
            Page<Record> paginate = Db.paginate(pageNumber,pageSize,select,from);
            set("paginate",paginate);
            //古籍图书
        }else if (fengpinfeilei.equals(book)){
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei='8a7aef0958b37e280158b391666e005b' ORDER BY c.id desc";
            Page<Record> paginate = Db.paginate(pageNumber,pageSize,select,from);
            set("paginate",paginate);
            //标本
        }else if (fengpinfeilei.equals(specimen)) {
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei='8a7aef0958b37e280158b38ffed8004b' ORDER BY c.id desc";
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, select, from);
            set("paginate", paginate);
            //其他
        }else if (fengpinfeilei.equals(rests) || fengpinfeilei.equals(record) || fengpinfeilei.equals(brass) || fengpinfeilei.equals(wood) || fengpinfeilei.equals(tool) || fengpinfeilei.equals(coin) || fengpinfeilei.equals(stoneware)) {
            String select = "select c.id,c.title,c.thumb ";
            String from = " from v9_cangpinguanlixitong c,v9_cangpinguanlixitong_data d where c.id=d.id and d.Fengpinfeilei IN('"+rests+"','"+record+"','"+stoneware+"','"+brass+"','"+wood+"','"+tool+"','"+coin+"') ORDER BY c.inputtime desc";
            Page<Record> paginate = Db.paginate(pageNumber, pageSize, select, from);
            set("paginate", paginate);
        }
        renderJson();
    }
}
