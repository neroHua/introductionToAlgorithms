package chapter08;

/**
 * 
 * 计数排序
 * 
 * 计数排序是一种根据比自己小或等于的元素的个数，直接把该元素放到指定为位置的算法 此时我们需要一个输入inputArray,和一个outputArray
 * 以及一个非常特殊的存储assistArray元素和比元素小的元素的个数的辅助数组
 * 这里的元素即为assistArray数组的下标，比元素小或者等于的元素的个数即为assistArray数组的值
 * 
 * 算法过程如下： 
 * 1.构建assitArray,assitArray的长度大于等于inputArray中的最大值，我这里取inputArray中的最大值+1
 * 2.计算inputArray中每个值出现的个数，并放入到assitArray
 * 3.计算i从1到assitArray.length-1，累加计算assitArray[i] = assitArray[i-1] + assitArray[i]，得到比元素小或者等于元素的个数
 * 4.根据inputArray中的元素值，在assitArray中查找比它小或者等于他的元素的个数（为了防止相等的元素放到同一个位置上，assistArray数组的值--）
 * 
 * 最长的一次执行循环取决于n和inpuArray中的最大值
 * 算法的复杂度为max(n,max(inputArray))
 * 
 * @author 滑德友
 * @since 2018年4月26日17:30:42
 *
 */
public class CountSort {

	public static void main(String[] args) {

		int[] inputArray = { 2, 5, 3, 0, 2, 3, 0, 3, 7, 8 };

		int[] outputArray = countSort(inputArray);

		for (int i : outputArray) {
			System.out.print(i);
		}

	}

	/**
	 * 
	 * 获得数组的最大值
	 * 
	 * @param inputArray 数组
	 * @return 数组的最大值
	 */
	public static int maxValueOfArray(int[] inputArray) {

		// 初始条件
		int max = inputArray[0];

		// 寻找最大值
		for (int i = 1; i < inputArray.length; i++) {
			if (inputArray[i] > max) {
				max = inputArray[i];
			}
		}

		// 返回最大值
		return max;

	}

	/**
	 * 
	 * 计数排序
	 * 
	 * @param inputArray 输入数组
	 * @return 排序后的数组
	 */
	public static int[] countSort(int[] inputArray) {

		// 创建输出数组
		int[] outputArray = new int[inputArray.length];

		// 创建辅助数组
		int max = maxValueOfArray(inputArray);
		int[] assistArray = new int[max + 1];
		for (int i = 1; i < assistArray.length; i++) {
			assistArray[i] = 0;
		}

		// 求得输入数组每个值的个数
		for (int i : inputArray) {
			assistArray[i]++;
		}

		// 求得输入数组小于等于该它的值元素的总个数
		for (int i = 1; i < assistArray.length; i++) {
			assistArray[i] += assistArray[i - 1];
		}

		// 把输入数组的里元素，在辅助数组中查找小于等于它的元素的个数，然后放到输出数组中去
		// 注意这种从左往右的方式是不稳定的，从右往左是稳定的(如果相同值的元素在排序后，如果相对位置不变则为稳定，改变则不稳定)
		for (int i : inputArray) {
			int outputIndex = assistArray[i];
			outputArray[outputIndex - 1] = i;
			// 防止相同值的元素放在同一个位置上
			assistArray[i]--;
		}

		return outputArray;

	}

}
