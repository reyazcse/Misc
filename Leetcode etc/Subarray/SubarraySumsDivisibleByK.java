//https://leetcode.com/problems/subarray-sums-divisible-by-k/
/*
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]


Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000

 * */

//Solution: Using cumulative sum and some maths
//References: https://www.geeksforgeeks.org/count-sub-arrays-sum-divisible-k/
package leetcode;

import java.util.HashMap;

public class SubarraySumsDivisibleByK {


	/*
	 * Solution idea : 
	 * sum (i, j) = sum(0,j) - sum(0, i-1)  ; where sum (a,b) is sum from a till b
	 * 			   = q1*k + rem1 - (q2*k + rem2)
	 * 			   = (q1-q2)*k + rem1 - rem2;
	 * For sum(i,j) to be divisible by k, RHS should be divisible.
	 * term1 on RHS is divisible by k. For term 2 to be divisible by k: 
	 * rem1 = rem2
	 * where rem1 = sum(0,j) % k and rem2 = sum(0,i-1) % k
	 * 
	 * O(n + k) time or O(n) time depending on implementation and O(k) space
	 * 
	 * */


	//O(n + k) time and O(k) space
	public int subarraysDivByK_1(int[] A, int K) {
		if( K <= 1) {								//2 <= K <= 10000 as per question
			return 0;
		}

		int[]map = new int[K];

		int sum=0, count=0;

		for(int i=0; i<A.length; i++) {
			sum += A[i];
			int remainder = sum % K;

			if(remainder < 0) {
				remainder += K;							//make it non-negative as stored in array
			}
			map[remainder]++;
		}

		for(int i=0; i<K; i++) {
			if(map[i] > 0) {
				count += map[i] * (map[i]-1)/2;				//no of pairs = n * (n-1)/2
			}
		}
		count += map[0];			//add all elements whose sum is divisible by K itself, i.e. remainder = 0
		return count;
	}

	//O(n) time and O(k) space
	public int subarraysDivByK_2(int[] A, int K) {
		if( K <= 1) {								//2 <= K <= 10000 as per question
			return 0;
		}

		int[]map = new int[K];

		int sum=0, count=0;
		map[0] = 1;							//for sums divisible by K itself.  Take {5,5} and K = 5
		for(int i=0; i<A.length; i++) {
			sum += A[i];
			int remainder = sum % K;

			if(remainder < 0) {			
				remainder += K;				//make it non-negative as stored in array
			}
			count += map[remainder];

			map[remainder]++;
		}

		return count;
	}



	//Another solution using hash map
	public int subarraysDivByK_3(int[] A, int k) {
		int ans=0;
		int sum=0;
		int rem=0;
		HashMap<Integer,Integer> hm=new HashMap<>();
		hm.put(0,1);

		for(int i=0;i<A.length;i++)
		{
			sum+=A[i];
			rem=sum%k;

			if(rem<0)
			{
				rem+=k;
			}

			if(hm.containsKey(rem))
			{
				ans+=hm.get(rem);
				hm.put(rem,hm.get(rem)+1);
			}
			else
			{
				hm.put(rem,1);
			}

		}

		return ans;
	}


	//	O (n^2) time and O(1) space. Take cumulative for each sub array 
	//	TLE
	public int subarraysDivByK_TLE(int[] A, int K) {
		if( K <= 1) {								//2 <= K <= 10000 as per question
			return 0;
		}

		int count = 0;
		for(int i=0; i<A.length; i++) {
			int sum=0;
			for(int j=i; j<A.length; j++) {
				sum += A[j];
				if(sum % K == 0) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {

		System.out.println(8*7/2);

	}

}
