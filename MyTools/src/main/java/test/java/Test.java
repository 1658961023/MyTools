package test.java;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void main(String[] args) {
        String[] A = {"cool","lock","cook"};
        System.out.println(commonChars(A));
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，
     * 返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，
     * 则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        char[] chars = A[0].toCharArray();
        Map<Character,Integer> times = new HashMap<Character,Integer>();
        for (int i=0;i<chars.length;i++){
            if(times.containsKey(chars[i])){
                times.put(chars[i],times.get(chars[i])+1);
            }else{
                times.put(chars[i],1);
            }
            int count = 0;
            boolean contained = true;
            for (int j=1;j<A.length;j++){
                if(A[j].length() >= i){
                    if(!A[j].contains(String.valueOf(chars[i]))){
                        contained = false;
                    }else{
                        A[j] = A[j].replaceFirst(chars[i]+"","");
                    }
                }
            }
            if(contained) {
                list.add(String.valueOf(A[0].charAt(i)));
            }
        }
        return list;
    }
}
