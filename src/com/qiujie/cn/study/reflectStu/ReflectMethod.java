package com.qiujie.cn.study.reflectStu;

import java.lang.reflect.Method;


public class ReflectMethod {
	
	public static void main(String[] args){
		Test test=new Test();
		Class c=test.getClass();
		try {
			Method noParam=c.getMethod("say");
			noParam.invoke(test);
			System.out.println("------------------------------------");
			Method intParm=c.getMethod("say", int.class,int.class);
			intParm.invoke(test, 10,20);
			System.out.println("--------------------------------------");
			Method stringInt=c.getMethod("say", String.class,String.class,int.class);
			stringInt.invoke(test, "qiu","jie",666);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

class Test{
	
	public String say(){
		System.out.println("我是一个没有参数的say");
		return "我是一个没有参数的say";
	}
	
	public void say(int a,int b){
		System.out.println("a+b="+(a+b));
	}
	
	public void say(String a,String b,int c){
		System.out.println(a+b+c);
	}
}