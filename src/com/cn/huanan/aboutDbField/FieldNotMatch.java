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
 * ����xml��maxlength�������ݿ��ж��峤����
 * @author qiujie
 *
 */
public class FieldNotMatch {

	//���ڴ�����ݿ������еı���
	private static ArrayList<String> tableNameList = new ArrayList<String>();
	
	/**
	 * �õ����ݿ������еı����Ž�list��
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
	 * �ȶ�xml����
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
							String dataType=rs.getString(2);
							for(int j=0;j<itemList.size();j++){
								Element item=itemList.get(j);
								/***************���ݿ�������ΪNUMBER �����������У��������string��  start*****************/
								if("NUMBER".equals(dataType) && columnName.equalsIgnoreCase(item.getAttributeValue("name"))){
									String jiaoyanType=item.getAttributeValue("datatype");
									//String dbType=item.getAttributeValue("dbtype");
									if( "string".equals(jiaoyanType)){
										System.out.println("CreateEditV_"+tablename+".xml��У����������"+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname")+"\t"+"���ݿ������ǣ�"+dataType+"\t"+"xml�е�datatype��:"+jiaoyanType);
									}
								}
								/***************���ݿ�������ΪNUMBER �����������У�����Ͳ���integer��  end*****************/
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