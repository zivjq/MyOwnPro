package com.cn.aboutOracleDatabase;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
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

public class DataColumn {
	
	private static ArrayList<String> tableNameList = new ArrayList<String>();
	
	
		
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
			    if(tablename.startsWith("ST_")|| tablename.startsWith("XW_")){
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
	public static void compareDBAndXML() throws JDOMException, IOException{
		getAllTableName();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn = OperationUtils.getConnection();
			String sql="select column_name,data_type ,data_length from user_tab_columns where table_name=?";
			String filePath="E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml";
			SAXBuilder builder = new SAXBuilder();
			if(tableNameList !=null && tableNameList.size()>0){
				for(int i=0;i<tableNameList.size();i++){
					String tablename=tableNameList.get(i);
					String finalPath=filePath+"/CreateEditV_"+tablename+".xml";
					File file = new File(finalPath);
					if(file.exists()){
						Document document = builder.build(file);
						Element root = document.getRootElement();
						List<Element> itemList=root.getChildren("item");
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, tablename);
						rs=pstmt.executeQuery();
						while(rs.next()){//得到列名和长度
							//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+ rs.getString(3));
							String columnName=rs.getString(1);
							int columnLength=Integer.valueOf(rs.getString(3));
							for(int j=0;j<itemList.size();j++){
								Element item=itemList.get(j);
								String name=item.getAttributeValue("name");
								if(name.equalsIgnoreCase(columnName) && item.getAttribute("maxlength")!=null){
									String maxlengtStr=item.getAttributeValue("maxlength");
//									if(maxlengtStr.contains(",")){
//										System.out.println("maxlength配置错误："+"\t"+file.getName()+"\t"+"maxlength:"+maxlengtStr+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname"));
//									}
									if(!"".equals(maxlengtStr) && null != maxlengtStr && !maxlengtStr.contains(",")){
										int xmlLength=Integer.valueOf(maxlengtStr);
										if(xmlLength>columnLength){
											System.out.println(file.getName()+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname")+"\t"+"xml中maxlength是："+xmlLength+" 而数据库长度是"+columnLength);
										}
									}
									
								}
							}
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
		
		
	}	
	
	public static void main(String[] args) throws JDOMException, IOException {
		compareDBAndXML();
	}
	
}
		
