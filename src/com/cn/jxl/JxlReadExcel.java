package com.cn.jxl;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class JxlReadExcel {

	/**
	 * JXL解析 excel
	 * @param args
	 */
	public static void main(String[] args) {
		
		File file = new File("E:/Study/001Excel/001jxl/001exltest.xls");
		try {
			//获取到workbook
			Workbook workbook =Workbook.getWorkbook(file);
			//获取sheet
			Sheet sheet=workbook.getSheet(0);
			//循环获取sheet中的数据
			for(int r=0;r<sheet.getRows();r++){
				for(int c=0;c<sheet.getColumns();c++){
					Cell cell=sheet.getCell(c,r);
					String contents=cell.getContents();
					System.out.print(contents+"\t");
				}
				System.out.println();
			}
			//使用完毕之后关闭workbook
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
