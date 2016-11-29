package com.cn.huanan.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class FindXBDictionaryJBNo {

	private static ArrayList<String> jbFilePathList=new ArrayList<String>();
	 
	static void getJbFilePath(String folderPath){
		//String folderPath="E:\\workspace\\GD\\20160817�㶫���첹������\\basic\\simplequery\\queryConfigFiles";
		File root = new File(folderPath);
		File[] files=root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){//������ļ�·������������
				//getJbFilePath(file.getAbsolutePath());
				//jbFilePathList.add(file.getAbsolutePath());
			}else{
				jbFilePathList.add(file.getAbsolutePath());
			}
		}
	}
	
	public static void findXbIsDictionaryAndJbIsNo() throws JDOMException, IOException{
		getJbFilePath("E:\\workspace\\GD\\20160817�㶫���첹������\\basic\\simplequery\\queryConfigFiles");
		for(String str : jbFilePathList){
			File jbfile=new File(str);
			if(jbfile.exists()){
				String fileName=jbfile.getName();
				if(!"simplequery.dtd".equals(fileName)){
					SAXBuilder builder=new SAXBuilder();
					Document jbDocument=builder.build(jbfile);
					Element jbRootQueryEntity=jbDocument.getRootElement();
					Element jbEleListData=jbRootQueryEntity.getChild("listData");
					Element jbEleListTable=jbEleListData.getChild("listTable");
					String jbTableName=jbEleListTable.getAttributeValue("tableName");
					if(jbTableName.startsWith("v_st") || jbTableName.startsWith("V_ST") || jbTableName.startsWith("V_XW") || jbTableName.startsWith("v_xw")){
						String xbFilePath="E:/workspace/GD/20160817�㶫���첹������/basic/library/xml/CreateEdit"+jbTableName+".xml";
						//��ȡ����  ��ѯ�ֶ����� 
						Element jbEleColumns=jbEleListTable.getChild("columns");
						List<Element> jbColumnsList=jbEleColumns.getChildren("column");
						//��ȡ����ѯ����
						Element jbEleQueryConditions=jbRootQueryEntity.getChild("queryConditions");
						List<Element> jbQueryColumns=null;
						if(jbEleQueryConditions!=null && jbEleQueryConditions.getChildren("queryCondition")!=null){
							jbQueryColumns=jbEleQueryConditions.getChildren("queryCondition");
						}
						
						//���������Ϊ�ֵ���ֶ�
						File xbfile=new File(xbFilePath);
						if(xbfile.exists()){
							SAXBuilder xbBuilder=new SAXBuilder();
							Document xbDocument=xbBuilder.build(xbfile);
							Element xbRootEle=xbDocument.getRootElement();
							List<Element> xbItemList=xbRootEle.getChildren("item");
							for (Element xbItem : xbItemList) {
								String xbInputType=xbItem.getAttributeValue("inputtype");//select �����ֵ�
								Element xbDitems=xbItem.getChild("ditems");
								String xbSrc=null;//dictionary�Ĳ��Ƕ�̬�ֵ� dynamic �Ƕ�̬�ֵ�
								String xbDictionaryKind=null;//�ֵ�kind
								if(xbDitems!=null){
									xbSrc=xbDitems.getAttributeValue("src");//dictionary
									if("dictionary".equals(xbSrc)){
										xbDictionaryKind=xbDitems.getAttributeValue("kind");
									}
								}
								
								if("select".equals(xbInputType) && "dictionary".equals(xbSrc)){
									String xbFieldName=xbItem.getAttributeValue("name");
									//�����ʾ������
//									for(Element showColumn : jbColumnsList){
//										if(xbFieldName.equalsIgnoreCase(showColumn.getAttributeValue("colName")) && showColumn.getAttribute("dictKind")==null){
////											if(xbDictionaryKind!=null){
////												showColumn.setAttribute("dictKind","dict");
////												showColumn.setAttribute("dictExpression",xbDictionaryKind);
////											}
//											//System.out.println("CreateEdit"+jbTableName+".xml"+"\t"+xbItem.getAttributeValue("name")+"\t"+xbItem.getAttributeValue("cnname")+"\t"+jbfile.getName()+"\t"+showColumn.getAttributeValue("colName")+"\t"+showColumn.getAttributeValue("cname")+"\t"+"��ʾ����");
//										}
//									}
									//��ѯ������
									if(jbQueryColumns!=null){
										for(Element queryColumn : jbQueryColumns){
											if(xbFieldName.equalsIgnoreCase(queryColumn.getAttributeValue("colName")) && queryColumn.getAttribute("dictKind")==null){
//												if(xbDictionaryKind!=null){
//													queryColumn.setAttribute("dictKind","dict");
//													queryColumn.setAttribute("dictExpression",xbDictionaryKind);
//												}
												System.out.println(xbDictionaryKind+"\t"+"CreateEdit"+jbTableName+".xml"+"\t"+xbItem.getAttributeValue("name")+"\t"+xbItem.getAttributeValue("cnname")+"\t"+jbfile.getName()+"\t"+queryColumn.getAttributeValue("colName")+"\t"+queryColumn.getAttributeValue("cname")+"\t"+"��ѯ������");
											}
										}
									}
								}
							}
						}
//						Format format = Format.getCompactFormat();  
//						format.setEncoding("GBK");
//						format.setIndent("     ");  
//						XMLOutputter XMLOut = new XMLOutputter(format); 
//						
//						XMLOut.output(jbDocument, new FileOutputStream(jbfile));
					}
				}
			}
			
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static void main(String[] args) throws JDOMException, IOException {
		
		findXbIsDictionaryAndJbIsNo();
		System.out.println("ִ�����");

	}

}
