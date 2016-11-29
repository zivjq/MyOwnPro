package com.cn.huanan.company;

/**
 * �г�ָ��Ŀ¼��ȫ������
 * */
import java.io.File;

public class FileList {
    private static final String fileDir = "E:/asjdev/gdzfba/�㶫���첹������/db/xz/newtab02/03tab_alter_2016".replace("/", File.separator);
    
    public static void main(String[] args) {
        File f = new File(fileDir);
        listFile(f);
    }
    
    /**
     * ���ݱ������Ҷ�Ӧ��bean�ļ����޸�bean�ļ�
     * 
     * @param f
     */
    public static void listFile(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // �ݹ����
                        listFile(fileArray[i]);
                    }
                }
            } else {
                System.out.println("@" + f.getAbsolutePath());
            }
        }
    }
}
