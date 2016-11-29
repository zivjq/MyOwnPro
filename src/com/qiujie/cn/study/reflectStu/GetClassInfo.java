package com.qiujie.cn.study.reflectStu;

import java.lang.reflect.Method;

public class GetClassInfo {
	
	public static void getClassMessage(Object obj){
		Class clazz=obj.getClass();
//		Method[] methods=clazz.getMethods();
//		for(Method item : methods){
//			System.out.println("son中所有的方法包括父类继承来的："+item.getName());//只能得到public的
//		}
		Method[] methods=clazz.getDeclaredMethods();
		for(Method item : methods){
			System.out.println(item.getName());//得到自己类中的所有方法，不管权限是什么
		}
	}
	
	public static void main(String[] args){
		Son son=new Son();
		getClassMessage(son);
	}
}


