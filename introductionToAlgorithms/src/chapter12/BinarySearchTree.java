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
 * 基本上所有的操作都跟树的高度有关，如果树越是不平衡，树的高度越是接近节点的个数
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
	public void deleteNodeTree(BinarySearchTreeNode node) {

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
	public BinarySearchTreeNode getMinimumNode1(BinarySearchTreeNode parent) {

		while (parent != null && parent.left != null) {
			parent = parent.left;
		}

		return parent;

	}

	/**
	 * 
	 * 查找最小的节点（最左边的节点）
	 * 
	 * @param parent
	 *            根节点
	 * @return 最小的节点
	 */
	public BinarySearchTreeNode getMinimumNode2(BinarySearchTreeNode parent) {

		if (parent != null && parent.left != null) {
			return getMinimumNode2(parent.left);
		} else {
			return parent;
		}

	}

	/**
	 * 
	 * 查找最大的节点（最右边的节点）
	 * 
	 * @param parent
	 *            根节点
	 * @return 最大的节点
	 */
	public BinarySearchTreeNode getMaximumNode1(BinarySearchTreeNode parent) {

		while (parent != null && parent.right != null) {
			parent = parent.right;
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
	public BinarySearchTreeNode getMaximumNode2(BinarySearchTreeNode parent) {

		if (parent != null && parent.right != null) {
			return getMaximumNode2(parent.right);
		} else {
			return parent;
		}

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

	/**
	 * 
	 * 插入一个节点
	 * 
	 * @param parent
	 *            根节点
	 * @param value
	 *            节点的值
	 */
	public void insertNode(BinarySearchTreeNode parent, int value) {

		// 非法输入
		if (parent == null) {
			return;
		}

		// 新建一个节点
		BinarySearchTreeNode node = new BinarySearchTreeNode();
		node.value = value;
		node.parent = null;
		node.left = null;
		node.right = null;

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

	}
	
	/**
	 * 
	 * 仅删除当前节点，保留其子节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void deleteNodeOnly(BinarySearchTreeNode node) {

		// 非法输入
		if (node == null) {
			return;
		}

		// 当前节点没有子节点，直接删除
		if (node.left == null && node.right == null) {

			// 当前节点为根节点
			if (node.equals(root)) {
				node = null;
				root = null;

			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				node.parent.left = null;

			// 当前节点为父节点的右节点
			} else {
				node.parent.right = null;
			}
			

		// 当前节点只有右节点，右节点变为当前节点
		} else if (node.left == null && node.right != null) {

			// 当前节点为根节点
			if (node.parent == null) {
				root = root.right;
				root.parent = null;

			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				node.parent.left = node.right;
				node.right.parent = node.parent;

			// 当前节点为父节点的右节点
			} else {
				node.parent.right = node.right;
				node.right.parent = node.parent;
				node = null;
			}

		// 当前节点只有左节点，左节点变为当前节点
		} else if (node.left != null && node.right == null) {

			// 当前节点为根节点
			if (node.parent == null) {
				root = root.left;
				root.parent = null;

			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				node.parent.left = node.left;
				node.left.parent = node.parent;
				node = null;

			// 当前节点为父节点的右节点
			} else {
				node.parent.right = node.left;
				node.left.parent = node.parent;
				node = null;
			}

		// 当前节点有左节点和右节点
		// 选取当前节点的上一个或者是下一个节点来做为当前节点
		// 修改以上一个节点或者是下一个节点的为根节点的子树
		} else {

			// 通过上一个节点删除节点
			// subDeleteNodeOnlyPrevious(node);
			// 通过下一个节点删除节点
			subDeleteNodeOnlyNext(node);

		}

	}
	
	/**
	 * 
	 * 根据前一个节点删除具有两个子节点的节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void subDeleteNodeOnlyPrevious(BinarySearchTreeNode node) {

		// 因为被deleteNodeOnly调用时，node具有左右子节点
		BinarySearchTreeNode previous = getPreviousNode(node);

		// 前一个节点一定没有右节点
		// 当前一个节点的左节点为空，前一个节点应当移动到被删除的节点处
		if (previous.left == null) {

			// 当被删除的节点为根节点
			if (node.parent == null) {

				// 前一个节点是被删除的节点的左节点
				if (previous.parent.equals(root)) {
					previous.parent = null;
					previous.right = root.right;
					root.right.parent = previous;
					root = previous;

				// 前一个节点是被删除的节点的左节点的最右子节点
				} else {
					previous.parent.right = null;
					previous.parent = null;
					previous.left = root.left;
					previous.right = root.right;
					root.right.parent = previous;
					root = previous;
				}

			// 当被删除的节点不为根节点
			} else {

				// 前一个节点是被删除的节点的左节点
				if (previous.parent.equals(node)) {
					
					if(node.equals(node.parent.left)){
						node.parent.left = previous;
					}else{
						node.parent.right = previous;
					}
					previous.parent = node.parent;
					previous.right = node.right;
					node.right.parent = previous;
					node = previous;

				// 前一个节点是被删除的节点的左节点的最右子节点
				} else {
					if(node.equals(node.parent.left)){
						node.parent.left = previous;
					}else{
						node.parent.right = previous;
					}
					previous.parent.right = null;
					previous.parent = node.parent;
					previous.left = node.left;
					previous.right = node.right;
					node.right.parent = previous;
					node = previous;
				}

			}

		// 当前一个节点的左节点不空，前一个节点应当移动到被删除的节点处，且前一个节点的左节点应该移动到前一个节点处
		} else {

			// 当被删除的节点为根节点
			if (node.parent == null) {

				// 前一个节点是被删除的节点的左节点
				if (previous.parent.equals(root)) {
					previous.parent = null;
					previous.right = root.right;
					root.right.parent = previous;
					root = previous;

				// 前一个节点是被删除的节点的左节点的最右子节点
				} else {
					previous.parent.right = previous.left;
					previous.left.parent = previous.parent;
					previous.parent = null;
					previous.left = root.left;
					previous.right = root.right;
					root.right.parent = previous;
					root = previous;
				}

			// 当被删除的节点不为根节点
			} else {

				// 前一个节点是被删除的节点的左节点
				if (previous.parent.equals(node)) {
					if(node.equals(node.parent.left)){
						node.parent.left = previous;
					}else{
						node.parent.right = previous;
					}
					previous.parent = node.parent;
					previous.right = node.right;
					node.right.parent = previous;
					node = previous;

				// 前一个节点是被删除的节点的左节点的最右子节点
				} else {
					if(node.equals(node.parent.left)){
						node.parent.left = previous;
					}else{
						node.parent.right = previous;
					}
					previous.parent.right = previous.left;
					previous.left.parent = previous.parent;
					previous.parent = node.parent;
					previous.left = node.left;
					previous.right = node.right;
					node.right.parent = previous;
					node = previous;
				}

			}

		}

	}

	/**
	 * 
	 * 根据前一个节点删除具有两个子节点的节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void subDeleteNodeOnlyNext(BinarySearchTreeNode node) {
		
		// 因为被deleteNodeOnly调用时，node具有左右子节点
		BinarySearchTreeNode next = getNextNode(node);
		
		// 后一个节点一定没有左节点
		// 当后一个节点的右节点为空，后一个节点应当移动到被删除的节点处
		if (next.right == null) {

			// 当被删除的节点为根节点
			if (node.parent == null) {

				// 后一个节点是被删除的节点的右节点
				if (next.parent.equals(root)) {
					next.parent = null;
					next.left = root.left;
					root.left.parent = next;
					root = next;

				// 后一个节点是被删除的节点的右节点的最左子节点
				} else {
					next.parent.left = null;
					next.parent = null;
					next.left = root.left;
					next.right = root.right;
					root.left.parent = next;
					root = next;
				}

			// 当被删除的节点不为根节点
			} else {

				// 后一个节点是被删除的节点的右节点
				if (next.parent.equals(node)) {
					if(node.equals(node.parent.left)){
						node.parent.left = next;
					}else{
						node.parent.right = next;
					}
					next.parent = node.parent;
					next.left = node.left;
					node.left.parent = next;
					node = next;

				// 后一个节点是被删除的节点的右节点的最左子节点
				} else {
					if(node.equals(node.parent.left)){
						node.parent.left = next;
					}else{
						node.parent.right = next;
					}
					next.parent.left = null;
					next.parent = node.parent;
					next.left = node.left;
					next.right = node.right;
					node.left.parent = next;
					node = next;
				}

			}

		// 当前一个节点的右节点不空，前一个节点应当移动到被删除的节点处，且前一个节点的右节点应该移动到前一个节点处
		} else {

			// 当被删除的节点为根节点
			if (node.parent == null) {

				// 后一个节点是被删除的节点的右节点
				if (next.parent.equals(root)) {
					next.parent = null;
					next.left = root.left;
					root.left.parent = next;
					root = next;

				// 后一个节点是被删除的节点的右节点的最左子节点
				} else {
					next.parent.left = next.right;
					next.right.parent = next.parent;
					next.parent = null;
					next.left = root.left;
					next.right = root.right;
					root.left.parent = next;
					root = next;
				}

			// 当被删除的节点不为根节点
			} else {

				// 后一个节点是被删除的节点的右节点
				if (next.parent.equals(node)) {
					if(node.equals(node.parent.left)){
						node.parent.left = next;
					}else{
						node.parent.right = next;
					}
					next.parent = node.parent;
					next.left = node.left;
					node.left.parent = next;
					node = next;

				// 后一个节点是被删除的节点的右节点的最左子节点
				} else {
					if(node.equals(node.parent.left)){
						node.parent.left = next;
					}else{
						node.parent.right = next;
					}
					next.parent.left = next.right;
					next.right.parent = next.parent;
					next.parent = node.parent;
					next.left = node.left;
					next.right = node.right;
					node.left.parent = next;
					node = next;
				}

			}

		}
		
	}
	
	public static void main(String[] args) {

		BinarySearchTree binarySearchTree = new BinarySearchTree();

		binarySearchTree.addRootNode(8);
		
		/*binarySearchTree.addChildNodeByParent(binarySearchTree.root, 4);
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
		binarySearchTree.addChildNodeByParent(binarySearchTree.getNodeByValue2(binarySearchTree.root, 14), 15);*/

		// binarySearchTree.showMyself1(binarySearchTree.root);
		
		/*System.out.println();
		System.out.println(binarySearchTree.getMinimumNode1(binarySearchTree.root).value);
		System.out.println(binarySearchTree.getMinimumNode2(binarySearchTree.root).value);
		System.out.println(binarySearchTree.getMaximumNode1(binarySearchTree.root).value);
		System.out.println(binarySearchTree.getMaximumNode2(binarySearchTree.root).value);*/

		/*System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 1)).value);
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
		System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 14)).value);*/
		// System.out.println(binarySearchTree.getNextNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 15)));

		// System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 1)));
		/*System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 2)).value);
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
		System.out.println(binarySearchTree.getPreviousNode(binarySearchTree.getNodeByValue1(binarySearchTree.root, 15)).value);*/

		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 1);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 3);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 5);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 7);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 9);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 11);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 13);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 15);*/
		
		// binarySearchTree.showMyself1(binarySearchTree.root);
		
		// 以下测试删除
		/*BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 以下没有子节点
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 以下仅有右节点
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 11);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,10);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 以下仅有左节点
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 5);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,6);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 以下为有左右子节点情况，且使用前一个节点，来删除
		// 前一个节点的左节点为空，根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点为空，根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 7);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点为空，非根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点为空，非根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 1);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 3);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 9);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 11);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点不空，根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点不空，根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 5);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点不空，非根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 1);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 9);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点不空，非根节点，不连续
		/*binarySearchTree.updateRootNode(16);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 8);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 5);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.updateRootNode(16);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 24);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 20);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 28);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 22);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 21);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,24);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// *************************************************************
		
		// 以下为有左右子节点情况，且使用后一个节点，来删除
		// 后一个节点的右节点为空，根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点为空，根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 9);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点为空，非根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点为空，非根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 5);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 7);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 13);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 15);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点不空，根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点不空，根节点，不连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 11);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 后一个节点的右节点不空，非根节点，连续
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 2);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 6);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 7);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,4);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 15);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,12);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		
		// 前一个节点的左节点不空，非根节点，不连续
		/*binarySearchTree.updateRootNode(16);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 8);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 4);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 12);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 10);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 14);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 11);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,8);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/
		/*binarySearchTree.updateRootNode(16);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 24);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 20);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 28);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 26);
		binarySearchTree.insertNode(binarySearchTree.getRootNode(), 27);
		BinarySearchTreeNode node = binarySearchTree.getNodeByValue1(binarySearchTree.root,24);
		System.out.println(node.value);
		binarySearchTree.deleteNodeOnly(node);
		binarySearchTree.showMyself1(binarySearchTree.root);*/

	}

}
