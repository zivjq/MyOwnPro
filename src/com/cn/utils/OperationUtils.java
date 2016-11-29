package com.cn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OperationUtils {
	
	/**
	 * ��ȡ���ݿ�����
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		  Connection conn = null;
		  
		  Class.forName("oracle.jdbc.driver.OracleDriver");//����oracle������������������������·��
		   
		  String url = "jdbc:oracle:thin:@dzzx-db4.hnisi.com.cn:1525:dsrkjck";// ���ݿ����ӣ�oracle�������ӵ���oracle���ݿ⣻thin:@MyDbComputerNameOrIP����������ݿ����ڵ�IP��ַ�����Ա���thin:����1521�����������ݿ�Ķ˿ںţ�ORCL����������ݿ�����
	
		  String UserName = "pcs_gdxzbctx";// ���ݿ��û���½�� ( Ҳ��˵�� schema ���ֵ� )
	
		  String Password = "pcs_gdxzbctx";// ����
	
		  conn = DriverManager.getConnection(url, UserName, Password);
		  
		  return conn;
	}
	
	/**
	 * �رս����
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
	 * �ر�Ԥ����
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
	 * �ر�����
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
