package chapter18;

/**
 * 
 * 数据结构：B树(一种特殊的多路平衡查找树)
 * 
 * 由一系列的节点组成
 * 每个节点由五个部分组成:
 * 1.当前节点的值的个数
 * 2.当前节点的所有的值
 * 3.当前节点是否是叶节点
 * 4.指向当前节点所有子节点的变量
 * 5.指向当前节点父节点的变量
 * 
 * 注意:B树要求
 * 1.当前节点的关键值是递增的
 * 2.每个非叶节点都有valueCount+1个子节点
 * 3.每个非叶节点的关键值与子节点的关键值有这样的关系value[n-1] <= childNode[n]的所有value <= value[n]
 * 4.每个叶节点的高度相同
 * 5.每个节点(非根节点)最少有t - 1个value,每个节点(包括根节点)最多有2 * t - 1个value
 * 
 * 注意：对B树要求的解释
 * a.第4点和第5点决定了h = k * lgt(n)
 * b.B树插入且需要高度增加的时候,需要根节点在第5点中的特殊地位
 * c.每次IO都是对一个节点而言的(一个节点包含多个数据)
 * 
 * 常用的一种数据结构
 * 基本上所有的操作都跟树的高度有关，上面的性质保证了树的平衡性，高度接近lgn(注意这里是以t为低n的对数)
 * 
 * 因为一次IO所花费的时间是比较多的，我们通常希望一次IO处理的数据不是1个，而是多个来提高IO的效率
 * B树每个节点有比较多的值，在IO方面效率是比较高的
 * 
 * @author 滑德友
 * @since 2018年6月6日14:10:35
 * 
 */
public class BTree {
	
	BTreeNode root;
	int t;
	
	/**
	 * 
	 * 创建一个空的B树
	 * 
	 * @param t
	 */
	public BTree(int t) {
		
		if(t <2) {
			System.out.println("t");
		} else {
			this.t = t;
		}
		
	}
	
	/**
	 * 
	 * 增加根节点
	 * 
	 */
	public void addRootNode() {
		
		if(root != null) {
			System.out.println("已经有root节点了");
			return ;
		}
		root = new BTreeNode();
		root.parentNode = null;
		root.value = new int[2*t - 1];
		root.childNode = new BTreeNode[2*t];
		root.leaf = true;
		root.valueCount = 0;
		write(root);
		
	}
	
	/**
	 * 
	 * 删除根节点
	 * 
	 */
	public void removeRootNode() {
		
		root = null;
		t = 0;
		
	}

