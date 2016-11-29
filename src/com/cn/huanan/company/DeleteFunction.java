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
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				// getFiles(file.getAbsolutePath());
				// filelist.add(file.getAbsolutePath());
				// System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getName());
			} else {
				// System.out.println("显示"+filePath+"下所有子目录"+file.getName());
				filelist.add(file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void addDeleteFunction() throws JDOMException, IOException{
		//指定要操作的文件夹
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/simplequery/queryConfigFiles";
		//获取所有的文件绝对路径放进filelist中
		getFiles(filePath);
		//SAXBuilder是一个JDOM解析器 能将路径中的XML文件解析为Document对象
		SAXBuilder builder = new SAXBuilder();
		//文件修改计数器
		int count=0;
		//遍历每个文件
		for (int i = 0; i < filelist.size(); i++) {
			//是否存在dbUtil.js
			boolean isExistsDBtil=false;
			//是否存在CommonDeleteFunction.js
			boolean isExistsDelete=false;
			//是否写xml
			boolean isWriteXml=false;
			//得到单个文件路径
			String filePathAndName  = filelist.get(i);
			//转义一下文件的\为\\
			String filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			//得到这个文件
			File file  = new File(filePathZY);
			//获取文件的名字
			String fileName=file.getName();
			//文件后缀为.dtd的不处理
			if(!fileName.contains(".dtd")){
				//利用SAXBuilder将file解析成document对象
				Document document = builder.build(file);
				//获取到根节点
				Element root  = document.getRootElement();//queryEntity
				/*****************************删除方法的修改操作 start****************************************/
				Element listData=root.getChild("listData");//listData
				Element listTable=listData.getChild("listTable");
				//得到tablename，为删除赋值
				String tableName=listTable.getAttributeValue("tableName");
				//只处理tablename为V_ST,V_XW,v_st,v_xw开头的
				if(tableName.startsWith("V_ST") || tableName.startsWith("v_st") || tableName.startsWith("V_XW") ||tableName.startsWith("v_xw")){
					Element operations=listTable.getChild("operations");
					if(null!=operations){
						List<Element> allOperation=operations.getChildren("operation");
						if(allOperation!=null && allOperation.size()>0){
							for(int j=0;j<allOperation.size();j++){
								Element opeartion=allOperation.get(j);
								if("删除".equals(opeartion.getAttributeValue("name"))){
									String opeartionContext=opeartion.getChild("jsAction").getText();
									//没有修改成deleteRecordBySystemid的则添加
									if(!opeartionContext.contains("deleteRecordBySystemid")){
										opeartion.getChild("jsAction").setText("deleteRecordBySystemid('"+tableName+"',SELECTEDROW.SYSTEMID);");
									}
								}
							}
						}
					}
					/*****************************删除方法的修改操作 end****************************************/
					/*****************************js引入操作 start****************************************/
					//添加js
					Element refs=root.getChild("refs");
					if(null!=refs){
						List<Element> allJsRef=refs.getChildren("js-ref");
						for(int k=0;k<allJsRef.size();k++){
							String refContent=allJsRef.get(k).getText();
							//判断是否已经引入了dbutil.js
							if(refContent.contains("dbUtil.js")){
								isExistsDBtil=true;
							}
							//判断是否已经引入了CommonDeleteFunction.js
							if(refContent.contains("CommonDeleteFunction.js")){
								isExistsDelete=true;
							}
						}
						//不存在则添加
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
					//每个简表都引入了自己的js，所有这里不处理	
						
					//}
					/*****************************js引入操作 end****************************************/
					++count;
					//格式化xml
					Format format = Format.getCompactFormat();  
					//设置编码格式
					format.setEncoding("GBK");
					//setIndent是设置分隔附的意思，一般都是用空格，就是当你新节点后，自动换行并缩进，有层次感，
					//如果这样写setIndent("")，就只有换行功能，而不会缩进了，如果写成setIndent(null)，
					//这样就即不换行也不缩进，全部以一行显示了，默认的就是这样的效果，不好看得
					format.setIndent("     ");  
					if(isWriteXml){
						XMLOutputter XMLOut = new XMLOutputter(format); 
						//写出
						XMLOut.output(document, new FileOutputStream(filePathZY));
					}
					
				}
			}
		}
		System.out.println("一共修改了:"+count+" 文件");
	}	
	
	public static void main(String[] args) throws JDOMException, IOException{
		addDeleteFunction();
	}
}
