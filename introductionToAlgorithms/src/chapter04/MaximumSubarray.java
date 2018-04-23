package chapter04;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类主要用于解决一下问题
 * 	在给定的数组中寻找和的值是最大的子数组
 * 
 * 使用的算法是分治算法
 * 	一轮算法的过程是如下：
 * 		1.尽量将数组A[left,right]拆分成两个长度相等的数组A[left,mid]和A[mid + 1,right]
 * 		2.此时在A[left,right]中寻找和的值最大的子数组问题就变成了已下三个小问题：
 * 			1.在A[left,mid]中寻找和的最大的值的子数组(递归进行)
 * 			2.寻找在以A[left,mid]挑选起点i和在A[mid+1,right]挑选结点j组成的一系列数组A[i,j]中，找到最大的A[i,j]
 * 			3.在A[mid,right]中寻找和的最大的值的子数组(递归进行)
 *		3.在寻找和最大的A[i,j]的算法如下：
 *			1.在A[i,mid]中找到和的值最大的子数组
 *				从i = mid开始递减，计算A[i,mid]和值，保留最大的和值maxValueLeft和i，直到i = left
 *			2.在A[mid,j]中找到和的值最大的子数组
 *				从j = mid开始递增，计算A[mid,j]和值，保留最大的和值maxValueRight和j，直到j = right
 *			3.maxValue = maxValueLeft + maxValueRight,数组为A[i,j]
 *	算法的复杂度为：o(n*log(n))对于只寻找一个解时，o(n*n*log(n))对于寻找多个解时
 * @author 滑德友
 * @since 2018年4月9日09:22:12
 *
 */
public class MaximumSubarray {

