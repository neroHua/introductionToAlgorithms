package chapter12;

/**
 * 
 * 数据结构：二叉查找树
 * 
 * 由一系列的节点组成
 * 每个节点由四个部分组成:
 * 1.指向父节点地址的变量
 * 2.当前节点的值
 * 3.指向左子节点地址的变量
 * 4.指向右子节点地址的变量
 * 
 * 注意:二叉查找树要求
 * 1.当前节点的左子树的值都小于等于当前节点
 * 2.当前节点的右子树的值都大于等于当前节点
 * 
 * 常用的一种数据结构
 * 
 * @author 滑德友
 * @since 2018年5月8日14:48:59
 * 
 */
public class BinarySearchTree {

	BinarySearchTreeNode root;
	
	/**
	 * 
	 * 创建一个空的二叉查找树
	 * 
	 */
	public BinarySearchTree() {

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

		BinarySearchTreeNode node = new BinarySearchTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = null;

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
	public BinarySearchTreeNode getRootNode() {

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
	 * 根据父节点添加子节点
	 * 
	 * @param parent
	 *            父节点
	 * @param value
	 *            子节点
	 */
	public void addChildNodeByParent(BinarySearchTreeNode parent, int value) {

		// 非法输入
		if (parent.left != null && parent.right != null) {
			System.out.println("左右子树都满，不能在该父节点上增加子节点了");
			return;
		}

		// 生成新的节点
		BinarySearchTreeNode node = new BinarySearchTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = parent;

		// 添加节点
		if (parent.left == null) {
			parent.left = node;
		} else if (parent.right == null) {
			parent.right = node;
		}

	}

	/**
	 * 
	 * 根据父节点添加左节点
	 * 
	 * @param parent
	 *            父节点
	 * @param value
	 *            左节点的值
	 */
	public void addLeftChildNodeByParent(BinarySearchTreeNode parent, int value) {

		// 非法输入
		if (parent.left != null) {
			System.out.println("左子树不为空，不能增加左节点了");
			return;
		}

		// 生成新的节点
		BinarySearchTreeNode node = new BinarySearchTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;

		// 添加节点
		node.parent = parent;
		parent.left = node;

	}

	/**
	 * 
	 * 根据父节点添加右节点
	 * 
	 * @param parent
	 *            父节点
	 * @param value
	 *            右节点的值
	 */
	public void addRightChildNodeByParent(BinarySearchTreeNode parent, int value) {

		// 非法输入
		if (parent.left != null || parent.right != null) {
			System.out.println("右子树不为空，不能增加右子节点了");
			return;
		}

		// 生成新的节点
		BinarySearchTreeNode node = new BinarySearchTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;

		// 添加节点
		node.parent = parent;
		parent.right = node;

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
	public void updateNode(BinarySearchTreeNode node, int value) {

		node.value = value;

	}

	/**
	 * 
	 * 删除节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void deleteNode(BinarySearchTreeNode node) {

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
	public BinarySearchTreeNode getNodeByValue1(BinarySearchTreeNode parent, int value) {

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
	public BinarySearchTreeNode getNodeByValue2(BinarySearchTreeNode parent, int value) {

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
	public void showMyself1(BinarySearchTreeNode node) {

		if (node == null) {
			return;
		} else {

			// 前序遍历
			// System.out.print(node.value);
			// showMyself1(node.left);
			// showMyself1(node.right);

			// 中序遍历
			showMyself1(node.left);
			System.out.print(node.value + ",");
			showMyself1(node.right);

			// 后续序遍历
			//showMyself1(node.left);
			//showMyself1(node.right);
			//System.out.print(node.value + ",");

		}

	}

	/**
	 * 
	 * 查找最小的节点（最左边的节点）
	 * 
	 * @param parent
	 *            根节点
	 * @return 最小的节点
	 */
	public BinarySearchTreeNode getMinimumNode(BinarySearchTreeNode parent) {

		while (parent != null && parent.left != null) {
			parent = parent.left;
		}

		return parent;

	}

	/**
	 * 
	 * 查找最大的节点（最右边的节点）
	 * 
	 * @param parent
	 *            根节点
	 * @return 最大的节点
	 */
	public BinarySearchTreeNode getMaximumNode(BinarySearchTreeNode parent) {

		while (parent != null && parent.right != null) {
			parent = parent.right;
		}

		return parent;

	}

	/**
	 * 寻找下一个节点（顺序为中序遍历）
	 * 
	 * @param node
	 *            当前节点
	 * @return 下一个节点
	 */
	public BinarySearchTreeNode getNextNode(BinarySearchTreeNode node) {

		// 当前节点的右节点不空
		if (node != null && node.right != null) {

			// 下一个节点为当前节点的右节点的最左节点
			node = node.right;
			while (node.left != null) {
				node = node.left;
			}
			return node;

		// 当前节点的右节点为空
		} else if (node != null && node.right == null) {

			// 下一个节点的左节点是当前节点或是当前节点的父节点
			BinarySearchTreeNode parent = node.parent;
			while (parent != null && parent.right.equals(node)) {
				node = parent;
				parent = parent.parent;
			}
			return parent;

		// 当前节点为空
		} else {

			return null;

		}

	}

	/**
	 * 寻找前一个节点（顺序为中序遍历）
	 * 
	 * @param node
	 *            当前节点
	 * @return 前一个节点
	 */
	public BinarySearchTreeNode getPreviousNode(BinarySearchTreeNode node) {

		// 当前节点的左节点不空
		if (node != null && node.left != null) {

			// 前一个节点为当前节点的左节点的最右节点
			node = node.left;
			while (node.right != null) {
				node = node.right;
			}
			return node;

		// 当前节点的左节点为空
		} else if (node != null && node.left == null) {

			// 前一个节点的右节点是当前节点或者是当前节点的父节点
			BinarySearchTreeNode parent = node.parent;
			while (parent != null && parent.left.equals(node)) {
				node = parent;
				parent = parent.parent;
			}
			return parent;

		// 当前节点为空
		} else {

			return null;

		}

	}
	
	public static void main(String[] args) {

		BinarySearchTree binarySearchTree = new BinarySearchTree();

		/*
		 * binaryTree.addRootNode(1);
		 * System.out.println(binaryTree.getRootNode().value);
		 * System.out.println(binaryTree.root.value);
		 * binaryTree.updateRootNode(3);
		 * System.out.println(binaryTree.root.value);
		 */

		binarySearchTree.addRootNode(8);
		binarySearchTree.addChildNodeByParent(binarySearchTree.root, 4);
		binarySearchTree.addChildNodeByParent(binarySearchTree.root, 12);

		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 4), 2);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 4), 6);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 12), 10);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 12), 14);

		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 2), 1);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 2), 3);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 6), 5);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 6), 7);

		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 10), 9);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 10), 11);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 14), 13);
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 14), 15);

		binarySearchTree.showMyself1(binarySearchTree.root);
		
		// System.out.println();
		// System.out.println(binarySearchTree.getMinimumNode(binarySearchTree.root).value);
		// System.out.println(binarySearchTree.getMaximumNode(binarySearchTree.root).value);
		
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 1)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 2)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 3)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 4)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 5)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 6)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 7)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 8)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 9)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 10)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 11)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 12)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 13)).value);
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 14)).value);
		// System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 15)).value);
		
		// System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 1)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 2)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 3)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 4)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 5)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 6)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 7)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 8)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 9)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 10)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 11)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 12)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 13)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 14)).value);
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 15)).value);
		
	}

}
