package chapter13;

/**
 * 
 * 数据结构：红黑树(一种特殊的二叉查找树)
 * 
 * 由一系列的节点组成
 * 每个节点由五个部分组成:
 * 1.指向父节点地址的变量
 * 2.当前节点的值
 * 3.指向左子节点地址的变量
 * 4.指向右子节点地址的变量
 * 5.当前节点的颜色（红色0或者黑色1）
 * 
 * 注意:红黑树要求
 * 1.所有及节点的颜色只能是红色或者是黑色
 * 2.根节点的颜色为黑色。
 * 3.每个叶节点的子节点黑色（叶节点是指没有子节点的节点）
 * 4.每个红色的节点的子节点为黑色
 * 5.任意节点到其每个叶节点的所有路径上都包含相同数目的黑色节点
 * 
 * 注意：对红黑树要求的解释
 * a.第4点和第5点决定了n个节点的红黑树的最大高度为2lg（n+1）由n >= 2^（根节点到叶节点的黑色节点个数） - 1 得出 和 根节点到叶节点的黑色节点个数 >= h/2
 * b.根节点的为黑色，在修改根节点的子节点，不用修改根节点。
 * 
 * 常用的一种数据结构
 * 基本上所有的操作都跟树的高度有关，上面的性质保证了树的平衡性，高度接近lgn
 * 
 * @author 滑德友
 * @since 2018年5月8日14:48:59
 * 
 */
public class RedBlackTree {

	RedBlackTreeNode root;
	
	/**
	 * 
	 * 创建一个空的二叉查找树
	 * 
	 */
	public RedBlackTree() {

		root = null;

	}

	/**
	 * 
	 * 增加根节点
	 * 
	 * @param value
	 *            根节点值
	 * 
	 */
	public void addRootNode(int value) {

		if (root != null) {
			return;
		}

		RedBlackTreeNode node = new RedBlackTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = null;
		node.color = 1;
		
		root = node;

	}

	/**
	 * 
	 * 修改根节点
	 * 
	 * @param value
	 *            新的根节点的值
	 */
	public void updateRootNode(int value) {

		if (root == null) {
			return;
		}

		root.value = value;

	}

	/**
	 * 
	 * 获得根节点的值
	 * 
	 * @return 根节点
	 */
	public RedBlackTreeNode getRootNode() {

		return root;

	}

	/**
	 * 
	 * 删除根节点
	 * 
	 */
	public void deleteRootNode() {

		root = null;

	}

	/**
	 * 
	 * 修改节点
	 * 
	 * @param node
	 *            被修改的节点
	 * @param value
	 *            新的节点值
	 */
	public void updateNode(RedBlackTreeNode node, int value) {

		node.value = value;

	}

	/**
	 * 
	 * 删除节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void deleteNodeTree(RedBlackTreeNode node) {

		if (node.equals(root)) {
			root = null;
		} else {

			if (node.parent.left.equals(node)) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}

			node = null;

		}

	}

	/**
	 * 
	 * 根据值来获得父节点下对应的节点
	 * 
	 * @param parent
	 *            父节点
	 * @param value
	 *            节点值
	 * @return 节点
	 */
	public RedBlackTreeNode getNodeByValue1(RedBlackTreeNode parent, int value) {

		// 非法输入
		if (parent == null) {
			System.out.println("父节点已经没有子节点了");
			return null;
		}

		// 如果当前节点的值等于输入值，返回当前节点
		// 如果当前节点的值大于输入值，向左子树查找
		// 如果当前节点的值小于输入值，向右子树查找
		if (parent.value == value) {
			return parent;
		} else if(parent.value > value){

			// 向左查找节点
			return getNodeByValue1(parent.left, value);

		}else{
			
			// 向右查找节点
			return getNodeByValue1(parent.right, value);

		}

	}

	/**
	 * 
	 * 根据值来获得父节点下对应的节点
	 * 
	 * @param parent
	 *            父节点
	 * @param value
	 *            节点值
	 * @return 节点
	 */
	public RedBlackTreeNode getNodeByValue2(RedBlackTreeNode parent, int value) {

		// 如果当前节点的值等于输入值，返回当前节点
		// 如果当前节点的值大于输入值，向左子树查找
		// 如果当前节点的值小于输入值，向右子树查找
		while (parent != null) {
			if (parent.value == value) {
				return parent;
			} else if (parent.value > value) {
				// 向左查找节点
				parent = parent.left;
			} else {
				// 向右查找节点
				parent = parent.right;
			}
		}

		return null;

	}
	/**
	 * 
	 * 递归方式遍历二叉树
	 * 
	 * @param node
	 *            根节点
	 */
	public void showMyself1(RedBlackTreeNode node) {

		if (node == null) {
			return;
		} else {

			// 前序遍历
			// System.out.print(node.value);
			// showMyself1(node.left);
			// showMyself1(node.right);

			// 中序遍历
			showMyself1(node.left);
			System.out.print("(" + node.value + "," + node.color + "),");
			showMyself1(node.right);

			// 后续序遍历
			//showMyself1(node.left);
			//showMyself1(node.right);
			//System.out.print(node.value + ",");

		}

	}
	
