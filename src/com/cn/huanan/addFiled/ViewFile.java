package com.cn.huanan.addFiled;

/**
 * 列出指定目录的全部内容
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class ViewFile {
    private static final String viewAddFielsSqlStr;
    public static final String TABLE_NAME_EXCEL = "E:/workspace/GD/20160817广东刑侦补采条线/db/xz/newtab02/new.xls".replace("/", File.separator);
    private static final String viewAddFielsSqlOneLineStr = ", xxsc_pdbz, xxdjdw_gajgjgdm, xxdjdw_gajgmc, xxdjry_xm, xxdjry_gmsfhm, xxdjry_lxdh, djsj, xxczdw_gajgjgdm, xxczdw_gajgmc, xxczry_xm, xxczry_gmsfhm, gxsj, xxlyms FROM";
    
    private static final String[] viewFileDirArr = {"E:/workspace/GD/20160817广东刑侦补采条线/db/xz/06vw".replace("/", File.separator)};
    
    static {
        StringBuffer viewAddFielsSql = new StringBuffer(500);
        viewAddFielsSql.append("        xxsc_pdbz, \n");
        viewAddFielsSql.append("        xxdjdw_gajgjgdm, \n");
        viewAddFielsSql.append("        xxdjdw_gajgmc, \n");
        viewAddFielsSql.append("        xxdjry_xm, \n");
        viewAddFielsSql.append("        xxdjry_gmsfhm, \n");
        viewAddFielsSql.append("        xxdjry_lxdh, \n");
        viewAddFielsSql.append("        djsj, \n");
        viewAddFielsSql.append("        xxczdw_gajgjgdm, \n");
        viewAddFielsSql.append("        xxczdw_gajgmc, \n");
        viewAddFielsSql.append("        xxczry_xm, \n");
        viewAddFielsSql.append("        xxczry_gmsfhm, \n");
        viewAddFielsSql.append("        gxsj, \n");
        viewAddFielsSql.append("        xxlyms ");

        viewAddFielsSqlStr = viewAddFielsSql.toString();
    }
    
    public static void main(String[] args) {
        File f = new File(TABLE_NAME_EXCEL);
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
            for (String viewFileDir : viewFileDirArr) {
                listViewFile(new File(viewFileDir), tableName.toUpperCase());
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
                    for (String viewFileDir : viewFileDirArr) {
                        listViewFile(new File(viewFileDir), tabName.toUpperCase());
                    }
                }
                
            }
        }
    }
    
    public static void listViewFile(File oldFile, String tabName) {
        if (oldFile != null) {
            if (oldFile.isDirectory()) {
                File[] fileArray = oldFile.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // 递归调用
                        listViewFile(fileArray[i], tabName);
                    }
                }
            } else {
                String viewFileName = oldFile.getName().replace(".sql", "").toUpperCase();
                if (viewFileName.toUpperCase().indexOf(tabName.toUpperCase()) > 0) {
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
            boolean hasUpdatedFlag = false;
            int lastFieldFlag = 0;
            String sTemp = null;
            
            while ((s = in.readLine()) != null) {
                if (s.toUpperCase().indexOf("XXSC_PDBZ") > 0) {
                    hasUpdatedFlag = true;
                }
                
                sTemp = s.replaceAll("\\s*", "");
                if (!("").equals(sTemp)) {
                    
                    if (!hasUpdatedFlag && s.toUpperCase().indexOf("SELECT") >= 0 && s.toUpperCase().indexOf("FROM") > 0) {
                        s = s.replaceFirst("FROM", viewAddFielsSqlOneLineStr);
                        s = s.replaceFirst("From", viewAddFielsSqlOneLineStr);
                        s = s.replaceFirst("from", viewAddFielsSqlOneLineStr);
                    } else if (s.toUpperCase().indexOf("SELECT") >= 0 && s.toUpperCase().indexOf("FROM") < 0) {
                        lastFieldFlag = 1;
                    } else if (s.indexOf(",") < 0 && lastFieldFlag == 1) {
                        lastFieldFlag = 2;
                    }
                }
                
                out.write(s);
                if (!hasUpdatedFlag && lastFieldFlag >= 2) {
                    out.write(",");
                    out.newLine();
                    out.write(viewAddFielsSqlStr);
                    lastFieldFlag = 0;
                }
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
