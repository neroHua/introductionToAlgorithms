package chapter10;

/**
 * 
 * 数据结构：链表
 * 
 * 由一系列的节点组成
 * 每个节点由三个部分组成:1.指向上一个节点地址的变量 2.当前节点的值 3.下一个节点的地址的变量
 * 由数据结构可以看到在中间进行插入和删除的时候效率会比较高一点
 * 
 * 由于java没有指针这一概念，以下就用一个二维数组来实现一个链表
 * 
 * @author 滑德友
 * @since 2018年5月2日15:57:44
 *
 */
public class LinkList1 {

	int frist;
	int last;
	int[][] array;
	int currentSize;

	/**
	 * 
	 * 创建一个指定长度的链表
	 * 
	 * @param size
	 *            链表的长度
	 * @param element
	 *            链表的第一个长度
	 */
	public LinkList1(int size, int element) {

		// 初始化数组
		// -1代表着当前一维数组空闲
		array = new int[size][3];
		for (int i = 0; i < size; i++) {
			array[i][0] = -1;
			array[i][2] = -1;
		}

		// 初始化第一个元素
		// -2代表起点或者是节点
		array[0][0] = -2;
		array[0][1] = element;
		array[0][2] = -2;

		// 初始化其他变量
		frist = 0;
		last = 0;
		currentSize = 1;

	}

	/**
	 * 
	 * 在最后面增加一个元素
	 * 
	 * @param value
	 *            元素的值
	 * 
	 */
	public void addElement(int value) {

		// 非法输入
		if (currentSize >= array.length) {
			System.out.println("linkListOverFlow");
			return;
		}

		// 找到一个空闲的位置，把数据插入其中
		// 修改尾节点的相关信息
		for (int i = 0; i < array.length; i++) {
			if (array[i][0] == -1) {
				array[i][0] = last;
				array[i][1] = value;
				array[i][2] = -2;
				array[last][2] = i;
				last = i;
				currentSize++;
				return;
			}
		}

	}

	/**
	 * 
	 * 根据下标查找对应的元素
	 * 
	 * @param index
	 *            元素的下标
	 * @return 元素值
	 */
	public int getElementByIndex(int index) {

		// 非法输入
		if (index < 0 || index > currentSize - 1) {
			System.out.println("linkListIndexOutOfBounds");
			return -1;
		}

		// 下表为0直接返回
		// 下标不为0,先根据下标迭代找到其在array[]中的下标
		if (index == 0) {
			return array[frist][1];
		} else {
			int arrayIndex = array[frist][2];
			for (int i = 1; i < index; i++) {
				arrayIndex = array[arrayIndex][2];
			}
			return array[arrayIndex][1];
		}

	}

	/**
	 * 
	 * 根据元素查找对应的下标
	 * 
	 * @param value
	 *            元素值
	 * @return 元素的下标
	 */
	public int getElementByValue(int value) {

		// 如果下标为0满足，直接返回
		// 下标不为0,先根据下标迭代，比较，相等则返回
		if (array[frist][1] == value) {
			return 0;
		} else {

			int arrayIndex = array[frist][2];
			for (int i = 1; i < currentSize; i++) {
				if (array[arrayIndex][1] == value) {
					return i;
				} else {
					arrayIndex = array[arrayIndex][2];
				}
			}

			return -1;
		
		}

	}

	/**
	 * 
	 * 根据下标删除一个元素
	 * 
	 * @param index
	 *            元素的下标
	 */
	public void deleteElementByIndex(int index) {

		// 非法输入
		if (index < 0 || index > currentSize - 1) {
			System.out.println("linkListIndexOutOfBounds");
			return;
		}

		// 若为起点，修改起点信息
		// 若为终点，修改终点信息
		// 若为中间点，修改前后信息
		if (index == 0) {

			int arrayIndex = array[frist][2];
			array[arrayIndex][0] = -2;

			array[frist][0] = -1;
			array[frist][1] = 0;
			array[frist][2] = -1;

			frist = arrayIndex;

		} else if (index == currentSize - 1) {

			int arrayIndex = array[last][0];
			array[arrayIndex][2] = -2;

			array[last][0] = -1;
			array[last][1] = 0;
			array[last][2] = -1;

			last = arrayIndex;

		} else {

			// 找到被删除元素在array[]的下标
			int arrayIndex = array[frist][2];
			for (int i = 1; i < index; i++) {
				arrayIndex = array[arrayIndex][2];
			}

			int previousIndex = array[arrayIndex][0];
			int nextIndex = array[arrayIndex][2];

			array[previousIndex][2] = nextIndex;
			array[nextIndex][0] = previousIndex;

			array[arrayIndex][0] = -1;
			array[arrayIndex][1] = 0;
			array[arrayIndex][2] = -1;

		}

		currentSize--;

	}

