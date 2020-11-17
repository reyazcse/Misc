//https://leetcode.com/problems/powx-n/

//Solution: recursive | O(log n)
package leetcode;

public class PowerXN2 {
	public double myPow(double x, int n) {
		long nn = n;
        if(n < 0) {
			nn = -1 * nn;				//over flow can occur here if we do not use long and n is -inf
		}
        double val = utl(x, nn);
		return n > 0 ? val : 1/val;
	}
	
	//return x raised to power nn
	private double utl(double x, long nn) {
		if(nn == 0) {
			return 1.0;
		}
		if(nn % 2 == 1) {
			return x*utl(x, nn-1);
		}else {
			return utl(x*x, nn/2);
		}
	}
	public static void main(String[] args) {
		PowerXN2 ob = new PowerXN2();
		System.out.println(ob.myPow(2.0, 2));

	}

}
