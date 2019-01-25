package chapter32;

/**
 * 
 * 字符串匹配算法
 * 有限自动机算法
 *
 * 主要用于解决在给定文本T[0, n]中查找符合顺序为P[0, m]的字符串的出现问题
 * 约定T和P中的字符串都来自指定字母表A. 约定m <= n
 * 
 * Q:一系列的状态
 * A:为字母表
 * ft:状态转换函数,当前状态和下一个字符时,得到下一个状态，是一个Q x M的矩阵 ,Q[next] = ft(Q[current], T[next])
 * ff:T[i]时永动机的状态，ff(null) = Q[0], ff(T[i]) = ft(ff(T[i - 1], T[i])
 * sf:T[i]时最长的字符串的长度，即是P的前缀，又是T[0 ~ i]的后缀, sf(T[i]) = max(K), k 满足 P[0 ~ k] = T[i - k ~ i]
 * 
 * 设：
 * Q[0]为起始状态,Q[m]为最终接受状态
 * ft(ff(T[i - 1], T[i]) = sf(P[0 ~ j - 1] + T[i]), 其中 sf(T[i - 1]) = j - 1 即i + 1时，保留最长的字符串，即是P的前缀，又是T[0 ~ i]的后缀
 * 
 * 因此有：
 * ff(T[i]) = sf(T[i]), 因为sf(T[i - 1]) = j - 1， 所以P[0 ~ j - 1] + T[i]是T[0 ~ i]的后缀
 * 
 * sf(T[i]) <= sf(T[i - 1]) + 1
 * 
 * sf(T[i]) = sf(P[0 ~ j - 1] + T[i]) <= sf(T[i - 1]) + 1 = j - 1 + 1 <= sf(P[0 ~ j - 1]) + 1 = j - 1 + 1
 * 
 * ff(T[i]) = sf(T[i])
 * 
 * @author 滑德友
 * @time 2019年1月21日19:04:44
 *
 */

public class FiniteAutomata {

    public int[] match(char[] t, char[] p, char[] alphabet) {
        int[][] stateTransitionDiagram = prepare(p, alphabet);
        
        int[] result = new int[t.length];
        int q = 0;
        
        for (int i = 0; i < t.length; i++) {
            
            q = stateTransition(q + 1, t[i], stateTransitionDiagram, alphabet);
            
            if (q == p.length - 1) {
                result[i] = 1;
            }
        }
        
        return result;
    }

    private int[][] prepare(char[] p,  char[] alphabet) {
        int[][] stateTransitionDiagram = new int[p.length + 1][alphabet.length];
        
        for (int i = 0; i <= p.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                
                // 求取：ft(ff(T[i - 1], T[i]) = sf(P[0 ~ j - 1] + T[i])
                int max = i <= p.length - 1 ? i : p.length - 1;
                for (; max >= 0; max--) {
                    
                    // max == 0 时，需要特殊处理
                    if (max == 0) {
                        if (p[max] == alphabet[j]) {
                            break;
                        } else {
                            max--;
                            break;
                        }
                    }
                    
                    int r = 0;
                    for (; r < max; r++) {
                        if (p[i - max + r] != p[r]) {
                            break;
                        }
                    }
                    
                    if (r == max && p[max] == alphabet[j]) {
                        break;
                    }
                }
                
                stateTransitionDiagram[i][j] = max;
            }
        }
        
        return stateTransitionDiagram;
    }

    private int stateTransition(int q, char c, int[][] stateTransitionDiagram, char[] alphabet) {
        // 找到该字母所在字母表中的下标
        for (int i = 0; i < alphabet.length; i++) {
            if (c == alphabet[i]) {
                return stateTransitionDiagram[q][i];
            }
        }
        
        throw new IllegalArgumentException("不再字母表中的字符");
    }
    
    public static void main(String[] args) {
        char[] alphabet = {'a', 'b', 'c'};
        char[] p = {'a', 'b', 'a', 'b', 'a', 'c', 'a'};
        char[] t = {'a', 'b', 'a', 'b', 'a', 'c', 'a', 'a', 'b', 'a', 'b', 'a', 'c', 'a'};
        
        FiniteAutomata finiteAutomata = new FiniteAutomata();
        
        int[] result= finiteAutomata.match(t, p, alphabet);
        System.out.println(result);

    }

}
