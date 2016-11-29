package com.cn.huanan.aboutDbField;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.cn.utils.OperationUtils;
/**
 * 查找表中有信息主键编号的表和长度
 * @author qiujie
 *
 */
public class getTableContainXxzjbh {

	//用于存放数据库中所有的表名
	private static ArrayList<String> tableNameList = new ArrayList<String>();
	
	/**
	 * 得到数据库中所有的表并放进list中
	 */
	public static void getAllTableName(){
		//第一步，获取所有表名   
		Connection conn=null;
		ResultSet  rs=null;
		try {
			conn = OperationUtils.getConnection();
			DatabaseMetaData  dbmd=conn.getMetaData(); 
			String types[]={"TABLE"};
			rs=dbmd.getTables(null,null,"%",types);  
			while(rs.next()){  
			    String tablename=rs.getString(3);  
			    if(tablename.startsWith("ST_") || tablename.startsWith("XW_") || tablename.startsWith("ZT_") || tablename.startsWith("YW_")|| tablename.startsWith("GL_")){
			    	tableNameList.add(tablename);
			    }
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OperationUtils.closeRs(rs);
			OperationUtils.closeConn(conn);
		}
				 
	}
	
	/**
	 * 比对xml长度
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void getXxzjbhAndLength() throws JDOMException, IOException{
		getAllTableName();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = OperationUtils.getConnection();
			String sql="select column_name,data_type ,data_length from user_tab_columns where table_name=?";
			pstmt=conn.prepareStatement(sql);
			for(int i=0;i<tableNameList.size();i++){
				String tablename=tableNameList.get(i);
				pstmt.setString(1, tablename);
				rs=pstmt.executeQuery();
				while(rs.next()){//得到列名和长度
					String column_name=rs.getString(1);
					String data_length=rs.getString(3);
					if(column_name.contains("XXZJBH")){
						if(!"30".equals(data_length)){
							System.out.println(tablename+"\t"+column_name+"\t"+data_length+"\t"+"注意：我不是三十位的");
						}else{
							System.out.println(tablename+"\t"+column_name+"\t"+data_length);
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OperationUtils.closeRs(rs);
			OperationUtils.closePstmt(pstmt);
			OperationUtils.closeConn(conn);
		}
		System.out.println("finished......");
		
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		getXxzjbhAndLength();
	}
}