	/**
	 * 
	 * 根据元素值删除一个元素
	 * 
	 * @param value
	 *            元素值
	 */
	public void deleteElementByValue(int value) {

		// 若为起点，修改起点信息
		// 若为终点，修改终点信息
		// 若为中间点，修改前后信息
		if (array[frist][1] == value) {

			int arrayIndex = array[frist][2];
			array[arrayIndex][0] = -2;

			array[frist][0] = -1;
			array[frist][1] = 0;
			array[frist][2] = -1;

			frist = arrayIndex;
			currentSize--;
			
		} else if (array[last][1] == value) {

			int arrayIndex = array[last][0];
			array[arrayIndex][2] = -2;

			array[last][0] = -1;
			array[last][1] = 0;
			array[last][2] = -1;

			last = arrayIndex;
			currentSize--;
			
		} else {

			// 找到被删除元素在array[]的下标
			int arrayIndex = array[frist][2];
			for (int i = 1; i < currentSize; i++) {

				if (array[arrayIndex][1] == value) {

					int previousIndex = array[arrayIndex][0];
					int nextIndex = array[arrayIndex][2];

					array[previousIndex][2] = nextIndex;
					array[nextIndex][0] = previousIndex;

					array[arrayIndex][0] = -1;
					array[arrayIndex][1] = 0;
					array[arrayIndex][2] = -1;

					currentSize--;
					break;

				} else {
					arrayIndex = array[arrayIndex][2];
				}

			}

		}

	}

	/**
	 * 
	 * 根据指定位置插入元素
	 * 
	 * @param index
	 *            元素下标
	 * @param value
	 *            元素值
	 */
	public void insertElementByIndex(int index, int value) {

		// 非法输入
		if (index < 0 || index > currentSize - 1 || currentSize >= array.length) {
			System.out.println("linkListIndexOutOfBounds");
			return;
		}

		// 找到一个空闲的位置，把数据插入其中
		int arrayIndex = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i][0] == -1) {
				arrayIndex = i;
				break;
			}
		}

		// 若为起点，修改起点信息
		// 若为中间点，修改前后信息
		if (index == 0) {

			array[frist][0] = arrayIndex;

			array[arrayIndex][0] = -2;
			array[arrayIndex][1] = value;
			array[arrayIndex][2] = frist;

			frist = arrayIndex;

		} else {

			// 找到被删除元素在array[]的下标
			int currentArrayIndex = array[frist][2];
			for (int i = 1; i < index; i++) {
				currentArrayIndex = array[currentArrayIndex][2];
			}

			int previousIndex = array[currentArrayIndex][0];
			int nextIndex = currentArrayIndex;
			array[previousIndex][2] = arrayIndex;
			array[nextIndex][0] = arrayIndex;

			array[arrayIndex][0] = previousIndex;
			array[arrayIndex][1] = value;
			array[arrayIndex][2] = nextIndex;

		}

		currentSize++;

	}
	
	public static void main(String[] args) {

		LinkList1 linkList1 = new LinkList1(4, 11);

		/*linkList1.addElement(12);
		linkList1.addElement(13);
		linkList1.addElement(14);

		linkList1.addElement(21);
		linkList1.addElement(22);
		linkList1.addElement(21);
		linkList1.addElement(22);

		System.out.println(linkList1.getElementByIndex(-1));
		System.out.println(linkList1.getElementByIndex(0));
		System.out.println(linkList1.getElementByIndex(1));
		System.out.println(linkList1.getElementByIndex(2));
		System.out.println(linkList1.getElementByIndex(3));
		System.out.println(linkList1.getElementByIndex(4));

		System.out.println(linkList1.getElementByValue(0));
		System.out.println(linkList1.getElementByValue(11));
		System.out.println(linkList1.getElementByValue(12));
		System.out.println(linkList1.getElementByValue(13));
		System.out.println(linkList1.getElementByValue(14));
		System.out.println(linkList1.getElementByValue(15));

		linkList1.deleteElementByIndex(2);
		linkList1.deleteElementByIndex(0);
		linkList1.deleteElementByIndex(1);
		linkList1.deleteElementByIndex(2);

		linkList1.deleteElementByValue(13);
		linkList1.deleteElementByValue(11);
		linkList1.deleteElementByValue(14);
		linkList1.deleteElementByValue(16);

		linkList1.deleteElementByIndex(2);
		linkList1.addElement(81);
		linkList1.deleteElementByIndex(0);
		linkList1.addElement(82);
		linkList1.deleteElementByIndex(3);
		linkList1.addElement(83);

		linkList1.deleteElementByValue(13);
		linkList1.addElement(81);
		linkList1.deleteElementByValue(11);
		linkList1.addElement(82);
		linkList1.deleteElementByValue(14);
		linkList1.addElement(83);*/

		linkList1.insertElementByIndex(0, 12);
		linkList1.insertElementByIndex(1, 13);
		linkList1.insertElementByIndex(2, 14);

	}

}
