package com.cn.huanan.company;

/**
 * 列出指定目录的全部内容
 * */
import java.io.File;

public class FileList {
    private static final String fileDir = "E:/asjdev/gdzfba/广东刑侦补采条线/db/xz/newtab02/03tab_alter_2016".replace("/", File.separator);
    
    public static void main(String[] args) {
        File f = new File(fileDir);
        listFile(f);
    }
    
    /**
     * 根据表名查找对应的bean文件，修改bean文件
     * 
     * @param f
     */
    public static void listFile(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listFile(fileArray[i]);
                    }
                }
            } else {
                System.out.println("@" + f.getAbsolutePath());
            }
        }
    }
}
