package chapter10;

/**
 * 
 * 数据结构：二叉树
 * 
 * 由一系列的节点组成
 * 每个节点由四个部分组成:
 * 1.指向父节点地址的变量
 * 2.当前节点的值
 * 3.指向左子节点地址的变量
 * 4.指向右子节点地址的变量
 * 
 * 非常常用的一种数据结构
 * 
 * @author 滑德友
 * @since 2018年5月7日17:31:05
 * 
 */
public class BinaryTree {

	BinaryTreeNode root;

	/**
	 * 
	 * 创建一个空的二叉树
	 * 
	 */
	public BinaryTree() {

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
	public void addRootNode(Object value) {

		if (root != null) {
			return;
		}

		BinaryTreeNode node = new BinaryTreeNode();
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
	public void updateRootNode(Object value) {

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
	public BinaryTreeNode getRootNode() {

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
	public void addChildNodeByParent(BinaryTreeNode parent, Object value) {

		// 非法输入
		if (parent.left != null && parent.right != null) {
			System.out.println("左右子树都满，不能在该父节点上增加子节点了");
			return;
		}

		// 生成新的节点
		BinaryTreeNode node = new BinaryTreeNode();
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
	public void addLeftChildNodeByParent(BinaryTreeNode parent, Object value) {

		// 非法输入
		if (parent.left != null) {
			System.out.println("左子树不为空，不能增加左节点了");
			return;
		}

		// 生成新的节点
		BinaryTreeNode node = new BinaryTreeNode();
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
	public void addRightChildNodeByParent(BinaryTreeNode parent, Object value) {

		// 非法输入
		if (parent.left != null || parent.right != null) {
			System.out.println("右子树不为空，不能增加右子节点了");
			return;
		}

		// 生成新的节点
		BinaryTreeNode node = new BinaryTreeNode();
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
	public void updateNode(BinaryTreeNode node, Object value) {

		node.value = value;

	}

	/**
	 * 
	 * 删除节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void deleteNode(BinaryTreeNode node) {

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
	public BinaryTreeNode getNodeByValue1(BinaryTreeNode parent, Object value) {

		// 非法输入
		if (parent == null) {
			System.out.println("父节点已经没有子节点了");
			return null;
		}

		// 如果当前节点的值等于输入值，返回当前节点
		// 否则向右或者向左查找
		if (parent.value.equals(value)) {
			return parent;
		} else {

			// 向左查找节点
			BinaryTreeNode left = getNodeByValue1(parent.left, value);

			// 向右查找节点
			BinaryTreeNode right = getNodeByValue1(parent.right, value);

			// 结束条件
			if (left != null) {
				return left;
			}
			if (right != null) {
				return right;
			} else {
				return null;
			}

		}

	}

	/**
	 * 
	 * 递归方式遍历二叉树
	 * 
	 * @param node
	 *            根节点
	 */
	public void showMyself1(BinaryTreeNode node) {

		if (node == null) {
			return;
		} else {

			// 前序遍历
			// System.out.print(node.value);
			// showMyself1(node.left);
			// showMyself1(node.right);

			// 中序遍历
			// showMyself1(node.left);
			// System.out.print(node.value);
			// showMyself1(node.right);

			// 后续序遍历
			// 输出当前节点的左节点
			showMyself1(node.left);

			// 输出当前节点的右节点
			showMyself1(node.right);

			// 输出当前节点
			System.out.print(node.value);

		}

	}

	/**
	 * 
	 * Morris 方法遍历二叉树
	 * 以最左边边的节点为起点，访问起点，然后访问起点的父节点，再访问起点的父节点的右节点的最左边的节点
	 * 每次从左节点返回父节点的时候，左节点已被访问完毕。
	 * 每次从右节点返回父节点的时候，要特别注意，右节点返回父节点时，要求新的迭代节点的左节点是右节点或者是右节点的父节点
	 * 
	 * 1.以最左边的节点为迭代点的起点
	 * 2.只要迭代点不空，就循环迭代
	 * 3.输出迭代点的值
	 * 4.如果迭代点的右节点为空，返回一个新的迭代点，要求新的迭代点的左节点是当前迭代点或者是当前迭代点的父节点
	 * 5.如果迭代点的右节点不空，返回一个新的迭代点，要求新的迭代点是当前迭代点的右节点的最左边的节
	 * 6.第4步返回的为root节点的父节点时结束
	 * 
	 * @param node
	 *            根节点
	 */
	public void showMyself2(BinaryTreeNode node) {

		// 找到起点
		while (node.left != null) {
			node = node.left;
		}

		// 迭代点不为空
		while (node != null) {

			// 输出当前迭代点
			System.out.print(node.value);

			// 迭代点的右节点为空
			if (node.right == null) {

				BinaryTreeNode parent = node.parent;

				// 新的迭代点的左节点是当前迭代点或者是当前迭代点的父节点
				while (parent.right.equals(node)) {
					node = node.parent;
					parent = parent.parent;
					if (parent == null) {
						break;
					}
				}

				// 迭代
				node = parent;

			// 迭代点的右节点为空
			} else {

				// 新的迭代点是当前迭代点的右节点的最左边的节点
				BinaryTreeNode right = node.right;
				while (right.left != null) {
					right = right.left;
				}

				// 迭代
				node = right;

			}

		}
		
	}

	/**
	 * 
	 * 试探回溯算法遍历二叉树
	 * 这里需要用到两个引用变量，上一个迭代节点和当前迭代节点 根据上面两个节点的关系确定下一个迭代节点
	 * 
	 * 1.当前迭代点不空，就循环迭代 
	 * 2.如果迭代关系为由父节点向子节点移动
	 * 2.1.输出当前迭代点的值
	 * 2.2.如果当前迭代点的左节点不空，上一个迭代点变为当前迭代点，下一个迭代点变为当前迭代点
	 * 2.3.如果当前迭代点的左节点为空，右节点不为空，上一个迭代变，当前迭代点变为上一个迭代点的右节点
	 * 2.4.如果当前迭代点的左节点为空，右节点为空，互换迭代点
	 * 3.如果迭代关系由左子节点向父节点移动
	 * 3.1.不输出当前迭代点的值
	 * 3.2.如果当前迭代点的右节点不空，上一个迭代点变为当前迭代点，下一个迭代点变为当前迭代点的右节点
	 * 3.3.如果当前迭代点的右节点为空，上一个迭代点变为当前迭代点，下一个迭代点变为当前迭代点的父节点
	 * 4.如果迭代关系为由右子节点向父节点移动
	 * 4.1.不输出当前迭代点的值
	 * 4.2.上一个迭代点变为当前迭代点，下一个迭代点变为当前迭代点的父节点
	 * 5.循环1,2,3,4直到条件为假
	 * 
	 * @param node
	 *            父节点
	 * 
	 */
	public void showMyself3(BinaryTreeNode node) {

		// 非法输入
		if (node == null) {
			return;
		}

		// 输出根节点
		System.out.print(node.value);

		// 循环初始化条件
		BinaryTreeNode previous = node;
		node = node.left;

		// 迭代条件
		while (node != null) {

			// 父节点向子节点移动
			if (node.parent != null && node.parent.equals(previous)) {

				System.out.print(node.value);

				// 左节点不空
				if (node.left != null) {

					previous = node;
					node = node.left;

				// 左节点为空，右节点不为空
				} else if (node.left == null && node.right != null) {

					node = previous.right;

				// 左节点为空，右节点为空
				} else if (node.left == null && node.right == null) {

					previous = node;
					node = previous.parent;

				}

			// 右子节点向父节点移动
			} else if (node.left.equals(previous)) {

				// 右节点不空
				if (node.right != null) {

					previous = node;
					node = node.right;

				// 右节点为空
				} else {

					previous = node;
					node = node.parent;

				}

			// 4.1.不输出当前迭代点的值
			} else if (node.right.equals(previous)) {

				previous = node;
				node = node.parent;

			}

		}

	}

	public static void main(String[] args) {

		BinaryTree binaryTree = new BinaryTree();

		/*
		 * binaryTree.addRootNode(1);
		 * System.out.println(binaryTree.getRootNode().value);
		 * System.out.println(binaryTree.root.value);
		 * binaryTree.updateRootNode(3);
		 * System.out.println(binaryTree.root.value);
		 */

		binaryTree.addRootNode('a');
		binaryTree.addChildNodeByParent(binaryTree.root, 'b');
		binaryTree.addChildNodeByParent(binaryTree.root, 'c');

		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'b'), 'd');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'b'), 'e');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'c'), 'f');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'c'), 'g');

		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'd'), 'h');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'd'), 'i');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'e'), 'j');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'e'), 'k');

		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'f'), 'l');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'f'), 'm');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'g'), 'n');
		binaryTree.addChildNodeByParent(binaryTree.getNodeByValue1(binaryTree.root, 'g'), 'o');

		binaryTree.showMyself1(binaryTree.root);
		System.out.println();
		binaryTree.showMyself2(binaryTree.root);
		System.out.println();
		binaryTree.showMyself3(binaryTree.root);

	}

}
