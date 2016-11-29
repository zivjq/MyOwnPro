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
	 * �õ����ݿ������еı��Ž�list��
	 */
	public static void getAllTableName(){
		//��һ������ȡ���б���   
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
	 * �ȶ�xml����
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
			String filePath="E:/workspace/GD/20160817�㶫���첹������/basic/library/xml";
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
						while(rs.next()){//�õ������ͳ���
							//System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+ rs.getString(3));
							String columnName=rs.getString(1);
							int columnLength=Integer.valueOf(rs.getString(3));
							for(int j=0;j<itemList.size();j++){
								Element item=itemList.get(j);
								String name=item.getAttributeValue("name");
								if(name.equalsIgnoreCase(columnName) && item.getAttribute("maxlength")!=null){
									String maxlengtStr=item.getAttributeValue("maxlength");
//									if(maxlengtStr.contains(",")){
//										System.out.println("maxlength���ô���"+"\t"+file.getName()+"\t"+"maxlength:"+maxlengtStr+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname"));
//									}
									if(!"".equals(maxlengtStr) && null != maxlengtStr && !maxlengtStr.contains(",")){
										int xmlLength=Integer.valueOf(maxlengtStr);
										if(xmlLength>columnLength){
											System.out.println(file.getName()+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname")+"\t"+"xml��maxlength�ǣ�"+xmlLength+" �����ݿⳤ����"+columnLength);
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
		
