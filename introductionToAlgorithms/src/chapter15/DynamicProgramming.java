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

	int[] rodCutPrice = { 1, 5, 8, 10, 13, 17, 18, 22, 25, 30 };
	int[][][] rodCutMatrix = null;

	/**
	 * 
	 * 切管子问题 现在有一根长度为n的管子，我们需要把管子切成若干段，以获取管子的最大价值
	 * 有一个固定的价目表，长度1对应价格p1，长度2对应价格p3....,没有超过长度为10的价格 请问如何切割，获得的最大价值是多少？
	 * 求解思路：使用递归，将重复出现的子问题的解保存下来
	 * 如果F(n)，代表着当长度为n时，管子n的最大价值
	 * 如果f(x,n)，代表着切x刀，变成x-1份时，管子的n的最大价值 如果p(n)，代表着长度为n的管子价格
	 * 那么就会有迭代公式 F(n) = Max(f(0,n) , f(1,n) , f(2,n) , f(3,n),……,f(n-1,n))
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
	 * 
	 * @param length
	 *            管子的总长度
	 * 
	 * @return 管子的最大价值
	 */
	public int rodCut1(int length) {

		// 计算Max(f(0,n) , f(1,n) , f(2,n) , f(3,n),……,f(n-1,n))
		int maxValue = 0;
		for (int i = 0; i < length; i++) {

			int value = subRodCut1(i, length);

			if (value > maxValue) {
				maxValue = value;
			}

		}

		// 返回
		return maxValue;

	}

	public int subRodCut1(int cut, int length) {

		// 非法输入
		if (cut < 0 || cut >= length) {
			return 0;
		}

		// 递归结束条件
		if (cut == 0) {
			if (length >= 0 && length <= 10) {
				return rodCutPrice[length - 1];
			} else {
				return 0;
			}
		}

		// 计算Max(f(0,1)+f(0,n-1) , f(0,2)+f(0,n-2) ,
		// f(0,3)+f(0,n-3),……,f(0,n-1)+f(0,1))
		int maxValue = subRodCut1(0, length);
		for (int i = 1; i < 11; i++) {

			int value = 0;
			// 第一部分
			int part1 = subRodCut1(0, i);
			// 第二部分
			int part2 = subRodCut1(cut - 1, length - i);
			value = part1 + part2;

			if (part1 != 0 && part2 != 0 && maxValue < value) {
				maxValue = value;
			}

		}

		// 返回最大值
		return maxValue;

	}

	/**
	 * 
	 * 运用动态规划解决管子问题
	 * 通过观察可以看到: 在计算f(1,n)的时候会用到f(0,1)和f(0,n-1)或者f(0,3)和f(0,n-3)
	 * 在计算f(2,n)的时候会用到f(0,1)和f(1,n-1)或者f(0,3)和f(1,n-3)
	 * 由此我们可以看到在计算f(a,n)的时候会用到f(0,b)和f(a-1,n-b)
	 * 而f(0,b)和f(a-1,n-b)在我们在计算f(a,n)之前的时候已经计算过
	 * 此时如果我们运用动态规划的思想：把f(0,b)和f(a-1,n-b)存储起来，而后在计算f(a,n)的时候直接拿出来用就可以了
	 * 此时我们可以把递归的深度降低到2，因此也就减低了时间上的复杂度
	 * 算法的复杂度n*10*10
	 * 
	 * @param length
	 *           管子的总长度
	 * @return
	 */
	public int[] rodCut2(int length) {

		// 初始化存储器，f(0,1),f(0,2),……,f(0,10)
		rodCutMatrix = new int[10 + length][10 + length][11];
		for (int i = 0; i < 10; i++) {
			rodCutMatrix[0][i][i] = 1;
		}
		rodCutMatrix[0][0][10] = 1;
		rodCutMatrix[0][1][10] = 5;
		rodCutMatrix[0][2][10] = 8;
		rodCutMatrix[0][3][10] = 10;
		rodCutMatrix[0][4][10] = 13;
		rodCutMatrix[0][5][10] = 17;
		rodCutMatrix[0][6][10] = 18;
		rodCutMatrix[0][7][10] = 22;
		rodCutMatrix[0][8][10] = 25;
		rodCutMatrix[0][9][10] = 30;

		// 初始化存储器，f(0,11),f(0,12),……,f(0,10 + length)
		for (int i = 10; i < 10 + length; i++) {
			rodCutMatrix[0][i][10] = -1;
		}

		// 计算Max(f(0,n) , f(1,n) , f(2,n) , f(3,n),……,f(n-1,n))
		int[] maxValue = rodCutMatrix[0][0];
		for (int i = 0; i < length; i++) {

			int[] value = subRodCut2(i, length);

			if (value[10] > maxValue[10]) {
				maxValue = value;
			}

		}

		// 返回
		return maxValue;

	}

	public int[] subRodCut2(int cut, int length) {

		// 计算Max(f(0,1)+f(0,n-1) , f(0,2)+f(0,n-2) ,
		// f(0,3)+f(0,n-3),……,f(0,n-1)+f(0,1))
		int[] maxValue = rodCutMatrix[0][length - 1];
		for (int i = 1; i < 11 && cut - 1 > -1 && cut - 1 < length - i; i++) {

			int value = 0;
			// 第一部分
			int part1 = rodCutMatrix[0][i - 1][10];
			// 第二部分
			int[] part2 = null;
			if (rodCutMatrix[cut - 1][length - i - 1][10] == 0) {
				part2 = subRodCut2(cut - 1, length - i);
			} else if (rodCutMatrix[cut - 1][length - i - 1][10] == -1
					|| rodCutMatrix[cut - 1][length - i - 1][10] > 0) {
				part2 = rodCutMatrix[cut - 1][length - i - 1];
			}

			value = part1 + part2[10];

			// 计算f(cut,length)
			if (part1 > 0 && part2[10] > 0) {
				for (int j = 0; j < 11; j++) {
					rodCutMatrix[cut][length - 1][j] = rodCutMatrix[0][i - 1][j]
							+ rodCutMatrix[cut - 1][length - i - 1][j];
				}
			} else {
				rodCutMatrix[cut][length - 1][10] = -1;
			}

			if (part1 > 0 && part2[10] > 0 && maxValue[10] < value) {
				maxValue = rodCutMatrix[cut][length - 1];
			}

		}

		// 返回最大值
		return maxValue;

	}

	public static void main(String[] args) {

		DynamicProgramming dynamicProgramming = new DynamicProgramming();

		// System.out.println(dynamicProgramming.rodCut1(11));

		int[] cutResult = dynamicProgramming.rodCut2(11);
		for (int i : cutResult) {
			System.out.print(i + "\t");
		}

	}

}
