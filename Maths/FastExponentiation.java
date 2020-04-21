/*
 src: gkcs | https://www.youtube.com/watch?v=-3Lt-EwR_Hw | https://github.com/gkcs/Competitive-Programming/blob/master/src/main/java/main/java/videos/FastExponentiation.java
 
 Explanation:
 	power(a,N) takes O(N) time as it multiplies a with a N times
 	Using fast exponentiation, we reduce the complexity to O(logN). Base is 2.
 * */
package misc;

public class FastExponentiation {
	public static void main(String[] args) {
		final FastExponentiation exponentiation = new FastExponentiation();
		System.out.println(exponentiation.power(5, 6));
		System.out.println(exponentiation.power(5, 6, 3));
	}

	public int power(final int a, final int N) {
		if (N == 0) {
			return 1;
		} else {
			final int R = power(a, N / 2);
			if (N % 2 == 0) {
				return R * R;
			} else {
				return R * R * a;
			}
		}
	}
	//here use modulo since the result may overflow if N is large
	public long power(final int a, final int N, int M) {
		if (N == 0) {
			return 1;
		} else {
			final long R = power(a, N / 2, M);
			if (N % 2 == 0) {
				return (R * R) % M;
			} else {
				return (R * R * a) % M;
			}
		}
	}
}
