package com.cn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationUtils {
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		  Connection conn = null;
		  
		  Class.forName("oracle.jdbc.driver.OracleDriver");//加入oracle的驱动，“”里面是驱动的路径
		   
		  String url = "jdbc:oracle:thin:@dzzx-db4.hnisi.com.cn:1525:dsrkjck";// 数据库连接，oracle代表链接的是oracle数据库；thin:@MyDbComputerNameOrIP代表的是数据库所在的IP地址（可以保留thin:）；1521代表链接数据库的端口号；ORCL代表的是数据库名称
	
		  String UserName = "pcs_gdxzbctx";// 数据库用户登陆名 ( 也有说是 schema 名字的 )
	
		  String Password = "pcs_gdxzbctx";// 密码
	
		  conn = DriverManager.getConnection(url, UserName, Password);
		  
		  return conn;
	}
	
	/**
	 * 关闭结果集
	 * @param rs
	 */
	public static void closeRs(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭预处理
	 * @param pstmt
	 */
	public static void closePstmt(PreparedStatement pstmt){
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void closeConn(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
