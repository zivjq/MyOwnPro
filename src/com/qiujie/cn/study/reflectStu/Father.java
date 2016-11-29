package com.qiujie.cn.study.reflectStu;

public class Father {

	private String fatherName;
	private String fatherAge;

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherAge() {
		return fatherAge;
	}

	public void setFatherAge(String fatherAge) {
		this.fatherAge = fatherAge;
	}
	
	public void getMoney(){
		System.out.println("人生在世，开心最重要......");
	}
	
	public void fatherSay(){
		System.out.println("Father say......");
	}
}
