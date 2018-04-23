package chapter06;

/**
 * 堆是一种完全的二叉树
 * 每个节点都有0-2个子节点
 * 当父节点的值最大时，为最大堆，所有节点都如此
 * 当父节点的值最小时，为最小堆，所有节点都如此
 * 
 * 当堆按照中序遍历组成数组时下标有如下关系
 * parent = i
 * left = 2 * i
 * right = 2 * i + 1
 * 如堆结构									1
 * 							2								3
 * 					4				5				6				7
 * 				8		9		10		11		12		13	
 * 
 * 数组结构{1，2，3，4，5，6，7，8，9，10，11，12，13}
 *  下标为：0，1，2，3，4，5，6，7，8， 9，10，11，12
 * @author 滑德友
 *
 */
public class HeapNode {

	int parent;
	int left;
	int right;

	public HeapNode(int parement){

		this.parent = parement;
		this.left = parement * 2;
		this.right = parement * 2 + 1;

	}

	public int getLeftChildIndex(){

		return left;

	}

	public int getRightChildIndex(){

		return right;
	}

}
