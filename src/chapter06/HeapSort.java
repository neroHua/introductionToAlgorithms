package chapter06;

/**
 * 堆排序
 * 
 * 1.把要排序的数组A（n个元素）建成一个MaxHeap（父节点值 >= 子节点值）
 * 
 * 2.交换根节点A[0]和A[n-1],于是A[n-1]是个最大值
 * 3.这样A[0]-A[n-2]就不在是一个MaxHeap了，需要对A[0]-A[n-2]重新排序成一个MaxHeap
 * 
 * 4.重复以上两步，直到重新排序的堆的长度为2
 * 
 * 1.复杂度为n 2.复杂度为1 3.复杂度为lgn 4.复杂度为n 算法的复杂度为nlgn
 * 
 * @author 滑德友
 * @time 2018年4月24日08:00:30
 *
 */
public class HeapSort {

	public static void main(String[] args) {

		int[] heap = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		maxHeapSort(heap, heap.length - 1);

		for (int i : heap) {
			System.out.print(i);
		}
		System.out.println();

		minHeapSort(heap, heap.length - 1);

		for (int i : heap) {
			System.out.print(i);
		}
		System.out.println();
	}

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

	/**
	 * MaxHeap排序
	 * 
	 * 1.把要排序的数组A（n个元素）建成一个MaxHeap（父节点值 >= 子节点值）
	 * 
	 * 2.交换根节点A[0]和A[n-1],于是A[n-1]是个最大值
	 * 3.这样A[0]-A[n-2]就不在是一个MaxHeap了，需要对A[0]-A[n-2]重新排序成一个MaxHeap
	 * 
	 * 4.重复以上两步，直到重新排序的堆的长度为2
	 * 
	 * 1.复杂度为n 2.复杂度为1 3.复杂度为lgn 4.复杂度为n 算法的复杂度为nlgn
	 * @param heap 要排序的堆
	 * @param end 堆的尾节点下标
	 */
	public static void maxHeapSort(int[] heap, int end) {

		// 非法输入
		if (end > heap.length) {
			return;
		}

		// 建立MaxHeap
		buildMaxHeap(heap);

		for (int i : heap) {
			System.out.print(i);
		}
		System.out.println();

		// 递归取最大值
		for (int i = end; i >= 1; i--) {

			int temp = heap[i];
			heap[i] = heap[0];
			heap[0] = temp;
			buildSubMaxHeap(heap, 0, i - 1);

		}

	}

	public static void buildSubMinHeap(int[] heap, int parent, int end) {

		// 非法输入
		if (parent > end || parent < 0) {
			return;
		}

		// 父子节点坐标
		int left = parent * 2 + 1;
		int right = parent * 2 + 2;
		int smallest = parent;

		// 在父子节点中查找最小值得下标
		if (left <= end && heap[left] < heap[parent]) {
			smallest = left;
		}

		if (right <= end && heap[right] < heap[smallest]) {
			smallest = right;
		}

		// 根据结果返回
		// 最小值不是父节点下标是，父节点获得最小值，对获得父节点的值的子节点递归
		// 最小值是父节点下标时，返回
		if (smallest != parent) {
			int temp = heap[smallest];
			heap[smallest] = heap[parent];
			heap[parent] = temp;
			buildSubMaxHeap(heap, smallest, end);
		} else {
			return;
		}

	}

	public static void buildMinHeap(int[] heap) {

		// 非法输入
		if (heap.length < 2) {
			return;
		}

		// 从下往上，从左往右，把每个有子节点的父节点变成MinHeap
		for (int i = heap.length / 2 - 1; i >= 0; i--) {
			buildSubMinHeap(heap, i, heap.length - 1);
		}

	}

	public static void minHeapSort(int[] heap, int end) {

		// 非法输入
		if (end > heap.length) {
			return;
		}

		// 建立minHeap
		buildMinHeap(heap);

		for (int i : heap) {
			System.out.print(i);
		}
		System.out.println();

		// 递归取最小值
		for (int i = end; i >= 1; i--) {

			int temp = heap[i];
			heap[i] = heap[0];
			heap[0] = temp;
			buildSubMinHeap(heap, 0, i - 1);

		}

	}

}
