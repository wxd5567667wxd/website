package com.wxd.datapump.core;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wxd.datapump.entity.Table;
import com.wxd.datapump.util.DBhelp;

/**
 * Title:DataProcess.java
 * Description:com.wxd.datapump.core
 * 
 * Company: 
 * Copyright: Copyright (c) 2015
 * All right reserved.
 * Created time: 2016-5-30 下午3:07:09
 *
 * @author WangXuDong
 * @version 1.0
 */
public class DataProcess {
	/**
	 * 处理一个表
	 * @param table
	 */
	private void proccessTable(Table table){
		DBhelp dbh = new DBhelp();
		Statement otst  = null;
		Statement ttst  = null;
        try {
        	otst = dbh.getConnOrigin().createStatement();
        	ttst = dbh.getConnTarget().createStatement();
		} catch (SQLException e) {
			System.out.println("源数据源连接失败");
			e.printStackTrace();
		}
        System.out.println("开始导入从"+table.getOriname()+"表到"+table.getTarname());
        System.out.println("请稍后。。。。。。");
        String oproStr = "";
        String tproStr = "";
        Iterator<Entry<String, String>> iter = table.getProperty().entrySet().iterator();
        String[] oproStrs = new String[table.getProperty().size()];
        int i = 0;
        while (iter.hasNext()) {
        	Map.Entry<String,String> entry = (Map.Entry<String,String>) iter.next();
            String key = (String)entry.getKey();  //源数据表中的字段
            String val = entry.getValue();  //目标数据源中的字段
            oproStr += val+",";
            tproStr += key+",";
            oproStrs[i]=val;
            i++;
        }
        if(oproStr.length()>0){
        	oproStr = oproStr.substring(0, oproStr.length()-1);
        }
        if(tproStr.length()>0){
        	tproStr = tproStr.substring(0, tproStr.length()-1);
        }else{
        	System.out.println("配置文件出错!");
        	return ;
        }
        System.out.println(oproStr+"》》》》》》》"+tproStr);
        String osql = "SELECT "+oproStr+" FROM "+table.getOriname();
        ResultSet rs;
		try {
			rs = otst.executeQuery(osql);
	        while(rs.next()){
	        	String tvalStr = "";
	        	for (int j = 0; j < oproStrs.length; j++) {
	        		tvalStr += "'"+rs.getString(oproStrs[j])+"',";
				}
	        	tvalStr = tvalStr.substring(0, tvalStr.length()-1);
	        	String tsql = "INSERT INTO "+table.getTarname()+"("+tproStr+") VALUES("+tvalStr+")";
	        	int result = ttst.executeUpdate(tsql);
	        	if(result==1){
	        	}else{
	        		System.out.println("一条数据插入失败!"+tproStr);
	        	}
	        }
		} catch (SQLException e) {
			System.out.println("数据库访问出现错误"+e.getMessage());
			e.printStackTrace();
		}
	}
	public void proccess(){
		List<Table> tables = AnalyTablesXml.getTables();
		for (int i = 0; i < tables.size(); i++) {
			if(tables!=null&&tables.get(i).getProperty().size()>0){
				proccessTable(tables.get(i));
				System.out.println("数据库表"+tables.get(i).getOriname()+"导入数据到"+tables.get(i).getTarname()+"结束!");
			}
		}
		System.out.println("所有表全部导入，程序退出！");
	}
}
