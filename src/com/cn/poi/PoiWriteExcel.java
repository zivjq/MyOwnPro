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
	 * ʹ��POI����excel�ļ�
	 * @param args
	 */
	public static void main(String[] args) {
		
		//����������
		HSSFWorkbook workbook=new HSSFWorkbook();
		//����sheet
		HSSFSheet sheet=workbook.createSheet();
		//��������row
		HSSFRow titleRow=sheet.createRow(0);
		//��������ֵ
		String[] title={"id","name","sex"};
		HSSFCell cell=null;
		for(int i=0;i<title.length;i++){
			//����ĳ����Ԫ�����ɾ��������������
			cell=titleRow.createCell(i);
			cell.setCellValue(title[i]);
		}
		//�������
		for(int i=1;i<10;i++){
			HSSFRow contentRow=sheet.createRow(i);
			cell=contentRow.createCell(0);
			cell.setCellValue("id"+i);
			cell=contentRow.createCell(1);
			cell.setCellValue("name"+i);
			cell=contentRow.createCell(2);
			cell.setCellValue("sex"+i);
		}
		//���˹�����д���ļ���
		File file=new File("E:/Study/001Excel/002poi/002дExcel.xls");
		
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