	public static void main(String[] args) {

		// int[] a = {0,0,0,0};
		int[] a = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		List<int[]> result = maximumSubarray(a, 0, a.length-1);
		for (int[] is : result) {
			for (int i = 0; i < is.length; i++) {
				System.out.print(is[i]+",");
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @param a[] 要被求解的数组
	 * @param left 起点下标
	 * @param right 结点下标
	 * @return 最大值及所有的和为最大值的子数组的起点下标和结点下标
	 */
	public static List<int[]> maximumSubarray(int[] a, int left, int right){

		ArrayList<int[]> result = new ArrayList<int[]>();

		// 非法输入
		if(left > right){
			int[] result1 = new int[3];
			result1[0] = 0;
			result1[1] = 0;
			result1[2] = 0;
			result.add(result1);
			return result;
		}

		// 递归终止条件
		if(left == right){
			int[] result1 = new int[3];
			result1[0] = a[left];
			result1[1] = left;
			result1[2] = right;
			result.add(result1);
			return result;
		}

		// 求取中点下标
		int mid = (left + right) / 2;

		// 分解成三个子问题求解
		List<int[]> resultLeft = maximumSubarray(a, left, mid);
		List<int[]> resultCross = maximumCrossSubarray(a, left, mid, right);
		List<int[]> resultRight = maximumSubarray(a, mid + 1, right);

		// 根据三个子问题的解，返回原问题的解
		// 每个解可能包含多个解，相等时需要全部返回
		int[] resultLeft1 = resultLeft.get(0);
		int[] resultCross1 = resultCross.get(0);
		int[] resultRight1 = resultRight.get(0);
		if(resultLeft1[0] > resultCross1[0] && resultLeft1[0] > resultRight1[0]){
			return resultLeft;
		}else if(resultLeft1[0] > resultCross1[0] && resultLeft1[0] == resultRight1[0]){
			for (int[] i : resultLeft) {
				result.add(i);
			}
			for (int[] i : resultRight) {
				result.add(i);
			}
			return result;
		}else if(resultLeft1[0] > resultCross1[0] && resultLeft1[0] < resultRight1[0]){
			return resultRight;
		}else if(resultLeft1[0] == resultCross1[0] && resultLeft1[0] > resultRight1[0]){
			for (int[] i : resultLeft) {
				result.add(i);
			}
			for (int[] i : resultCross) {
				result.add(i);
			}
			return result;
		}else if(resultLeft1[0] == resultCross1[0] && resultLeft1[0] == resultRight1[0]){
			for (int[] i : resultLeft) {
				result.add(i);
			}
			for (int[] i : resultCross) {
				result.add(i);
			}
			for (int[] i : resultRight) {
				result.add(i);
			}
			return result;
		}else if(resultLeft1[0] == resultCross1[0] && resultLeft1[0] < resultRight1[0]){
			return resultRight;
		}else if(resultLeft1[0] < resultCross1[0] && resultLeft1[0] > resultRight1[0]){
			return resultCross;
		}else if(resultLeft1[0] < resultCross1[0] && resultLeft1[0] == resultRight1[0]){
			return resultCross;
		}else{
			if(resultCross1[0] > resultRight1[0]){
				return resultCross;
			}else if(resultCross1[0] == resultRight1[0]){
				for (int[] i : resultCross) {
					result.add(i);
				}
				for (int[] i : resultRight) {
					result.add(i);
				}
				return result;
			}else{
				return resultRight;
			}
		}

	}

	/**
	 * 
	 * @param a[] 要被求解的数组
	 * @param left 起点下标
	 * @param mid 中点下标
	 * @param right 结点下标
	 * @return 最大值及所有的和为最大值的子数组的起点下标和结点下标，且所有的子数组必定包含中点下标所在的元素
	 */
	public static List<int[]> maximumCrossSubarray(int[] a, int left, int mid, int right){

		// 非法输入
		if(left > mid || mid > right || left > right){
			ArrayList<int[]> result = new ArrayList<int[]>();
			int[] result1 = new int[3];
			result1[0] = 0;
			result1[1] = 0;
			result1[2] = 0;
			result.add(result1);
			return result;
		}

		// 存放解的容器
		ArrayList<int[]> result = new ArrayList<int[]>();
		ArrayList<int[]> resultLeft = new ArrayList<int[]>();
		ArrayList<int[]> resultRight = new ArrayList<int[]>();

		// 左边初始化
		int sumLeft = 0;
		int i = mid;
		int[] resultLeft1 = new int[2];
		int maxSumLeft = a[i];
		resultLeft1[0] = maxSumLeft;
		resultLeft1[1] = i;
		resultLeft.add(resultLeft1);

		// 左边循环，寻找A[i,mid]中的最大解
		for(; i >= left ; i--){
			sumLeft = a[i] +sumLeft;
			if(maxSumLeft < sumLeft){
				maxSumLeft = sumLeft;
				int[] resultLeft2 = new int[2];
				resultLeft2[0] = maxSumLeft;
				resultLeft2[1] = i;
				resultLeft.clear();
				resultLeft.add(resultLeft2);
			}else if(maxSumLeft == sumLeft && i != mid){
				int[] resultLeft2 = new int[2];
				resultLeft2[0] = maxSumLeft;
				resultLeft2[1] = i;
				resultLeft.add(resultLeft2);
			}
		}

		// 左边初始化
		int sumRight = 0;
		int j = mid + 1;
		int[] resultRight1 = new int[2];
		int maxSumRight = a[j];
		resultRight1[0] = maxSumRight;
		resultRight1[1] = j;
		resultRight.add(resultRight1);

		// 右边循环，寻找A[mid,j]中的最大解
		for(; j <= right ; j++){
			sumRight = a[j] +sumRight;
			if(maxSumRight < sumRight){
				maxSumRight = sumRight;
				int[] resultRight2 = new int[2];
				resultRight2[0] = maxSumRight;
				resultRight2[1] = j;
				resultRight.clear();
				resultRight.add(resultRight2);
			}else if(maxSumRight == sumRight && j != mid + 1){
				int[] resultRight2 = new int[2];
				resultRight2[0] = maxSumRight;
				resultRight2[1] = j;
				resultRight.add(resultRight2);
			}
		}

		// 根据左边跟右边的求得的最大值得所有下标进行组合
		for(int m = 0; m < resultLeft.size(); m++ ){
			for(int n = 0; n < resultRight.size(); n++){
				int[] result1 = new int[3];
				result1[0] = resultLeft.get(m)[0] + resultRight.get(n)[0];
				result1[1] = resultLeft.get(m)[1];
				result1[2] = resultRight.get(n)[1];
				result.add(result1);
			}
		}

		return result;

	}

}
