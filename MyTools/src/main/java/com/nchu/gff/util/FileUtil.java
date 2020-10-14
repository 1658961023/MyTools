package com.nchu.gff.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*********************************************************
 * 文件名称: FileUtil
 * 系统名称：交易银行系统V2.0
 * 模块名称：main.java.com.nchu.gff.util 
 * 功能说明：文件工具类 
 * 开发人员：@author guoff28031
 * 开发时间：2020/8/31 16:27
 * 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/
public class FileUtil {

    /**
     * 按行读取文件
     * @param path 入参
     * @return 出参
     */
    public static List<String> read(String path){
        List<String> list = new ArrayList<>();
        File file = new File(path);
        try {
            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            while((line=br.readLine())!=null){
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
