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
 * b.根节点的为黑色，在修改根节点的子节点，不用修改根节点
 * c.每次插入的时候，插入的节点的颜色为红色
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
	 * 寻找下一个节点（顺序为中序遍历）
	 * 
	 * @param node
	 *            当前节点
	 * @return 下一个节点
	 */
	public RedBlackTreeNode getNextNode(RedBlackTreeNode node) {

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
			RedBlackTreeNode parent = node.parent;
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
	public RedBlackTreeNode getPreviousNode(RedBlackTreeNode node) {

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
			RedBlackTreeNode parent = node.parent;
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
	 * 对当前节点进行左旋操作，当前节点变为当前节点的左节点
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
	 * 对当前节点进行右旋操作，当前节点变为当前节点的右节点
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
		subInsertNodeFix(node);

	}
	
	/**
	 * 
	 * 插入节点后，修改树的结构，保持红黑树的性质
	 * 当前违反规则的节点，父节点，叔节点，祖节点四者之间的关系，决定了如何修改
	 * 修改时，每一轮操作都不能更改每条路上黑色节点的个数（除非到了根节点）
	 * 修改时，违反规则的节点向着根节点移动
	 * 最差的情况是需要修改根节点，使旋转后的根节点的颜色变为绿色，使每条路上的黑色节点的个数增加1个
	 * 
	 * @param node
	 *            违反规则的节点
	 */
	public void subInsertNodeFix(RedBlackTreeNode node) {

		// 当违反规则的节点和违反规则的节点的父节点都是红色的时候，需要进行修改
		while (node.parent != null && node.parent.color == 0) {

			// 当祖节点为空
			if (node.parent.parent == null) {

				node.parent.color = 1;

			// 当祖节点不为空
			} else {

				// 获取父，叔，祖三个节点的相关信息
				// 父节点一定为红色
				// 叔节点可能为红色或者黑色或者为空
				// 祖节点一定为黑色
				RedBlackTreeNode parent = node.parent;
				RedBlackTreeNode grandParent = parent.parent;
				RedBlackTreeNode uncle = null;
				if (parent.equals(grandParent.left)) {
					uncle = grandParent.right;
				} else {
					uncle = grandParent.left;
				}

				// 对于叔节点为空的节点
				// 将父节点的颜色变为黑色
				// 将祖节点的颜色变为红色
				// 因为祖节点的父节点有可能是红色的，祖节点变为下一个违反规则的点
				if (uncle == null) {

					parent.color = 1;
					grandParent.color = 0;
					node = grandParent;
					continue;

				// 对于叔节点为红色的节点
				// 将父节点的颜色变为黑色
				// 将叔节点的颜色变为黑色
				// 将祖节点的颜色变为红色
				// 因为祖节点的父节点有可能是红色的，祖节点变为下一个违反规则的点
				} else if (uncle != null && uncle.color == 0) {

					parent.color = 1;
					uncle.color = 1;
					grandParent.color = 0;
					node = grandParent;
					continue;

				// 对于叔节点为黑色的节点
				// 需要通过旋转改变树的结构，来达到修改的目的
				// 如果当前节点和父节点和祖节点不在同一侧时，先旋转父节点，使他们三个在同一侧
				// 如果当前节点和父节点和组节点在同一侧时，旋转祖节点，组节点的颜色变为红色，旋转到祖节点的节点颜色变为黑色
				// 因为旋转到祖节点的节点颜色是黑色，已经不会在违反规则了
				} else if (uncle != null && uncle.color == 1) {

					// 父节点在祖节点的左侧，当前节点在父节点的左侧
					// 直接旋转祖节点
					if (node.equals(parent.left) && parent.equals(grandParent.left)) {

						rightRotate(grandParent);
						parent.color = 1;
						grandParent.color = 0;
						if (grandParent.equals(root)) {
							root = grandParent.left;
						}
						break;

					// 父节点在祖节点的左侧，当前节点在父节点的右侧
					// 先旋转父节点，将当前节点和父节点和祖节点变到同一条直线上
					// 再旋转祖节点
					} else if (node.equals(parent.left) && parent.equals(grandParent.right)) {

						rightRotate(parent);
						leftRotate(grandParent);
						node.color = 1;
						grandParent.color = 0;
						if (grandParent.equals(root)) {
							root = grandParent.right;
						}
						break;

					// 父节点在祖节点的右侧，当前节点在父节点的左侧
					// 先旋转父节点，将当前节点和父节点和祖节点变到同一条直线上
					// 再旋转祖节点
					} else if (node.equals(parent.right) && parent.equals(grandParent.left)) {

						leftRotate(parent);
						rightRotate(grandParent);
						node.color = 1;
						grandParent.color = 0;
						if (grandParent.equals(root)) {
							root = grandParent.left;
						}
						break;

					// 父节点在祖节点的右侧，当前节点在父节点的右侧
					// 直接旋转祖节点
					} else if (node.equals(parent.right) && parent.equals(grandParent.right)) {

						leftRotate(grandParent);
						parent.color = 1;
						grandParent.color = 0;
						if (grandParent.equals(root)) {
							root = grandParent.right;
						}
						break;

					}

				}

			}

		}

		// 修改有可能被动到的根节点
		root.color = 1;
		root.parent = null;

	}
	
	/**
	 * 
	 * 仅删除当前节点，保留其子节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void deleteNodeOnly(RedBlackTreeNode node) {

		// 非法输入
		if (node == null) {
			return;
		}

		RedBlackTreeNode fixNode = null;
		int fixColor = 0;
		
		// 当前节点没有子节点，直接删除
		if (node.left == null && node.right == null) {

			// 当前节点为根节点
			if (node.equals(root)) {
				
				node = null;
				root = null;

			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				
				node.parent.left = null;
				
				// 修改条件
				if(node.parent.right == null){
					if(node.color == 1){
						fixColor = 1;
						fixNode = node.parent;
					}
				}
				
			// 当前节点为父节点的右节点
			} else {
				
				node.parent.right = null;
				
				// 修改条件
				if(node.parent.left == null){
					if(node.color == 1){
						fixColor = 1;
						fixNode = node.parent;
					}
				}
				
			}
			
		// 当前节点只有右节点，右节点变为当前节点
		} else if (node.left == null && node.right != null) {

			// 当前节点为根节点
			if (node.parent == null) {
				
				root = root.right;
				root.parent = null;
				root.color = 1;
				
			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				
				node.parent.left = node.right;
				node.right.parent = node.parent;
				
				// 修改条件
				if(node.color == 1){
					if(node.right.color == 0){
						fixColor = 0 ;
						node.right.color = 1;
					}else{
						fixColor = 1;
						fixNode = node.right;
					}
				}
				
			// 当前节点为父节点的右节点
			} else {
				
				node.parent.right = node.right;
				node.right.parent = node.parent;
				
				// 修改条件
				if(node.color == 1){
					if(node.right.color == 0){
						fixColor = 0 ;
						node.right.color = 1;
					}else{
						fixColor = 1;
						fixNode = node.right;
					}
				}
				
			}

		// 当前节点只有左节点，左节点变为当前节点
		} else if (node.left != null && node.right == null) {

			// 当前节点为根节点
			if (node.parent == null) {
				
				root = root.left;
				root.parent = null;
				root.color = 1;
				
			// 当前节点为父节点的左节点
			} else if (node.equals(node.parent.left)) {
				
				node.parent.left = node.left;
				node.left.parent = node.parent;
				
				// 修改条件
				if(node.color == 1){
					if(node.left.color == 0){
						fixColor = 0 ;
						node.left.color = 1;
					}else{
						fixColor = 1;
						fixNode = node.left;
					}
				}

			// 当前节点为父节点的右节点
			} else {
				node.parent.right = node.left;
				node.left.parent = node.parent;
				
				// 修改条件
				if(node.color == 1){
					if(node.left.color == 0){
						fixColor = 0 ;
						node.left.color = 1;
					}else{
						fixColor = 1;
						fixNode = node.left;
					}
				}
				
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
		
		System.out.println(fixNode +"\t"+fixColor);

	}
	
	/**
	 * 
	 * 根据前一个节点删除具有两个子节点的节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void subDeleteNodeOnlyPrevious(RedBlackTreeNode node) {

		// 因为被deleteNodeOnly调用时，node具有左右子节点
		RedBlackTreeNode previous = getPreviousNode(node);

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
	 * 根据后一个节点删除具有两个子节点的节点
	 * 
	 * @param node
	 *            被删除的节点
	 */
	public void subDeleteNodeOnlyNext(RedBlackTreeNode node) {
		
		// 因为被deleteNodeOnly调用时，node具有左右子节点
		RedBlackTreeNode next = getNextNode(node);
		
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
		
		RedBlackTree redBlackTree = new RedBlackTree();
		
		redBlackTree.addRootNode(16);
		
		/*redBlackTree.insertNode(redBlackTree.root, 8);
		redBlackTree.insertNode(redBlackTree.root, 24);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 4);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 2);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 12);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 1);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);*/

		/*redBlackTree.insertNode(redBlackTree.root, 8);
		redBlackTree.insertNode(redBlackTree.root, 24);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 28);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 30);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 20);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		redBlackTree.insertNode(redBlackTree.root, 31);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);*/
		
		
		redBlackTree.insertNode(redBlackTree.root, 8);
		redBlackTree.insertNode(redBlackTree.root, 24);
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		
		redBlackTree.deleteNodeOnly(redBlackTree.getNodeByValue1(redBlackTree.root, 8));
		redBlackTree.showMyself1(redBlackTree.root);
		System.out.println("\t\t\t-----" + redBlackTree.root.value);
		
	}

}
