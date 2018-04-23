package chapter02;

/**
 * 插入排序
 * 
 * 类似于将扑克牌排序
 * 
 * 算法的复杂度
 * 最好情况的复杂度为aN + b，
 * 最坏情况的复杂度为aN^2 + bN + c
 * (其中N为要被排序的数的数量，系数a，b，c由给定的系统的决定)
 * 
 * @author 滑德友
 * 
 */
public class InsertionSort {

	public static void main(String[] args) {

		/*int[] b0 = {1,2,3,4,5,6,7};*/ 
		/*int[] b0 = {7,6,5,4,3,2,1};*/
		/*int[] b0 = {5,2,4,6,1,3,9};*/
		int[] b0 = { 7, 5, 9, 2, 6, 3, 8 };

		/*int[] b1 = insertSort1(b0);*/
		int[] b1 = insertSort2(b0);
		
		for (int i = 0; i < b1.length; i++) {
			System.out.print(b1[i]);
		}

	}

	/**
	 * 
	 * @param a[]
	 * @return A[0] <= A[1] <= A[2] <= A[3]*********A[n-2] <= A[n-1]
	 */
	public static int[] insertSort1(int[] a) {
																				// 该行代码执行时间	该行代码执行次数	
		for (int i = 1; i < a.length; i++) {									// t1			c1

			// 把A[i]插入到A[0]---A[i-1],j代表正在比较的位置
			int key = a[i];														// t2			c2
			int j = i - 1;														// t3			c3
			// 当j<0时比较结束
			while (j >= 0) {													// t4			c4
				
				// 当发生key > A[j]时
				if (key > a[j]) {												// t5			c5
					// 需要把A[i]放到A[j+1]的位置上
					// 需要把A[j+1]--A[i-1]向前移动一个位置
					for (int m = i - 1; m > j; m--) {							// t6			c6
						a[m + 1] = a[m];// 需要把A[j]--A[i-1]向前移动一个位置			// t7			c7
					}
					a[j + 1] = key;// key放到A[j]的位置上，同时结束本次的插入操作			// t8			c8
					break;														// t9			c9
				}
				j--;															// t10			c10
				// 当j==-1时，说明A[i]需要被放到A[0]的位置上
				if (j == -1) {													// t11			c11
					for (int m = i - 1; m > -1; m--) {							// t12			c12
						a[m + 1] = a[m];// 需要把A[0]--A[i-1]向前移动一个位置			// t13			c13
					}
					a[0] = key;// key放到A[0]的位置上，同时结束本次的插入操作				// t14			c14
				}
			}

		}

		return a;

	}

	/**
	 * 
	 * @param a[]
	 * @return A[0] >= A[1] >= A[2] >= A[3]*********A[n-2] >= A[n-1]
	 */
	public static int[] insertSort2(int[] a) {

		for (int i = 1; i < a.length; i++) {

			// 把A[i]插入到A[0]---A[i-1],j代表正在比较的位置
			int key = a[i];
			int j = i - 1;
			
			// 当j<0时比较结束
			while (j >= 0) {
				
				// 当发生key < A[j]时
				if(key < a[j]) {
					// 需要把A[i]放到A[j+1]的位置上
					// 需要把A[j+1]--A[i-1]向前移动一个位置
					for(int m = i-1; m > j ; m--) {
						a[m+1] = a[m];// 需要把A[j]--A[i-1]向前移动一个位置
					}
					a[j+1] = key;// key放到A[j]的位置上，同时结束本次的插入操作
					break;
				}
				
				j--;
				// 当j==-1时，说明A[i]需要被放到A[0]的位置上
				if(j == -1) {
					for(int m = i-1; m > -1 ; m--) {
						a[m+1] = a[m];
					}
					a[0] = key;// key放到A[0]的位置上，同时结束本次的插入操作
				}
			}
			
		}

		return a;

	}

}

/**
 * 算法的复杂度计算过程如下:
 * T(n) = t1*c1 + t2*c2 + t3*c3 + t4*c4 + t5*c5 + t6*c6 + t7*c7 + t8*c8 + t9*c9 + t10*c10 + t11*c11 + t12*c12 + t13*c13 + t14*c14
 * 
 * 最好的情况下：数据已经排好序了，正序
 * c1 = n-1
 * c2 = n-1
 * c3 = n-1
 * c4 = n-1
 * c5 = n-1
 * c6 = 0
 * c7 = 0
 * c8 = n-1
 * c9 = n-1
 * c10 = 0
 * c11 = 0
 * c12 = 0
 * c13 = 0
 * c14 = 0
 * T(n) = t1*(n-1) + t2*(n-1) + t3*(n-1) + t4*(n-1) + t5*(n-1) + t6*0 + t7*0 + t8*(n-1) + t9*(n-1) + t10*0 + t11*0 + t12*0 + t13*0 + t14*0
 * 		= (t1 + t2 + t3 + t4 + t5 + t8 + t9)*(n-1)
 * 		= a*n + b
 * 
 * 最坏的情况下：数据已经排好序了，倒序
 * c1 = n-1
 * c2 = n-1
 * c3 = n-1
 * c4 = n-1
 * c5 = 1 + 2 + 3 + 4 + ------ + (n-2) + (n-1) = n(n-1)/2
 * c6 = 0
 * c7 = 0
 * c8 = 0
 * c9 = 0
 * c10 = 1 + 2 + 3 + 4 + ------ + (n-2) + (n-1) = n(n-1)/2
 * c11 = 1 + 2 + 3 + 4 + ------ + (n-2) + (n-1) = n(n-1)/2
 * c12 = 1 + 2 + 3 + 4 + ------ + (n-2) + (n-1) = n(n-1)/2
 * c13 = 1 + 2 + 3 + 4 + ------ + (n-2) + (n-1) = n(n-1)/2
 * c14 = n-1
 * T(n) = t1*(n-1) + t2*(n-1) + t3*(n-1) + t4*(n-1) + t5*(n(n-1)/2) + t6*0 + t7*0 + t8*0 + t9*0 + t10*(n(n-1)/2) + t11*(n(n-1)/2) + t12*(n(n-1)/2) + t13*(n(n-1)/2) + t14*(n-1)
 * 		= (t1 + t2 + t3 + t4 + t14)*(n-1) + (t5 + t10 + t11 + t12 + t13)*(n(n-1)/2)
 * 		= a*n^2 + b*n + c
 */