	/**
	 * 
	 * 子节点已满
	 * 把子节点从中间分成左子节点和右子节点
	 * 并把子节点的中间值value[t]分给父节点
	 * 
	 * 
	 * @param childNode
	 * @param parentNode
	 * @param relationship
	 * @return
	 */
	public BTreeNode[] giveOneValueToParent(BTreeNode childNode,BTreeNode parentNode,int relationship) {
		
		// parentNode为空,childNode为根节点
		if(parentNode == null) {
			
			// leftChildNode为childNode左半边
			BTreeNode leftChildNode = new BTreeNode();
			leftChildNode.parentNode = null;
			leftChildNode.value = new int[2 * t - 1];
			leftChildNode.leaf = childNode.leaf;
			leftChildNode.valueCount = 0;
			leftChildNode.childNode = null;
			if(!childNode.leaf) {
				leftChildNode.childNode =  new BTreeNode[2*t];
			}
			
			// 赋值
			leftChildNode.valueCount = t - 1;
			for(int i = 0 ;i < t - 1;i++) {
				leftChildNode.value[i] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for(int i = 0 ;i < t;i++) {
					childNode.childNode[i].parentNode = leftChildNode;
					leftChildNode.childNode[i] = childNode.childNode[i];	
				}
			}

			// rightChildNode为childNode右半边
			BTreeNode rightChildNode = new BTreeNode();
			rightChildNode.parentNode = null;
			rightChildNode.value = new int[2 * t - 1];
			rightChildNode.leaf = childNode.leaf;
			rightChildNode.valueCount = 0;
			rightChildNode.childNode =  null;
			if(!childNode.leaf) {
				rightChildNode.childNode =  new BTreeNode[2*t];
			}
			
			// 赋值
			rightChildNode.valueCount = t - 1;
			for(int i = t ;i < 2 * t - 1;i++) {
				rightChildNode.value[i - t] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for (int i = t ;i < 2 * t;i++) {
					childNode.childNode[i].parentNode = rightChildNode;
					rightChildNode.childNode[i - t] = childNode.childNode[i];
				}
			}
			
			// 新的父节点
			BTreeNode newParentNode = new BTreeNode();
			newParentNode.parentNode = null;
			newParentNode.value = new int[2 * t - 1];
			newParentNode.leaf = false;
			newParentNode.valueCount = 0;
			newParentNode.childNode =  new BTreeNode[2*t];
			
			newParentNode.value[0] = childNode.value[t - 1];		
			newParentNode.valueCount = 1;
			
			// leftChildNode和rightChildNode和newParentNode连接,并丢弃childNode
			leftChildNode.parentNode = newParentNode;
			rightChildNode.parentNode = newParentNode;
			newParentNode.childNode[0] = leftChildNode;
			newParentNode.childNode[1] = rightChildNode;
			
			childNode = null;
			root = newParentNode;
			
			// 读写硬盘
			write(leftChildNode);
			write(rightChildNode);
			write(parentNode);
			
			// 返回
			BTreeNode[] leftRightNode = {leftChildNode,rightChildNode};
			return leftRightNode;
			
		}
		
		// parentNode已满
		if (parentNode.valueCount == 2 * t - 1 ) {
		
			// 找到父节点与祖节点关系
			int parentRelationship = 0;
			if(parentNode.parentNode != null) {
				for(int i = 0 ;i < 2 * t;i++) {
					if( parentNode.equals(parentNode.parentNode.childNode[i])) {
						parentRelationship = i;
						break;
					}
				}
			}
			
			// 获得父节点拆分的两个节点
			BTreeNode[] leftRightParentNode = giveOneValueToParent(parentNode,parentNode.parentNode,parentRelationship);
			
			// 新的父节点
			if(relationship <= t - 1) {
				parentNode = leftRightParentNode[0];
			}else {
				parentNode = leftRightParentNode[1];
			}

			//新的关系
			if(relationship > t - 1) {
				relationship -= t;
			}
			
			// leftChildNode为childNode左半边
			BTreeNode leftChildNode = new BTreeNode();
			leftChildNode.parentNode = null;
			leftChildNode.value = new int[2 * t - 1];
			leftChildNode.leaf = childNode.leaf;
			leftChildNode.valueCount = 0;
			leftChildNode.childNode =  null;
			if(!childNode.leaf) {
				leftChildNode.childNode =  new BTreeNode[2*t];
			}

			// 赋值
			leftChildNode.valueCount = t - 1;
			for(int i = 0 ;i < t - 1;i++) {
				leftChildNode.value[i] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for(int i = 0 ;i < t;i++) {
					childNode.childNode[i].parentNode = leftChildNode;
					leftChildNode.childNode[i] = childNode.childNode[i];	
				}
			}
			// rightChildNode为childNode右半边
			BTreeNode rightChildNode = new BTreeNode();
			rightChildNode.parentNode = null;
			rightChildNode.value = new int[2 * t - 1];
			rightChildNode.leaf = childNode.leaf;
			rightChildNode.valueCount = 0;
			rightChildNode.childNode =  null;
			if(!childNode.leaf) {
				rightChildNode.childNode =  new BTreeNode[2*t];
			}

			// 赋值
			rightChildNode.valueCount = t - 1;
			for(int i = t ;i < 2 * t - 1;i++) {
				rightChildNode.value[i - t] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for (int i = t ;i < 2 * t;i++) {
					childNode.childNode[i].parentNode = rightChildNode;
					rightChildNode.childNode[i - t] = childNode.childNode[i];
				}
			}
			
			// leftChildNode和rightChildNode和newParentNode连接,并丢弃childNode
			leftChildNode.parentNode = parentNode;
			rightChildNode.parentNode = parentNode;
			
			// 增加value
			for(int i = parentNode.valueCount - 1; i >= relationship && i >= 2 * t - 2; i-- ) {
				parentNode.value[i + 1] = parentNode.value[i];
			}
			parentNode.value[relationship] = childNode.value[t - 1];
			parentNode.valueCount += 1;

			// 增加子节点
			parentNode.childNode[relationship] = leftChildNode;
			for(int i = parentNode.valueCount - 1; i > relationship; i-- ) {
				parentNode.value[i + 1] = parentNode.value[i];
			}
			parentNode.childNode[relationship + 1] = rightChildNode;

			childNode = null;
			
			// 硬盘读写
			write(leftChildNode);
			write(rightChildNode);
			write(parentNode);

			// 返回
			BTreeNode[] leftRightNode = {leftChildNode,rightChildNode};
			return leftRightNode;

		// parentNode未满
		}else {
			
			// leftChildNode为childNode左半边
			BTreeNode leftChildNode = new BTreeNode();
			leftChildNode.parentNode = null;
			leftChildNode.value = new int[2 * t - 1];
			leftChildNode.leaf = childNode.leaf;
			leftChildNode.valueCount = 0;
			leftChildNode.childNode =  null;
			if(!childNode.leaf) {
				leftChildNode.childNode =  new BTreeNode[2*t];
			}

			// 赋值
			leftChildNode.valueCount = t - 1;
			for(int i = 0 ;i < t - 1;i++) {
				leftChildNode.value[i] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for(int i = 0 ;i < t;i++) {
					childNode.childNode[i].parentNode = leftChildNode;
					leftChildNode.childNode[i] = childNode.childNode[i];	
				}
			}
			
			// rightChildNode为childNode右半边
			BTreeNode rightChildNode = new BTreeNode();
			rightChildNode.parentNode = null;
			rightChildNode.value = new int[2 * t - 1];
			rightChildNode.leaf = childNode.leaf;
			rightChildNode.valueCount = 0;
			rightChildNode.childNode =  null;
			if(!childNode.leaf) {
				rightChildNode.childNode =  new BTreeNode[2*t];
			}

			// 赋值
			rightChildNode.valueCount = t - 1;
			for(int i = t ;i < 2 * t - 1;i++) {
				rightChildNode.value[i - t] = childNode.value[i];
			}
			// 赋子节点
			if(!childNode.leaf) {
				for (int i = t ;i < 2 * t;i++) {
					childNode.childNode[i].parentNode = rightChildNode;
					rightChildNode.childNode[i - t] = childNode.childNode[i];
				}
			}
			
			// leftChildNode和rightChildNode和newParentNode连接,并丢弃childNode
			leftChildNode.parentNode = parentNode;
			rightChildNode.parentNode = parentNode;
			
			// 增加value
			for(int i = parentNode.valueCount - 1; i >= relationship && i >= 2 * t - 2; i-- ) {
				parentNode.value[i + 1] = parentNode.value[i];
			}
			parentNode.value[relationship] = childNode.value[t - 1];
			parentNode.valueCount += 1;
			
			// 增加子节点
			parentNode.childNode[relationship] = leftChildNode;
			for(int i = parentNode.valueCount - 1; i > relationship; i-- ) {
				parentNode.value[i + 1] = parentNode.value[i];
			}
			parentNode.childNode[relationship + 1] = rightChildNode;

			childNode = null;
			
			// 硬盘读写
			write(leftChildNode);
			write(rightChildNode);
			write(parentNode);

			// 返回
			BTreeNode[] leftRightNode = {leftChildNode,rightChildNode};
			return leftRightNode;

		}

	}

