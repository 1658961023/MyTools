package com.nchu.gff.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*********************************************************
 * 文件名称: StringUtil
 * 系统名称：交易银行系统V2.0
 * 模块名称：main.java.com.nchu.gff.util 
 * 功能说明：字符串工具类 
 * 开发人员：@author guoff28031
 * 开发时间：2020/8/31 16:28
 * 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/
public class StringUtil {

    /**
     * 截取字符串中特定位置开始后的第一个单词
     *
     * @param str      入参字符串
     * @param curIndex 开始位置
     * @return 返回单词
     */
    public static String getNextWord(String str, int curIndex) {
        // 后面的剩余字符串
        String leftStr = str.substring(curIndex);
        char[] strArray = leftStr.toCharArray();
        //单词开始和结束位置
        int startIdx = 0;
        int endIdx = 0;
        boolean started = false;

        for (int i = 0; i < leftStr.length(); i++) {
            //单词未开始遇到的第一个非空格即开始位置
            if (!started && !Character.isSpaceChar(strArray[i])) {
                startIdx = i;
                started = true;
            }
            //单词已开始遇到的第一个空格-1即结束位置
            if (started && Character.isSpaceChar(strArray[i])) {
                endIdx = i;
                break;
            }
        }

        //最后一个单词
        if (startIdx > endIdx) {
            endIdx = leftStr.length();
        }

        //截取单词
        return leftStr.substring(startIdx, endIdx);
    }

    /**
     * 是否是数字
     *
     * @param str 入参
     * @return 出参
     */
    public static boolean isNumeric(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            //异常 说明包含非数字。
            return false;
        }
        return true;
    }

    /**
     * 日期合法
     *
     * @param str 入参
     * @return 出参
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
