package chapter06;

/**
 * 堆是一种完全的二叉树
 * 每个节点都有0-2个子节点
 * 当父节点的值最大时，为最大堆，所有节点都如此
 * 当父节点的值最小时，为最小堆，所有节点都如此
 * 
 * 当堆按照从上到下，从左至右组成数组时下标有如下关系
 * parent = i
 * left = 2 * i + 1
 * right = 2 * i + 2
 * 如堆结构									1
 * 							2								3
 * 					4				5				6				7
 * 				8		9		10		11		12		13	
 * 
 * 数组结构{1，2，3，4，5，6，7，8，9，10，11，12，13}
 *  下标为：0，1，2，3，4，5，6，7，8， 9，10，11，12
 * @author 滑德友
 * @time 2018年4月24日17:23:14
 *
 */
public class HeapNode {

	int parentIndex;
	int leftIndex;
	int rightIndex;
	
	int parentValue;
	int leftValue;
	int rightValue;
	
	public HeapNode(int parentIndex){

		this.parentIndex = parentIndex;
		this.leftIndex = 2 * parentIndex + 1;
		this.rightIndex = 2 * parentIndex + 2;
	}

}
