//https://leetcode.com/problems/powx-n/
/*
 Implement pow(x, n), which calculates x raised to the power n (i.e. xn).
 
 
 Constraints:
-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= x^n <= 10^4
 */

/*
Solution:
	Here n can be negative also.
	If n is negative, then we need to return 1/(x^n), else return x^n
	So in any case we need to find x^n
	So we have to make n as positive if it is negative.
	Edge case: if n say -inf, then if we make it positive it will overflow
	To avoid overflow, we use long variable to store n.
	
	Calculating the value x^n:
	1. Brute force: O(n)
		Here we multiply x n-times
	2. Efficient : O(logn)
	   Here if n is odd, then we do n-1, else do n/2
	   for example:
	   2^5 = 2.2^4
	   2^4  = (2.2)^(4/2)
References: take u forward youtube | https://www.youtube.com/watch?v=l0YC3876qxg&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2
	   	
 * */
package leetcode;

public class PowerXN {
	//efficient solution: O(log n)
    public double myPow(double x, int n) {
		long nn = n;
        if(n < 0) {
			nn = -1 * nn;				//over flow can occur here if we do not use long and n is -inf
		}
		double ans = 1;
		while(nn > 0) {
			if(nn % 2 == 1) {
				ans *= x;
				nn = nn-1;
			}else {
				x = x*x;
				nn = nn/2;
			}
		}
		return n > 0 ? ans : 1/ans;
	}
	
	public static void main(String[] args) {
		PowerXN ob = new PowerXN();

		System.out.println(ob.myPow(2.0, 4));


	}

}




//brute force algorithm: O(n)
//multiply x n-times
//public double myPow(double x, int n) {
//	long nn = n;
//	if(n < 0) {
//		nn = -1 * nn;
//	}
//	double ans = 1;
//	while(nn > 0) {
//		ans *= x;
//		nn--;
//	}
//	return n > 0 ? ans : 1/ans;
//}