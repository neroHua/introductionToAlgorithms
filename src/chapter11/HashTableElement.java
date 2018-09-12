package chapter11;

/**
 * 
 * 哈希表的元素
 * 放在节点的linkedList李曼
 * 
 * @author 滑德友
 * @since 2018年5月8日10:32:42
 *
 */
public class HashTableElement {

	int key;
	Object value;

	/**
	 * 
	 * 创建一个哈希元素
	 * 
	 * @param key
	 *            元素的关键值
	 * @param value
	 *            元素的值即数据
	 */
	public HashTableElement(int key, Object value) {

		this.key = key;
		this.value = value;

	}
	
}
