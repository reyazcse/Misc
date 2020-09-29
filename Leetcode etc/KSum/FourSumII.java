//https://leetcode.com/problems/4sum-ii/submissions/
/*
Question:
	Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

	To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

	Example:
	
	Input:
	A = [ 1, 2]
	B = [-2,-1]
	C = [-1, 2]
	D = [ 0, 2]
	
	Output:
	2
 * */
package leetcode;

import java.util.HashMap;

public class FourSumII {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
		
		HashMap<Integer, Integer> map = new HashMap<>();
		//put all pairs from C and D in map with the sum of the pair as key
		for(int i=0; i<C.length; i++) {
			for (int j=0; j<D.length; j++) {
				int sum = C[i] + D[j];
				if(map.containsKey(sum)) {
					map.put(sum, map.get(sum)+1);
				}else {
					map.put(sum, 1);
				}
			}
		}
		int totalQuadruplets = 0;
		//check in the map if negative of sum of a pair of A and B values exists in the map.
		//if exists then it means we can get four values from A,B,C and D whose sum is 0.
		for(int i=0; i<A.length; i++) {
			for(int j=0; j<B.length; j++) {
				int negSum = -(A[i] + B[j]);
				if(map.containsKey(negSum)) {
					totalQuadruplets += map.get(negSum);
				}
			}
		}
        return totalQuadruplets;
        
    }
	public static void main(String[] args) {
	int[] A = { 1, 2};
	int[] B = {-2,-1};
	int[] C = {-1, 2};
	int[] D = { 0, 2};
	
	FourSumII obj = new FourSumII();
	System.out.println(obj.fourSumCount(A, B, C, D));
				

	}

}
