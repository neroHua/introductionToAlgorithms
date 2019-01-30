package chapter32;

/**
 * 
 * 字符串匹配算法
 * KMP算法:结合上面两个(天真，有限自动机）算法
 *
 * 主要用于解决在给定文本T[0, n]中查找符合顺序为P[0, m]的字符串的出现问题
 * 约定T和P中的字符串都来自指定字母表A. 约定m <= n
 * 
 * Q:一系列的状态
 * A:为字母表
 * fst:偏移状态转换函数, 一个长度为Q的数组, fst(i) = Q[i] = max(K), k 满足 P[0 ~ k] = P[i - k ~ i], k < i
 * fst*:一个集合, fst*(i) = {fst(i), fst ^ 1 (i), fst ^ 2 (i), fst ^ 3 (i), .... , fst ^ (k - 1) (i), fst ^ k (i)}, 其中fst ^ k (i) = fst ^ (k - 1) (fst(i)), fst ^ k (i) = 0
 *
 * 因此有：
 * fst*(i) = 一个k的集合 B, k 满足 P[0 ~ k] = P[i - k ~ i], k < i
 *     k0 = fst(i) 意味着 P[0 ~ k0] = P[i - k0 ~ i], k1 = fst ^ 1 (i) = fst(k0) 意味着 P[0 ~ k1] = P[k0 - k1 ~ k0]
 *     可以得到P[i] = P[k0] = P[k1], P[i - 1] = P[k0 - 1] = P[k1 - 1], .... , P[i - k1] = P[k0 - K1] = P[k1 - k1]
 *     即是 P[0 ~ k1] = P[i - k1 ~ i], k < i, 类似可以推到kn = fst ^ k (i) = 0
 *     也就是说k 属于 fst*(i) 则 k 属于 B， 即fst*(i) 是 B的子集
 *
 *     下面用反证法证明，B - fst*(i) = 空集 (当fst*(i) = {0}, 自然成立)
 *     设B - fst*(i) = C != 空集
 *     取 kc = max(C) 意味着kc不属于fst*(i)
 *     取 k* = min(fst*(i)), k* > kc
 *     则有P[0 ~ kc] = P[i - kc ~ i], P[0 ~ k*] = P[i - k* ~ i]
 *     于是可以得到P[i] = P[kc] = P[k*], P[i - 1] = P[kc - 1] = P[k* - 1], .... , P[i - kc] = P[kc - Kc] = P[k* - kc]
 *     也就可以得到P[0 ~ Kc] = P[k* - kc ~ k*]
 *     因为kc是最大的小于k*的数，所以 fst(k*) = kc
 *     又因为k* 属于fst*(i)，所以fst(k*) = kc 属于fst*(i), 跟条件kc = max(C) = B - fst*(i) != 空集
 *     所以B - fst*(i) = 空集 
 *
 * fst(i) - 1 属于 fst*(i - 1), 当 i > 1
 *     令：fst(i) = k, P[0 ~ k] = P[i - k ~ i], k < i
 *     则有P[0] = P{i - k], P[1] = P[i - k + 1], .... , P[k - 1] = P[i - 1], P[k] = P[i], k < i 
 *     则有P[0] = P{i - k], P[1] = P[i - k + 1], .... , P[k - 1] = P[i - 1], k < i 
 *     可以得到:P[0 ~ k - 1] = P[i - k ~ i - 1] = P[i - 1 - (k - 1) ~ i - 1], k - 1 < i -1
 *     所以 k - 1 = fst(i)  属于fst*(i - 1)
 * 
 * fet: fet(i) = max(k), k 满足 属于 fst*(i - 1), P[k + 1] = P[i]
 *
 * fst(i) = 0, 当 fet(i - 1) = 空集
 *        = fet(i - 1) + 1, 当fet(i - 1) ！= 空集
 *
 * 
 * ff:T[i]时永动机的状态
 *     ff(i) = ff(i - 1) + 1, 当 P[i] = T[i]
 *     ff(i) = max(k) + 1, k 满足 属于 D = fst*(ff(i - 1)) != 空集, P[k + 1] = T[i]
 *     ff(i) = 0, D = 空集
 * 
 * @author 滑德友
 * @time 2019年1月28日19:04:44
 *
 */
public class KMP {

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
        
        KMP finiteAutomata = new KMP();
        
        int[] result= finiteAutomata.match(t, p, alphabet);
        System.out.println(result);

    }

}
