package com.cn.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PoiWriteExcel {

	/**
	 * 使用POI生成excel文件
	 * @param args
	 */
	public static void main(String[] args) {
		
		//创建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//创建sheet
		HSSFSheet sheet=workbook.createSheet();
		//创建列名row
		HSSFRow titleRow=sheet.createRow(0);
		//给列名赋值
		String[] title={"id","name","sex"};
		HSSFCell cell=null;
		for(int i=0;i<title.length;i++){
			//具体某个单元格是由具体的行来创建的
			cell=titleRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		//添加数据
		for(int i=1;i<10;i++){
			HSSFRow contentRow=sheet.createRow(i);
			cell=contentRow.createCell(0);
			cell.setCellValue("id"+i);
			cell=contentRow.createCell(1);
			cell.setCellValue("name"+i);
			cell=contentRow.createCell(2);
			cell.setCellValue("sex"+i);
		}
		//将此工作薄写入文件中
		File file=new File("E:/Study/001Excel/002poi/002写Excel.xls");
		
		try {
			if(file.exists()){
				file.delete();
			}else{
				file.createNewFile();
			}
			
			FileOutputStream stream=FileUtils.openOutputStream(file);
			workbook.write(stream);
			workbook.close();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
