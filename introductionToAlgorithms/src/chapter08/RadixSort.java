package chapter08;

/**
 * 
 * 基数排序(基数也就是常说的进制)
 * 基数排序利用数的进制，从低进制位到到高进位逐次按权分组，每一个次完成一个进位上的排序，直到所有所有元素最高进制位都为0为止
 * 
 * 一次分组的过程如下：
 * 1.创建权组
 * 2.把元素按照当前进制为上的权值进行分类
 * 3.将各组内的元素按照组的顺序，连接起来变成新的数组
 * 
 * 可以看到每一次分组都需要分配n个元素
 * 每次分配一个元素需要与进制的值比较次 d
 * 一共需要进行最高进制位次分组  h
 * 算法的复杂度为 n*h*d(这个d可以认为是1)
 * 
 * 关于从高进制位到到底进制位的基数排序
 * 可以使用分治而递归的思想来完成
 * 此算法的复杂度最好的情况为 n*h*d(这个d可以认为是1)
 * 
 * @author 滑德友
 * @since 2018年4月27日15:28:21
 *
 */

public class RadixSort {

	public static void main(String[] args) {

		int[] array={9,12,982,8,13,234,983,2,18,289,245,211,287};
		radixSort1(array,0,2);
		// radixSort2(array,0,array.length - 1,2);
		for (int i : array) {
			System.out.print(i+",");
		}
		
	}

	/**
	 * 
	 * 基数排序
	 * 
	 * @param array
	 *            输入数组
	 * @param currentBase
	 *            当前需要比较进制位(个位0，十位1)
	 * @param maxBase
	 *            最大的进制位
	 */
	public static void radixSort1(int[] array, int currentBase, int maxBase) {

		// 结束条件
		if (currentBase > maxBase) {
			return;
		}

		// 创建分组
		// 每组的最后一个元素用来指示改组从array获得了多少个元素
		int[][] group = new int[10][array.length + 1];
		for (int i = 0; i < 10; i++) {
			group[i][array.length] = 0;
		}

		// 分组
		for (int i = 0; i < array.length; i++) {

			// 得到当前进制位权值
			int currentBaseWeight = (array[i] / ((int) Math.pow(10, currentBase))) % 10;

			// 根据权值分配
			switch (currentBaseWeight) {
			case 0:
				int currentPosition0 = group[0][array.length];
				group[0][currentPosition0] = array[i];
				group[0][array.length] += 1;
				break;
			case 1:
				int currentPosition1 = group[1][array.length];
				group[1][currentPosition1] = array[i];
				group[1][array.length] += 1;
				break;
			case 2:
				int currentPosition2 = group[2][array.length];
				group[2][currentPosition2] = array[i];
				group[2][array.length] += 1;
				break;
			case 3:
				int currentPosition3 = group[3][array.length];
				group[3][currentPosition3] = array[i];
				group[3][array.length] += 1;
				break;
			case 4:
				int currentPosition4 = group[4][array.length];
				group[4][currentPosition4] = array[i];
				group[4][array.length] += 1;
				break;
			case 5:
				int currentPosition5 = group[5][array.length];
				group[5][currentPosition5] = array[i];
				group[5][array.length] += 1;
				break;
			case 6:
				int currentPosition6 = group[6][array.length];
				group[6][currentPosition6] = array[i];
				group[6][array.length] += 1;
				break;
			case 7:
				int currentPosition7 = group[7][array.length];
				group[7][currentPosition7] = array[i];
				group[7][array.length] += 1;
				break;
			case 8:
				int currentPosition8 = group[8][array.length];
				group[8][currentPosition8] = array[i];
				group[8][array.length] += 1;
				break;
			default:
				int currentPosition9 = group[9][array.length];
				group[9][currentPosition9] = array[i];
				group[9][array.length] += 1;
				break;
			}
		}

		// 把分组中的元素按照顺序连接起来
		int putPositon = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < group[i][array.length]; j++) {
				array[putPositon] = group[i][j];
				putPositon++;
			}
		}

		// 进位++
		currentBase++;

		// 递归
		radixSort1(array, currentBase, maxBase);

	}

	/**
	 * 基数排序进位从大到小
	 * 
	 * @param array
	 *            输入数组
	 * @param start
	 *            排序起点下标
	 * @param end
	 *            排序中点下标
	 * @param maxBase
	 *            当前数组最大进位
	 */
	public static void radixSort2(int[] array, int start, int end, int maxBase) {

		// 结束条件
		if (maxBase < 0 || start >= end) {
			return;
		}

		// 创建分组
		// 每组的最后一个元素用来指示改组从array获得了多少个元素
		int subArrayLength = end - start + 1;
		int[][] group = new int[10][subArrayLength + 1];
		for (int i = 0; i < 10; i++) {
			group[i][subArrayLength] = 0;
		}

		// 分组
		for (int i = start; i <= end; i++) {

			// 得到当前最大进制位权值
			int currentMaxBaseWeight = (array[i] / ((int) Math.pow(10, maxBase))) % 10;

			// 根据权值分配
			switch (currentMaxBaseWeight) {
			case 0:
				int currentPosition0 = group[0][subArrayLength];
				group[0][currentPosition0] = array[i];
				group[0][subArrayLength]++;
				break;
			case 1:
				int currentPosition1 = group[1][subArrayLength];
				group[1][currentPosition1] = array[i];
				group[1][subArrayLength]++;
				break;
			case 2:
				int currentPosition2 = group[2][subArrayLength];
				group[2][currentPosition2] = array[i];
				group[2][subArrayLength]++;
				break;
			case 3:
				int currentPosition3 = group[3][subArrayLength];
				group[3][currentPosition3] = array[i];
				group[3][subArrayLength]++;
				break;
			case 4:
				int currentPosition4 = group[4][subArrayLength];
				group[4][currentPosition4] = array[i];
				group[4][subArrayLength]++;
				break;
			case 5:
				int currentPosition5 = group[5][subArrayLength];
				group[5][currentPosition5] = array[i];
				group[5][subArrayLength]++;
				break;
			case 6:
				int currentPosition6 = group[6][subArrayLength];
				group[6][currentPosition6] = array[i];
				group[6][subArrayLength]++;
				break;
			case 7:
				int currentPosition7 = group[7][subArrayLength];
				group[7][currentPosition7] = array[i];
				group[7][subArrayLength]++;
				break;
			case 8:
				int currentPosition8 = group[8][subArrayLength];
				group[8][currentPosition8] = array[i];
				group[8][subArrayLength]++;
				break;
			default:
				int currentPosition9 = group[9][subArrayLength];
				group[9][currentPosition9] = array[i];
				group[9][subArrayLength]++;
				break;
			}
		}

		// 把分组中的元素按照顺序连接起来
		int putPositon = start;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < group[i][subArrayLength]; j++) {
				array[putPositon] = group[i][j];
				putPositon++;
			}
		}

		// 获得分割位置
		int[] sub = new int[11];
		sub[0] = 0;
		for (int i = 1; i < 11; i++) {
			sub[i] = group[i - 1][subArrayLength] + sub[i - 1];
		}

		// 进位++
		maxBase--;

		// 递归
		for (int i = 0; i < 10; i++) {
			radixSort2(array, start + sub[i], start + sub[i + 1] - 1, maxBase);
		}

	}

}
