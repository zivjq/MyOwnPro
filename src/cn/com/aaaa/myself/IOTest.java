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
			//FileUtils.copyFile(oldFile,newFile);//�����ļ�
			//FileUtils.copyFileToDirectory(oldFile, tarDir);//�����ļ���ָ��·���£�û������ļ����򴴽�
			//FileUtils.copyDirectory(srcDir, destDir);//�����ļ��е�ָ��·���£�û������ļ����򴴽�
			//FileUtils.cleanDirectory(destDir);//ɾ���ļ������е�����
			
			//��ȡ�ļ�
			InputStream input=new FileInputStream(new File("E:/workspace/GD/20160817�㶫���첹������/src/cn/sinobest/jzpt/xzbc/aj/domain/V_ST_ASJ.java"));
			List<String> IOUtilsReadList=IOUtils.readLines(input,"UTF-8");//IOUtils ���ж�ȡ�ļ�����
//			
//			List<String> FileUtilsReadList=FileUtils.readLines(new File("E:/workspace/GD/20160817�㶫���첹������/src/cn/sinobest/jzpt/xzbc/aj/domain/V_ST_ASJ.java"));
			//FileUtils ���ж�ȡ�ļ�����
//			for(String str : FileUtilsReadList){
//				System.out.println(str);
//			}
			
			//д���ļ�
			// by FileUtils    
			//param1 Ҫд���ļ���·��,û���򴴽�
			//param2 �ַ�������
			//parma3 Ҫд�������
			//FileUtils.writeLines(new File("E:/text.java"), "UTF-8", FileUtilsReadList);    
			    
			// by IOUtils 
			//param1 Ҫд���ļ���·��,û���򴴽�
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