	/**
	 * 
	 * 对当前节点进行左旋操作
	 * 要求当前节点的右节点不能为空，需要用右节点替换当前节点
	 * 
	 * @param node
	 *            当前节点
	 */
	public void leftRotate(RedBlackTreeNode node) {

		// 非法输入
		if (node == null || node.right == null) {
			return;
		}

		RedBlackTreeNode right = node.right;

		// 将右节点的左节点移动到当前节点的右节点
		if (right.left != null) {
			node.right = right.left;
			right.left.parent = node;
		} else {
			node.right = null;
		}

		// 将右节点放到当前节点的位置上
		if (node.parent != null) {

			if (node.equals(node.parent.left)) {
				node.parent.left = right;
			} else {
				node.parent.right = right;
			}
			right.parent = node.parent;

		} else {

			right.parent = null;
			node.parent = right;
			root = right;
		}

		// 将右节点作为当前节点的父节点
		node.parent = right;
		right.left = node;

	}

	/**
	 * 
	 * 对当前节点进行右旋操作
	 * 要求当前节点的左节点不能为空，需要用左节点替换当前节点
	 * 
	 * @param node
	 *            当前节点
	 */
	public void rightRotate(RedBlackTreeNode node) {

		// 非法输入
		if (node == null || node.right == null) {
			return;
		}

		RedBlackTreeNode left = node.left;

		// 将左节点的右节点移动到当前节点的左节点
		if (left.right != null) {
			node.left = left.right;
			left.right.parent = node;
		} else {
			node.left = null;
		}

		// 将左节点放到当前节点的位置上
		if (node.parent != null) {

			if (node.equals(node.parent.left)) {
				node.parent.left = left;
			} else {
				node.parent.right = left;
			}
			left.parent = node.parent;

		} else {

			left.parent = null;
			node.parent = left;
			root = left;

		}

		// 将左节点作为当前节点的父节点
		node.parent = left;
		left.right = node;

	}

	/**
	 * 
	 * 插入一个节点
	 * 
	 * @param parent
	 *            根节点
	 * @param value
	 *            节点的值
	 */
	public void insertNode(RedBlackTreeNode parent, int value) {

		// 非法输入
		if (parent == null) {
			return;
		}

		// 新建一个节点，注意颜色为红色，红色有可能违反第4条，黑色一定违反第5条
		RedBlackTreeNode node = new RedBlackTreeNode();
		node.value = value;
		node.parent = null;
		node.left = null;
		node.right = null;
		node.color = 0;
		
		// 循环找到一个空位置插入
		while (true) {

			// 向右走
			if (value > parent.value) {

				// 右节点不空，向右走
				// 右节点为空，插入
				if (parent.right != null) {
					parent = parent.right;
				} else {
					node.parent = parent;
					parent.right = node;
					break;
				}

			// 向左走
			} else {

				// 左节点不空，向左走
				// 左节点为空，插入
				if (parent.left != null) {
					parent = parent.left;
				} else {
					node.parent = parent;
					parent.left = node;
					break;
				}

			}

		}
		
		// 插入后，如果违反规性质4，需要修改
		subInsertNode(node);

	}
	
	public void subInsertNode(RedBlackTreeNode node){
		
	}
	
	public static void main(String[] args) {
		
		RedBlackTree redBlackTree = new RedBlackTree();
		
		redBlackTree.addRootNode(8);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 4);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 12);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 2);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 6);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 10);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 14);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 1);
		//redBlackTree.insertNode(redBlackTree.getRootNode(), 3);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 5);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 7);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 9);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 11);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 13);
		redBlackTree.insertNode(redBlackTree.getRootNode(), 15);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println();
		RedBlackTreeNode node = redBlackTree.getNodeByValue1(redBlackTree.root, 12);
		redBlackTree.rightRotate(node);
		// redBlackTree.leftRotate(node);
		redBlackTree.showMyself1(redBlackTree.root);

	}

}
