//https://leetcode.com/problems/permutation-sequence/

/*
Solution:
	Efficient solution.
	References : tech dose | https://www.youtube.com/watch?v=W9SIlE2jhBQ&t=781s
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence2 {
	public String getPermutation(int n, int k) {			
		Result res = new Result();
		List<Integer> digits = new ArrayList<>();
		for(int i=0; i<n; i++) {
			digits.add(i+1);
		}
		
		int[] factorial = new int[n];
		factorial[0] = 1;
		for(int i=1; i<n; i++) {
			factorial[i] = factorial[i-1] * i;
		}
		
		utl(n, k, res, digits, factorial);
		return res.result;
				
	}
	
	private void utl(int n, int k, Result res, List<Integer> digits, int[] factorial) {
		if(n == 1) {
			res.result += digits.get(0);
			return;
		}
		int block_size = factorial[n-1];			//get size of each block
		int index_block = k/block_size;				//get the index of block where our answer is
		
		if(k % block_size == 0) {					//corner case when kth permutation is at the last of the block
			index_block --;
		}
		
		res.result += digits.get(index_block);
		digits.remove(index_block);
		
		k = k - block_size * index_block;
		
		utl(n-1, k, res, digits, factorial);
		
	}
	
	private class Result{
		public String result = "";
	}
	public static void main(String[] args) {
		PermutationSequence2 ob = new PermutationSequence2();
		int n=4, k=14;
		System.out.println(ob.getPermutation(n, k));

	}

}