	/**
	 * 
	 * 插入一个值
	 * 
	 * @param node
	 * @param value
	 */
	public void insertValue(BTreeNode node,int value) {
		
		// 非法输入
		if(node == null) {
			System.out.println("根节点不能为空");
			return ;
		}
		
		// 查找需要被放入的叶节点
		BTreeNode leafNode = null;
		if(node.leaf) {
			leafNode = node;
		}else {
			leafNode = insertFindLeafNode(node,value);
		}
		
		// 叶节点已满
		if(leafNode.valueCount == 2 * t - 1) {
			
			//  记录中间值
			int middleValue = leafNode.value[t];
			
			// 将叶节点从中间拆分成两个节点，并把叶节点的中间值分给父节点
			// 返回leafNode节点的左右子节点
			BTreeNode parentNode = leafNode.parentNode;
			BTreeNode[] leftRightLeafChildNode = null;
			if(parentNode == null ) {
				leftRightLeafChildNode = giveOneValueToParent(leafNode,parentNode,0);
			}else {
				int relationship = 0;
				for(int i = 0 ;i < 2 * t;i++) {
					if( leafNode.equals(leafNode.parentNode.childNode[i])) {
						relationship = i;
						break;
					}
				}
				leftRightLeafChildNode = giveOneValueToParent(leafNode,parentNode,relationship);
			}
			BTreeNode leftLeafChildNode = leftRightLeafChildNode[0];
			BTreeNode rightLeafChildNode = leftRightLeafChildNode[1];
			
			// 把value插入到leftLeafChildNode
			if(value <= middleValue ) {
				
				for(int i = 0 ; i < leftLeafChildNode.valueCount;i++) {
					if (value <= leftLeafChildNode.value[i]) {
						for(int j = leftLeafChildNode.valueCount - 1 ; j >= i ;j--) {
							leftLeafChildNode.value[j+1] = leftLeafChildNode.value[j];
						}
						leftLeafChildNode.value[i] = value;
						break;
					} else if(i == leftLeafChildNode.valueCount - 1){
						leftLeafChildNode.value[leftLeafChildNode.valueCount] = value;
						break;
					}
				}
				leftLeafChildNode.valueCount += 1;
				
			// 把value插入到rightLeafChildNode
			} else {
				
				for(int i = 0 ; i < rightLeafChildNode.valueCount;i++) {
					if (value <= rightLeafChildNode.value[i]) {
						for(int j = rightLeafChildNode.valueCount - 1 ; j >= i ;j--) {
							rightLeafChildNode.value[j+1] = rightLeafChildNode.value[j];
						}
						rightLeafChildNode.value[i] = value;
						break;
					} else if(i == rightLeafChildNode.valueCount - 1){
						rightLeafChildNode.value[rightLeafChildNode.valueCount] = value;
						break;
					}
				}
				rightLeafChildNode.valueCount += 1;
				
			}
			
		// 叶节点未满
		}else {
			
			// 插入value
			if(leafNode.valueCount == 0) {
				leafNode.value[0] = value;
			}else {
				for(int i = 0 ; i < leafNode.valueCount;i++) {
					if (value <= leafNode.value[i]) {
						for(int j = leafNode.valueCount - 1 ; j >= i ;j--) {
							leafNode.value[j+1] = leafNode.value[j];
						}
						leafNode.value[i] = value;
						break;
					} else if(i == leafNode.valueCount - 1){
						leafNode.value[leafNode.valueCount] = value;
						break;
					}
				}
			}
			
			leafNode.valueCount += 1;
			
		}
		
	}
	
