package chapter09;

/**
 * 
 * 在数组中寻找特殊位置的元素。主要有以下三种方式
 * 
 * 1.最小值 
 * 2.最大值 
 * 3.最小值和最大值 
 * 4.第i大的元素
 * 
 * @author 滑德友
 * @since 2018年4月28日14:28:21
 *
 */
public class SpecialElementInArray {

	public static void main(String[] args) {

		int[] array = { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };

		// System.out.println(minElementInArray(array) + "\t" +
		// maxElementInArray(array));

		// int[] minMaxArray = minAndMaxElementInArray(array);
		// System.out.println(minMaxArray[0] + "\t" + minMaxArray[1]);

		int ithValue = ithElementInArray1(array, 0, array.length - 1, 3);
		System.out.println(ithValue);

		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
		}

	}

	/**
	 * 
	 * 在数组中查找最小值
	 * 
	 * @param array
	 *            输入数组
	 * @return 最小值
	 */
	public static int minElementInArray(int[] array) {

		// 初始条件
		int min = array[0];

		// 寻找最小值
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}

		// 寻找最小值
		return min;

	}

	/**
	 * 
	 * 在数组中查找最大值
	 * 
	 * @param array
	 *            输入数组
	 * @return 最大值
	 */
	public static int maxElementInArray(int[] array) {

		// 初始条件
		int max = array[0];

		// 寻找最大值
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}

		// 返回最大值
		return max;

	}

	/**
	 * 
	 * 在数组中查找最大值和最小值
	 * 
	 * 一对值先比较，然后大的跟max比大，小的跟min比小
	 * 
	 * @param array
	 *            输入数组
	 * @return 最大值和最小值
	 */
	public static int[] minAndMaxElementInArray(int[] array) {

		// 初始条件
		int max = array[0];
		int min = array[0];

		// 分奇偶进行比较
		if (array.length % 2 == 0) {

			// 循环比较每次比较一对
			for (int i = 0; i < array.length; i += 2) {

				if (array[i] > array[i + 1]) {
					if (array[i + 1] < min) {
						min = array[i + 1];
					}
					if (array[i] > max) {
						max = array[i];
					}
				} else {
					if (array[i] < min) {
						min = array[i];
					}
					if (array[i + 1] > max) {
						max = array[i + 1];
					}
				}

			}

		} else {

			// 循环比较每次比较一对
			for (int i = 1; i < array.length; i += 2) {

				if (array[i] > array[i + 1]) {
					if (array[i + 1] < min) {
						min = array[i + 1];
					}
					if (array[i] > max) {
						max = array[i];
					}
				} else {
					if (array[i] < min) {
						min = array[i];
					}
					if (array[i + 1] > max) {
						max = array[i + 1];
					}
				}

			}

		}

		// 组装返回值
		int[] minMaxArray = new int[2];
		minMaxArray[0] = min;
		minMaxArray[1] = max;

		// 返回
		return minMaxArray;

	}

	/**
	 * 
	 * 快速排序的一轮排序
	 * 
	 * @param array
	 *            输入数组
	 * @param start
	 *            起点下标
	 * @param end
	 *            中点下标
	 * @return 关键值下标
	 */
	public static int subithElementInArray(int[] array, int start, int end) {

		// 初始条件
		int i = start;
		int j = start;
		int key = array[end];

		// key固定在end处，j主动且带动i动
		for (; j < end; j++) {

			// 如果该元素<key,则将该元素规划到i处
			if (array[j] < key) {
				int temp1 = array[j];
				array[j] = array[i];
				array[i] = temp1;
				i++;
			}

		}

		// 将key放到中间来
		int temp2 = array[end];
		array[end] = array[i];
		array[i] = temp2;

		return i;

	}

	/**
	 * 
	 * 数组中第i大的元素 借助于快速排序的思想
	 * 
	 * 如果关键元素下标等于ith，则返回该元素 如果关键元素下标大于ith，则在较小的一组中继续查找
	 * 如果关键元素下标小与ith，则在交大的一组中继续查找
	 * 
	 * 算法最坏的情况是每一轮都只有一个长度为0的数组，且要查找的值在另外一个数组中此时复杂度为n^2 算法最好的情况是只进行一轮此时的复杂度为n
	 * 算法期望的情况是每一轮分配的两个数组长度相等，此时复杂度为=n + n/2 + n/4 + n/4 …… +n/(2^lgn) = 2n - n/(2^lgn) = n
	 *
	 * 可以看出该算法的期望还是相当不错的，如果我们能通过一定手段把每一轮的分配都弄得非常均匀，那么这种效率是非常可观的
	 * 第一种每一轮选取比较值的时候随机化(几乎不依赖数据的分布) 
	 * 第二种每一轮选取的比较值为当前一轮中数据中位数的值(对数据的分布有一定依赖性)
	 * 第三种每一轮选取的比较值根据数据分布(跟数据的分布息息相关)
	 * 
	 * @param array
	 *            输入数组
	 * @param start
	 *            起点下标
	 * @param end
	 *            中点下标
	 * @param ith
	 *            第几大
	 * @return 第i大元素
	 * 
	 */
	public static int ithElementInArray1(int[] array, int start, int end, int ith) {

		// 获得关键值下标
		int middle = subithElementInArray(array, start, end);

		// 分情况或是递归或是返回
		if (middle == ith - 1) {
			return array[middle];
		} else if (middle > ith - 1) {
			return ithElementInArray1(array, start, middle - 1, ith);
		} else {
			return ithElementInArray1(array, middle + 1, end, ith);
		}

	}

}
