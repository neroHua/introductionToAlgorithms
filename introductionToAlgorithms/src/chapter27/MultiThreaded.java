package chapter27;

/**
 * 
 * 多线程算法
 * 
 * 在我看来，这一章，仅具备指导意义，基本上完全偏理论
 * 很遗憾没有看到在此章节中看到多线程任务调度器的实现
 * 很遗憾没有看到更多多线程算法的实现
 * 
 * 我认为以下几点是比较关键 并行计算的关键思想：
 * nested parallelism：并行的内部调用
 * parallel loops：并行的循环
 * 
 * 并行计算的计算模型
 * parallel model：建立在多核心运算器共享内存的并行运算模型
 * 
 * 并行计算的性能指标
 * work rule：指的是并行计算所需要的时间 >= 串行计算的时间/并行计算的并行数量
 * span rule：指的是n个并行数量的并行计算所需要的时间 >= 无穷多个并行数量的并行计算所需要的时间
 * 
 * 并行计算的中间工具 schedule：任务调度器，将执行的并行计算的代码分配到多核心运算器
 * 
 * 好在有两个例子：斐波那契数列，和归并排序 不过很遗憾java中并没有向书中的那种关键字，我只能另寻他法了
 * 
 * @author 滑德友 2018年6月20日08:23:05
 * 
 *
 */
public class MultiThreaded {

	public static void main(String[] args) {

		// 初始化静态常量
		FibonacciThread.fn = new int[10];
		FibonacciThread.fn[0] = 1;
		FibonacciThread.fn[1] = 1;
		FibonacciThread.fn[2] = 2;

		// 循环进行并行计算
		for (int i = 8; i > 2; i -= 3) {
			FibonacciThread ft = new FibonacciThread(i, true);
			ft.start();
			System.out.println("ft" + i + "start");
		}

		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("main-----" + FibonacciThread.fn[0] + "," + FibonacciThread.fn[1] + ","
				+ FibonacciThread.fn[2] + "," + FibonacciThread.fn[3] + "," + FibonacciThread.fn[4] + ","
				+ FibonacciThread.fn[5] + "," + FibonacciThread.fn[6] + "," + FibonacciThread.fn[7] + ","
				+ FibonacciThread.fn[8] + "," + FibonacciThread.fn[9]);

	}

}
