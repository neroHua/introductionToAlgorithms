package chapter06;

/**
 * 把一个堆变成最大堆的一种算法
 * 
 * 先比较父节点与一个的节点，如果父节点大，则交换 后比较父节点与另外一个节点，如果父节点大，则交换
 * 
 * 递归进行以上两步，直到比较完所有父节点为止
 * 
 * 
 * @author 算法的复杂度为 aN+b
 *
 */
public class MinHeapIFY {

	public void maxHeapIFY(int[] heap) {

		MaxHeapNode(heap, 0);

	}

	public void MaxHeapNode(int[] heap, int parent) {

		int left = parent * 2;
		int right = parent * 2 + 1;
		int largest = parent;

		if (heap[parent] > heap[left] && left < heap.length) {

			largest = left;

		}

		if (heap[largest] > heap[right] && right < heap.length) {

			largest = right;

		}

		if (largest != parent) {

			int temp = heap[parent];
			heap[parent] = heap[largest];
			heap[largest] = temp;

		}

		MaxHeapNode(heap, left);
		MaxHeapNode(heap, right);

	}

}
