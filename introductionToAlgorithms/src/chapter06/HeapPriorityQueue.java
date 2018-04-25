package chapter06;

/**
 * 首先了解什么是队列 
 * 普通的队列是一种先进先出的数据结构 操作有插入元素，取出元素
 * 
 * 其次了解什么是优先级队列 
 * 是一种带有优先级的数据结构，优先级最高的元素先被访问，同等优先级则随机访问，或按照先进先出的原则访问
 * 取出最高优先级元，增加指定位置元素的优先级，插入带有优先级的元素
 * 
 * 以下用MaxHeap来实现优先级队列，并实现以上的功能
 * 说明A[i]的值就是优先级，queue的有效长度应当有个变量来维护，此处省略
 * 
 * 
 * @author 滑德友
 * @time 2018年4月25日17:43:16
 *
 */
public class HeapPriorityQueue {

	public static void main(String[] args) {

		int[] heapPriorityQueue = { 1, 2, 3, 4, 5, 6, 7, 8 };

		createHeapPriorityQueue(heapPriorityQueue);
		for (int i : heapPriorityQueue) {
			System.out.print(i);
		}
		System.out.println();

		System.out.println(getMaxPriorityElement(heapPriorityQueue));

		removeMaxPriorityElement(heapPriorityQueue, 8);
		for (int i : heapPriorityQueue) {
			System.out.print(i);
		}
		System.out.println();

		insertElement(heapPriorityQueue, 7, 9);
		for (int i : heapPriorityQueue) {
			System.out.print(i);
		}
		System.out.println();

	}

	/**
	 * 创建一个优先级队列，其实也就是创建一个MaxHeap
	 * 
	 * 复杂度为 n
	 * 
	 * @param heapPriorityQueue
	 *            被转换成队列的数组
	 */
	public static void createHeapPriorityQueue(int[] heapPriorityQueue) {

		// 创建maxHeap也就是优先级队列
		buildMaxHeap(heapPriorityQueue);

		return;

	}

	/**
	 * 获得最大优先级的元素
	 * 
	 * 复杂度为1
	 * 
	 * @param heapPriorityQueue
	 *            优先级队列
	 * @return 最大优先级的元素
	 */
	public static int getMaxPriorityElement(int[] heapPriorityQueue) {

		return heapPriorityQueue[0];

	}

	/**
	 * 移除最大优先级的元素，并把剩余的优先级最高的元素移动到首位置
	 * 
	 * 1.把最大优先级的元素删除，尾元素放到首元素的位置上。
	 * 2.对新生成的优先级队列，排序变成MaxHeap
	 * 
	 * 复杂度为lgn
	 * 
	 * @param heapPriorityQueue
	 *            优先级队列
	 * @param heapPriorityQueueSize
	 *            优先级队列元素的总个数
	 */
	public static void removeMaxPriorityElement(int[] heapPriorityQueue, int heapPriorityQueueSize) {

		// 移除最大优先级的元素
		heapPriorityQueue[0] = heapPriorityQueue[heapPriorityQueueSize - 1];
		heapPriorityQueue[heapPriorityQueueSize - 1] = 0;

		// 优先级队列总元素数-1
		heapPriorityQueueSize -= 1;

		// 重新排序
		buildSubMaxHeap(heapPriorityQueue, 0, heapPriorityQueueSize - 2);

	}

	/**
	 * 
	 * 增加指定位置的元素的优先级
	 * 
	 * 1.更换指定元素的优先级
	 * 2a.假如，被更换元素比父元素的优先级低，结束。
	 * 2b.假如，被更换元素比父元素的优先级高，则替换父元素与子元素，而后比较父元素和父元素的父元素，直到父元素为首元素。
	 * 
	 * 复杂度为lgn
	 * 
	 * @param heapPriorityQueue
	 *            优先级队列
	 * @param heapPriorityQueueSize
	 *            优先级队列元素的总个数
	 * @param index
	 *            待增加的元素的标
	 * @param priority
	 *            新的优先级
	 */
	public static void increaseElementPriority(int[] heapPriorityQueue, int heapPriorityQueueSize, int index,
			int priority) {

		// 非法输入
		if (index + 1 > heapPriorityQueueSize || priority <= heapPriorityQueue[index]) {
			return;
		}
		if (heapPriorityQueue[index] > priority) {
			return;
		}

		// 更换优先级
		heapPriorityQueue[index] = priority;

		// 父子节点初始条件
		int parent = (index - 1) / 2;
		int child = index;

		// 循环比较
		for (; parent >= 0 && child >= 0;) {

			// 假如，被更换元素比父元素的优先级高，则替换父元素与子元素，否则结束
			if (heapPriorityQueue[index] > heapPriorityQueue[parent]) {

				int temp = heapPriorityQueue[child];
				heapPriorityQueue[child] = heapPriorityQueue[parent];
				heapPriorityQueue[parent] = temp;
				parent = (parent - 1) / 2;
				child = parent;

			} else {

				break;

			}

		}

	}

