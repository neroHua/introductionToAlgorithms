package chapter10;

/**
 * 
 * 数据结构：链表
 * 
 * 由一系列的节点组成
 * 每个节点由三个部分组成:1.指向上一个节点地址的变量 2.当前节点的值 3.下一个节点的地址的变量
 * 由数据结构可以看到在中间进行插入和删除的时候效率会比较高一点
 * 
 * 由于java虽然没有指针，但是引用型的变量其实是就是一个地址，跟指针没有太大的区别，以下用面向对象的方式实现
 * 
 * @author 滑德友
 * @since 2018年5月3日17:10:23
 *
 */
public class LinkList2 {

	LinkList2Node frist;
	LinkList2Node last;
	int size;

	/**
	 * 
	 * 创建一个空的LinkList
	 */
	public LinkList2() {

		frist = null;
		last = null;
		size = 0;

	}

	/**
	 * 
	 * 在最后面增加一个节点
	 * 
	 * @param element
	 *            节点的值
	 */
	public void addElement(Object element) {

		// 创建节点
		LinkList2Node node = new LinkList2Node();
		node.value = element;

		// 当没有节点时，插入一个新的节点
		// 当有节点时，插入并修改尾节点
		if (size == 0) {
			node.provious = null;
			node.next = null;
			frist = node;
			last = node;
		} else {
			last.next = node;
			node.provious = last;
			node.next = null;
			last = node;
		}

		size++;

	}

	/**
	 * 
	 * 根据下标查找对应的节点
	 * 
	 * @param index
	 *            节点的下标
	 * @return 节点的值
	 * 
	 */
	public Object getElementByIndex(int index) {

		// 非法输入
		if (index < 0 || index > size - 1) {
			return null;
		}

		// 当为0时，返回首节点的值
		// 不为0时，迭代找到该节点，并返回该节点的值
		LinkList2Node node = frist;
		if (index == 0) {
			return node.value;
		} else {
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
			return node.value;
		}

	}

	/**
	 * 
	 * 根据节点的值查找对应的下标
	 * 
	 * @param value
	 *            节点的值
	 * @return 节点的下标
	 */
	public int getElementByValue(Object value) {

		LinkList2Node node = frist;

		// 当为首节点时，直接返回0
		// 不为首节点时，循环比较节点的值
		if (node.value.equals(value)) {
			return 0;
		} else {
			for (int i = 1; i < size; i++) {
				node = node.next;
				if (node.value.equals(value)) {
					return i;
				}
			}
			return -1;
		}

	}

	/**
	 * 
	 * 根据下标删除一个节点
	 * 
	 * @param index
	 *            节点的下标
	 */
	public void deleteElementByIndex(int index) {

		// 非法输入
		if (index < 0 || index > size - 1) {
			return;
		}

		// 若为首节点，修改首节点信息
		// 若为尾节点，修改尾节点信息
		// 若为中间点，修改前后信息
		LinkList2Node node = frist;
		if (index == 0) {
			node = frist.next;
			node.provious = null;
			frist = node;
		} else if (index == size - 1) {
			node = last.provious;
			node.next = null;
			last = node;
		} else {

			// 循环找到该节点
			for (int i = 1; i <= index; i++) {
				node = node.next;
			}
			node.provious.next = node.next;
			node.next.provious = node.provious;
		}

		size--;

	}

	/**
	 * 
	 * 根据节点的值删除一个节点
	 * 
	 * @param value
	 *            节点的值
	 */
	public void deleteElementByValue(Object value) {

		// 若为首节点，修改首节点信息
		// 若为尾节点，修改尾节点信息
		// 若为中间点，修改前后信息
		LinkList2Node node = frist;
		if (node.value.equals(value)) {
			node = frist.next;
			node.provious = null;
			frist = node;
			size--;
		} else if (last.value.equals(value)) {
			node = last.provious;
			node.next = null;
			last = node;
			size--;
		} else {

			// 循环比较节点
			for (int i = 1; i < size; i++) {
				node = node.next;
				if (node.value.equals(value)) {
					node.provious.next = node.next;
					node.next.provious = node.provious;
					size--;
					break;
				}
			}

		}

		return;

	}

	public void insertElementByIndex(int index, Object element) {

		// 非法输入
		if (index < 0 || index > size) {
			return;
		}

		LinkList2Node node = new LinkList2Node();
		node.value = element;

		// 若为首节点，修改首节点信息
		// 若为其他点，修改前后信息
		if (index == 0) {

			// size为0，直接插入
			if (size == 0) {
				node.provious = null;
				node.next = null;
				frist = node;
				last = node;
			} else {
				node.next = frist;
				node.provious = null;
				frist.provious = node;
				frist = node;
			}

		} else {
			LinkList2Node nodeOld = frist;
			for (int i = 1; i <= index; i++) {
				nodeOld = nodeOld.next;
			}
			nodeOld.provious = node;
			nodeOld.provious.next = node;
			node.provious = nodeOld.provious;
			node.next = nodeOld;
		}

		size++;

	}

	public static void main(String[] args) {

		LinkList2 linkList2 = new LinkList2();

		/*linkList2.addElement(1);
		linkList2.addElement(2);
		linkList2.addElement(3);
		linkList2.addElement(4);

		System.out.println(linkList2.getElementByIndex(-1));
		System.out.println(linkList2.getElementByIndex(0));
		System.out.println(linkList2.getElementByIndex(1));
		System.out.println(linkList2.getElementByIndex(2));
		System.out.println(linkList2.getElementByIndex(3));
		System.out.println(linkList2.getElementByIndex(4));

		System.out.println(linkList2.getElementByValue(0));
		System.out.println(linkList2.getElementByValue(1));
		System.out.println(linkList2.getElementByValue(2));
		System.out.println(linkList2.getElementByValue(3));
		System.out.println(linkList2.getElementByValue(4));
		System.out.println(linkList2.getElementByValue(5));

		linkList2.deleteElementByIndex(2);
		linkList2.deleteElementByIndex(0);
		linkList2.deleteElementByIndex(1);
		linkList2.deleteElementByIndex(-1);
		linkList2.deleteElementByIndex(12);

		linkList2.deleteElementByValue(3);
		linkList2.deleteElementByValue(1);
		linkList2.deleteElementByValue(4);
		linkList2.deleteElementByIndex(15);*/

		linkList2.insertElementByIndex(0, 1);
		linkList2.insertElementByIndex(0, 2);
		linkList2.insertElementByIndex(0, 3);
		linkList2.insertElementByIndex(0, 4);
		linkList2.insertElementByIndex(0, 5);
		linkList2.insertElementByIndex(0, 6);

	}

}