	/**
	 * 
	 * 插入时，查找一个合适的叶节点
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public BTreeNode insertFindLeafNode(BTreeNode node,int value) {
		
		while (!node.leaf) {
			int i = 0;
			for (; i < node.valueCount; i++) {
				if (value <= node.value[i]) {
					break;
				} 
			} 
			node = node.childNode[i];
		}
		
		return node;
		
	}
	
	/**
	 * 
	 * 以node为根，查找value所在的node和位置
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public Object[] getNodeByValue(BTreeNode node,int value) {
		
		// 查找直到叶子节点
		while (!node.leaf) {
			int i = 0;
			for (; i < node.valueCount; i++) {
				if (value < node.value[i]) {
					break;
				}else if(value == node.value[i]) {
					Object[] result = new Object[2];
					result[0] = node;
					result[1] = i;
					return result;
				}
			}
			node = node.childNode[i];
		}
		
		if(value < node.value[0] || value > node.value[node.valueCount - 1]) {
			return null;
		}
		if(value > node.value[0]) {
			return null;
		}
		
		// 处理叶子节点
		for(int i = 0 ; i < node.valueCount; i++) {
			
			if(value == node.value[i]) {
				Object[] result = new Object[2];
				result[0] = node;
				result[1] = i;
				return result;
			}else if(i == node.valueCount - 1) {
				return null;
			}
			
		}
		
		return null;
		
	}
	
	
	public void borrowOneValueFromParent() {
		
	}
	
	public void removeValue(BTreeNode node,int value) {
		
		Object[] object = getNodeByValue(node, value);
		if(object == null){
			System.out.println("找不到需要被删除的值");
			return ;
		}
		
		BTreeNode removeNode = (BTreeNode)object[0];
		int removePositon = (int)object[1];
		
		// 叶节点
		if(removeNode.leaf) {
			
			// 根节点
			if(removeNode.parentNode == null) {
				
				if(removePositon == 2 * t - 1 ) {
					removeNode.value[removePositon] = 0;
				} else {
					for(int i = removePositon; i < removeNode.valueCount - 1; i++) {
						removeNode.value[i] = removeNode.value[i + 1];
					}
					removeNode.value[removeNode.valueCount - 1] = 0;
				}
				removeNode.valueCount -= removeNode.valueCount;
				return ;
				
			// 非根节点
			} else {
				
				// 不需要借
				if( node.valueCount > t - 1) {
					
					if(removePositon == 2 * t - 1 ) {
						removeNode.value[removePositon] = 0;
						return ;
					} else {
						for(int i = removePositon; i < removeNode.valueCount - 1; i++) {
							removeNode.value[i] = removeNode.value[i + 1];
						}
						removeNode.value[removeNode.valueCount - 1] = 0;
					}
					removeNode.valueCount -= removeNode.valueCount;
					return ;
					
				// 需要父节点借
				} else {

					// 找到与父节点的关系
					int relationship = 0;
					for(int i = 0 ;i < 2 * t;i++) {
						if( removeNode.equals(removeNode.parentNode.childNode[i])) {
							relationship = i;
							break;
						}
					}
					
					// 当子节点的位置等于父节点值的个数
					if(relationship == removeNode.parentNode.valueCount) {
						
						// 借
						if(removePositon == 2 * t - 1 ) {
							removeNode.value[removePositon] = removeNode.parentNode.value[relationship];
							return ;
						} else {
							for(int i = removePositon; i > 0; i--) {
								removeNode.value[i] = removeNode.value[i - 1];
							}
							removeNode.value[0] = removeNode.parentNode.value[relationship - 1];
						}
						
						// 删除父节点被借的值
						removeValue(removeNode.parentNode,relationship - 1);
						return;
						
					// 当子节点的位置等于父节点值的个数 -1
					} else {
						
						// 借
						if(removePositon == 2 * t - 1 ) {
							removeNode.value[removePositon] = removeNode.parentNode.value[relationship];
							return ;
						} else {
							for(int i = removePositon; i < removeNode.valueCount - 1; i++) {
								removeNode.value[i] = removeNode.value[i + 1];
							}
							removeNode.value[removeNode.valueCount - 1] = removeNode.parentNode.value[relationship];
						}
						
						// 删除父节点被借的值
						removeValue(removeNode.parentNode,relationship);
						return;
						
					}
					
				}
				
			}
			
		// 非叶节点
		} else {
			
			
			
		}
		
	}
	
	
	/**
	 * 
	 * 模拟从硬盘读取数据
	 * 
	 * @param bTreeNode
	 */
	public void read(BTreeNode bTreeNode) {

		System.out.println("从硬盘读取数据" + bTreeNode);

	}

