package com.wxd.datapump.entity;

import java.util.HashMap;

/**
 * Title:存放配置文件中的Table对象
 * Description:com.wxd.datapump.entity
 * 
 * Company:
 * Copyright: Copyright (c) 2015
 * All right reserved.
 * Created time: 2016-5-30 下午2:23:52
 *
 * @author WangXuDong
 * @version 1.0
 */
public class Table {
	private String oriname;
	private String tarname;
	private HashMap<String,String> property;
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getTarname() {
		return tarname;
	}
	public void setTarname(String tarname) {
		this.tarname = tarname;
	}
	public HashMap<String, String> getProperty() {
		return property;
	}
	public void setProperty(HashMap<String, String> property) {
		this.property = property;
	}
}
