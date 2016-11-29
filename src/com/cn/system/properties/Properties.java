package com.cn.system.properties;

public class Properties {

	public static void main(String[] args) {
		
		String userDir=System.getProperty("user.dir");
		//user.dir
		//E:\workspace\eclipse\eclipse-jee-indigo-win32-20110914-dzzw\workspace\20160911ExcelOperation
		String javaVersion=System.getProperty("java.version");
		//1.6.0_24
		String javaVendor=System.getProperty("java.vendor");
		//运行时环境供应商 Sun Microsystems Inc.
		String javaVendorUrl=System.getProperty("java.vendor.url");
		//http://java.sun.com/
		String javaHome=System.getProperty("java.home");
		//E:\workspace\eclipse\eclipse-jee-indigo-win32-20110914-dzzw\jdk160_24\jre
		String javaVmSpecificationVersion=System.getProperty("java.vm.specification.version");
		//1.0
		String userHome=System.getProperty("user.home");
		//用户的主目录 C:\Users\qiujie
		String userName=System.getProperty("user.name");
		//用户的账户名称 qiujie 
		System.out.println(userName);

	}

}
