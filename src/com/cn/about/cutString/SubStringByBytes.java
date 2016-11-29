package com.cn.about.cutString;
import java.io.UnsupportedEncodingException;

/** 
 *  
 * 面试题：                                                                  
截取字符串指定指定字节数的内容，如果指定的字节数在汉字的中间，汉字不能截取部分，只截取前面的内容。             
如"ab我",截取3个字节的字符，如果采用非iso-8859-1编码 汉字所占字符超过1个字节，所以此时只能截取"ab" 。        
题目意思：                                                                 
        应该是字符串中不存在乱码的情况下，如果编码形式是iso-8859-1,那么截取字符串就是截取的字节格式，          
所以主要考核的是非iso-8859-1编码格式是如何截取。                                         
                                                                      
思路：                                                                   
假设截取 n个字节，                                                            
截取字符串的n个字符，n个字符的GBK编码的字节数一定>=要截取字节个数，如果等于说明全是字母                       
如果不等，说明包含汉字，截取的n个字符的字节数>需要截取的n个字节,故截取字符n-1，                           
再次进行比较，直到，字节数n  和       截取字符串的字节数相等,那么所截取的字符串就是结果。                    
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
//        String cn = "我";  
//        System.out.println("英文_ISO-8859-1：" + en.getBytes("ISO-8859-1").length);  
//        System.out.println("汉字_ISO-8859-1：" + cn.getBytes("ISO-8859-1").length);  
//        System.out.println("英文_GBK：" + en.getBytes("GBK").length);  
//        System.out.println("汉字_GBK：" + cn.getBytes("GBK").length);  
//        System.out.println("英文_UTF-8：" + en.getBytes("UTF-8").length);  
//        System.out.println("汉字_UTF-8：" + cn.getBytes("UTF-8").length);  
          
        String str = "abcdef我们啊ddd我怎么知道你是多少啊";  
        /* 
         * 这里还和截取何种编码的字节数有关，如果截取9个字节 
         * GBK 是  abc我们啊 
         * UTF8是  abc我们 
         */  
        //System.out.println(subStringByBytes(str, 9,"gbk"));  
        System.out.println(subStringByBytes(str, 11,"utf-8"));  
    }  
    /** 
     *  
     * @param str 要截取的字符串 
     * @param bytes 截取的字节数 
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    public static String subStringByBytes(String str, int bytes,String charSetName) throws UnsupportedEncodingException {  
        String subAfter = str.substring(0, bytes); 
        int temp = bytes;  
        try {  
            while(bytes < subAfter.getBytes(charSetName).length){  
            	//这里是根据字数来截取的，然后上面是根据字符在获取该字符串的字节数组的长度来作为循环的终止条件的
                subAfter = subAfter.substring(0,--temp );  
            }  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        return subAfter;  
    }  
    /** 
     *  
     * @return 当前系统的编码格式  
     */  
//    public static String getSystemEncode() {  
//        System.getProperties().list(System.out);// 得到当前的系统属性。并将属性列表输出到控制台  
//        String encoding = System.getProperty("file.encoding");  
//        System.out.println("Encoding:" + encoding);  
//        return encoding;  
//    }  
} 