	/**
	 * 
	 * 增加一个元素
	 * 
	 * 1.更换指定元素的优先级
	 * 2a.假如，被更换元素比父元素的优先级低，结束。
	 * 2b.假如，被更换元素比父元素的优先级高，则替换父元素与子元素，而后比较父元素和父元素的父元素，直到父元素为首元素。
	 *
	 * 复杂度为lgn
	 * 
	 * @param heapPriorityQueue
	 *            优先级队列
	 * @param heapPriorityQueueSize
	 *            优先级队列元素的总个数
	 * @param priority
	 *            优先级
	 */
	public static void insertElement(int[] heapPriorityQueue, int heapPriorityQueueSize, int priority) {

		// 非法输入
		if (heapPriorityQueueSize == heapPriorityQueue.length) {
			return;
		}

		// 更换优先级
		heapPriorityQueue[heapPriorityQueueSize] = priority;

		// 父子节点初始条件
		int parent = (heapPriorityQueueSize - 1) / 2;
		int child = heapPriorityQueueSize;

		// 循环比较
		for (; parent >= 0 && child >= 0;) {

			// 假如，被更换元素比父元素的优先级高，则替换父元素与子元素，否则结束
			if (heapPriorityQueue[child] > heapPriorityQueue[parent]) {

				int temp = heapPriorityQueue[child];
				heapPriorityQueue[child] = heapPriorityQueue[parent];
				heapPriorityQueue[parent] = temp;
				child = parent;
				parent = (parent - 1) / 2;

			} else {

				break;

			}

		}

		// 增加优先级队列的总元素个数
		heapPriorityQueueSize += 1;

		return;

	}

	// -----------------------------------------以下为MaxHeap--------------------------------------------------------
	/**
	 * 
	 * 将以parent为父节点，且左子堆和右子堆都已经是MaxHeap的堆变成MaxHeap
	 * 
	 * 1.比较父节点和左节点和有节点，在其中选择最大的值放入父节点中
	 * 
	 * 2a.如果，父节点的值已经就是最大的值了，那么，此时父堆就已经是MaxHeap了
	 * 2b.如果，父节点的值不是最大的值，那么，没有获得父节点的值得子堆还是MaxHeap，而获得父节点的子堆有可能不是MaxHeap
	 * 
	 * 3.每发生2b，就递归1，2；每发生2a，就返回
	 * 
	 * 递归次数等于以父节点为根节点的堆的高度，所以算法的复杂度为lgn
	 * 
	 * @param heap
	 *            要变成MaxHeap的堆
	 * @param parent
	 *            父节点下标
	 */
	public static void buildSubMaxHeap(int[] heap, int parent, int end) {

		// 非法输入
		if (parent > end || parent < 0) {
			return;
		}

		// 父子节点坐标
		int left = parent * 2 + 1;
		int right = parent * 2 + 2;
		int largest = parent;

		// 在父子节点中查找最大值得下标
		if (left <= end && heap[left] > heap[parent]) {
			largest = left;
		}
		if (right <= end && heap[right] > heap[largest]) {
			largest = right;
		}

		// 根据结果返回
		// 最大值不是父节点下标是，父节点获得最大值，对获得父节点的值的子节点递归
		// 最大值是父节点下标时，返回
		if (largest != parent) {
			int temp = heap[largest];
			heap[largest] = heap[parent];
			heap[parent] = temp;
			buildSubMaxHeap(heap, largest, end);
		} else {
			return;
		}

	}

	/**
	 * 将一个堆变成一个MaxHeap
	 * 
	 * 为所有有子节点的父节点进行buildSubMaxHeap 顺序是从右往左，从下往上，这样底层的大值就会向上层移动
	 * 
	 * 复杂度=lgn + 2lg(n/2) + 4lg(n/4)......2^lgn * lg(n/(2^lgn))=n
	 * 
	 * @param heap
	 *            要变成MaxHeap的堆
	 */
	public static void buildMaxHeap(int[] heap) {

		// 非法输入
		if (heap.length < 2) {
			return;
		}

		// 从下往上，从左往右，把每个有子节点的父节点变成MaxHeap
		for (int i = heap.length / 2 - 1; i >= 0; i--) {
			buildSubMaxHeap(heap, i, heap.length - 1);
		}

	}

}
