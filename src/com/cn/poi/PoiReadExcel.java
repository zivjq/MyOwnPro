package com.cn.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class PoiReadExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String filePath="E:/Study/001Excel/002poi/便衣大队信息采集表（33人） - 副本.xls";
		
		File file=new File(filePath);
		
		try {
			HSSFWorkbook workbook=new HSSFWorkbook(FileUtils.openInputStream(file));
			HSSFSheet sheet=workbook.getSheetAt(0);
			int firstRowNum=sheet.getFirstRowNum();
			int lastRowNum=sheet.getLastRowNum();
			//System.out.println(lastRowNum);
			for(int i=0;i<=lastRowNum;i++){
				HSSFRow row=sheet.getRow(i);
				int lastCellNum=row.getLastCellNum();
				for(int j=0;j<lastCellNum;j++){
					HSSFCell cell=row.getCell(j);
					if(cell!=null){
						int cellType=cell.getCellType();
						String content=null;
						Double numericContent=0D;
						//HSSFCell.
						if(cellType==HSSFCell.CELL_TYPE_NUMERIC){
							numericContent=cell.getNumericCellValue();
							content=numericContent.toString();
						}else{
							content=cell.getStringCellValue()==null?"":cell.getStringCellValue();
						}
						System.out.print(content+"\t");
					}
					
					//String content=cell.getStringCellValue()==null?"":cell.getStringCellValue();
					
				}
				System.out.println();
			}
			//workbook.close();
			
			//String excelPath = "D:\\Excel\\test.xls";
			  String savePicturePath = "D:\\images\\";
			  PoiReadExcel readExcelPicture = new PoiReadExcel();
			  Map<Integer,HSSFPictureData> map = readExcelPicture.readPicture(filePath);
			  //传入一个你需要的Excel图片行索引，必须确保该Excel行索引中有图片
			  //而且是在已经读取的Excel行索引范围内
			  //System.out.println(map.size());
			  HSSFPictureData pictureData=null;
			  String ext =null;
			  byte[] data =null;
			  for(int i=0;i<map.size();i++){
				  pictureData= map.get(i);
				//获取包含图片格式的文件字符串
				  ext= pictureData.suggestFileExtension();
				  //代表图片信息的字节数据
				  data= pictureData.getData();
				  //根据图片格式将图片写出到磁盘
				  if (ext.equals("jpeg")) {
				   FileOutputStream out = new FileOutputStream(savePicturePath+"jpeg格式"+i+".jpg");
				   out.write(data);
				   out.close();
				  }
				  if (ext.equals("png")) {
				   FileOutputStream out = new FileOutputStream(savePicturePath+"png格式"+i+".jpg");
				   out.write(data);
				   out.close();
				  }
			  }
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Map readPicture(String excelPath) throws InvalidFormatException,
			IOException {

		FileInputStream fis = new FileInputStream(excelPath);
		HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(fis);
		List<HSSFPictureData> pictures = workbook.getAllPictures();
		// 读取制定sheet工作表的名字的工作表
		//HSSFSheet sheet = workbook.getSheet("Sheet1");
		//或者使用getSheetAt(0) 来读取第一张工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		Map<Integer, HSSFPictureData> map = new HashMap<Integer, HSSFPictureData>();
		HSSFPicture picture = null;
		HSSFPictureData pictureData = null;
		List<HSSFShape> hssfshapeList = sheet.getDrawingPatriarch()
				.getChildren();
		int pictureIndex=0;
		for (HSSFShape shape : hssfshapeList) {
			HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
			if (shape instanceof HSSFPicture) {
				// int rowmark = rowIndex;
				picture = (HSSFPicture) shape;
				System.out.println("here:"+picture.getPictureIndex());
				pictureIndex = picture.getPictureIndex() - 1;
				pictureData = pictures.get(pictureIndex);
				map.put(pictureIndex, pictureData);
			}
		}
		return map;
	}
}
