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
 * 查找xml中maxlength大于数据库中定义长度类
 * @author qiujie
 *
 */
public class FieldNotMatch {

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
			    if(tablename.startsWith("ST_") || tablename.startsWith("XW_") || tablename.startsWith("ZT_")){
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
	public static void getFieldNotMatch() throws JDOMException, IOException{
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
							String dataType=rs.getString(2);
							for(int j=0;j<itemList.size();j++){
								Element item=itemList.get(j);
								/***************数据库中类型为NUMBER 但是在详表中校验类型是string的  start*****************/
								if("NUMBER".equals(dataType) && columnName.equalsIgnoreCase(item.getAttributeValue("name"))){
									String jiaoyanType=item.getAttributeValue("datatype");
									//String dbType=item.getAttributeValue("dbtype");
									if( "string".equals(jiaoyanType)){
										System.out.println("CreateEditV_"+tablename+".xml中校验数据类型"+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname")+"\t"+"数据库类型是："+dataType+"\t"+"xml中的datatype是:"+jiaoyanType);
									}
								}
								/***************数据库中类型为NUMBER 但是在详表中校验类型不是integer的  end*****************/
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
	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		getFieldNotMatch();
	}
}
