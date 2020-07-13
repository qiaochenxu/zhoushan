#sql("antique")
 select * from v9_cangpinguanlixitong AS c,v9_cangpinguanlixitong_data AS  d where c.id=d.id and c.title like '% #para(keyword) %'
 #if(jibie!=null and jibie!=' ')
   and d.Cangpinjibie= #para(jibie)
 #end
 #if(feilei!=null and feilei!=' ')
   and d.Fengpinfeilei=#para(feilei)
 #end
 #if(niandai!null and niandai!=' ')
   and d.Niandai like '%#para(niandai)%'
 #end
#end