package com.cn.about.beansToXml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class BeanToXml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		People people=new People();
		people.setId("001");
		people.setName("第一个人");
		people.setAge(25);
		
		Son son=new Son();
		son.setSonId("sonId001");
		son.setSonName("sonName");
		son.setSonAge(1);
		
		people.setSon(son);
		
		XStream xs=new XStream(new DomDriver());
		xs.alias("people", People.class);
		xs.aliasField("人名", People.class, "name");
		System.out.println(xs.toXML(people));

	}

}
