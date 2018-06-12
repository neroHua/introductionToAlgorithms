package chapter19;

/**
 * 
 * 数据结构:斐波那契堆
 * 
 * 由一系列的节点组成
 * 每个节点包含的由5部分组成
 * 1.当前节点的值
 * 2.指向当前节点父节点变量
 * 3.指向当前节点其中一个的子节点的变量
 * 4.指向当前节点的左兄弟节点的变量
 * 5.指向当前节点的右兄弟节点的变量
 * 
 * 注意:斐波那契堆
 * 1.父节点的值小于任意子节点的值
 * 2.子节点是一个闭合的双向链表
 * 3.兄弟节点是无序的
 * 
 * 一种复合结构的数据结构，由于结构复杂，且，仅在基本没有删除的情况下会有比较高的效率，限制了它的应用
 * 该数据结构，仅具备理论价值
 * 
 * 由于其应用并不广泛，仅具备指导意义，仅了解即可
 * 
 * @author 滑德友
 * 2018年6月12日13:47:08
 *
 */
public class FibonacciHeap {

	FibonacciHeapNode root;
	
	/**
	 * 
	 * 生成一个斐波那契堆
	 * 
	 * @param array
	 */
	public void makeFibonacciHeap(int[] array) {
		
	}
	
	/**
	 * 
	 * 插入一个值
	 * 
	 * @param value
	 */
	public void insert(int value) {
		
	}
	
	/**
	 * 
	 * 返回最小值
	 * 
	 */
	public void min() {
		
	}
	
	/**
	 * 
	 * 删除最小值
	 * 
	 */
	public void removeMin() {
		
	}
	
	/**
	 * 
	 * 降低指定node的值
	 * 
	 * @param node
	 * @param value
	 */
	public void decreaseValue(FibonacciHeapNode node,int value) {
		
	}
	
	/**
	 * 
	 * 删除一个值
	 * 
	 * @param value
	 */
	public void remove(int value) {
		
	}
	
	/**
	 * 
	 * 合并两个斐波那契堆
	 * 
	 * @param heap1
	 * @param heap2
	 */
	public void merge(FibonacciHeap heap1,FibonacciHeap heap2) {
		
	}
}
