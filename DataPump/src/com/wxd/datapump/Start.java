package com.wxd.datapump;

import com.wxd.datapump.core.DataProcess;

/**
 * Title:Start.java
 * Description:com.wxd.datapump
 * 
 * Company:
 * Copyright: Copyright (c) 2015
 * All right reserved.
 * Created time: 2016-5-30 下午4:44:29
 *
 * @author WangXuDong
 * @version 1.0
 */
public class Start {
	public static void main(String[] args) {
		DataProcess dp = new  DataProcess();
		dp.proccess();  //开始执行
	}
}
