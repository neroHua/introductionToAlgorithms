﻿package chapter15;

import java.util.Arrays;

/**
 * 八皇后问题
 * 问题描素：八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上
 * 问有多少种摆法。
 * 以下是滑德友提供的算法    该算法由滑德友历时1天左右完成
 * 由于算法效率比较低，对硬件的依赖性比较强，在皇后数量在1-7的时候都能得到正确的解
 * 而在皇后数量等于8的时候由于硬件关系不足以一次性得到所有的解
 * 
 * @author 滑德友
 * @time 2019年2月15日18:16:36
 * 
 */
public class EightQueen {

    // 第一行，可先在第一行可以摆放的区域的第一个位置摆放皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第二行，然后在第二行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第三行，然后在第三行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第四行，然后在第四行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第五行，然后在第五行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第六行，然后在第六行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第七行，然后在第七行可以摆放的区域的第一个位置放置皇后，重新设定不能摆放的区域，记录每个皇后的位置，然后保存起来。
    // 第八行，然后在第八行可以摆放的区域的第一个位置放置皇后，判定是是否是正解，是则保存每个皇后的位置，正解数量++，否者则不保存！
    // 由于第八行，已经没有可以摆放的位置，返回第七行，并释放，第八行可以摆放的位置。
    // 设定第七行的第一个位置为不能摆放区域，并在第七行的可以摆放第一个位置上，放置皇后。（第七个可以摆放的位置的数量+1）
    // 第八行，然后在第八行可以摆放的区域的第一个位置放置皇后，判定是是否是正解，是则保存每个皇后的位置，正解数量++，否者则不保存！
    // 由于第八行，已经没有可以摆放的位置，返回第七行，并释放，第八行可以摆放的位置。
    // 由于第七行，已经没有可以摆放的位置，返回第六行，并释放，第七行可以摆放的位置。
    // 设定第六行的第一个位置为不能摆放区域，并在第六行的可以摆放第一个位置上，放置皇后。
    // ————————————
    // ————————————
    // ————————————
    // ————————————
    // 由此可以看出每行中每个个节点要做事情如下：
    // if(是否是最后一行？){
    // if(本行已摆放的个数是否为n？){
    //     if(本行的的第一个位置可以摆放？){
    //         在本行放置皇后
    //         将皇后的位置数组存起来或者输出，解得数量++
    //         本行已摆放的位置的个数++
    //         f(x)
    //     }else{
    //         本行已摆放的的位置的个数++；
    //         f(x)
    //     }        
    //  }else{
    //         返回前一行同时，本行可以摆放的位置的个数设为0，前一行已摆放的位置的个数++
    //     }
    //  }else{
    //     if(本行已摆放位置的个数是否为n？){
    //         if(本行的第一个位置可以摆放？){
    //             在本行放置皇后
    //             前进到前一行，同时，前一行摆放的位置的个数设为0
    //         }else{
    //             本行已摆放的位置的个数++
    //             f(x)
    //         }
    //     }else{
    //         if(不是是最后一行吗？){
    //             后退到后一行，同时，本行已摆放的位置的个数设为0，后一行摆放的位置的个数++
    //         }else{
    //             如果是第一行退出程序
    //         }
    //     }
    // }
    //需要一个存储本行已摆放过得的位置的数组a[0,0,0,0......0,0,0]
    //需要一个存储已经摆放好的皇后的位置的数组b[]
    //需要一个根据已经摆放好的皇后的位置的判定当前位置能不能放置皇后方法，并返回一个布尔行得数据
    //结束条件是A[0]=N(皇后的个数)
    int a[]={0,0,0,0,0,0,0,0};//本行已摆放过得的位置的数组
    int b[]={0,0,0,0,0,0,0,0};//摆放好的皇后的位置的数组
    int count=0;
    int c[][]=new int[100][100];
    public void answer(int x,int y,int N){//x代表行，y代表列，N代表皇后的个数
        if(a[0]==N){
            System.out.println("已经查找完毕！");
        }else{
            if(x==N-1){//最后一行吗？
                if(a[x]!=N){
                    if(judge(x,a[x])){
                        b[x]=a[x];
                        count++;
                        System.out.print("第"+count+"个解：");
                        System.out.println(Arrays.toString(b));
                        a[x]++;
                        answer(x,a[x],N);
                    }else{
                        a[x]++;
                        answer(x,a[x],N);
                    }
                }else{
                    a[x]=0;
                    a[x-1]++;
                    answer(x-1,a[x-1],N);
                }
            }else{
                if(a[x]!=N){
                    if(judge(x,a[x])){
                        b[x]=a[x];
                        a[x+1]=0;
                        answer(x+1,a[x+1],N);
                    }else{
                        a[x]++;
                        answer(x,a[x],N);
                    }
                }else{
                    if(x!=0){
                        a[x]=0;
                        b[x]=0;
                        a[x-1]++;
                        answer(x-1,a[x-1],N);    
                    }else{
                        System.out.println("已经查找完毕！");
                    }
                }
            }
        }
    }
    
    public boolean judge(int x,int y){
        // 从0到x-1行找到存储在a[x]中的皇后的位置，然后根据皇后的位置，判定当前位置是否可以分放置皇后
        // 当当前位置，与其他已经摆放好的皇后没有在一条45°斜线和同一行和同一列的时候就可以在当前位置摆放皇后
        boolean condition=true;
        for(int i=0;i<x;i++){
            if(y==a[i]||y==(a[i]+(x-i))||y==(a[i]-(x-i))){// 判定是否和a[i]同行||判定是否在a[i]的上方的45°||判定是否在a[i]的下方的45°
                condition=false;
                break;
            }
        }
        return condition;
    }
    
    public static void main(String[] args) {
        EightQueen test = new EightQueen();
        test.answer(0,0,7);
        System.out.println(test.count);
    }
    
}
