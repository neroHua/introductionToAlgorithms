package chapter27;

public class FibonacciThread extends Thread {

	static int[] fn;
	int cfn;
	int fnValue;
	boolean sumEnablue;

	public FibonacciThread(int cfn, boolean sumEnablue) {
		this.cfn = cfn;
		this.sumEnablue = sumEnablue;
	}

	/**
	 * 计算F(n)
	 * 
	 * @param cfn1
	 * @return
	 */
	public int fn1(int cfn1) {
		if (cfn1 == 0 || cfn1 == 1) {
			return 1;
		} else {
			int a = fn1(cfn1 - 1) + fn1(cfn1 - 2);
			this.fnValue = a;
			return a;
		}
	}

	@Override
	public void run() {

		if (cfn > 3 && sumEnablue) {

			// 计算F(n-1)(内部并行计算)
			FibonacciThread ft = new FibonacciThread(cfn - 1, false);
			ft.start();

			// 计算F(n-2)
			fn[cfn - 3] = fn1(cfn - 3);

			// 等待F(n-1)计算完成
			while (ft.fnValue == 0) {

			}
			fn[cfn - 2] = ft.fnValue;

			// 计算F(n)
			fn[cfn - 1] = fn[cfn - 2] + fn[cfn - 3];
			System.out.println(cfn + "------" + fn[0] + "," + fn[1] + "," + fn[2] + "," + fn[3] + "," + fn[4] + ","
					+ fn[5] + "," + fn[6] + "," + fn[7] + "," + fn[8] + "," + fn[9]);

		} else if (cfn > 3 && !sumEnablue) {
			fn1(cfn - 1);
		}

	}

}
