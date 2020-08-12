package test.java;


import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*********************************************************
 @author guoff28031
  * <p> 文件名称： Test
  * <p> 系统名称：交易银行系统V2.0
  * <p> 模块名称：test.java 
  * <p> 功能说明：
  * <p> 开发人员：guoff28031
  * <p> 开发时间：2020/1/14 下午 04:29
  * <p> 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/

public class Test {


    public static void main(String[] args){
        System.out.println(isValidDate("2007-2-30"));
    }

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

    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }
}
