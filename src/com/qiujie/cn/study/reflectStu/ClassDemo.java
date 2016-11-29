package com.qiujie.cn.study.reflectStu;

public class ClassDemo {

	/**
	 * @param args
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class clazz=void.class;
		System.out.println(clazz);
	}

}

class Student{
//	private String name;
//	private Student(String name){
//		this.name=name;
//	}
	public  void test(){
		System.out.println("I am test");
	}
}
