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
		String title[]={"ѧ��","����","�Ա�","�ɼ�"};
		try {
			//����һ��������
			WritableWorkbook workbook=Workbook.createWorkbook(file);
			//����һ��sheet
			WritableSheet sheet=workbook.createSheet("sheet1", 0);
			//
			Label label=null;
			//��������
			for (int i = 0; i < title.length; i++) {
				label=new Label(i, 0, title[i]);
				sheet.addCell(label);
			}
			//������
			for(int i=0;i<5;i++){
				label=new Label(0,i+1,"ѧ�ţ�"+i);
				sheet.addCell(label);
				label=new Label(1,i+1,"������"+i);
				sheet.addCell(label);
				label=new Label(2,i+1,"�Ա�"+i);
				sheet.addCell(label);
				label=new Label(3,i+1,"�ɼ���"+i);
				sheet.addCell(label);
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
