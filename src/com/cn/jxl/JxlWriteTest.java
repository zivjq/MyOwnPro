package com.cn.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class JxlWriteTest {
	
	public static void main(String[] args){
		
		File file = new File("E:/Study/001Excel/001jxl/001exltest.xls");
		if(file.exists()){
			file.delete();
		}
		String title[]={"学号","姓名","性别","成绩"};
		try {
			//创建一个工作簿
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//创建一个sheet
			WritableSheet sheet=workbook.createSheet("sheet1", 0);
			//
			Label label=null;
			//设置列名
			for (int i = 0; i < title.length; i++) {
				label=new Label(i, 0, title[i]);
				sheet.addCell(label);
			}
			//加数据
			for(int i=0;i<5;i++){
				label=new Label(0,i+1,"学号："+i);
				sheet.addCell(label);
				label=new Label(1,i+1,"姓名："+i);
				sheet.addCell(label);
				label=new Label(2,i+1,"性别："+i);
				sheet.addCell(label);
				label=new Label(3,i+1,"成绩："+i);
				sheet.addCell(label);
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
