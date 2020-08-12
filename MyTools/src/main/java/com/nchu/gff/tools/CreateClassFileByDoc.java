package main.java.com.nchu.gff.tools;


import java.io.*;
import java.util.*;

/*********************************************************
 @author guoff28031
  * <p> 文件名称： CreateClassFileByDoc
  * <p> 系统名称：交易银行系统V2.0
  * <p> 模块名称：main.java
  * <p> 功能说明：根据接口文档生成类字段
  * <p> 开发人员：guoff28031
  * <p> 开发时间：2019/11/27 下午 02:27
  * <p> 修改记录：程序版本	修改日期	修改人员	修改单号	修改说明
 *********************************************************/
public class CreateClassFileByDoc {

    private static final String REQUIRED = "M";
    private static final String NOT_REQUIRED = "O";
    private static final String ENUM_FLAG = "G";

    public static void main(String[] args) {
        String path = System.getProperty("user.dir")+ File.separator+ "MyTools"+File.separator+"config.txt";
        outputToFile(getFields(read(path).get(0).split("=")[1]));
    }

    private static Map<String,String> getFields(String path){
        Map<String,String> fields = new LinkedHashMap<>(20);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            String line;
            while((line = br.readLine())!=null){
                //判断是否是一行一个字段信息
                if(line.contains(REQUIRED)||line.contains(NOT_REQUIRED)||line.contains(ENUM_FLAG)){
                    String[] units = line.split("\t");
                    String field = units[2];
                    String desc = units[3]+(units.length>6?units[6]:"");
                    fields.put(field,desc);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fields;
    }

    private static void outputToFile(Map<String,String> fields){
        File file = new File(System.getProperty("user.dir")+File.separator+"clazzFile");
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file));
            for(Map.Entry<String,String> e : fields.entrySet()){
                pw.println("/**\n* "+e.getValue()+"\n*/\nprivate String "+e.getKey()+";\n");
            }
            pw.println();
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<String> read(String path){
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
