package com.cn.about.cutString;
import java.io.UnsupportedEncodingException;

/** 
 *  
 * �����⣺                                                                  
��ȡ�ַ���ָ��ָ���ֽ��������ݣ����ָ�����ֽ����ں��ֵ��м䣬���ֲ��ܽ�ȡ���֣�ֻ��ȡǰ������ݡ�             
��"ab��",��ȡ3���ֽڵ��ַ���������÷�iso-8859-1���� ������ռ�ַ�����1���ֽڣ����Դ�ʱֻ�ܽ�ȡ"ab" ��        
��Ŀ��˼��                                                                 
        Ӧ�����ַ����в��������������£����������ʽ��iso-8859-1,��ô��ȡ�ַ������ǽ�ȡ���ֽڸ�ʽ��          
������Ҫ���˵��Ƿ�iso-8859-1�����ʽ����ν�ȡ��                                         
                                                                      
˼·��                                                                   
�����ȡ n���ֽڣ�                                                            
��ȡ�ַ�����n���ַ���n���ַ���GBK������ֽ���һ��>=Ҫ��ȡ�ֽڸ������������˵��ȫ����ĸ                       
������ȣ�˵���������֣���ȡ��n���ַ����ֽ���>��Ҫ��ȡ��n���ֽ�,�ʽ�ȡ�ַ�n-1��                           
�ٴν��бȽϣ�ֱ�����ֽ���n  ��       ��ȡ�ַ������ֽ������,��ô����ȡ���ַ������ǽ����                    
 * @author qq1013985957 
 * 
 */  
public class SubStringByBytes {  
  
    /** 
     * @param args 
     * @throws UnsupportedEncodingException 
     */  
    public static void main(String[] args) throws UnsupportedEncodingException {  
//        String en = "a";  
//        String cn = "��";  
//        System.out.println("Ӣ��_ISO-8859-1��" + en.getBytes("ISO-8859-1").length);  
//        System.out.println("����_ISO-8859-1��" + cn.getBytes("ISO-8859-1").length);  
//        System.out.println("Ӣ��_GBK��" + en.getBytes("GBK").length);  
//        System.out.println("����_GBK��" + cn.getBytes("GBK").length);  
//        System.out.println("Ӣ��_UTF-8��" + en.getBytes("UTF-8").length);  
//        System.out.println("����_UTF-8��" + cn.getBytes("UTF-8").length);  
          
        String str = "abcdef���ǰ�ddd����ô֪�����Ƕ��ٰ�";  
        /* 
         * ���ﻹ�ͽ�ȡ���ֱ�����ֽ����йأ������ȡ9���ֽ� 
         * GBK ��  abc���ǰ� 
         * UTF8��  abc���� 
         */  
        //System.out.println(subStringByBytes(str, 9,"gbk"));  
        System.out.println(subStringByBytes(str, 11,"utf-8"));  
    }  
    /** 
     *  
     * @param str Ҫ��ȡ���ַ��� 
     * @param bytes ��ȡ���ֽ��� 
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    public static String subStringByBytes(String str, int bytes,String charSetName) throws UnsupportedEncodingException {  
        String subAfter = str.substring(0, bytes); 
        int temp = bytes;  
        try {  
            while(bytes < subAfter.getBytes(charSetName).length){  
            	//�����Ǹ�����������ȡ�ģ�Ȼ�������Ǹ����ַ��ڻ�ȡ���ַ������ֽ�����ĳ�������Ϊѭ������ֹ������
                subAfter = subAfter.substring(0,--temp );  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return subAfter;  
    }  
    /** 
     *  
     * @return ��ǰϵͳ�ı����ʽ  
     */  
//    public static String getSystemEncode() {  
//        System.getProperties().list(System.out);// �õ���ǰ��ϵͳ���ԡ����������б����������̨  
//        String encoding = System.getProperty("file.encoding");  
//        System.out.println("Encoding:" + encoding);  
//        return encoding;  
//    }  
} 