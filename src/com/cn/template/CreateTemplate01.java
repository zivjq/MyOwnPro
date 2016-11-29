package com.cn.template;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class CreateTemplate01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//获取模板文件路径
		String path=System.getProperty("user.dir")+"/bin/student.xml";
		//System.out.println(path);
		//E:\workspace\eclipse\eclipse-jee-indigo-win32-20110914-dzzw\workspace\20160911ExcelOperation
		File file=new File(path);
		//解析文件
		SAXBuilder builder=new SAXBuilder();
		try {
			Document  document =builder.build(file);
			//创建工作簿
			HSSFWorkbook workbook=new HSSFWorkbook();
			//创建工作表
			HSSFSheet sheet=workbook.createSheet("第一个sheet");
			//获取xml文件根节点
			Element root=document.getRootElement();
			//获取模板名称
			String templateName=root.getAttributeValue("name");
			
			int rownum=0;
			int colnum=0;
			Element colgroup= root.getChild("colgroup");
			//设置列宽
			setColumnWidth(sheet,colgroup);
			//设置标题
			rownum=setTitleName(sheet,root,workbook,colnum,rownum);
			//设置表头
			rownum=setTableTitle(sheet,root,workbook,colnum,rownum);
			//设置数据区域的样式
			rownum=setDataAreaStyle(sheet,root,workbook,colnum,rownum);
			//生成excel导入模板
			File templateFile=new File("E:/qiujie.xls");
			templateFile.delete();
			templateFile.createNewFile();
			
			FileOutputStream stream=FileUtils.openOutputStream(templateFile);
			workbook.write(stream);
			stream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 设置数据区域样式
	 * @param sheet
	 * @param root
	 * @param workbook
	 * @param colnum
	 * @param rownum
	 * @return
	 * @throws DataConversionException 
	 */
	private static int setDataAreaStyle(HSSFSheet sheet, Element root,
			HSSFWorkbook workbook, int colnum, int rownum) throws DataConversionException {
		
		Element tr=root.getChild("tbody").getChild("tr");
		Attribute repeatAttr=tr.getAttribute("repeat");
		int repeat=repeatAttr.getIntValue();//初始化时候有多少
		System.out.println(repeat);
		List<Element> tds=tr.getChildren("td");
		for(int i=0;i<repeat;i++){
			HSSFRow row=sheet.createRow(rownum);
			++rownum;
			for(int j=0;j<tds.size();j++){
				System.out.println("here"+j);
				Element td=tds.get(j);
				HSSFCell cell=row.createCell(j);
				setType(workbook,cell,td);
			}
		}
		
		return rownum+1;
	}

	private static void setType(HSSFWorkbook workbook, HSSFCell cell, Element td) {
		
		Attribute typeAttr=td.getAttribute("type");
		String type=typeAttr.getValue();
		HSSFDataFormat dataFormat=workbook.createDataFormat();
		HSSFCellStyle cellstyle=workbook.createCellStyle();
		if("NUMERIC".equalsIgnoreCase(type)){
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			String formatValue=StringUtils.isNotBlank(td.getAttributeValue("format"))?td.getAttributeValue("format"):"#，##0.00";
			cellstyle.setDataFormat(dataFormat.getFormat(formatValue));
		}else if("STRING".equalsIgnoreCase(type)){
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellstyle.setDataFormat(dataFormat.getFormat("@"));
		}else if("ENUM".equalsIgnoreCase(type)){
			CellRangeAddressList regions=new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(),
						cell.getColumnIndex(), cell.getColumnIndex());
			//获取枚举的值
			Attribute enumAttr=td.getAttribute("format");
			String enumValue=enumAttr.getValue();
			//加载下拉列表的值
			DVConstraint constraint=DVConstraint.createExplicitListConstraint(enumValue.split(","));
			//加入数据有效验证性
			HSSFDataValidation dataValidation =new HSSFDataValidation(regions, constraint);
			workbook.getSheetAt(0).addValidationData(dataValidation);
			
		}else if("DATE".equalsIgnoreCase(type)){
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellstyle.setDataFormat(dataFormat.getFormat("yyyy-mm-dd"));
		}
		
		cell.setCellStyle(cellstyle);
	}

	/**
	 * 设置表头信息
	 * @param sheet
	 * @param root
	 * @param workbook
	 * @param colnum
	 * @param rownum
	 * @return
	 */
	private static int setTableTitle(HSSFSheet sheet, Element root,
			HSSFWorkbook workbook, int colnum, int rownum) {
		Element tr=root.getChild("thead").getChild("tr");
		HSSFRow row=sheet.createRow(rownum);
		List<Element> ths=tr.getChildren("th");
		for(int i=0;i<ths.size();i++){
			Element th=ths.get(i);//Attribute
			Attribute attributeValue=th.getAttribute("value");
			HSSFCell cell=row.createCell(i);
			if(attributeValue!=null){
				String value=attributeValue.getValue();
				cell.setCellValue(value);
			}
		}
		
		return rownum+1;
	}

	/**
	 * 设置标题
	 * @param sheet
	 * @param root
	 */
	private static  int setTitleName(HSSFSheet sheet, Element root ,HSSFWorkbook workbook,int column,int rownum) {
		
		Element title=root.getChild("title");
		List<Element> trs=title.getChildren("tr");
		for(int i=0;i<trs.size();i++){
			Element tr=trs.get(i);
			List<Element> tds=tr.getChildren("td");
			HSSFRow row=sheet.createRow(rownum);
			//设置单元格样式
			HSSFCellStyle cellStyle=workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			
			//设置字体
			HSSFFont font =workbook.createFont();
			font.setFontName("仿宋_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//font.setFontHeight((short) 12);
			cellStyle.setFont(font); 
			
			for(column=0;column<tds.size();column++){
				Element td=tds.get(column);
				HSSFCell cell=row.createCell(column);
				//将样式设置给cell
				cell.setCellStyle(cellStyle);
				String rowspanString=td.getAttributeValue("rowspan");
				int rowspan=Integer.valueOf(rowspanString)-1;
				String colspanString=td.getAttributeValue("colspan");
				int colspan=Integer.valueOf(colspanString)-1;
				String titleName=td.getAttributeValue("value");
				if(titleName!=null && !"".equals(titleName)){
					cell.setCellValue(titleName);
					//合并单元格
					sheet.addMergedRegion(new CellRangeAddress(rowspan, rowspan, 0, colspan));
					
				}
			}
		}
		return rownum+1;
	}

	/**
	 * 设置列宽
	 * @param sheet
	 * @param colgroup
	 */
	private static void setColumnWidth(HSSFSheet sheet, Element colgroup) {
		
		List<Element> cols=colgroup.getChildren();
		for(int i=0;i<cols.size();i++){
			Attribute width=cols.get(i).getAttribute("width");
			String unit=width.getValue().replaceAll("[0-9,\\.]", "");
			String value=width.getValue().replaceAll(unit, "");
			int v=0;
			if(StringUtils.isBlank(unit) || "px".endsWith(unit)){
				v=Math.round(Float.parseFloat(value)*37F);
			}else if("em".endsWith(unit)){
				v=Math.round(Float.parseFloat(value)*267.5F);	
			}
			sheet.setColumnWidth(i, v);
		}
	}

}
