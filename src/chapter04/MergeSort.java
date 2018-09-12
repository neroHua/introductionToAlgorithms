package chapter04;

/**
 * 归并排序 
 * 
 * 将两个有序的数组进行排序，
 * 从两个数组中选取最前面的一个数，把他们最小的移动到第三个数组上，大的放回原位置
 * 重复上面一个步骤，直到其中一条数组为空
 * 以上步骤为一次排序，
 * 
 * 算法的复杂度
 * 最好的情况为：aN+b
 * 最坏的情况：aN+b
 * N为两个数组的总长度
 * 
 * 但是使用分治算法的归并排序算法的复杂度为NlgN
 * N为数组的要排序的元素的个数
 * 
 * 
 * 
 * @author 滑德友
 *
 */
public class MergeSort {

	public static void main(String[] args) {

		//int[] b1 = {1,3,5,7,9}; 
		//int[] b2 = {0,2,4,6,8};
		//int[] b3 = {1,3,5,7,9,0,2,4,6,8};
		int[] b4 = {9,8,7,6,5,4,3,2,1,0};
		/*int[] b0 = {5,2,4,6,1,3,9};*/
		/*int[] b0 = { 7, 5, 9, 2, 6, 3, 8 };*/

		/*int[] b0 = merge1(b1,b2);*/
		/*int[] b0 = merge2(b3,0,4,9);*/
		/*int [] b0 = mergeSort1(b4 , 0 , 9);*/
		int [] b0 = mergeSort1(b4 , 0 , 2);

		for (int i = 0; i < b0.length; i++) {
			System.out.print(b0[i]);
		}

	}

	/**
	 * 
	 * @param a[] A[0] <= A[1] <= A[2]********* <= A[n-2] <= A[n-1]
	 * @param b[] B[0] <= B[1] <= B[2]********* <= B[n-2] <= B[n-1]
	 * @return c[] C[0] <= C[1] <= C[2]********* <= C[n-2] <= C[n-1] a、b的所有元素组成了c的所有元素
	 */
	public static int[] merge1(int[] a , int[] b) {

		// 准备要输出的数组c										// 该行代码执行时间	该行代码执行次数
		int k = a.length + b.length;						// t1			c1
		int[] c = new int[k];								// t2			c2

		// 设置初始化条件
		int i = 0; 											// t3			c3
		int j = 0; 											// t4			c4
		int m = 0; 											// t5			c5

		// 只要a或b某一方的长度没有达到最大值，就意味着还需要循环比较
		while(i < a.length && j < b.length){				// t6			c6

			// 当发生a[i] > b[j]就代表着 b[j]需要被放到c[m]位置上
			// 否则，a[i]需要被被放到c[m]位置上
			if(a[i] > b[j]){								// t7			c7
				c[m] = b[j];								// t8			c8
				m++;										// t9			c9
				j++;										// t10			c10
			} else {
				c[m] = a[i];								// t11			c11
				m++;										// t12			c12
				i++;										// t13			c13
			}

		}
		// 当其中一方达到最大长度的时候就需要把另外的没有比较完的一组接到c的后面
		if(i == a.length){									// t14			c14
			for( ; j < b.length ; j++ , m++){				// t15			c15
				c[m] = b[j];								// t16			c16
			}
		} else {
			for( ; i < a.length ; i++ , m++){				// t15			c15
				c[m] = a[i];								// t16			c16
			}
		}

		return c;

	}

	/**
	 * 很容易看出我们上面最好情况：其中一个数组移动完成后，另外一个数组一直没有被移动过
	 * 被比较的次数为移动完成的数组的长度，总的移动次数等于两个数组之和，若是取n1=n2,N=n1+n2
	 * 则复杂度为aN+b
	 * 
	 * 很容易看出我们上面最坏情况：其中一个数组移动完成后，另外一个数组还剩一个没有被移动过
	 * 被比较的次数近似等于两个数组的长度，总的移动次数等于两个数组之和，若是取n1=n2,N=n1+n2
	 * 则复杂度为aN+b
	 * 
	 */

	/**
	 * 
	 * 要求 start <= middle < end(至少有两个数组才行)
	 * @param a[]
	 * @param start 起始下标
	 * @param middle 中间下标
	 * @param end 结束下标
	 * @return a[] a[start] <= a[start+1] <= ****** a[middle] <= a[middle+1] <= ****** a[end] 其他位置不变
	 */
	public static int[] merge2(int a[], int start, int middle, int end) {

		int m = middle - start + 1;
		int n = end - middle;

		int[] l = new int[m];
		int[] r = new int[n];

		for (int i = 0; i < m; i++) {
			l[i] = a[start + i];
		}

		for (int i = 0; i < n; i++) {
			r[i] = a[middle + i + 1];
		}

		int i = 0;
		int j = 0;
		int k = 0;

		while (i < m && j < n) {
			if (l[i] > r[j]) {
				a[start + k] = r[j];
				k++;
				j++;
			} else {
				a[start + k] = l[i];
				k++;
				i++;
			}
		}

		if (i == m) {
			for (; j < n; j++, k++) {
				a[start + k] = r[j];
			}
		} else {
			for (; i < m; i++, k++) {
				a[start + k] = l[i];
			}
		}

		return a;

	}

	/**
	 * 
	 * @param a[]
	 * @param start 起始下标
	 * @param end 结束下标
	 * @return a[] A[0] <= A[1] <= A[2]********* <= A[n-2] <= A[n-1]
	 */
	public static int[] mergeSort1(int[] a, int start, int end) {

		if (end > start) {
			int middle = (start + end) / 2;//拆分
			a = mergeSort1(a, start, middle);//左边
			a = mergeSort1(a, middle + 1, end);//右边
			a = merge2(a, start, middle, end);//合并
		}

		return a;
	}

}
