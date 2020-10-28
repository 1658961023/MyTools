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
        String[] A = {"cool", "lock", "cook"};
        ListNode listNode = new ListNode(1);
        ListNode dummyNode = listNode;
        for (int i = 2; i < 5; i++) {
            dummyNode.next = new ListNode(i);
            dummyNode = dummyNode.next;
        }
        String S = "a#c";
        String T = "b";
        System.out.println(backspaceCompare(S, T));
    }

    public static void displayBackward(ListNode node) {
        if (node != null) {
            displayBackward(node.next);
            System.out.println(node.val);
        }
    }

    /**
     * 给定仅有小写字母组成的字符串数组 A，
     * 返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，
     * 则需要在最终答案中包含该字符 3 次。
     * <p>
     * 你可以按任意顺序返回答
     *
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A) {
        List<String> list = new ArrayList<>();
        char[] chars = A[0].toCharArray();
        Map<Character, Integer> times = new HashMap<Character, Integer>();
        for (int i = 0; i < chars.length; i++) {
            if (times.containsKey(chars[i])) {
                times.put(chars[i], times.get(chars[i]) + 1);
            } else {
                times.put(chars[i], 1);
            }
            int count = 0;
            boolean contained = true;
            for (int j = 1; j < A.length; j++) {
                if (A[j].length() >= i) {
                    if (!A[j].contains(String.valueOf(chars[i]))) {
                        contained = false;
                    } else {
                        A[j] = A[j].replaceFirst(chars[i] + "", "");
                    }
                }
            }
            if (contained) {
                list.add(String.valueOf(A[0].charAt(i)));
            }
        }
        return list;
    }

    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，
     * 判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {
        /*
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        for (char c : s) {
            int index = S.indexOf("#");
            if (c == '#') {
                if (index == 0) {
                    S = S.replaceFirst("#", "");
                } else {
                    S = S.replaceFirst(S.substring(index - 1, index + 1), "");
                }
            }
        }

        for (char c : t) {
            int index = T.indexOf("#");
            if (c == '#') {
                if (index == 0) {
                    T = T.replaceFirst("#", "");
                } else {
                    T = T.replaceFirst(T.substring(index - 1, index + 1), "");
                }
            }
        }
        return S.equals(T);
         */

        int i = S.length() - 1, j = T.length() - 1;
        int skipS = 0, skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }
            while (j >= 0) {
                if (T.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }
            if (i >= 0 && j >= 0) {
                if (S.charAt(i) != T.charAt(j)) {
                    return false;
                }
            } else {
                if (i >= 0 || j >= 0) {
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;
    }
}
