package com.cn.huanan.addFiled;

/**
 * �г�ָ��Ŀ¼��ȫ������
 * */
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SqlFile {
    public static final String TABLE_NAME_EXCEL = "E:/workspace/GD/20160817�㶫���첹������/db/xz/newtab02/new.xls".replace("/", File.separator);
    public static final String TAB_NAME = "XW_LA";
    public static final boolean DEBUG_FLAG = false;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
    private static final String ALTER_TABLE_SQL_DIR = "E:/workspace/GD/20160817�㶫���첹������/db/xz/newtab02/03tab_alter_2016/".replace("/", File.separator);
    private static final String alterTAbSqlInit;
    private static final String replaceTabStr = "@TABLE";

    static {
        StringBuffer sqlSb = new StringBuffer(500);
        sqlSb.append(" -- Add/modify columns  \n");
        sqlSb.append(" alter table @TABLE add CREATOR VARCHAR2(50); \n");
        sqlSb.append(" alter table @TABLE add CREATEDTIME date default sysdate; \n");
        sqlSb.append(" alter table @TABLE add REFRESHTIME date default sysdate; \n");
        sqlSb.append(" alter table @TABLE add LASTUPDATEDBY VARCHAR2(50); \n");
        sqlSb.append(" alter table @TABLE add LASTUPDATEDTIME date default sysdate; \n");
        sqlSb.append(" alter table @TABLE add DEPARTMENTCODE VARCHAR2(12); \n");
        sqlSb.append(" alter table @TABLE add UPLOADFLAG VARCHAR2(1); \n");
        sqlSb.append(" alter table @TABLE add DOWNLOADFLAG VARCHAR2(1); \n");
        sqlSb.append(" alter table @TABLE add DELETEFLAG VARCHAR2(1); \n");
        sqlSb.append(" alter table @TABLE add DEPARTMENTCODE_CN VARCHAR2(100); \n");
        sqlSb.append(" alter table @TABLE add CREATOR_CN VARCHAR2(50); \n");
        sqlSb.append(" alter table @TABLE add LASTUPDATEDBY_CN VARCHAR2(50); \n");
        
        sqlSb.append(" -- Add comments to the columns  \n");
        sqlSb.append(" comment on column @TABLE.CREATOR \n");
        sqlSb.append("   is '������'; \n");
        sqlSb.append(" comment on column @TABLE.CREATEDTIME \n");
        sqlSb.append("   is '����ʱ��'; \n");
        sqlSb.append(" comment on column @TABLE.REFRESHTIME \n");
        sqlSb.append("   is 'ˢ��ʱ��'; \n");
        sqlSb.append(" comment on column @TABLE.LASTUPDATEDBY \n");
        sqlSb.append("   is '��������'; \n");
        sqlSb.append(" comment on column @TABLE.LASTUPDATEDTIME \n");
        sqlSb.append("   is '������ʱ��'; \n");
        sqlSb.append(" comment on column @TABLE.DEPARTMENTCODE \n");
        sqlSb.append("   is '���ű��'; \n");
        sqlSb.append(" comment on column @TABLE.UPLOADFLAG \n");
        sqlSb.append("   is '�ϴ���־'; \n");
        sqlSb.append(" comment on column @TABLE.DOWNLOADFLAG \n");
        sqlSb.append("   is '���ر�־(ȫ���·����ݱ�ʶ��1��ʶȫ���·���������ʶ��ʡ)'; \n");
        sqlSb.append(" comment on column @TABLE.DELETEFLAG \n");
        sqlSb.append("   is 'ɾ����־'; \n");
        sqlSb.append(" comment on column @TABLE.DEPARTMENTCODE_CN \n");
        sqlSb.append("   is '��������'; \n");
        sqlSb.append(" comment on column @TABLE.CREATOR_CN \n");
        sqlSb.append("   is '����������'; \n");
        sqlSb.append(" comment on column @TABLE.LASTUPDATEDBY_CN \n");
        sqlSb.append("   is '������������'; \n");

        alterTAbSqlInit = sqlSb.toString();
    }
//    static {
//        StringBuffer sqlSb = new StringBuffer(500);
//        sqlSb.append(" -- Add/modify columns  \n");
//        sqlSb.append(" alter table @TABLE add SFXFSJ_PDBZ VARCHAR2(1); \n");
//        
//        
//        sqlSb.append(" -- Add comments to the columns  \n");
//        sqlSb.append(" comment on column @TABLE.SFXFSJ_PDBZ \n");
//        sqlSb.append("   is '�Ƿ��·������жϱ�ʶ:1�·�����;0����ϵͳ����������'; \n");
//        
//
//        alterTAbSqlInit = sqlSb.toString();
//    }
    
    public static void main(String[] args) {
        // File f = new File(SQL_FILE_DIR);
        //listTabSqlFile(f);

        File file = new File(TABLE_NAME_EXCEL);
        listTabNameFromExcel(file);
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
        System.out.println("rowNum:"+rowNum);
        for (int k = 0; k <= rowNum; k++) {
            HSSFRow row = tableSheet.getRow(k);
            if (row == null) {
                break;
            }

            HSSFCell cell = row.getCell((short) 0);

            String tableName = getCellValueAsString(cell);
            System.out.println(tableName);
            writeAlterTabFile(tableName);
        }

    }

    public static void listTabSqlFile(File f) {
        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        // �ݹ����
                        listTabSqlFile(fileArray[i]);
                    }
                }
            } else {
                String tabName = f.getName().replace(".tab", "");
                if (!SqlFile.DEBUG_FLAG || (SqlFile.TAB_NAME).equalsIgnoreCase(tabName)) {
                    System.out.println(tabName);
                    writeAlterTabFile(tabName);
                }
            }
        }
    }

    public static void writeAlterTabFile(String name) {
        File alterTabSqlFile;
        FileOutputStream alterSqlFileFos = null;
        OutputStreamWriter alterSqlFilePw = null;
        
        try {
            alterTabSqlFile = new File(ALTER_TABLE_SQL_DIR + sdf.format(new Date()) + "_alter_" + name + ".sql");

            if (!alterTabSqlFile.exists()) {
                alterTabSqlFile.createNewFile();
            }
            
            alterSqlFileFos = new FileOutputStream(alterTabSqlFile);
            alterSqlFilePw = new OutputStreamWriter(alterSqlFileFos, "gbk");
            alterSqlFilePw.write(alterTAbSqlInit.replaceAll(replaceTabStr, name));
            alterSqlFilePw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                alterSqlFilePw.close();
                alterSqlFileFos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    public static String getCellValueAsString(HSSFCell cell) {
        String rs = null;

        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                rs = cell.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                double d = cell.getNumericCellValue();
                rs = String.valueOf((int) d);
                break;

            default:
                break;
        }

        return rs;
    }
}
