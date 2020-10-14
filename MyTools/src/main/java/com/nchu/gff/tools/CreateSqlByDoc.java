package com.nchu.gff.tools;

import main.java.com.nchu.gff.util.FileUtil;
import main.java.com.nchu.gff.util.StringUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*********************************************************
 * 文件名称: CreateSqlByDoc
 * 系统名称：交易银行系统V2.0
 * 模块名称：main.java.com.nchu.gff.tools 
 * 功能说明：根据文档生成Sql语句 
 * 开发人员：@author guoff28031
 * 开发时间：2020/8/24 19:37
 * 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/
public class CreateSqlByDoc {

    private static final String NOT_NULL = "√";
    private static final String IS_NULL = "×";

    private static List<String> fields = new ArrayList<>();
    private static HashMap<String, String> fieldType = new HashMap<>();
    private static HashMap<String, String> notnull = new HashMap<>();
    private static HashMap<String, String> comments = new HashMap<>();
    private static final String TABLE_NAME = "CMP_CFCP_PRODUCT";

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + File.separator + "MyTools" + File.separator + "DocUtil" + File.separator + "config.txt";
        getFields(FileUtil.read(path).get(0).split("=")[1]);
        outToFile(path, TABLE_NAME);
        System.out.println("generate success!!");
    }

    private static void getFields(String path) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\t", " ");
                //字段名
                String field = StringUtil.getNextWord(line, 0);
                fields.add(field);
                //字段类型
                String type = StringUtil.getNextWord(line, line.lastIndexOf(field) + field.length());
                fieldType.put(field, type);
                //是否可为空
                if (line.contains(NOT_NULL)) {
                    notnull.put(field, "not null");
                    //字段说明
                    comments.put(field, line.substring(line.lastIndexOf(NOT_NULL) + 1).trim());
                } else {
                    notnull.put(field, "");
                    comments.put(field, line.substring(line.lastIndexOf(IS_NULL) + 1).trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outToFile(String path, String tableName) {
        File file = new File(System.getProperty("user.dir") + File.separator + "MyTools" + File.separator + "DocUtil" + File.separator + "sqlFile.txt");
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            StringBuilder sb = new StringBuilder();
            sb.append("create table ").append(tableName).append("(\n");
            for (String field : fields) {
                sb.append(field).append(" ")
                        .append(fieldType.get(field))
                        .append(" ").append(notnull.get(field))
                        .append(",\n");
            }
            sb.append(")\n/\n\n");
            for (String field : fields) {
                sb.append("comment on column ").append(tableName).append(".").append(field)
                        .append(" is '").append(comments.get(field)).append("'\n/\n\n");
            }
            pw.println(sb.toString());
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
