package cn.com.aaaa.myself;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class IOTest {

	public static void testIdFun(){
		
		File oldFile=new File("E:/check.jar");
		File newFile=new File("E:/checkNew.jar");
		File tarDir = new File("E:/2016/"); 
		File srcDir=new File("E:/2016");
		File destDir=new File("E:/2016_bak");
		try {
			//FileUtils.copyFile(oldFile,newFile);//复制文件
			//FileUtils.copyFileToDirectory(oldFile, tarDir);//复制文件到指定路径下，没有这个文件夹则创建
			//FileUtils.copyDirectory(srcDir, destDir);//复制文件夹到指定路径下，没有这个文件夹则创建
			//FileUtils.cleanDirectory(destDir);//删除文件夹下中的内容
			
			//读取文件
			InputStream input=new FileInputStream(new File("E:/workspace/GD/20160817广东刑侦补采条线/src/cn/sinobest/jzpt/xzbc/aj/domain/V_ST_ASJ.java"));
			List<String> IOUtilsReadList=IOUtils.readLines(input,"UTF-8");//IOUtils 逐行读取文件内容
//			
//			List<String> FileUtilsReadList=FileUtils.readLines(new File("E:/workspace/GD/20160817广东刑侦补采条线/src/cn/sinobest/jzpt/xzbc/aj/domain/V_ST_ASJ.java"));
			//FileUtils 逐行读取文件内容
//			for(String str : FileUtilsReadList){
//				System.out.println(str);
//			}
			
			//写入文件
			// by FileUtils    
			//param1 要写入文件的路径,没有则创建
			//param2 字符集编码
			//parma3 要写入的内容
			//FileUtils.writeLines(new File("E:/text.java"), "UTF-8", FileUtilsReadList);    
			    
			// by IOUtils 
			//param1 要写入文件的路径,没有则创建
			//param2 lineEnding
			//param3 output
			IOUtils.writeLines(IOUtilsReadList, null, new FileOutputStream(new File("E:/text1.java"))); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("finished......");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testIdFun();
	}

}
