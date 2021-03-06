package chapter16;

/**
 * 
 * 贪心算法
 * 将大问题分解成多个阶段的多个子问题
 * 在每个阶段总是选择其中一个子问题的解，作为当前阶段的解（而不是通过比较所有子问题的解，然后在选择最优解作为当前问题的解）
 * 且认为多个阶段的解合并起来等于大问题的解
 * 
 * 解决问题的步骤如下：
 * 1.将大问题拆分成多阶段的多个子问题
 * 2.在每一阶段选择一个子问题作为当前阶段的解
 * 3.递归解决其他阶段的问题
 * 4.解决原问题
 * 
 * 应用贪心算法的三个必要条件：
 * 1.要求大问题和多个阶段的问题是等价的
 * 2.每个阶段跟其他阶段是相互独立，没有相互影响（否则，大问题有可能不能直接等于多阶段解的相叠加）
 * 3.要求每个阶段总是能进行贪婪的选择
 * 
 * 贪心算法跟动态规划跟试探回溯有非常微妙的关系
 * 
 * @author 滑德友
 * @since 2018年6月4日16:12:54
 *
 */
public class GreedyAlgorithm {

	/**
	 * 
	 * 任务调度问题
	 * 给定一个任务时刻表，上面标有多个任务，每个任务发生时间跟结束时间都已经被标明，如
	 * 起始时间start={1,3,0,5,3,5,6,8,8,2,12} 结束时间end={4,5,6,7,8,9,10,11,12,14,16}
	 * 如任务0开始于时间1,结束于时间4，任务1开始于时间3,结束于时间5
	 * 同一时间只能进行一个任务,求给定时间[1,16]内最多能进行多个任务，各个任务都是什么？
	 * 
	 * 对于此种问题可以使用动态规划
	 * 第一阶段可以选择任务0，或者任务1，或者任务2，或者任务3，等等多个任务
	 * 然后第二阶段根据第一阶段在选择
	 * 最终通过比较所有可能的情况，得到正确的解
	 * 
	 * 如果使用贪婪算法
	 * 每次总选则持续时间最短，且不与当前任务冲突的任务
	 * 最终每个阶段的选择就是最终结果如(3,5),(5,7),(8,11),(12,16)
	 * 
	 * 显然这里的贪婪算法有可能会出错,因为每个阶段会相互影响,每次的贪婪选择也未必是正确的
	 * 
	 * @param schedule
	 */
	public void activitySelection(int[][] schedule){
		System.out.println("贪婪选择不一定正确");
		System.out.println("动态规划一定正确");
	}
	
	/**
	 * 
	 * 0-1背包问题
	 * 一个小偷抢劫了银行,发现了一系列的物品,这些物品有这样的规律
	 * 第i个物品价值是v(i),重量是w(i),每个物品都是一个不可分割的整体,每个物品要吗装，要吗不装
	 * 现在这个小偷只有一个包,最多可以装w重量的物品
	 * 因为时间紧迫，小偷必须一直向前走，且向前选择商品，后面的商品不能再被选择
	 * 问，小偷应该如何选择物品，能保证自己的利益最大化？
	 * 
	 * 显然这个问题用动态规划是可以解的，贪婪算法得到的解不一定是最优的
	 * 
	 * 如果每个商品都是金子，且是粉末，此时就可以使用贪婪算法了
	 * 
	 * @param weight
	 * 		包裹最大承受总量
	 * @param gold
	 * 		每段金粉的重量
	 */
	public void knapsackProblem(double weight,double[] gold){
		System.out.println("可以得到的最大价值是weight或者是sum(gold)中的较小值");
	}
	
	
	/**
	 * 
	 * 霍夫曼编码是一种编码树，是一种完全二叉树，每个节点都有两个节点，是一种可变长度的编码
	 * 每个叶节点到根节点的最简单路径代表着给叶节点的编码值(如：左子节点到父节点为0,右子节点到父节点为1)
	 * 每个叶节点都有固定的频率，且要求所有叶节点的被搜索的期望值的总和最小(每个为深度*频率)
	 * 
	 * 在构造时需要生成父节点且父节点的频率为子节点的和 parentNode
	 * 在构造时需要一个minHeapPriorityQueue Q
	 * 1.每次在Q中找到两个最小频率的节点，并在Q中删除该两个节点
	 * 2.生成一个新的节点parentNode,并把上面生成的两个子节点作为parentNode的子节点
	 * 3.把parentNode插入到Q中
	 * 循环以上三步直至结束即可
	 * 
	 * 这是一个比较综合型的工程问题,我暂时没有实现它的打算
	 * 
	 * @param frequery
	 */
	public void huffmanCode(int[] frequery){
		
	}
	
	public static void main(String[] args) {

	}

}
