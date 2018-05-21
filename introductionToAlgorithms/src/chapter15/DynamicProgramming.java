package chapter15;

/**
 * 
 * 动态规划
 * 将一个解决方案存储起来，下次碰到相同的情况直接调用
 * 对于递归的时候，如果某一个子问题重复出现，可以先将子问题的解存取起来，下次直接调用
 * 
 * 解决问题的步骤如下：
 * 1.确定最优解的结构
 * 2.递归定义最优解
 * 3.计算最优解，通常自底向上
 * 4.根据计算信息构造最优解
 * 
 * @author 滑德友
 * @since 2018年5月18日14:12:30
 *
 */
public class DynamicProgramming {

	int[] price = {1,5,8,10,13,17,18,22,25,30};
	int[] count1 = {0,0,0,0,0,0,0,0,0,0};
	int[] count2 = {0,0,0,0,0,0,0,0,0,0};
	/**
	 * 
	 * 切管子问题
	 * 现在有一根长度为n的管子，我们需要把管子切成若干段，以获取管子的最大价值
	 * 有一个固定的价目表，长度1对应价格p1，长度2对应价格p3....,没有超过长度为10的价格
	 * 请问如何切割，获得的最大价值是多少？
	 * 求解思路：使用递归，将重复出现的子问题的解保存下来
	 * 如果F(n)，代表着当长度为n时，管子n的最大价值
	 * 如果f(x,n)，代表着切x刀，变成x-1份时，管子的n的最大价值
	 * 如果p(n)，代表着长度为n的管子价格
	 * 那么就会有迭代公式
	 * F(n) = Max(f(0,n) , f(1,n) , f(2,n) , f(3,n),……,f(n-1,n))
	 * f(0,n) = p(n) 
	 * f(1,n) = Max(f(0,1)+f(0,n-1) , f(0,2)+f(0,n-2) , f(0,3)+f(0,n-3),……,f(0,n-1)+f(0,1))
	 * f(2,n) = Max(f(0,1)+f(1,n-1) , f(0,2)+f(1,n-2) , f(0,3)+f(1,n-3),……,f(0,n-1)+f(1,1))
	 * f(3,n) = Max(f(0,1)+f(2,n-1) , f(0,2)+f(2,n-2) , f(0,3)+f(2,n-3),……,f(0,n-1)+f(2,1))
	 * 注意：p(n),当n>10时,p(n) = 0 , 对应的f(x,y)的第一个参数，只有0<= x <= 10 并且x <= y-1才有意义
	 * p(1) = 1
	 * p(2) = 5
	 * p(3) = 8
	 * p(4) = 10
	 * p(5) = 13
	 * p(6) = 17
	 * p(7) = 18
	 * p(8) = 22
	 * p(9) = 25
	 * p(10) = 30
	 * p(11） = 0
	 * p(12) = 0
	 * ……
	 * 算法的复杂度n*10^n
	 * 由此我们可以看到上面的f(1,5),f(2,4)等等会被多次重复计算，如果我们把这些重复计算的函数的值存储起来，就可以，直接使用了
	 * @param length 管子的总长度
	 * 
	 * @return 管子的最大价值
	 */
	public int rodCut1( int length){

		// 计算Max(f(0,n) , f(1,n) , f(2,n) , f(3,n),……,f(n-1,n))
		int maxValue = 0;
		for (int i = 0; i < length; i++) {

			int value = subRodCut1(i,length);

			if(value > maxValue){
				maxValue = value;
			}

		}

		// 返回
		return maxValue;

	}

	public int subRodCut1(int cut,int length){

		// 非法输入
		if(cut < 0 || cut >= length){
			return 0;
		}
		
		// 递归结束条件
		if(cut == 0){
			if( length >= 0 && length <= 10){
				return price[length - 1];
			}else{
				return 0;
			}
		}

		// 计算Max(f(0,1)+f(0,n-1) , f(0,2)+f(0,n-2) , f(0,3)+f(0,n-3),……,f(0,n-1)+f(0,1))
		int maxValue = subRodCut1(0,length);
		for(int i = 1; i < 11; i++){

			int value = 0;
			// 第一部分
			int part1 = subRodCut1(0 , i );
			// 第二部分
			int part2 = subRodCut1(cut - 1, length - i);
			value = part1 + part2;

			if(part1 != 0 && part2 != 0 && maxValue < value){
				maxValue = value;
			}else{
				
			}

		}

		// 返回最大值
		return maxValue;

	}
	
	public static void main(String[] args) {
		
		DynamicProgramming dynamicProgramming = new DynamicProgramming();
		
		System.out.println(dynamicProgramming.rodCut1(4));
		
	}

}
