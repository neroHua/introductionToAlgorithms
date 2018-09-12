package chapter10;

/**
 * 
 * 数据结构：栈 
 * 永远只对栈顶的元素进行操作，特点是：先进先出(last in first out LIFO)
 * 
 * 仅有两种可执行的操作 
 * 1.压栈:把数据放入栈顶(push)
 * 2.弹栈:把数据从栈顶取出(pop)
 * 
 * 现在使用数组来实现一个栈
 * 
 * @author 滑德友
 * @since  2018年4月28日15:07:08
 *
 */
public class Stack {

	public int stackPointer;
	public int array[];

	/**
	 * 
	 * 构造一个指定长度的栈
	 * 
	 * @param length
	 *            栈的长度
	 */
	public Stack(int length) {

		// 超过1024或者小于0时变成100
		if (length < 0 || length > 1024) {
			length = 100;
		}
		
		// 初始化内部成员
		stackPointer = -1;
		array = new int[length];

	}

	/**
	 * 
	 * 压栈
	 * 
	 * @param element
	 *            压入的元素
	 */
	public void push(int element) {

		// 非法输入
		if (stackPointer >= array.length - 1) {
			System.out.println("stackOverFlow");
			return;
		}

		// 修改指针和栈顶
		stackPointer++;
		array[stackPointer] = element;

	}

	/**
	 * 
	 * 弹出
	 * 
	 * @return 当前栈顶元素
	 */
	public int pop() {

		// 非法输入
		if (stackPointer <= -1) {
			System.out.println("stackUnderFlow");
			return 0;
		}

		// 修改指针和栈顶
		stackPointer--;
		return array[stackPointer + 1];

	}

	/**
	 * 
	 * 栈是否为空
	 * 
	 * @return 空：true；非空：false
	 */
	public boolean isEmpty() {

		if (stackPointer == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 栈是否已满
	 * 
	 * @return 满：true；不满：false
	 */
	public boolean isFull() {

		if (stackPointer == array.length - 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * 当前栈内元素的个数
	 * 
	 * @return 当前栈内元素的个数
	 */
	public int currentElementCout() {

		return stackPointer + 1;

	}

	/**
	 * 
	 * 栈的大小
	 * 
	 * @return 栈的大小
	 */
	public int size() {

		return array.length;

	}

	public static void main(String[] args) {

		Stack stack = new Stack(2);

		stack.push(1);
		stack.push(2);

		System.out.println(stack.size());
		System.out.println(stack.currentElementCout());

		System.out.println(stack.pop() + "\t" + stack.pop());

		System.out.println(stack.size());
		System.out.println(stack.currentElementCout());

	}

}
