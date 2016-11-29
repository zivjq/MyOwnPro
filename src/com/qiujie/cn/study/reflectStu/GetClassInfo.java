package com.qiujie.cn.study.reflectStu;

import java.lang.reflect.Method;

public class GetClassInfo {
	
	public static void getClassMessage(Object obj){
		Class clazz=obj.getClass();
//		Method[] methods=clazz.getMethods();
//		for(Method item : methods){
//			System.out.println("son�����еķ�����������̳����ģ�"+item.getName());//ֻ�ܵõ�public��
//		}
		Method[] methods=clazz.getDeclaredMethods();
		for(Method item : methods){
			System.out.println(item.getName());//�õ��Լ����е����з���������Ȩ����ʲô
		}
	}
	
	public static void main(String[] args){
		Son son=new Son();
		getClassMessage(son);
	}
}