	/**
	 * 
	 * 模拟向硬盘写入数据
	 * 
	 * @param bTreeNode
	 */
	public void write(BTreeNode bTreeNode) {

		System.out.println("向硬盘写入数据" + bTreeNode);

	}
	
	public static void main(String[] args) {
		
		BTree bTree = new BTree(3);
		bTree.addRootNode();
		bTree.insertValue(bTree.root, 1);
		bTree.insertValue(bTree.root, 2);
		bTree.insertValue(bTree.root, 3);
		bTree.insertValue(bTree.root, 4);
		bTree.insertValue(bTree.root, 5);
		bTree.insertValue(bTree.root, 6);
		bTree.insertValue(bTree.root, 7);
		bTree.insertValue(bTree.root, 8);
		bTree.insertValue(bTree.root, 9);
		bTree.insertValue(bTree.root, 10);
		bTree.insertValue(bTree.root, 11);
		bTree.insertValue(bTree.root, 12);
		bTree.insertValue(bTree.root, 13);
		bTree.insertValue(bTree.root, 14);
		bTree.insertValue(bTree.root, 15);
		bTree.insertValue(bTree.root, 16);
		bTree.insertValue(bTree.root, 17);
		bTree.insertValue(bTree.root, 18);
		bTree.insertValue(bTree.root, 19);
		bTree.insertValue(bTree.root, 20);
		bTree.insertValue(bTree.root, 21);
		bTree.insertValue(bTree.root, 22);
		bTree.insertValue(bTree.root, 23);
		bTree.insertValue(bTree.root, 24);
		bTree.insertValue(bTree.root, 25);
		bTree.insertValue(bTree.root, 26);
		bTree.insertValue(bTree.root, 27);
		bTree.insertValue(bTree.root, 28);
		bTree.insertValue(bTree.root, 29);
		bTree.insertValue(bTree.root, 30);
		bTree.insertValue(bTree.root, 31);
		bTree.insertValue(bTree.root, 32);
		bTree.insertValue(bTree.root, 33);
		bTree.insertValue(bTree.root, 34);
		bTree.insertValue(bTree.root, 35);
		bTree.insertValue(bTree.root, 36);
		bTree.insertValue(bTree.root, 37);
		bTree.insertValue(bTree.root, 38);
		bTree.insertValue(bTree.root, 39);
		
		/*Object[] object = bTree.getNodeByValue(bTree.root, 33);
		System.out.println(object + "\t" + object[1]);*/
		
		
	}
	
}
