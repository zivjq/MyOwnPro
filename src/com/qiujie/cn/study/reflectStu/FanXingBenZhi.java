package com.qiujie.cn.study.reflectStu;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class FanXingBenZhi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList list1=new ArrayList();
		ArrayList<String> list2=new ArrayList<String>();
		list1.add("string");
		list2.add("qiujie");
		//list2.add(10);
		Class list2Class=list2.getClass();
		try {
			Method add=list2Class.getMethod("add",Object.class);
			add.invoke(list2, 10);
			System.out.println(list2.size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
