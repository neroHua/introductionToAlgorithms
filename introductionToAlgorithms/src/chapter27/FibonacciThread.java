package chapter27;

public class FibonacciThread extends Thread {

	static int[] fn;
	int cfn;
	
	public FibonacciThread(int cfn) {
		this.cfn = cfn;
	}
	
	/**
	 * 计算F(n)
	 * @param cfn1
	 * @return
	 */
	public int fn1(int cfn1){
		if(cfn1 == 0 || cfn1 == 1) {
			return 1;
		} else {
			int a = fn1(cfn1 -1) + fn1(cfn1 - 2);
			return a;
			
		}
	}
	
	@Override
	public void run() {
		
		// 计算F(n-1)
		fn[cfn - 2] = fn1(cfn - 2);
		
		// 等待F(n-2)计算完成
		while(fn[cfn - 3] == 0) {
		}
		
		// 计算F(n)
		fn[cfn - 1] = fn[cfn - 2] + fn[cfn - 3];
		System.out.println(cfn+"------"+fn[0]+","+fn[1]+","+fn[2]+","+fn[3]+","+fn[4]+","+fn[5]+","+fn[6]+","+fn[7]+","+fn[8]+","+fn[9]);
	
	}

}
