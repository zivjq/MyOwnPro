package com.cn.huanan.addFiled;

/**
 * 列出指定目录的全部内容
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class JspFile {
    private static final String jspFieldCodeStr;
    
    private static final String[] xmlFileDirArr = {"E:/workspace/GD/20160817广东刑侦补采条线/basic/txbc".replace("/", File.separator),
                                                    "E:/workspace/GD/20160817广东刑侦补采条线/basic/txyw".replace("/", File.separator),
                                                    "E:/workspace/GD/20160817广东刑侦补采条线/basic/xzbc".replace("/", File.separator)};
    
    static {
        StringBuffer jspFieldCodeSb = new StringBuffer(500);
        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"sFXFSJ_PDBZ:\" class=\"cls_label_init\">sFXFSJ_PDBZ</span></td>\n");
        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_sFXFSJ_PDBZ\"></span></td>\n");
        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXDJDW_GAJGJGDM:\" class=\"cls_label_init\">XXDJDW_GAJGJGDM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXDJDW_GAJGJGDM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXDJDW_GAJGMC:\" class=\"cls_label_init\">XXDJDW_GAJGMC</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXDJDW_GAJGMC\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXDJRY_XM:\" class=\"cls_label_init\">XXDJRY_XM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXDJRY_XM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXDJRY_GMSFHM:\" class=\"cls_label_init\">XXDJRY_GMSFHM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXDJRY_GMSFHM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXDJRY_LXDH:\" class=\"cls_label_init\">XXDJRY_LXDH</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXDJRY_LXDH\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"dJSJ:\" class=\"cls_label_init\">DJSJ</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_dJSJ\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXCZDW_GAJGJGDM:\" class=\"cls_label_init\">XXCZDW_GAJGJGDM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXCZDW_GAJGJGDM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXCZDW_GAJGMC:\" class=\"cls_label_init\">XXCZDW_GAJGMC</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXCZDW_GAJGMC\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXCZRY_XM:\" class=\"cls_label_init\">XXCZRY_XM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXCZRY_XM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXCZRY_GMSFHM:\" class=\"cls_label_init\">XXCZRY_GMSFHM</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXCZRY_GMSFHM\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"gXSJ:\" class=\"cls_label_init\">GXSJ</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_gXSJ\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");
//        jspFieldCodeSb.append("\t\t\t<tr class=\"edit_table_tr\">\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_title\" nowrap=\"nowrap\"><span id=\"xXLYMS:\" class=\"cls_label_init\">XXLYMS</span></td>\n");
//        jspFieldCodeSb.append("\t\t\t\t<td class=\"edit_table_td_input\"><span id=\"_xXLYMS\"></span></td>\n");
//        jspFieldCodeSb.append("\t\t\t</tr>\n");

        jspFieldCodeStr = jspFieldCodeSb.toString();
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
            for (String viewFileDir : xmlFileDirArr) {
                listJspFile(new File(viewFileDir), tableName.toUpperCase());
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
                    for (String viewFileDir : xmlFileDirArr) {
                        listJspFile(new File(viewFileDir), tabName.toUpperCase());
                    }
                }
                
            }
        }
    }
    
    public static void listJspFile(File oldFile, String tabName) {
        if (oldFile != null) {
            if (oldFile.isDirectory()) {
                File[] fileArray = oldFile.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listJspFile(fileArray[i], tabName);
                    }
                }
            } else {
                String jspFileName = oldFile.getName().replace(".jsp", "").toUpperCase();
                if (jspFileName.indexOf(tabName) > 0 && oldFile.getAbsolutePath().indexOf("jsp") > 0) {
                    System.out.println(oldFile.getAbsolutePath());
                    File newFile = new File(oldFile.getAbsolutePath() + "_new");
                    genNewJspFile(oldFile, newFile);
                    copyFile(newFile, oldFile);
                    if (newFile.exists()) {
                        newFile.delete();
                    }
                }
            }
        }
    }
    
    public static void genNewJspFile(File oldFile, File newFile) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(oldFile.getAbsolutePath()));
            BufferedWriter out = new BufferedWriter(new FileWriter(newFile.getAbsolutePath()));
            String s = null;
            int insertFlag = 0;
            String sTemp = null;
            boolean hasUpdatedFlag = false;
            
            while ((s = in.readLine()) != null) {
                if (s.toUpperCase().indexOf("SFXFSJ_PDBZ") > 0) {
                    hasUpdatedFlag = true;
                }
                
                sTemp = s.replaceAll("\\s*", "");
                if (!("").equals(sTemp)) {
                    if (sTemp.indexOf("<table") >= 0 && sTemp.indexOf("display:none") > 0) {
                        insertFlag = 1;
                    } else if (sTemp.indexOf("/table") > 0 && insertFlag == 1) {
                        insertFlag = 2;
                    }
                }
                
                if (!hasUpdatedFlag && insertFlag >= 2) {
                    out.write(jspFieldCodeStr);
                    insertFlag = 0;
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
