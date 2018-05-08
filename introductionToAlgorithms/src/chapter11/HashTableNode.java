package chapter11;

import java.util.LinkedList;

/**
 * 
 * 哈希表的节点
 * 内部使用linkedList来解决冲突问题
 * 
 * @author 滑德友
 * @since 2018年5月8日10:43:16
 *
 */
public class HashTableNode {

	LinkedList<HashTableElement> linkedList = new LinkedList<HashTableElement>();

}
