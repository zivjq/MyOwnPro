package com.cn.huanan.addFiled;

/**
 * 列出指定目录的全部内容
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;

public class JavaFile {
	
    private static final String javaFieldsCodeStr;
    private static final String javaFieldsGetSetCodeStr;
    
    private static final String[] javaFileDirArr = {"E:/workspace/GD/20160817广东刑侦补采条线/framework_ga".replace("/", File.separator), 
                                                     "E:/workspace/GD/20160817广东刑侦补采条线/framework_src".replace("/", File.separator), 
                                                     "E:/workspace/GD/20160817广东刑侦补采条线/src".replace("/", File.separator)};
    static {
        StringBuffer javaFieldsCode = new StringBuffer(500);
        javaFieldsCode.append("\t/** 是否下发数据判断标识:1下发数据;0警综系统产生的数据 */ \n");
        javaFieldsCode.append("\tpublic java.lang.String sFXFSJ_PDBZ; \n");
//        javaFieldsCode.append("\t/** 信息登记单位_公安机关机构代码/采用GA 380《全国公安机关机构代码编码规则》统一编制的代码/CODE_GXS */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXDJDW_GAJGJGDM; \n");
//        javaFieldsCode.append("\t/** 信息登记单位_公安机关名称 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXDJDW_GAJGMC; \n");
//        javaFieldsCode.append("\t/** 信息登记人员_姓名 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXDJRY_XM; \n");
//        javaFieldsCode.append("\t/** 信息登记人员_公民身份号码/符合GB 11643《公民身份号码》 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXDJRY_GMSFHM; \n");
//        javaFieldsCode.append("\t/** 信息登记人员_联系电话 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXDJRY_LXDH; \n");
//        javaFieldsCode.append("\t/** 登记时间 */ \n");
//        javaFieldsCode.append("\tpublic java.sql.Timestamp dJSJ; \n");
//        javaFieldsCode.append("\t/** 信息操作单位_公安机关机构代码/采用GA 380《全国公安机关机构代码编码规则》统一编制的代码 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXCZDW_GAJGJGDM; \n");
//        javaFieldsCode.append("\t/** 信息操作单位_公安机关名称 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXCZDW_GAJGMC; \n");
//        javaFieldsCode.append("\t/** 信息操作人员_姓名 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXCZRY_XM; \n");
//        javaFieldsCode.append("\t/** 信息操作人员_公民身份号码/符合GB 11643《公民身份号码》 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXCZRY_GMSFHM; \n");
//        javaFieldsCode.append("\t/** 更新时间 */ \n");
//        javaFieldsCode.append("\tpublic java.sql.Timestamp gXSJ; \n");
//        javaFieldsCode.append("\t/** 信息来源描述 */ \n");
//        javaFieldsCode.append("\tpublic java.lang.String xXLYMS; \n");

        javaFieldsCodeStr = javaFieldsCode.toString();
        
        StringBuffer javaFieldsGetSetCode = new StringBuffer(500);
        javaFieldsGetSetCode.append("\tpublic void setSFXFSJ_PDBZ(java.lang.String value) { \n");
        javaFieldsGetSetCode.append("\t\tthis.sFXFSJ_PDBZ = value; \n");
        javaFieldsGetSetCode.append("\t} \n");
        javaFieldsGetSetCode.append("\t\n");
        javaFieldsGetSetCode.append("\tpublic java.lang.String getSFXFSJ_PDBZ() { \n");
        javaFieldsGetSetCode.append("\t\treturn this.sFXFSJ_PDBZ; \n");
        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXDJDW_GAJGJGDM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXDJDW_GAJGJGDM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXDJDW_GAJGJGDM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXDJDW_GAJGJGDM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXDJDW_GAJGMC(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXDJDW_GAJGMC = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXDJDW_GAJGMC() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXDJDW_GAJGMC; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXDJRY_XM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXDJRY_XM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXDJRY_XM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXDJRY_XM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXDJRY_GMSFHM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXDJRY_GMSFHM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXDJRY_GMSFHM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXDJRY_GMSFHM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXDJRY_LXDH(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXDJRY_LXDH = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXDJRY_LXDH() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXDJRY_LXDH; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setDJSJ(java.sql.Timestamp value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.dJSJ = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.sql.Timestamp getDJSJ() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.dJSJ; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXCZDW_GAJGJGDM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXCZDW_GAJGJGDM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXCZDW_GAJGJGDM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXCZDW_GAJGJGDM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXCZDW_GAJGMC(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXCZDW_GAJGMC = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXCZDW_GAJGMC() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXCZDW_GAJGMC; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXCZRY_XM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXCZRY_XM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXCZRY_XM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXCZRY_XM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXCZRY_GMSFHM(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXCZRY_GMSFHM = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXCZRY_GMSFHM() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXCZRY_GMSFHM; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setGXSJ(java.sql.Timestamp value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.gXSJ = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.sql.Timestamp getGXSJ() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.gXSJ; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\tpublic void setXXLYMS(java.lang.String value) { \n");
//        javaFieldsGetSetCode.append("\t\tthis.xXLYMS = value; \n");
//        javaFieldsGetSetCode.append("\t} \n");
//        javaFieldsGetSetCode.append("\t\n");
//        javaFieldsGetSetCode.append("\tpublic java.lang.String getXXLYMS() { \n");
//        javaFieldsGetSetCode.append("\t\treturn this.xXLYMS; \n");
//        javaFieldsGetSetCode.append("\t} \n");

        javaFieldsGetSetCodeStr = javaFieldsGetSetCode.toString();
    }
    
    public static void main(String[] args) {
        File f = new File(SqlFile.TABLE_NAME_EXCEL);
        listTabNameFromExcel(f);
    }

    public static void listTabNameFromExcel(File file) {
        HSSFWorkbook hssfworkbook = null;
        try {
            hssfworkbook = new HSSFWorkbook(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HSSFSheet tableSheet = hssfworkbook.getSheetAt(0);
        int rowNum = tableSheet.getLastRowNum();
        for (int k = 0; k <= rowNum; k++) {
            HSSFRow row = tableSheet.getRow(k);
            if (row == null) {
                break;
            }

            HSSFCell cell = row.getCell((short) 0);

            String tableName = SqlFile.getCellValueAsString(cell);
            for (String javaFileDir : javaFileDirArr) {
                listJavaFile(new File(javaFileDir), tableName.toUpperCase());
            }
        }

    }

    /**
     * 根据表名查找对应的bean文件，修改bean文件
     * @param f
     */
    public static void updJavaBeanFile(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        updJavaBeanFile(fileArray[i]);
                    }
                }
            } else {
                String tabName = f.getName().replace(".tab", "");
                if (!SqlFile.DEBUG_FLAG || (SqlFile.TAB_NAME).equalsIgnoreCase(tabName)) {
                    System.out.println(tabName);
                    // 遍历JavaBean目录
                    for (String javaFileDir : javaFileDirArr) {
                        listJavaFile(new File(javaFileDir), tabName.toUpperCase());
                    }
                }
            }
        }
    }
    
    public static void listJavaFile(File oldFile, String tabName) {
        if (oldFile != null) {
            if (oldFile.isDirectory()) {
                File[] fileArray = oldFile.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listJavaFile(fileArray[i], tabName);
                    }
                }
            } else {
                String javaFileName = oldFile.getName().replace(".java", "").toUpperCase();
                if (oldFile.getAbsolutePath().indexOf("domain") > 0 && javaFileName.indexOf(tabName) >= 0) {
                    System.out.println(oldFile.getAbsolutePath());
                    /*try {
                        String str = readLastLine(oldFile);
                        System.out.println(str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
                    File newFile = new File(oldFile.getAbsolutePath() + "_new");
                    boolean flag = genNewJavaFile(oldFile, new File(oldFile.getAbsolutePath() + "_new"));

                    if (flag) {
                        /*newFile.renameTo(oldFile);
                        oldFile.delete();*/

                        copyFile(newFile, oldFile);
                        if (newFile.exists()) {
                            newFile.delete();
                        }

                    } else {
                        newFile.delete();
                    }

                }
            }
        }
    }
    
    public static boolean genNewJavaFile(File oldFile, File newFile) {
        boolean addFieldFlag = false;

        try {
            BufferedReader in = new BufferedReader(new FileReader(oldFile.getAbsolutePath()));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getAbsolutePath()));
            String s = null;
            
            boolean needAddFieldsFlag = true;
            boolean needAddSetGetFlag = false;
            boolean hasAddSetGetFlag = false;
            String strFlag = "}";
            String sTemp = "}";
            int num = 0;
            int line = 0;
            
            while ((s = in.readLine()) != null) {
                if (s.toUpperCase().indexOf("SFXFSJ_PDBZ") > 0) {
                    needAddFieldsFlag = false;
                }

                if (needAddFieldsFlag && s.indexOf("public") > 0 && s.replaceAll("\\s*", "").indexOf(oldFile.getName().replace(".java", "") + "()") > 0) {
                    out.newLine();
                    out.write(javaFieldsCodeStr);
                    needAddFieldsFlag = false;
                    needAddSetGetFlag = true;
                }

                sTemp = s.replaceAll("\\s*", "");
                if (!("").equals(sTemp)) {
                    if (sTemp.equals(strFlag) && num <= 1) {
                        num++;
                    } else {
                        num = 0;
                    }
                }

                if (needAddSetGetFlag && !hasAddSetGetFlag && num >= 2) {
                    out.newLine();
                    out.write(javaFieldsGetSetCodeStr);
                    num = 0;
                    needAddSetGetFlag = false;
                    hasAddSetGetFlag = true;
                }

                if (needAddFieldsFlag && !hasAddSetGetFlag && num >= 2) {
                    out.newLine();
                    out.write(javaFieldsCodeStr);

                    out.newLine();
                    out.write(javaFieldsGetSetCodeStr);

                    hasAddSetGetFlag = true;
                }

                if (line > 0) {
                    out.newLine();
                }
                out.write(s);
                line++;
            }

            addFieldFlag = hasAddSetGetFlag;
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return addFieldFlag;
    }
    
    public static void copyFile(File oldFile, File newFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(oldFile.getAbsolutePath()));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getAbsolutePath()));
            String s = null;
            int line = 0;
            while ((s = in.readLine()) != null) {
                if (line > 0) {
                    out.newLine();
                }
                out.write(s);
                line++;
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String readLastLine(File file) throws IOException {
        if (!file.exists() || file.isDirectory() || !file.canRead()) {
            return null;
        }
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "r");
            long len = raf.length();
            if (len == 0L) {
                return null;
            } else {
                long pos = len - 1;
                while (pos > 0) {
                    pos--;
                    raf.seek(pos);
                    if (raf.readByte() == '\n') {
                        break;
                    }
                }
                if (pos == 0) {
                    raf.seek(0);
                }
                byte[] bytes = new byte[(int) (len - pos)];
                raf.read(bytes);
                return new String(bytes);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
            }
        }
        return null;
    }
}
