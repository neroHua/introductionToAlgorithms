package chapter11;

import java.util.LinkedList;

/**
 * 
 * 数据结构：哈希表
 * 
 * 哈希表是一种能够把大量的数据通过计算数据的类别值来分类到不同类别中的一种数据结构
 * 哈希表包含两部分
 * 1.分类的方法
 * 2.存储数据的容器
 * 
 * 注意，为了解决冲突问题（不同的数据分到同一个类别）数据的容器可以选择内嵌链表的方式来存储
 * 
 * @author 滑德友
 * @since 2018年5月8日11:33:26
 *
 */
public class HashTable {

	HashTableNode[] hashTableNodeArray;

	/**
	 * 
	 * 创建一个空的哈希表
	 * 
	 * @param size
	 *            哈希表的类别的个数
	 */
	public HashTable(int size) {

		if (size < 0) {
			return;
		}

		hashTableNodeArray = new HashTableNode[size];
		for (int i = 0; i < hashTableNodeArray.length; i++) {
			hashTableNodeArray[i] = new HashTableNode();
		}

	}

	/**
	 * 
	 * 获得类别的方法
	 * 
	 * @param key
	 *            元素的关键值
	 * @return 类别值
	 */
	public int getHashArrayIndex(int key) {

		return key % hashTableNodeArray.length;

	}

	/**
	 * 
	 * 增加一个数据
	 * 
	 * @param key
	 *            元素的关键值
	 * @param value
	 *            元素的值
	 */
	public void addElement(int key, Object value) {

		HashTableElement hashTableElement = new HashTableElement(key, value);
		int index = getHashArrayIndex(key);
		hashTableNodeArray[index].linkedList.add(hashTableElement);

	}

	/**
	 * 
	 * 根据关键值获取数据
	 * 
	 * @param key
	 *            元素的关键值
	 * @return 数据
	 */
	public Object getElement(int key) {

		int index = getHashArrayIndex(key);
		LinkedList<HashTableElement> linkedList = hashTableNodeArray[index].linkedList;
		for (int i = 0; i < linkedList.size(); i++) {

			if (linkedList.get(i).key == key) {
				return linkedList.get(i).value;
			}

		}

		return null;

	}

	/**
	 * 
	 * 根据关键值删除数据
	 * 
	 * @param key
	 *            元素的关键值
	 */
	public void deleteElement(int key) {

		int index = getHashArrayIndex(key);
		LinkedList<HashTableElement> linkedList = hashTableNodeArray[index].linkedList;
		for (int i = 0; i < linkedList.size(); i++) {

			if (linkedList.get(i).key == key) {
				linkedList.remove(i);
			}

		}

	}

	public static void main(String[] args) {

		HashTable hashTable = new HashTable(3);

		hashTable.addElement(0, 1);
		hashTable.addElement(1, 2);
		hashTable.addElement(2, 3);
		hashTable.addElement(3, 4);

		System.out.println(hashTable.getElement(0));
		System.out.println(hashTable.getElement(1));
		System.out.println(hashTable.getElement(2));
		System.out.println(hashTable.getElement(3));

	}

}
