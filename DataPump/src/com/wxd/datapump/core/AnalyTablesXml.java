package com.wxd.datapump.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wxd.datapump.entity.Table;

/**
 * Title:AnalyTablesXml.java
 * Description:com.wxd.datapump.core
 * 
 * Company: 
 * Copyright: Copyright (c) 2015
 * All right reserved.
 * Created time: 2016-5-30 下午2:17:10
 *
 * @author WangXuDong
 * @version 1.0
 */
public class AnalyTablesXml {
	private AnalyTablesXml(){}
	
	private static List<Table> tables;
	static{
		SAXReader reader = new SAXReader();
		Document document=null;
		tables = new ArrayList<Table>();
        try {
			document = reader.read(new File("config.xml"));
		} catch (DocumentException e) {
			System.out.println("配置文件加载错误。");
			e.printStackTrace();
		}
        String xpath= "/config/tables/table";
		@SuppressWarnings("unchecked")
		List<Element> elements = document.selectNodes(xpath);
		Table table = null;
		HashMap<String,String> map = null;
        for (int i = 0;i<elements.size();i++){
            String oname = elements.get(i).attributeValue("oriname");
            String tname = elements.get(i).attributeValue("tarname");
            table = new Table();
            table.setOriname(oname);
            table.setTarname(tname);
            map = new HashMap<String, String>();
            for(@SuppressWarnings("rawtypes")
			Iterator chirdelements =  elements.get(i).elementIterator("mapping");
            		chirdelements.hasNext();){
				Element elem  = (Element) chirdelements.next();
				map.put(elem.attributeValue("name"),elem.getTextTrim());
			}
            if(map==null||map.size()<=0){
            	System.out.println("文件读取错误，请设置配置文件中映射字段");
				System.exit(1);
            }
            table.setProperty(map);
            tables.add(table);
            System.out.println("数据从表"+oname+"导入到表"+tname+"准备好 done!");
        }
        if(tables.size()<=0){
			System.out.println("文件读取错误，请设置配置文件中映射字段");
			System.exit(1);
        }
	}
	/**
	 * 获取配置的Table对象
	 * @return
	 */
	public static List<Table> getTables() {
		return tables;
	}
}
