package chapter15;

import java.util.ArrayList;

/**
 * 
 * 动态规划
 * 将一个解决方案存储起来，下次碰到相同的情况直接调用
 * 对于递归的时候，如果某些子问题重复出现，可以先将子问题的解存取起来，下次直接调用
 * 
 * 解决问题的步骤如下：
 * 1.将大问题拆分成多个小问题
 * 2.递归各个问题
 * 3.解决各个问题，且把各个子问题的解存储起来
 * 4.解决原问题的解
 * 
 * 应用动态规划的两个必要条件：
 * 1.要求大问题和多个小问题是等价的，且大问题的解和小问题的解法相同（不相同，不能直接应用第二步）
 * 2.必须要有重复出现的子问题
 * 
 * 应用动态规划的一个注意事项：
 * 对于某些子问题，在用到他们的时候，并没有还没有计算过他们，并没有存储过他们，需要先计算他们
 * 
 * 关于复杂度，跟解的空间有直接关系
 * 
 * @author 滑德友
 * @since 2018年5月18日14:12:30
 *
 */
public class DynamicProgramming {

	int[] rodCutPrice = { 1, 5, 8, 10, 13, 17, 18, 22, 25, 30 };
	int[][][] rodCutMatrix = null;

	int[] A = null;
	int[] B = null;
	ArrayList<Integer>[][] DNAMatrix = null;
	
