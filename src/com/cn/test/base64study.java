package com.cn.test;

import org.apache.xmlbeans.impl.util.Base64;


public class base64study {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testStr="qiujie";
		byte[] resultByte=Base64.encode(testStr.getBytes());
		System.out.println(new String(resultByte));
	}

}
