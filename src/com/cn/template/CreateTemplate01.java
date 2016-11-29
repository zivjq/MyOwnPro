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
		//��ȡģ���ļ�·��
		String path=System.getProperty("user.dir")+"/bin/student.xml";
		//System.out.println(path);
		//E:\workspace\eclipse\eclipse-jee-indigo-win32-20110914-dzzw\workspace\20160911ExcelOperation
		File file=new File(path);
		//�����ļ�
		SAXBuilder builder=new SAXBuilder();
		try {
			Document  document =builder.build(file);
			//����������
			HSSFWorkbook workbook=new HSSFWorkbook();
			//����������
			HSSFSheet sheet=workbook.createSheet("��һ��sheet");
			//��ȡxml�ļ����ڵ�
			Element root=document.getRootElement();
			//��ȡģ������
			String templateName=root.getAttributeValue("name");
			
			int rownum=0;
			int colnum=0;
			Element colgroup= root.getChild("colgroup");
			//�����п�
			setColumnWidth(sheet,colgroup);
			//���ñ���
			rownum=setTitleName(sheet,root,workbook,colnum,rownum);
			//���ñ�ͷ
			rownum=setTableTitle(sheet,root,workbook,colnum,rownum);
			//���������������ʽ
			rownum=setDataAreaStyle(sheet,root,workbook,colnum,rownum);
			//����excel����ģ��
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
	 * ��������������ʽ
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
		int repeat=repeatAttr.getIntValue();//��ʼ��ʱ���ж���
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
			String formatValue=StringUtils.isNotBlank(td.getAttributeValue("format"))?td.getAttributeValue("format"):"#��##0.00";
			cellstyle.setDataFormat(dataFormat.getFormat(formatValue));
		}else if("STRING".equalsIgnoreCase(type)){
			cell.setCellValue("");
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellstyle.setDataFormat(dataFormat.getFormat("@"));
		}else if("ENUM".equalsIgnoreCase(type)){
			CellRangeAddressList regions=new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex(),
						cell.getColumnIndex(), cell.getColumnIndex());
			//��ȡö�ٵ�ֵ
			Attribute enumAttr=td.getAttribute("format");
			String enumValue=enumAttr.getValue();
			//���������б��ֵ
			DVConstraint constraint=DVConstraint.createExplicitListConstraint(enumValue.split(","));
			//����������Ч��֤��
			HSSFDataValidation dataValidation =new HSSFDataValidation(regions, constraint);
			workbook.getSheetAt(0).addValidationData(dataValidation);
			
		}else if("DATE".equalsIgnoreCase(type)){
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellstyle.setDataFormat(dataFormat.getFormat("yyyy-mm-dd"));
		}
		
		cell.setCellStyle(cellstyle);
	}

	/**
	 * ���ñ�ͷ��Ϣ
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
	 * ���ñ���
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
			//���õ�Ԫ����ʽ
			HSSFCellStyle cellStyle=workbook.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//����
			
			//��������
			HSSFFont font =workbook.createFont();
			font.setFontName("����_GB2312");
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			//font.setFontHeight((short) 12);
			cellStyle.setFont(font); 
			
			for(column=0;column<tds.size();column++){
				Element td=tds.get(column);
				HSSFCell cell=row.createCell(column);
				//����ʽ���ø�cell
				cell.setCellStyle(cellStyle);
				String rowspanString=td.getAttributeValue("rowspan");
				int rowspan=Integer.valueOf(rowspanString)-1;
				String colspanString=td.getAttributeValue("colspan");
				int colspan=Integer.valueOf(colspanString)-1;
				String titleName=td.getAttributeValue("value");
				if(titleName!=null && !"".equals(titleName)){
					cell.setCellValue(titleName);
					//�ϲ���Ԫ��
					sheet.addMergedRegion(new CellRangeAddress(rowspan, rowspan, 0, colspan));
					
				}
			}
		}
		return rownum+1;
	}

	/**
	 * �����п�
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
