package com.wxd.datapump.core;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Title:AnalyXml.java
 * Description:com.wxd.datapump.core
 * 
 * Company: 
 * Copyright: Copyright (c) 2015
 * All right reserved.
 * Created time: 2016-5-30 上午11:49:29
 *
 * @author WangXuDong
 * @version 1.0
 */
public class AnalyDatasourceXml {
	private AnalyDatasourceXml(){
		System.out.println("不能实例化");
	}
	private static String ORIDRI;
	private static String ORIURL;
	private static String ORIUSER;
	private static String ORIPWD;
	private static String TAGDRI;
	private static String TAGURL;
	private static String TAGUSER;
	private static String TAGPWD;
	static{
		SAXReader reader = new SAXReader();
		Document document=null;
		
        try {
			document = reader.read(new File("config.xml"));
		} catch (DocumentException e) {
			System.out.println("配置文件加载错误。");
			e.printStackTrace();
		}
        String xpath1= "/config/datasource/origin/property";
        String xpath2= "/config/datasource/target/property";
        @SuppressWarnings("unchecked")
		List<Element> elements1 = document.selectNodes(xpath1);
        @SuppressWarnings("unchecked")
		List<Element> elements2 = document.selectNodes(xpath2);
        for (Element e : elements1) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            if("driver".equals(name)){
            	ORIDRI = value;
            }else if("url".equals(name)){
            	ORIURL = value;
            }else if("username".equals(name)){
            	ORIUSER = value;
            }else if("password".equals(name)){
            	ORIPWD = value;
            }
        }
        System.out.println("源数据源:"+ORIDRI+" "+ORIURL+"  "+ORIUSER+"  "+"******");
        for (Element e : elements2) {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            if("driver".equals(name)){
            	TAGDRI = value;
            }else if("url".equals(name)){
            	TAGURL = value;
            }else if("username".equals(name)){
            	TAGUSER = value;
            }else if("password".equals(name)){
            	TAGPWD = value;
            } 
        }
        System.out.println("目标数据源:"+TAGDRI+" "+TAGURL+"  "+TAGUSER+"  "+"******");
        System.out.println("配置文件读取成功!");
	}
	public static String getORIDRI() {
		return ORIDRI;
	}
	public static String getORIURL() {
		return ORIURL;
	}
	public static String getORIUSER() {
		return ORIUSER;
	}
	public static String getORIPWD() {
		return ORIPWD;
	}
	public static String getTAGDRI() {
		return TAGDRI;
	}
	public static String getTAGURL() {
		return TAGURL;
	}
	public static String getTAGUSER() {
		return TAGUSER;
	}
	public static String getTAGPWD() {
		return TAGPWD;
	}

}
