package com.qiujie.cn.study.reflectStu;

public class Son extends Father{

	private String sonName;
	private String sonAge;
	
	public Son(){}

	public String getSonName() {
		return sonName;
	}

	public void setSonName(String sonName) {
		this.sonName = sonName;
	}

	public String getSonAge() {
		return sonAge;
	}

	public void setSonAge(String sonAge) {
		this.sonAge = sonAge;
	}
	
	public String toString(){
		return "i am son class ......";
	}
	
	public void sonSay(){
		System.out.println("son say");
	}
	
	private void testIsCanGetParivateMethod(){
		
		System.out.println("i am in son is private testIsCanGetParivateMethod");
	}
}
