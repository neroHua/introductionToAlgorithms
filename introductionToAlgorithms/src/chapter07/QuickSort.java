package chapter07;

/**
 * 
 * 快速排序
 * 快速排序运用了分治思想和动态规划思想，类似于归并排序 
 * 归并排序重于，将两个有序数组合并成一个数组
 * 快速排序重点在于将一个数组分成一大一小两部分
 * 
 * 通过一趟排序将要排序的数据分割成独立的两部分
 * 其中一部分的所有数据都比另外一部分的所有数据都要小
 * 然后再按此方法对这两部分数据分别递归进行快速排序
 * 
 * 第一种一趟算法的详细过程如下（原创，借鉴于以下两种）
 * 1.设置两个变量i,j,排序开始的时候：i=start,j=end
 * 2.以start元素作为关键数据，赋值给key，即key=A[start]
 * 3.以i < j为条件，判定A[start]和A[i]
 * 		如果，A[i] < A[start] , i++ ,再次执行3，直到条件为假
 * 		否者，交换A[i]和A[j],j--,再次执行3，直到条件为假
 * 		可以看出i由左向右移动，指示着需要与key比较的元素的最小下标
 * 		start+1到(i-1)代表着与key比较过且 < key的元素
 * 		j由右向左移动，指示着需要与key比较的元素的最大下标
 * 		(j+1)到end代表着与key比较过且>=key的元素
 * 		key固定在一端，i主动，j从动
 * 	因为每次移动只能是i++或者j--，所以当条件为假时，i==j
 * 4.当完成3的时候，key位置和i位置的数据需要排列，以形成如下形式的数组{<key,key,>=key},并返回 key的下标
 * 		如果A[i]<key，交换A[i]和A[start]，返回i
 * 		否者，交换A[i-1]和A[start]，返回i-1
 * 
 * 
 * 第二种一趟的详细过程如下（百度百科）
 * 1.设置两个变量i、j，排序开始的时候：i=start，j=end
 * 2.以第一个数组元素作为关键数据，赋值给key，即key=A[start]
 * 3.从j由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换
 * 4.从i由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换
 *		可以看出i由左向右移动，指示着需要与key比较的元素的最小下标，或是key
 * 		start到(i-1)代表着与key比较过且 < key的元素
 * 		j由右向左移动，指示着需要与key比较的元素的最大下标，或是key
 * 		(j+1)到end代表着与key比较过且>=key的元素
 * 		key随着i，j的联动而移动，始终被放在需要比较的元素内
 * 5.重复3和4，直到i = j，此时i和j同时指向key
 * 
 * 
 * 
 * 第三种一趟的详细过程如下（算法导论）
 * 1.设置两个变量i、j，排序开始的时候：i=start，j=start
 * 2.以最后一个数组元素作为关键数据，赋值给key，即key=A[end]
 * 3.j从左向右移动，条件为j<end
 * 		如果，A[j] > key, j++
 * 		否者，交换A[j]和A[i],j++,i++
 * 		可以看出i从左向右移动，指示着需要与key进行比较且<key的元素，应该被放的下标
 * 		start到(i-1)代表着与key比较过且<key的元素
 * 		j由由左向右移动，指示着需要与key进行比较的元素，且指示着需要与key进行比较且>=key的元素，应该被放的下标
 * 		i到j-1代表着与key比较过且>key的元素
 * 		key被固定在一端，j主动，i从动
 * 4.当完成3的时候，key位置数据需要排列，以形成如下形式的数组{<key,key,>=key},并返回 key的下标
 * 		如果A[i]<key，交换A[i]和A[end]，返回i
 * 
 * 
 * 复杂度为n^2，期望为nlgn
 * 
 * @author 滑德友
 * @since 2018年4月25日17:38:34
 *
 */
public class QuickSort {

	public static void main(String[] args) {

		int[] array={5,4,3,2,1};

		// quickSort1(array,0, array.length-1);

		// quickSort2(array,0, array.length-1);

		quickSort3(array,0, array.length-1);

		for (int i : array) {
			System.out.print(i);
		}

	}


	public static int subQuickSort1(int[] array, int start, int end) {

		int i = start;
		int j = end;
		int key = array[start];

		while(i < j){

			if(array[i] > key){
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				j--;
			}else{
				i++;
			}

		}

		if(array[i] < key){

			int temp = array[i];
			array[i] = array[start];
			array[start] = temp;
			return i;

		}else{

			int temp = array[i-1];
			array[i-1] = array[start];
			array[start] =temp;

			return i -1;

		}

	}

	public static void quickSort1(int[] array, int start ,int end){

		if(start >= end){
			return;
		}

		int middle = subQuickSort1(array ,start , end);

		quickSort1(array, start, middle-1);
		quickSort1(array, middle + 1, end);

		return;

	}

	public static int subQuickSort2(int[] array, int start, int end){

		int i = start;
		int j = end;
		int key = array[start];

		while(i != j){

			if(array[j] < key){

				int temp1 = array[j];
				array[j] = array[i];
				array[i] = temp1;

				i++;

				while(i != j){

					if(array[i] > key){

						int temp2 = array[j];
						array[j] = array[i];
						array[i] = temp2;

						j--;

					}

					i++;

				}

			}else{

				j--;

			}

		}

		return i;

	}

	public static void quickSort2(int[] array, int start ,int end){

		if(start >= end){
			return;
		}

		int middle = subQuickSort2(array ,start , end);

		quickSort2(array, start, middle-1);
		quickSort2(array, middle + 1, end);

		return;

	}

	public static int subQuickSort3(int[] array, int start, int end){


		int i = start;
		int j = start;
		int key = array[end];

		for(; j < end ; j++){

			if(array[j] < key){

				int temp1 = array[j];
				array[j] = array[i];
				array[i] = temp1;

				i++;
			}

		}

		int temp2 = array[end];
		array[end] = array[i];
		array[i] = temp2;

		return i;

	}

	public static void quickSort3(int[] array, int start ,int end){

		if(start >= end){
			return;
		}

		int middle = subQuickSort3(array ,start , end);

		quickSort3(array, start, middle-1);
		quickSort3(array, middle + 1, end);

		return;

	}

}
