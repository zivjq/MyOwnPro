package com.cn.aboutOracleDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.cn.utils.OperationUtils;

public class FindDifferentFieldBetweenTwoTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		compareField("XW_LA","XW_LA_BAK");
	}
	
	public static void compareField(String tableName1,String tableName2){
		Connection conn=null;
		PreparedStatement pstmt1=null;
		ResultSet rs1=null;
		int i=0;
		HashMap<String,String> table1_field_map=new HashMap<String,String>();
		HashMap<String,String> table2_field_map=new HashMap<String,String>();
		try {
			conn = OperationUtils.getConnection();
			String sql="select column_name,data_type ,data_length from user_tab_columns where table_name=?";
			pstmt1=conn.prepareStatement(sql);
			pstmt1.setString(1, tableName1);
			rs1=pstmt1.executeQuery();
			while(rs1.next()){//得到列名和长度
				String columnName=rs1.getString(1);
				table1_field_map.put(columnName, columnName);
			}
			pstmt1=conn.prepareStatement(sql);
			pstmt1.setString(1, tableName2);
			rs1=pstmt1.executeQuery();
			while(rs1.next()){//得到列名和长度
				String columnName=rs1.getString(1);
				table2_field_map.put(columnName, columnName);
			}
			
			for(Entry<String,String> entry : table1_field_map.entrySet()){
				String xw_la_field=entry.getKey();
				if(!table2_field_map.containsKey(xw_la_field)){
					++i;
					System.out.println(xw_la_field);
				}
			}
			
			System.out.println("两表差异字段数为:"+i);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OperationUtils.closeRs(rs1);
			OperationUtils.closePstmt(pstmt1);
			OperationUtils.closeConn(conn);
		}
	}

}