	ArrayList<Integer>[][] palindromeMatrix = null;
	
	
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
	 * 注意对于同一最大值，管子的分法有可能会有不同的情况，这里仅取其中一种
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
	 * 算法的复杂度n*n(n*n个解)
	 * 
	 * 注意对于同一最大值，管子的分法有可能会有不同的情况，这里仅取其中一种
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
			} else if (rodCutMatrix[cut - 1][length - i - 1][10] == -1 || rodCutMatrix[cut - 1][length - i - 1][10] > 0) {
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

	/**
	 * 
	 * 关于DNA的最长的共同子序列问题
	 * 求取在DNAA和DNAB中找出他们之间共有的最长子序列，且DNBA的长度高于DNAB的长度
	 * 
	 * 设F(startA,endA,startB,endB)为在A[startA,endA]和B[startB,endB]之间找出最长的子序列,且要求endA-startA>=endB-startB
	 * 设f(starta,enda,startb,endb,type)为在B[startb,endb]中找出所有等于A[starta,enda]的子序列 ，当type = 0，且要求endb-startb>=enda-starta
	 *									为在A[starta,enda]中找出所有等于B[startb,endb]的子序列 ，当type = 1，且要求enda-starta>=endb-startb
	 * 则有以下递推公式：
	 * F(0，endA + 1,0,endB+1) = A[0,endA+1],B[0,endB+1],  当F(0,endA + 1,0,endB)有一个解为A[0,endA],B[0,endB],且A[endA+1] = B[endB+1],且endA=endB
	 *  					   = Merge(F(0,endA + 1,0,endB) , f(0,endA+1,endB+1-length( F(0,endA+1,0,endB) ),endB+1,1)),  当F(0,endA + 1,0,endB)没有一个解为A[0,endA],B[0,endB],且endA=endB
	 * F(0,endA + 1,0,endB) = Merge(F(0,endA,0,endB) , f(endA+1-(length( F(0,endA,0,endB) )),endA+1,0,endB,0)),  当endA = endB,且endB < length[B]
	 * F(0,endA + 1,0,endB) = Merge(F(0,endA,0,endB) , f(endA+1-(length( F(0,endA,0,endB) )),endA+1,0,endB,0)),  当endA > endB+1,且endB = lenght[B]-1
	 * 
	 * 以上三个递推公式的使用策略是：
	 * 首先：f(0,0,0,0)-->f(0,1,0,0)-->f(0,1,0,1)-->f(0,2,0,1)-->f(0,2,0,2)-->f(0,3,0,2)*******直到endB-startB+1 = length(B)
	 * 然后：f(0,length(B)-1,0,length(B)-1)-->f(0,length(B),0,length(B)-1)-->f(0,length(B)+1,0,length(B)-1)*****直到endA-startA+1 = length(A)
	 * 
	 * @param 
	 * 		a
	 * @param 
	 * 		b
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> DNACompare(int[] a, int[] b) {

		// 初始化条件
		A = a;
		B = b;

		// 因为对于f我们每次计算过后都会把它叠加到F里面，
		// 又，对于F每次使用的第一个参数和第三个参数总是为0
		// 于是解的空间可以用二维数组表示
		// 初始化解空间
		DNAMatrix = new ArrayList[a.length][b.length];
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				DNAMatrix[i][j] = new ArrayList<Integer>();
				DNAMatrix[i][j].add(-1);
			}
		}

		// 计算首先
		for (int i = 0; i < b.length; i++) {
			subDNACompare1(0, i, 0, i);
			subDNACompare1(0, i + 1, 0, i);
		}
		// 计算然后
		for (int i = b.length + 1; i < a.length; i++) {
			subDNACompare1(0, i, 0, b.length - 1);
		}

		// 返回结果
		return DNAMatrix[a.length - 1][b.length - 1];

	}

	public void subDNACompare1(int startA, int endA, int startB, int endB) {

		// 对于起点
		if (endA == 0 && endB == 0) {
			if (A[0] == B[0]) {
				DNAMatrix[0][0].set(0, 1);
				DNAMatrix[0][0].add(1);
				DNAMatrix[0][0].add(0);
				DNAMatrix[0][0].add(0);
				DNAMatrix[0][0].add(0);
				DNAMatrix[0][0].add(0);
			} else {
				DNAMatrix[0][0].set(0, 0);
				DNAMatrix[0][0].add(0);
			}
			return;
		}

		// 递推公式1
		if (endA == endB) {

			// 判定F(0,endA,0,endB-1)是否有一个解为A[0,endA-1],B[0,endB-1]
			// 最终判定A[0,endA],B[0,endB]是否为F(0,endA,0,endB-1)的解
			boolean condition = false;
			if (DNAMatrix[endA][endB - 1].get(1) == endB && A[endA] == B[endB]) {
				if (DNAMatrix[endA][endB - 1].get(0) == 1) {
					if (DNAMatrix[endA][endB - 1].get(2) == DNAMatrix[endA][endB - 1].get(4) && DNAMatrix[endA][endB - 1].get(3) == DNAMatrix[endA][endB - 1].get(5)) {
						condition = true;
					}
				} else if (DNAMatrix[endA][endB - 1].get(0) == 2) {
					boolean subConditon1 = DNAMatrix[endA][endB - 1].get(2) == DNAMatrix[endA][endB - 1].get(4) && DNAMatrix[endA][endB - 1].get(3) == DNAMatrix[endA][endB - 1].get(5);
					boolean subConditon2 = DNAMatrix[endA][endB - 1].get(6) == DNAMatrix[endA][endB - 1].get(8) && DNAMatrix[endA][endB - 1].get(7) == DNAMatrix[endA][endB - 1].get(9);
					if (subConditon1 || subConditon2) {
						condition = true;
					}
				}
			}

			// A[0,endA],B[0,endB]是F(0,endA,0,endB-1)的解
			if (condition) {

				DNAMatrix[endA][endB].set(0, 1);
				DNAMatrix[endA][endB].add(endA + 1);
				DNAMatrix[endA][endB].add(0);
				DNAMatrix[endA][endB].add(endA);
				DNAMatrix[endA][endB].add(0);
				DNAMatrix[endA][endB].add(endB);
				return;

			// A[0,endA],B[0,endB]不是F(0,endA,0,endB-1)的解
			} else {

				// 求解对应f()
				ArrayList<Integer> list = null;
				if (DNAMatrix[endA][endB - 1].get(1) <= 0) {
					list = subDNACompare2(0, endA, endB, endB, 1);
				} else {
					list = subDNACompare2(0, endA, endB - DNAMatrix[endA][endB - 1].get(1), endB, 1);
				}

				// 对应的f()的解为空
				if (list == null) {

					DNAMatrix[endA][endB].set(0, DNAMatrix[endA][endB - 1].get(0));
					DNAMatrix[endA][endB].add(DNAMatrix[endA][endB - 1].get(1));
					for (int i = 2; i < DNAMatrix[endA][endB - 1].size(); i++) {
						DNAMatrix[endA][endB].add(DNAMatrix[endA][endB - 1].get(i));
					}
					return;

				// 对应的f()的解不为空
				} else {

					// 对应的f()的解的长度 == F()解的长度
					if (list.get(1) == DNAMatrix[endA][endB - 1].get(0)) {

						DNAMatrix[endA][endB].set(0, DNAMatrix[endA][endB - 1].get(0) + list.get(0));
						DNAMatrix[endA][endB].add(list.get(1));
						for (int i = 2; i < DNAMatrix[endA][endB - 1].size(); i++) {
							DNAMatrix[endA][endB].add(DNAMatrix[endA][endB - 1].get(i));
						}
						for (int i = 2; i < list.size(); i++) {
							DNAMatrix[endA][endB].add(list.get(i));
						}
						return;

					// 对应的f()的解的长度 > F()解的长度
					} else if (list.get(1) > DNAMatrix[endA][endB - 1].get(0)) {

						DNAMatrix[endA][endB].set(0, list.get(0));
						DNAMatrix[endA][endB].add(list.get(1));
						for (int i = 2; i < list.size(); i++) {
							DNAMatrix[endA][endB].add(list.get(i));
						}
						return;

					}

				}

			}

		}

		// 递推公式2
		if (endA == endB + 1) {

			// 求解对应f()
			ArrayList<Integer> list = null;
			if (DNAMatrix[endA - 1][endB].get(1) <= 0) {
				list = subDNACompare2(endA, endA, 0, endB, 0);
			} else {
				list = subDNACompare2(endA - DNAMatrix[endA - 1][endB].get(1), endA, 0, endB, 0);
			}

			// 对应的f()的解为空
			if (list == null) {

				DNAMatrix[endA][endB].set(0, DNAMatrix[endA - 1][endB].get(0));
				DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(1));
				for (int i = 2; i < DNAMatrix[endA - 1][endB].size(); i++) {
					DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(i));
				}
				return;

			// 对应的f()的解不为空
			} else {

				// 对应的f()的解的长度 == F()解的长度
				if (list.get(1) == DNAMatrix[endA - 1][endB].get(1)) {

					DNAMatrix[endA][endB].set(0, DNAMatrix[endA - 1][endB].get(0) + list.get(0));
					DNAMatrix[endA][endB].add(list.get(1));
					for (int i = 2; i < DNAMatrix[endA - 1][endB].size(); i++) {
						DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(i));
					}
					for (int i = 2; i < list.size(); i++) {
						DNAMatrix[endA][endB].add(list.get(i));
					}
					return;

				// 对应的f()的解的长度 > F()解的长度
				} else if (list.get(1) > DNAMatrix[endA - 1][endB].get(1)) {

					DNAMatrix[endA][endB].set(0, list.get(0));
					DNAMatrix[endA][endB].add(list.get(1));
					for (int i = 2; i < list.size(); i++) {
						DNAMatrix[endA][endB].add(list.get(i));
					}
					return;

				}

			}

		}

		// 递推公式3
		if (endA > endB + 1) {

			// 求解对应f()
			ArrayList<Integer> list = null;
			if (DNAMatrix[endA - 1][endB].get(1) <= 0) {
				list = subDNACompare2(endA, endA, 0, endB, 0);
			} else {
				list = subDNACompare2(endA - DNAMatrix[endA - 1][endB].get(1), endA, 0, endB, 0);
			}

			// 对应的f()的解为空
			if (list == null) {

				DNAMatrix[endA][endB].set(0, DNAMatrix[endA - 1][endB].get(0));
				DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(1));
				for (int i = 2; i < DNAMatrix[endA - 1][endB].size(); i++) {
					DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(i));
				}
				return;

			// 对应的f()的解不为空
			} else {

				// 对应的f()的解的长度 == F()解的长度
				if (list.get(1) == DNAMatrix[endA - 1][endB].get(0)) {

					DNAMatrix[endA][endB].set(0, DNAMatrix[endA - 1][endB].get(0) + list.get(0));
					DNAMatrix[endA][endB].add(list.get(1));
					for (int i = 2; i < DNAMatrix[endA - 1][endB].size(); i++) {
						DNAMatrix[endA][endB].add(DNAMatrix[endA - 1][endB].get(i));
					}
					for (int i = 2; i < list.size(); i++) {
						DNAMatrix[endA][endB].add(list.get(i));
					}
					return;

				// 对应的f()的解的长度 > F()解的长度
				} else if (list.get(1) > DNAMatrix[endA - 1][endB].get(0)) {

					DNAMatrix[endA][endB].set(0, list.get(0));
					DNAMatrix[endA][endB].add(list.get(1));
					for (int i = 2; i < list.size(); i++) {
						DNAMatrix[endA][endB].add(list.get(i));
					}
					return;

				}

			}

		}

	}

	public ArrayList<Integer> subDNACompare2(int starta, int enda, int startb, int endb, int type) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(0);

		// 为在B[startb,endb]中找出所有等于A[starta,enda]的子序列
		if (type == 0) {

			list.add(enda - starta + 1);
			for (int i = startb; i <= endb - enda + starta; i++) {

				if (B[i] != A[starta]) {

					continue;

				} else {

					for (int j = 0; j <= enda - starta; j++) {
						if (B[i + j] != A[starta + j]) {
							break;
						}
						// 添加一个解
						if (j == enda - starta) {
							list.set(0, list.get(0) + 1);
							list.add(starta);
							list.add(enda);
							list.add(i);
							list.add(i + enda - starta);
						}

					}

				}

			}

			// 为在A[starta,enda]中找出所有等于B[startb,endb]的子序列
		} else {

			list.add(endb - startb + 1);
			for (int i = starta; i <= enda - endb + startb; i++) {

				if (A[i] != B[startb]) {

					continue;

				} else {

					for (int j = 0; j <= endb - startb; j++) {
						if (A[i + j] != B[startb + j]) {
							break;
						}
						// 添加一个解
						if (j == endb - startb) {
							list.set(0, list.get(0) + 1);
							list.add(i);
							list.add(i + endb - startb);
							list.add(startb);
							list.add(endb);
						}
					}

				}

			}

		}

		// 返回数据
		if (list.get(0) == 0) {
			return null;
		} else {
			return list;
		}

	}
	
	/**
	 * 
	 * 关于最优子结构搜索二叉树
	 * 输入：值为{K0_P0,K1_P1,K2_P2,****Ki_Pi*****,Kn-1_Pn-1},其中ki代表值且逐渐增加，Pi代表统计概率,共n个元素
	 * 输出：将以上n个元素，组成一个最优搜索二叉树，且要求所有元素的被搜索的期望值的总和最小
	 * 设：F(start,end)为把Kstart到Kend共计n个元素，为最优搜索二叉树
	 * 设：f(start,root,end)为在Kstart到Kstart+n-1共计n个元素中，选取start+root处的元素为根节点时，最优搜索二叉树的期望值
	 * F(start,end)=Min(f(start,start+0,end),f(start,start+1,end),f(start,start+2,end),******,f(start,end,end))
	 * f(start,root,end)=F(start,root-1) + F(root+1,end)
	 * 这是一个比较浩瀚的工程问题,我暂时没有实现它的打算
	 * 
	 * @param args
	 */

	/**
	 * 
	 * 最大回文串问题
	 * 在给定字符串A中找到最长的回文字符串，回文串是指这个字符串无论从左读还是从右读，所读的顺序是一样的
	 * 设F(start,end)为找出从start到end之间的最长的回文字符串
	 * F(start,end)可以被分解为以下几种子问题
	 * F(start,end)=A[start,end],当A[start]==A[end],且F(start+1,end-1)=A[start+1,end-1]
	 * F(start,end)=Max(F(start+1,end),F(start,end-1))
	 * 算法的复杂度为n*n(从解的空间来看)
	 * 
	 * 注意F(start,end)有多解时，需要把多个解都返回
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> palindrome(String s) {

		// 初始化解空间
		palindromeMatrix = new ArrayList[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < s.length(); j++) {
				palindromeMatrix[i][j] = new ArrayList<Integer>();
				palindromeMatrix[i][j].add(-1);
			}
		}

		// 递归求解
		return subPalindrome(s, 0, s.length() - 1);

	}

	public ArrayList<Integer> subPalindrome(String s, int start, int end) {

		// 是否已经计算过
		if (palindromeMatrix[start][end].get(0) != -1) {
			return palindromeMatrix[start][end];
		}

		// 递归结束条件
		if (start == end) {
			palindromeMatrix[start][end].set(0, 1);
			palindromeMatrix[start][end].add(1);
			palindromeMatrix[start][end].add(start);
			palindromeMatrix[start][end].add(end);
			return palindromeMatrix[start][end];
		}

		// 当A[start]==A[end],且F(start+1,end-1)=A[start+1,end-1]
		if (s.charAt(start) == s.charAt(end)) {

			if (start == end - 1) {
				palindromeMatrix[start][end].set(0, 1);
				palindromeMatrix[start][end].add(2);
				palindromeMatrix[start][end].add(start);
				palindromeMatrix[start][end].add(end);
				return palindromeMatrix[start][end];
			}

			// F(start+1,end-1)
			if (palindromeMatrix[start + 1][end - 1].get(0) == -1) {
				subPalindrome(s, start + 1, end - 1);
			}

			if (palindromeMatrix[start + 1][end - 1].get(0) == 1) {
				if (palindromeMatrix[start + 1][end - 1].get(1) == end - start - 1) {
					palindromeMatrix[start][end].set(0, 1);
					palindromeMatrix[start][end].add(end - start + 1);
					palindromeMatrix[start][end].add(start);
					palindromeMatrix[start][end].add(end);
					return palindromeMatrix[start][end];
				}
			}

		}

		// 当A[start]!=A[end]
		// F(start+1,end)
		if (palindromeMatrix[start + 1][end].get(0) == -1) {
			subPalindrome(s, start + 1, end);
		}
		// F(start,end-1)
		if (palindromeMatrix[start][end - 1].get(0) == -1) {
			subPalindrome(s, start, end - 1);
		}

		// 根据F(start+1,end)和F(start,end-1)处理F(start,end)
		if (palindromeMatrix[start + 1][end].get(1) > palindromeMatrix[start][end - 1].get(1)) {

			palindromeMatrix[start][end].set(0, palindromeMatrix[start + 1][end].get(0));
			for (int i = 1; i < palindromeMatrix[start + 1][end].size(); i++) {
				palindromeMatrix[start][end].add(palindromeMatrix[start + 1][end].get(i));
			}
			return palindromeMatrix[start][end];

		} else if (palindromeMatrix[start + 1][end].get(1) == palindromeMatrix[start][end - 1].get(1)) {

			palindromeMatrix[start][end].set(0,
					palindromeMatrix[start + 1][end].get(0) + palindromeMatrix[start][end - 1].get(0));
			for (int i = 1; i < palindromeMatrix[start + 1][end].size(); i++) {
				palindromeMatrix[start][end].add(palindromeMatrix[start + 1][end].get(i));
			}
			for (int i = 2; i < palindromeMatrix[start][end - 1].size(); i++) {
				palindromeMatrix[start][end].add(palindromeMatrix[start][end - 1].get(i));
			}
			return palindromeMatrix[start][end];

		} else {

			palindromeMatrix[start][end].set(0, palindromeMatrix[start][end - 1].get(0));
			for (int i = 1; i < palindromeMatrix[start][end - 1].size(); i++) {
				palindromeMatrix[start][end].add(palindromeMatrix[start][end - 1].get(i));
			}
			return palindromeMatrix[start][end];

		}

	}

	public static void main(String[] args) {

		DynamicProgramming dynamicProgramming = new DynamicProgramming();

		// System.out.println(dynamicProgramming.rodCut1(11));

		/*int[] cutResult = dynamicProgramming.rodCut2(11);
		for (int i : cutResult) {
			System.out.print(i + "\t");
		}*/

		/*ArrayList<Integer> list = dynamicProgramming.palindrome("abacdaba");
		System.out.println(list.get(0));
		System.out.println(list.get(1));*/
		
		int[] a = {1,0,0,1,4};
		int[] b = {0,0,1};
		
		ArrayList<Integer> list = dynamicProgramming.DNACompare(a,b);
		System.out.println(list.toString());
		
	}

}
