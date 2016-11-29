package com.cn.huanan.addFiled;

/**
 * 列出指定目录的全部内容
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;

public class ViewFileNew {
    
	private static ArrayList<String> viewFilePathList=new ArrayList<String>();
	public static final String TABLE_NAME_EXCEL = "E:/workspace/GD/20160817广东刑侦补采条线/db/xz/newtab02/new.xls".replace("/", File.separator);
	
	static void getJbFilePath(String folderPath){
		//String folderPath="E:\\workspace\\GD\\20160817广东刑侦补采条线\\basic\\simplequery\\queryConfigFiles";
		File root = new File(folderPath);
		File[] files=root.listFiles();
		for (File file : files) {
			if(file.isDirectory()){//如果是文件路径，继续遍历
				//getJbFilePath(file.getAbsolutePath());
				//jbFilePathList.add(file.getAbsolutePath());
			}else{
				viewFilePathList.add(file.getAbsolutePath());
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	getJbFilePath("E:\\workspace\\GD\\20160817广东刑侦补采条线\\db\\xz\\06vw");
    	File file = new File(TABLE_NAME_EXCEL);
        listTabNameFromExcel(file);
    }

    public static void listTabNameFromExcel(File file) throws IOException {
        HSSFWorkbook hssfworkbook = null;
        try {
            hssfworkbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFSheet tableSheet = hssfworkbook.getSheetAt(0);
        int rowNum = tableSheet.getLastRowNum();
        for (int k = 0; k <= rowNum; k++) {
            HSSFRow row = tableSheet.getRow(k);
            if (row == null) {
                break;
            }

            HSSFCell cell = row.getCell((short) 0);

            String tableName = SqlFile.getCellValueAsString(cell);
            for(String viewFilePath : viewFilePathList){
            	File viewFile=new File(viewFilePath);
            	if(viewFile.exists()){
            		String yuanshiName=viewFile.getName();
            		int houzui=yuanshiName.indexOf(".");
            		String newName=yuanshiName.substring(0,houzui);
            		if(newName.equalsIgnoreCase("V_"+tableName)){
            			String s="";
            			
            			StringBuffer viewString=new StringBuffer("");
            			BufferedReader in = new BufferedReader(new FileReader(viewFilePath));
            			while(s!=null){
            				s=in.readLine();
            				viewString.append(s+"\n");
            				if(s.indexOf(";")>0){
            					break;
            				}
            			}
            			int fromFlag=0;
            			//在此处添加要加入的字段
            			if(viewString.toString().indexOf("from")>0){
            				fromFlag=viewString.toString().indexOf("from");
            			}else if(viewString.toString().indexOf("FROM")>0){
            				fromFlag=viewString.toString().indexOf("FROM");
            			}else if(viewString.toString().indexOf("From")>0){
            				fromFlag=viewString.toString().indexOf("From");
            			}
            			if(fromFlag!=0){
            				StringBuffer qianbanduanStr=new StringBuffer(viewString.toString().substring(0,fromFlag));
            				String houbanduan=viewString.toString().substring(fromFlag);
            				qianbanduanStr.append(",\n");
            				qianbanduanStr.append("\"test\"\n");
            				System.out.println(qianbanduanStr+houbanduan);
            			}
            		}
            		
            	}
            	
            }
        }

    }
    
}
