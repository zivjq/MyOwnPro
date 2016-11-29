package com.cn.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ldfwCheck
{
  //public static String childListName = "childList.txt";
  public static String logName = "log.txt";
  public static String excelName="ldfwcheck.xls";
  
  public static List<String> childList = new ArrayList();

//  public static void initChildList(InputStream is) {
//    BufferedReader br = null;
//    try {
//      br = new BufferedReader(new InputStreamReader(is));
//      String data = null;
//      while ((data = br.readLine()) != null)
//        childList.add(data);
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//      try
//      {
//        if (br != null) br.close(); 
//      }
//      catch (IOException e1)
//      {
//        e1.printStackTrace();
//      }
//    }
//    finally
//    {
//      try
//      {
//        if (br != null) br.close(); 
//      }
//      catch (IOException e)
//      {
//        e.printStackTrace();
//      }
//    }
//  }

  public static void check(InputStream is) {
    try { BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      StringBuilder log = new StringBuilder();
      String line = null;
      while ((line = reader.readLine()) != null) {
        log.append(line + "\n");
      }

      for (String c : childList) {
        if (findStr(c, log.toString()))
          System.out.println(c + "--->ok");
        else
          System.out.println(c + "--->bad");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      try
      {
        if (is != null) is.close(); 
      }
      catch (IOException e2)
      {
        e2.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (is != null) is.close(); 
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  private static boolean findStr(String c, String log) {
    if (log.indexOf(c + "成功") != -1) return true;

    return false;
  }
  public static void getTableNames(String ldfwmc){
		String filePath="E:/ldfwcheck.xls";
		File file=new File(filePath);
		try {
			HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			HSSFSheet sheet=workbook.getSheet(ldfwmc);
			int lastRowNum=sheet.getLastRowNum();
			for(int i=0;i<=lastRowNum;i++){
				HSSFRow row=sheet.getRow(i);
				int lastCellNum=row.getLastCellNum();
				for(int j=0;j<lastCellNum;j++){
					HSSFCell cell=row.getCell(j);
					if(cell!=null){
						int cellType=cell.getCellType();
						String content=null;
						Double numericContent=0D;
						if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
							numericContent=cell.getNumericCellValue();
							content=numericContent.toString();
						}else{
							content=cell.getStringCellValue()==null?"":cell.getStringCellValue();
						}
						childList.add(content);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
  public static void main(String[] args)
  {
    try {
      JarFile jar = new JarFile("check.jar");
      //JarEntry childList = jar.getJarEntry(childListName);
      //InputStream clIs = jar.getInputStream(childList);
      //initChildList(clIs);
      System.out.println("请输入联动服务名称：");
      Scanner scan = new Scanner(System.in);
      String ldfwmc = scan.nextLine();
      //检查联动服务名称是否正确
      getTableNames(ldfwmc);
      JarEntry log = jar.getJarEntry(logName);
      InputStream lIs = jar.getInputStream(log);

      check(lIs);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}