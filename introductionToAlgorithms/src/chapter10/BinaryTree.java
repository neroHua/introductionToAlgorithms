package chapter10;

public class BinaryTree {

	BinaryTreeNode root;

	public BinaryTree() {

		root = null;

	}

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

	public void updateRootNode(Object value) {

		if (root == null) {
			return;
		}

		root.value = value;

	}

	public BinaryTreeNode getRootNode() {

		return root;

	}

	public void deleteRootNode() {

		root = null;

	}

	public void addChildNodeByParent(BinaryTreeNode parent, Object value) {

		if (parent.left != null && parent.right != null) {
			System.out.println("左右子树都满，不能在该父节点上增加子节点了");
			return;
		}

		BinaryTreeNode node = new BinaryTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = parent;

		if (parent.left == null) {
			parent.left = node;
		} else if (parent.right == null) {
			parent.right = node;
		}

	}

	public void addLeftChildNodeByParent(BinaryTreeNode parent, Object value) {

		if (parent.left != null) {
			System.out.println("左子树不为空，不能增加左节点了");
			return;
		}

		BinaryTreeNode node = new BinaryTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = parent;
		parent.left = node;

	}

	public void addRightChildNodeByParent(BinaryTreeNode parent, Object value) {

		if (parent.left != null || parent.right != null) {
			System.out.println("右子树不为空，不能增加右子节点了");
			return;
		}

		BinaryTreeNode node = new BinaryTreeNode();
		node.value = value;
		node.left = null;
		node.right = null;
		node.parent = parent;
		parent.right = node;

	}

	public void updateNode(BinaryTreeNode node, Object value) {

		node.value = value;

	}

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

	public BinaryTreeNode getNodeByValue1(BinaryTreeNode parent, Object value) {

		if (parent == null) {
			System.out.println("父节点已经没有子节点了");
			return null;
		}

		if( parent.value.equals(value)){
			return parent;
		}else{

			BinaryTreeNode left = getNodeByValue1(parent.left, value);
			BinaryTreeNode right = getNodeByValue1(parent.right, value);

			if(left!=null){
				return left;
			}if(right != null){
				return right;
			}else{
				return null;
			}

		}

	}

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
			showMyself1(node.left);
			showMyself1(node.right);
			System.out.print(node.value);

		}

	}

	public void showMyself2(BinaryTreeNode node){
		
		
		
	}
	
	public static void main(String[] args) {

		BinaryTree binaryTree = new BinaryTree();

		/*binaryTree.addRootNode(1);
		System.out.println(binaryTree.getRootNode().value);
		System.out.println(binaryTree.root.value);
		binaryTree.updateRootNode(3);
		System.out.println(binaryTree.root.value);*/

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

	}

}
