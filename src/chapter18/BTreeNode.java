package chapter18;

/**
 * 
 * B树的节点
 * 
 * @author 滑德友
 * @since 2018年6月6日14:11:27
 *
 */
public class BTreeNode {

	int valueCount;
	int[] value;
	boolean leaf;
	
	BTreeNode[] childNode;
	BTreeNode parentNode;
	
}
