package com.cn.huanan.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class JBAndXBOperation {
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
	 * 查找祥表中字典为X或XXX或XXXXX的ditems
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void findDictionaryXXXXX() throws JDOMException, IOException{
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml";
		getFiles(filePath);
		File file = null;
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		String filePathAndName = null;
		String filePathZY = null;
		for (int i = 0; i < filelist.size(); i++) {
			filePathAndName = filelist.get(i);
			//System.out.println(filePathAndName);
			//filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			file = new File(filePathAndName);
			document = builder.build(file);
			root = document.getRootElement();
			List<Element> child_item_list = root.getChildren("item");
			for (int j = 0; j < child_item_list.size(); j++) {
				Element item = child_item_list.get(j);
				Element ditem = item.getChild("ditems");
				if (null != ditem) {
					String ditem_kind_value = ditem.getAttributeValue("kind");
					if ("X".equals(ditem_kind_value)) {
						System.out.println(file.getName() + "\t" + item.getAttributeValue("cnname") + "\t" + item.getAttributeValue("name") + "\t" + "字典类型为X");
					} else if ("XXX".equals(ditem_kind_value)) {
						System.out.println(file.getName() + "\t" + item.getAttributeValue("cnname") + "\t" + item.getAttributeValue("name") + "\t" + "字典类型为XXX");
					} else if ("XXXXX".equals(ditem_kind_value)) {
						System.out.println(file.getName() + "\t" + item.getAttributeValue("cnname") + "\t" + item.getAttributeValue("name") + "\t" + "字典类型为XXXXX");
					}
				}
			}
		}
		System.out.println("检查完毕......");
	}
	
	/**
	 * 查找配置成select但是没有配置ditems的
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void findSelectNoDitems() throws JDOMException, IOException{
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml";
		getFiles(filePath);
		File file = null;
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		String filePathAndName = null;
		String filePathZY = null;
		for (int i = 0; i < filelist.size(); i++) {
			filePathAndName = filelist.get(i);
			filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			file = new File(filePathZY);
			document = builder.build(file);
			root = document.getRootElement();
			List<Element> child_item_list = root.getChildren("item");
			for (int j = 0; j < child_item_list.size(); j++) {
				Element item = child_item_list.get(j);
				if(null != item && "select".equals(item.getAttributeValue("inputtype"))){
					Element ditem=item.getChild("ditems");
					if(null == ditem){
						System.out.println(file.getName()+"\t"+"字段名："+item.getAttributeValue("name")+"\t"+"中文字段名："+item.getAttributeValue("cnname"));
					}
				}
			}
		}
		System.out.println("检查完毕......");
	}	
	
	/**
	 * 查找float类型错误的并修改
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void findFloatConfig() throws JDOMException, IOException{
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml";
		//E:/CreateEditV_YW_XLTH_SJAJ.xml
		getFiles(filePath);
		File file = null;
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		String filePathAndName = null;
		String filePathZY = null;
		String[] leftAndRight;
		int leftnum=0;
		int rightnum=0;
		int totalnum=0;
		boolean douhao=false;
		String maxlength=null;
		Element item=null;
		for (int i = 0; i < filelist.size(); i++) {
			filePathAndName = filelist.get(i);
			filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			//filePathZY="E:/CreateEditV_YW_XLTH_SJAJ.xml";
			file = new File(filePathZY);
			document = builder.build(file);
			root = document.getRootElement();
			List<Element> child_item_list = root.getChildren("item");
			for (int j = 0; j < child_item_list.size(); j++) {
				item= child_item_list.get(j);
				maxlength=item.getAttributeValue("maxlength");
				if(null !=maxlength && maxlength.contains(",")){
					douhao=true;
				}else{
					douhao=false;
				}
				if(null != item &&  douhao){
					
					leftAndRight=maxlength.split(",");
					
					leftnum=Integer.valueOf(leftAndRight[0])-Integer.valueOf(leftAndRight[1]);
					rightnum=Integer.valueOf(leftAndRight[1]);
					totalnum=Integer.valueOf(leftAndRight[0])+1;
					
					//设置maxlength
//					item.setAttribute("maxlength", totalnum+"");
//					//设置leftnum
//					item.setAttribute("leftnum",leftnum+"");
//					//设置rightnum
//					item.setAttribute("rightnum",rightnum+"");
//					
//					Format format = Format.getCompactFormat();  
//					format.setEncoding("GBK");
//					format.setIndent("     ");  
//					XMLOutputter XMLOut = new XMLOutputter(format); 
//					
//					XMLOut.output(document, new FileOutputStream(filePathZY));
					System.out.println(file.getName()+"\t"+item.getAttributeValue("name")+"\t"+item.getAttributeValue("cnname")+"\t"+maxlength);
				}
			}
		}
		System.out.println("检查完毕......");
	}
	
	/**
	 * 查找简表中字典为X或XXX或XXXXX的ditems
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void findDictionaryQueryXXXXX() throws JDOMException, IOException{
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/simplequery/queryConfigFiles";
		getFiles(filePath);
		File file = null;
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		String filePathAndName = null;
		String filePathZY = null;
		for (int i = 0; i < filelist.size(); i++) {
			filePathAndName = filelist.get(i);
			filePathZY = filePathAndName.replaceAll("\\\\", "\\\\\\\\");
			file = new File(filePathZY);
			String fileName=file.getName();
			if(!fileName.contains(".dtd")){
				document = builder.build(file);
				root = document.getRootElement();
				Element listData=root.getChild("listData");
				Element listTable=listData.getChild("listTable");
				Element columns=listTable.getChild("columns");
				List<Element> column=columns.getChildren("column");
				/***************************column 中的XXXXX******************************************/
				for(int j=0;j<column.size();j++){
					Element columnEle=column.get(j);
					Attribute attribute=columnEle.getAttribute("dictExpression");
					if(attribute!=null){
						String dictExpressionStr=columnEle.getAttributeValue("dictExpression");
						if("X".equals(dictExpressionStr)){
							System.out.println(file.getName()+"\t"+columnEle.getAttributeValue("colName")+"\t"+columnEle.getAttributeValue("cname")+"\t"+"字典类型为X");
						}else if("XXX".equals(dictExpressionStr)){
							System.out.println(file.getName()+"\t"+columnEle.getAttributeValue("colName")+"\t"+columnEle.getAttributeValue("cname")+"\t"+"字典类型为XXX");
						}else if("XXXXX".equals(dictExpressionStr)){
							System.out.println(file.getName()+"\t"+columnEle.getAttributeValue("colName")+"\t"+columnEle.getAttributeValue("cname")+"\t"+"字典类型为XXXXX");
						}
					}
				}
				/***************************column 中的XXXXX******************************************/
				/***************************查询条件queryConditions中的XXXXX******************************************/
				Element queryConditions=root.getChild("queryConditions");
				if(queryConditions!=null){
					List<Element> queryConditionList=queryConditions.getChildren("queryCondition");
					if(queryConditionList!=null && queryConditionList.size()>0){
						for (Element element : queryConditionList) {
							if(element.getAttribute("dictExpression")!=null){
								String dictExpressionContent=element.getAttributeValue("dictExpression");
								if("X".equals(dictExpressionContent)){
									System.out.println(file.getName()+"\t"+element.getAttributeValue("colName")+"\t"+element.getAttributeValue("cname")+"\t"+"字典类型为X");
								}else if("XXX".equals(dictExpressionContent)){
									System.out.println(file.getName()+"\t"+element.getAttributeValue("colName")+"\t"+element.getAttributeValue("cname")+"\t"+"字典类型为XXX");
								}else if("XXXXX".equals(dictExpressionContent)){
									System.out.println(file.getName()+"\t"+element.getAttributeValue("colName")+"\t"+element.getAttributeValue("cname")+"\t"+"字典类型为XXXXX");
								}
							}
						}
						
					}
				}
				/***************************查询条件queryConditions中的XXXXX******************************************/
				
			}
		}
		System.out.println("finished");
			
	}
	
	/**
	 * 查找公安部必要字段
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static void findRecordField() throws JDOMException, IOException{
		String filePath = "E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml";
		getFiles(filePath);
		File file = null;
		Document document = null;
		SAXBuilder builder = new SAXBuilder();
		Element root = null;
		String filePathAndName = null;
		String filePathZY = null;
		for (int i = 0; i < filelist.size(); i++) {
			filePathAndName = filelist.get(i);
			file = new File(filePathAndName);
			document = builder.build(file);
			root = document.getRootElement();
			String entryName=root.getAttributeValue("entryname");
			if(entryName.startsWith("V_") || entryName.startsWith("v_")){
				List<Element> child_item_list = root.getChildren("item");
				Map<String,String> itemNameMap=new HashMap<String,String>();
				for (int j = 0; j < child_item_list.size(); j++) {
					Element item = child_item_list.get(j);
					String itemName=item.getAttributeValue("name");
					itemNameMap.put(itemName.toUpperCase(), itemName);
				}
				if(!itemNameMap.containsKey("XXDJDW_GAJGMC")){
					System.out.println(file.getName()+"：中缺少上报公安部字段");
				}
			}
		}
		System.out.println("检查完毕......");
	}
	
	public static void main(String[] args) throws Exception{
		
		//findDictionaryXXXXX();//祥表字典为XXXXX的
		//findSelectNoDitems();//配置了select但是没有配置ditems的
		//findDictionaryByKind("DE00060");//查找指定kind类型的
		//findFloatConfig();//查找float类型错误的并修改
		//findDictionaryQueryXXXXX();//简表字典配置为X的
		findRecordField();//查找上传公安部必录字段缺少的简表
	}
}
