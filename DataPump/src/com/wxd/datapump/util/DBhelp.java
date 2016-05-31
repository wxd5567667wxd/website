package com.wxd.datapump.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wxd.datapump.core.AnalyDatasourceXml;
/**
 * 数据库操作工具类
 * @author 
 *
 */
public class DBhelp {
	public Connection conn=null;
	/**
	 * 获取数据源数据库连接
	 * @return
	 */
	public Connection getConnOrigin(){
		try {
			Class.forName(AnalyDatasourceXml.getORIDRI());
			conn = DriverManager.getConnection(AnalyDatasourceXml.getORIURL(),AnalyDatasourceXml.getORIUSER(),AnalyDatasourceXml.getORIPWD());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
			
	}
	/**
	 * 获取目标数据库连接
	 * @return
	 */
	public Connection getConnTarget(){
		try {
			Class.forName(AnalyDatasourceXml.getORIDRI());
			conn = DriverManager.getConnection(AnalyDatasourceXml.getTAGURL(),AnalyDatasourceXml.getTAGUSER(),AnalyDatasourceXml.getTAGPWD());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
			
	}
	public void closeAll(ResultSet rs,PreparedStatement ps,Connection conn){
		try {
			rs.close();
			ps.close();
			conn = null;
			System.out.println("连接关闭");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
