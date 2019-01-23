package chapter32;

import java.util.Arrays;

/**
 * 
 * 字符串匹配算法
 * 天真算法
 *
 * 主要用于解决在给定文本T[0, n]中查找符合顺序为P[0, m]的字符串的出现问题
 * 约定T和P中的字符串都来自指定字母表A. 约定m <= n
 * 
 * 天真算法的一次比较过程如下
 *  比较T[i]和P[0]
 *      如果T[i]=P[0],则顺序比较T[i + 1, i + m]和P[1, m]
 *  否者 i++
 * 
 * 算法的复杂度：为n * m
 * 当P中没有重复的字符时，算法的复杂度可以为：n
 * 
 * 
 * @author 滑德友
 * @time 2019年1月17日18:13:02
 *
 */
public class Navie {

    public int[] matchFromLeftToRight(char[] t, char[] p) {

        int[] result = new int[t.length];

        for (int i = 0; i < t.length; i++) {
            int j = 0;
            for (; j < p.length; j++) {
                if (t[i + j] != p[j]) {
                    break;
                }
            }

            if (j == p.length) {
                result[i] = 1;
            }
        }

        return result;

    }

    public int[] matchFromRightToLeft(char[] t, char[] p) {

        int[] result = new int[t.length];

        for (int i = t.length - 1; i >= 0; i--) {
            int j = p.length - 1;
            for (; j >= 0; j--) {
                if (t[i + j - p.length + 1] != p[j]) {
                    break;
                }
            }

            if (j == -1) {
                result[i] = 1;
            }
        }

        return result;

    }

    public static void main(String[] args) {
        Navie navie = new Navie();

        char[] t = "abcabeab".toCharArray();
        char[] p = "ab".toCharArray();

        int[] matchFromLeftToRight = navie.matchFromLeftToRight(t, p);
        System.out.println(Arrays.toString(matchFromLeftToRight));

        int[] matchFromRightToLeft = navie.matchFromRightToLeft(t, p);
        System.out.println(Arrays.toString(matchFromRightToLeft));

    }

}
