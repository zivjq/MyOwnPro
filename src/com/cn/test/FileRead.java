package com.cn.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileRead {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath="E:/workspace/GD/20160817广东刑侦补采条线/basic/xzbc/aj/jsp/more_menu.jsp";
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			while(in.readLine()!=null){
				String str=in.readLine();
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
