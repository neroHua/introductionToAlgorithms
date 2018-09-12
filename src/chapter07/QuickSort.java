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
 * 第一种一趟算法的详细过程如下（借鉴于以下两种）
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
 * 此一轮算法的复杂度为end-start+1
 * 
 * 第二种一趟的详细过程如下（C. A. R. Hoare）
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
 * 此一轮算法的复杂度为end-start+1
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
 * 此一轮算法的复杂度为end-start+1
 * 
 * 该方法的递归使用递归方法T(start,end) = T(start,middle-1) + T(middle+1,end) + a(end-start+1) + b
 * 最坏的情况是一直出现middle = start，或者middle=end
 * 即是被分成的两组数据，一组是key，另一组全是比key大或者小的数据
 * 相当于每轮得到一个key，一共进行end-start轮，复杂度为(end-start+1)*(end-start) 即是n^2
 * 
 * 最好的情况是每次middle = (start+end) / 2
 * 此时T(start,end) = T(start,middle-1) + T(middle+1,end) + a(end-start+1) + b
 * 可以写成T(n)= 2T(n/2) + a*n + b  其中(n = end - start + 1)
 * 此时的复杂度为nlgn
 * 
 * 直观上看，最坏的情况的子节点永远只有一个，最好的情况子节点的永远比重一样
 * 而大多数情况是子节点有两个且比重不一样，还是一棵树，期望值应当接近nlgn
 * 
 * 复杂度为n^2，期望为nlgn
 * 
 * 关于随机版本的快速排序
 * 随机指在选定key的时候，不是指定（start或者是end）而是随机生成一个key的位置，
 * 然后把key和start位置或者end位置的元素交换再进行一轮排序
 * 
 * @author 滑德友
 * @since 2018年4月26日17:18:47
 *
 */
public class QuickSort {

	public static void main(String[] args) {

		int[] array={9,8,7,6,5,4,3,2,1};

		quickSort1(array,0, array.length-1);
		// quickSort2(array,0, array.length-1);
		// quickSort3(array,0, array.length-1);

		for (int i : array) {
			System.out.print(i);
		}

	}


	/**
	 * 
	 * 第一种一轮排序
	 * 
	 * @param array 被排序的数组
	 * @param start 被排序的起点
	 * @param end 被排序的中间点
	 * @return 关键值所在的下标
	 */
	public static int subQuickSort1(int[] array, int start, int end) {

		// 初始条件
		int i = start;
		int j = end;
		int key = array[start];

		// 只要i<j就代表着需要被比较的元素的个数大于1
		// key固定在start处，i主动且带动j动
		while(i < j){

			// 如果该元素的值大于关键值，就划归到到j处
			// 否则，就划归到i处
			if(array[i] > key){
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				j--;

			}else{
				i++;
			}

		}

		// 对最后一个元素进行比较，并将key放到中间来
		// 当元素的值<=key时，交换array[i]和array[start]
		// 否则，需要交换array[i-1]和array[start]
		if(array[i] <= key){
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

		// 非法输入
		if(start >= end){
			return;
		}

		// 查找中间值
		int middle = subQuickSort1(array ,start , end);

		// 递归排序
		quickSort1(array, start, middle-1);
		quickSort1(array, middle + 1, end);

		return;

	}

	/**
	 * 
	 * 第一种一轮排序
	 * 
	 * @param array 被排序的数组
	 * @param start 被排序的起点
	 * @param end 被排序的中间点
	 * @return 关键值所在的下标
	 */
	public static int subQuickSort2(int[] array, int start, int end){

		// 初始条件
		int i = start;
		int j = end;
		int key = array[start];

		// 只要i！=j就表示还有元素需要被比较
		// key固定在i处，j动
		while(i != j){

			// 如果该元素<key，说明该元素需要被规划到i处,交换该元素于key元素即可
			// 否则该元素规划到j处
			if(array[j] < key){
				int temp1 = array[j];
				array[j] = array[i];
				array[i] = temp1;
				i++;

				// 只要i！=j就表示还有元素需要被比较
				// key固定在j处，i动
				while(i != j){
					// 如果该元素>key，说明该元素需要被规划到j处,交换该元素于key元素即可
					// 否则该元素规划到i处
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

		// 非法输入
		if(start >= end){
			return;
		}

		// 查找中间值
		int middle = subQuickSort2(array ,start , end);

		// 递归排序
		quickSort2(array, start, middle-1);
		quickSort2(array, middle + 1, end);

		return;

	}

	/**
	 * 
	 * 第一种一轮排序
	 * 
	 * @param array 被排序的数组
	 * @param start 被排序的起点
	 * @param end 被排序的中间点
	 * @return 关键值所在的下标
	 */
	public static int subQuickSort3(int[] array, int start, int end){

		// 初始条件
		int i = start;
		int j = start;
		int key = array[end];

		// key固定在end处，j主动且带动i动
		for(; j < end ; j++){

			// 如果该元素<key,则将该元素规划到i处
			if(array[j] < key){
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

	public static void quickSort3(int[] array, int start ,int end){

		// 非法输入
		if(start >= end){
			return;
		}

		// 查找中间值
		int middle = subQuickSort3(array ,start , end);

		// 递归排序
		quickSort3(array, start, middle-1);
		quickSort3(array, middle + 1, end);

		return;

	}

}
