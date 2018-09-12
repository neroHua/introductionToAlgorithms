package chapter10;

/**
 * 
 * 数据结构：队列
 * 永远只对头和尾的元素进行操作,特点是：先进先出(first in first out LIFO)
 * 
 * 仅有两种可执行的操作 
 * 1.入队:把数据放入队列的尾部(enQueue)
 * 2.出队:把数据从队列的头部取出(deQueue)
 * 
 * 现在使用数组来实现一个队列
 * 
 * @author 滑德友
 * @since 2018年4月28日16:38:24
 *
 */
public class Queue {

	public int queueTailPointer;
	public int queueHeadPointer;
	public int queueCurrentElementCount;
	public int array[];

	/**
	 * 
	 * 构建一定长度的队列
	 * 
	 * @param length 队列的长度
	 */
	public Queue(int length) {

		// 超过1024或者小于0时变成100
		if (length < 0 || length > 1024) {
			length = 100;
		}

		// 初始化内部成员
		queueTailPointer = 0;
		queueHeadPointer = 0;
		queueCurrentElementCount = 0;
		array = new int[length];

	}

	/**
	 * 
	 * 入队
	 * 
	 * @param element
	 *            入队的元素
	 */
	public void enQueue(int element) {

		// 非法输入
		if (queueCurrentElementCount >= array.length) {
			System.out.println("queueOverFlow");
			return;
		}

		// 修改元素计数器
		queueCurrentElementCount++;

		// 修改队列
		if (queueTailPointer == array.length - 1) {
			array[queueTailPointer] = element;
			queueTailPointer = 0;
		} else {
			array[queueTailPointer] = element;
			queueTailPointer++;
		}

	}

	/**
	 * 
	 * 出队
	 * 
	 * @return 队列里最前面的元素
	 */
	public int deQueue() {

		// 非法输入
		if (queueCurrentElementCount <= 0) {
			System.out.println("queueUnderFlow");
			return 0;
		}

		// 修改元素计数器
		queueCurrentElementCount--;

		// 修改队列
		if (queueHeadPointer == array.length - 1) {
			queueHeadPointer = 0;
			return array[array.length - 1];
		} else {
			queueHeadPointer++;
			return array[queueHeadPointer - 1];

		}

	}

	/**
	 * 
	 * 队列是否为空
	 * 
	 * @return 空：true；非空：false
	 */
	public boolean isEmpty() {

		if (queueCurrentElementCount <= 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 队列是否已满
	 * 
	 * @return 满：true；不满：false
	 */
	public boolean isFull() {

		if (queueCurrentElementCount >= array.length) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 当前队列内元素的个数
	 * 
	 * @return 当前队列内元素的个数
	 */
	public int currentElementCout() {

		return queueCurrentElementCount;

	}

	public static void main(String[] args) {

		Queue queue = new Queue(3);

		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(5);

		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		queue.enQueue(4);
		queue.enQueue(5);
		queue.enQueue(6);

		System.out.println(queue.deQueue());
		queue.enQueue(6);

		System.out.println(queue.deQueue());
		queue.enQueue(6);

		System.out.println(queue.deQueue());
		queue.enQueue(6);

	}

}
