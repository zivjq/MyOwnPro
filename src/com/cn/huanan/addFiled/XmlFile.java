package com.cn.huanan.addFiled;

/**
 * 列出指定目录的全部内容
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class XmlFile {
    private static final String xmlFieldConfigStr;
    
    private static final String[] xmlFileDirArr = {"E:/workspace/GD/20160817广东刑侦补采条线/basic/library/xml".replace("/", File.separator)};
    
    
    static {
        StringBuffer xmlFieldConfigSb = new StringBuffer(1000);
        xmlFieldConfigSb.append("\t<item name=\"sFXFSJ_PDBZ\" cnname=\"是否下发数据判断标识:1下发数据;0警综系统产生的数据\" inputtype=\"hidden\" datatype=\"string\" dbtype=\"string\"  maxlength=\"1\" default=\"\" editable=\"true\" whendefault=\"\" constrain=\"\" >\n");
        xmlFieldConfigSb.append("\t\t<value/>\n");
        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXDJDW_GAJGJGDM\" cnname=\"信息登记单位_公安机关机构代码\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"12\" whendefault=\"init,create\" default=\":user.dept\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXDJDW_GAJGMC\" cnname=\"信息登记单位_公安机关名称\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"100\" whendefault=\"init,create\" default=\":user.deptname\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXDJRY_XM\" cnname=\"信息登记人员_姓名\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"50\" whendefault=\"init,create\" default=\":user.name\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXDJRY_GMSFHM\" cnname=\"信息登记人员_公民身份号码\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"18\" whendefault=\"init,create\" default=\":user.zjhm\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXDJRY_LXDH\" cnname=\"信息登记人员_联系电话\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"18\" whendefault=\"init,create\" default=\":user.sys_reserver6\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"dJSJ\" cnname=\"登记时间\" inputtype=\"text\" datatype=\"datetime\" dbtype=\"datetime\" maxlength=\"7\" whendefault=\"init,create\" default=\":sys.currtime\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXCZDW_GAJGJGDM\" cnname=\"信息操作单位_公安机关机构代码\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"12\" whendefault=\"init,create,save\" default=\":user.dept\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXCZDW_GAJGMC\" cnname=\"信息操作单位_公安机关名称\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"100\" whendefault=\"init,create,save\" default=\":user.deptname\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXCZRY_XM\" cnname=\"信息操作人员_姓名\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"50\" whendefault=\"init,create,save\" default=\":user.name\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXCZRY_GMSFHM\" cnname=\"信息操作人员_公民身份号码\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"18\" whendefault=\"init,create,save\" default=\":user.zjhm\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"gXSJ\" cnname=\"更新时间\" inputtype=\"text\" datatype=\"datetime\" dbtype=\"datetime\" maxlength=\"7\" whendefault=\"init,create,save\" default=\":sys.currtime\" editable=\"false\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");
//        xmlFieldConfigSb.append("\t<item name=\"xXLYMS\" cnname=\"信息来源描述\" inputtype=\"text\" datatype=\"string\" dbtype=\"string\" maxlength=\"100\">\n");
//        xmlFieldConfigSb.append("\t\t<value/>\n");
//        xmlFieldConfigSb.append("\t</item>\n");

        xmlFieldConfigStr = xmlFieldConfigSb.toString();
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
            for (String xmlFileDir : xmlFileDirArr) {
                listXmlFile(new File(xmlFileDir), tableName.toUpperCase());
            }
        }

    }

    /**
     * 根据表名查找对应的bean文件，修改bean文件
     * @param f
     */
    public static void listTabSqlFile(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listTabSqlFile(fileArray[i]);
                    }
                }
            } else {
                String tabName = f.getName().replace(".tab", "");
                if (!SqlFile.DEBUG_FLAG || (SqlFile.TAB_NAME).equalsIgnoreCase(tabName)) {
                    System.out.println(tabName);
                    // 遍历JavaBean目录
                    for (String xmlFileDir : xmlFileDirArr) {
                        listXmlFile(new File(xmlFileDir), tabName.toUpperCase());
                    }
                }
                
            }
        }
    }
    
    public static void listXmlFile(File oldFile, String tabName) {
        if (oldFile != null) {
            if (oldFile.isDirectory()) {
                File[] fileArray = oldFile.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listXmlFile(fileArray[i], tabName);
                    }
                }
            } else {
                String xmlFileName = oldFile.getName().replace(".xml", "").toUpperCase();
                if (xmlFileName.indexOf(tabName) > 0) {
                    System.out.println(oldFile.getAbsolutePath());
                    File newFile = new File(oldFile.getAbsolutePath() + "_new");
                    genNewViewFile(oldFile, newFile);
                    copyFile(newFile, oldFile);
                    if (newFile.exists()) {
                        newFile.delete();
                    }
                }
            }
        }
    }
    
    public static void genNewViewFile(File oldFile, File newFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(oldFile.getAbsolutePath()));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getAbsolutePath()));
            String s = null;
            String sTemp = null;
            boolean hasUpdatedFlag = false;
            
            while ((s = in.readLine()) != null) {
                if (s.toUpperCase().indexOf("SFXFSJ_PDBZ") > 0) {
                    hasUpdatedFlag = true;
                }
                
                sTemp = s.replaceAll("\\s*", "");
                if (!hasUpdatedFlag && ("</FormItems>").equalsIgnoreCase(sTemp)) {
                    out.write(xmlFieldConfigStr);
                }
                
                out.write(s);
                out.newLine();
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void copyFile(File oldFile, File newFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(oldFile.getAbsolutePath()));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getAbsolutePath()));
            String s = null;
            while ((s = in.readLine()) != null) {
                out.write(s);
                out.newLine();
            }
            out.flush();
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
