package test.java;


import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
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
        int[] a = {2,7,4,1,8,1};
        String s = "aaa";
        System.out.println(largeGroupPositions(s));
        System.out.println((a));
    }

    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList();
        int start = 0, end = 0;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i-1) == s.charAt(i)){
                if(start == 0){
                    start = i-1;
                } else{
                    end = i;
                }
                if ((end-start)>=2 && i==(s.length()-1)) {
                    result.add(Arrays.asList(start,end));
                }
            } else{
                if ((end-start)>=2){
                    result.add(Arrays.asList(start,end));
                }
                start=0;
                end=0;
            }
        }
        return result;
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

    public static int islandPerimeter(int[][] grid) {
        int length = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (i > 0 && j > 0 && i < grid.length - 1 && j < grid[i].length) {
                        if (grid[i - 1][j] == 0 || grid[i + 1][j] == 0 || grid[i][j - 1] == 0 || grid[i][j + 1] == 0) {
                            length++;
                        }
                        length++;
                    }
                }
            }
        }
        return length;
    }

    /**
     * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
     * <p>
     * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<Integer> result = new ArrayList();
        int left = newInterval[0];
        int right = newInterval[1];
        boolean end = false;
        for (int[] interval : intervals) {
            if (interval[1] < left) {
                result.add(interval[0]);
                result.add(interval[1]);
            } else if (interval[0] > right) {
                if (!end) {
                    result.add(left);
                    result.add(right);
                    end = true;
                }
                result.add(interval[0]);
                result.add(interval[1]);
            } else {
                left = Math.min(left, interval[0]);
                right = Math.max(right, interval[1]);
            }
        }
        if (!end) {
            result.add(left);
            result.add(right);
        }
        int[][] ans = new int[result.size() / 2][2];
        for (int i = 0; i < result.size(); i = i + 2) {
            ans[i / 2][0] = result.get(i);
            ans[i / 2][1] = result.get(i + 1);
        }
        return ans;
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head.next;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode lastSorted = head;
        while (cur != null) {
            if (lastSorted.val <= cur.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyNode;
                while (cur.val >= prev.next.val) {
                    prev = prev.next;
                }
                lastSorted.next = cur.next;
                cur.next = prev.next;
                prev.next = cur;
            }
            cur = lastSorted.next;
        }
        return dummyNode.next;
    }

    /**
     * 上升下降字符串
     */
    public static String sortString(String s) {
        int[] buckets = new int[26];
        for (int i = 0; i < 26; i++) {
            buckets[s.charAt(i) - 'a']++;
        }
        StringBuffer sb = new StringBuffer();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (buckets[i] > 0) {
                    sb.append(buckets[i]);
                    buckets[i]--;
                }
            }
            for (int j = 25; j >= 0; j--) {
                if (buckets[j] > 0) {
                    sb.append(buckets[j]);
                    buckets[j]--;
                }
            }
        }
        return sb.toString();
    }

    public static char findTheDifference(String s, String t) {
        char[] s1 = s.toCharArray();
        char[] t1 = t.toCharArray();
        int[] bucket = new int[26];
        for (int i = 0; i < s1.length; i++) {
            bucket[s1[i] - 'a']++;
        }
        for (int i = 0; i < t1.length; i++) {
            bucket[t1[i] - 'a']--;
        }
        int result = 0;
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) result = i;
        }
        return (char) ('a' + result);
    }


}
