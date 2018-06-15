package chapter21;

import java.util.LinkedList;

/**
 * 
 * 数据结构：并查集(一种特殊的集合：注意这里的集合是数学意义上的集合)
 * 
 * 使用LinkedList实现的并查子集的结构
 * 大并查集为LinkedList，内部包含一个或多个子并查集
 * 子并查集为LInkedList，内部包含一个或多个子元素
 * 
 * 并查集主要由三个操作组成
 * 1.创建一个大并查集，内部包含多个子并查集
 * 2.查找一个元素是否在一个并查集中
 * 3.合并两个子并查集
 * 
 * 注意：这一部分的实现均会有bug
 * 原因是：我学习算法导论主要是想学习里面的基础的算法和数据结构
 * 这一部分已经是一种复合的数据结构了，所以，我才没有详细去实现它
 * 
 * 下面的几个算法从原理上讲，创建和查找的复杂度为n，合并的复杂度为1
 * 
 * @author 滑德友
 * 2018年6月14日14:47:16
 *
 */
public class LinkedListDisjionSet {

	LinkedList<LinkedList<Integer>> list = null;
	
	/**
	 * 
	 * 创建一个大的并查集，内部包含多个子并查集，且每个子并查集包含一个元素
	 * 
	 * @param element
	 */
	public LinkedListDisjionSet(int[] element) {

		list = new LinkedList<LinkedList<Integer>>();

		for (int i = 0; i < element.length; i++) {

			LinkedList<Integer> subList = new LinkedList<Integer>();
			subList.add(element[i]);
			list.add(subList);

		}

	}
	
	/**
	 * 
	 * 创建一个大的并查集，内部包含element.length-1个子并查集，且每个子并查集包含element[i]的所有元素
	 * 仅留作相关测试，可惜最后并没有用上
	 * 
	 * @param element
	 */
	public LinkedListDisjionSet(int[][] element) {

		list = new LinkedList<LinkedList<Integer>>();

		for (int i = 0; i < element.length; i++) {

			LinkedList<Integer> subList = new LinkedList<Integer>();
			for (int j = 0; j < element[i].length; j++) {
				subList.add(element[i][j]);
			}
			list.add(subList);

		}

	}

	/**
	 * 
	 * 找到某个元素所在的子并查集
	 * 
	 * @param element
	 * @return
	 */
	public LinkedList<Integer> findDisjoinSet(Integer element) {

		for (int i = 0; i < list.size(); i++) {
			
			LinkedList<Integer> subList = list.get(i);
			
			if(subList.contains(element)) {
				return subList;
			}
			
		}

		return null;

	}
	
	/**
	 * 
	 * 合并两个子并查集
	 * 
	 * @param list1
	 * @param list2
	 */
	public void unionDisjoinSet(LinkedList<Integer> list1, LinkedList<Integer> list2) {
		
		list1.addAll(list2);
		
	}
	
}
