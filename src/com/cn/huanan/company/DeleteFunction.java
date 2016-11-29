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

public class DeleteFunction {
	
	private static ArrayList<String> filelist = new ArrayList<String>();

	/*
	 * ͨ���ݹ�õ�ĳһ·�������е�Ŀ¼�����ļ�
	 */
	static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * �ݹ����
				 */
				// getFiles(file.getAbsolutePath());
				// filelist.add(file.getAbsolutePath());
				// System.out.println("��ʾ"+filePath+"��������Ŀ¼�����ļ�"+file.getName());
			} else {
				// System.out.println("��ʾ"+filePath+"��������Ŀ¼"+file.getName());
				filelist.add(file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void addDeleteFunction() throws JDOMException, IOException{
		//ָ��Ҫ�������ļ���
		String filePath = "E:/workspace/GD/20160817�㶫���첹������/basic/simplequery/queryConfigFiles";
		//��ȡ���е��ļ�����·���Ž�filelist��
		getFiles(filePath);
		//SAXBuilder��һ��JDOM������ �ܽ�·���е�XML�ļ�����ΪDocument����
		SAXBuilder builder = new SAXBuilder();
		//�ļ��޸ļ�����
		int count=0;
		//����ÿ���ļ�
		for (int i = 0; i < filelist.size(); i++) {
			//�Ƿ����dbUtil.js
			boolean isExistsDBtil=false;
			//�Ƿ����CommonDeleteFunction.js
			boolean isExistsDelete=false;
			//�Ƿ�дxml
			boolean isWriteXml=false;
			//�õ������ļ�·��
			String filePathAndName  = filelist.get(i);
			//ת��һ���ļ���\Ϊ\\
			String filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			//�õ�����ļ�
			File file  = new File(filePathZY);
			//��ȡ�ļ�������
			String fileName=file.getName();
			//�ļ���׺Ϊ.dtd�Ĳ�����
			if(!fileName.contains(".dtd")){
				//����SAXBuilder��file������document����
				Document document = builder.build(file);
				//��ȡ�����ڵ�
				Element root  = document.getRootElement();//queryEntity
				/*****************************ɾ���������޸Ĳ��� start****************************************/
				Element listData=root.getChild("listData");//listData
				Element listTable=listData.getChild("listTable");
				//�õ�tablename��Ϊɾ����ֵ
				String tableName=listTable.getAttributeValue("tableName");
				//ֻ����tablenameΪV_ST,V_XW,v_st,v_xw��ͷ��
				if(tableName.startsWith("V_ST") || tableName.startsWith("v_st") || tableName.startsWith("V_XW") ||tableName.startsWith("v_xw")){
					Element operations=listTable.getChild("operations");
					if(null!=operations){
						List<Element> allOperation=operations.getChildren("operation");
						if(allOperation!=null && allOperation.size()>0){
							for(int j=0;j<allOperation.size();j++){
								Element opeartion=allOperation.get(j);
								if("ɾ��".equals(opeartion.getAttributeValue("name"))){
									String opeartionContext=opeartion.getChild("jsAction").getText();
									//û���޸ĳ�deleteRecordBySystemid�������
									if(!opeartionContext.contains("deleteRecordBySystemid")){
										opeartion.getChild("jsAction").setText("deleteRecordBySystemid('"+tableName+"',SELECTEDROW.SYSTEMID);");
									}
								}
							}
						}
					}
					/*****************************ɾ���������޸Ĳ��� end****************************************/
					/*****************************js������� start****************************************/
					//���js
					Element refs=root.getChild("refs");
					if(null!=refs){
						List<Element> allJsRef=refs.getChildren("js-ref");
						for(int k=0;k<allJsRef.size();k++){
							String refContent=allJsRef.get(k).getText();
							//�ж��Ƿ��Ѿ�������dbutil.js
							if(refContent.contains("dbUtil.js")){
								isExistsDBtil=true;
							}
							//�ж��Ƿ��Ѿ�������CommonDeleteFunction.js
							if(refContent.contains("CommonDeleteFunction.js")){
								isExistsDelete=true;
							}
						}
						//�����������
						if(!isExistsDBtil){
							isWriteXml=true;
							refs.addContent(new Element("js-ref").setText("${contextPath}/library/js/common/db/dbUtil.js"));
						}
						if(!isExistsDelete){
							isWriteXml=true;
							refs.addContent(new Element("js-ref").setText("${contextPath}/xzbc/aj/js/CommonDeleteFunction.js"));
						}
					}
					//else{
					//ÿ������������Լ���js���������ﲻ����	
						
					//}
					/*****************************js������� end****************************************/
					++count;
					//��ʽ��xml
					Format format = Format.getCompactFormat();  
					//���ñ����ʽ
					format.setEncoding("GBK");
					//setIndent�����÷ָ�������˼��һ�㶼���ÿո񣬾��ǵ����½ڵ���Զ����в��������в�θУ�
					//�������дsetIndent("")����ֻ�л��й��ܣ������������ˣ����д��setIndent(null)��
					//�����ͼ�������Ҳ��������ȫ����һ����ʾ�ˣ�Ĭ�ϵľ���������Ч�������ÿ���
					format.setIndent("     ");  
					if(isWriteXml){
						XMLOutputter XMLOut = new XMLOutputter(format); 
						//д��
						XMLOut.output(document, new FileOutputStream(filePathZY));
					}
					
				}
			}
		}
		System.out.println("һ���޸���:"+count+" �ļ�");
	}	
	
	public static void main(String[] args) throws JDOMException, IOException{
		addDeleteFunction();
	}
}
