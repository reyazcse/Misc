//https://www.geeksforgeeks.org/count-number-subarrays-given-xor/
/*
Given an array of integers arr[] and a number m, count the number of subarrays having XOR of their elements as m.

Examples:  
Input : arr[] = {4, 2, 2, 6, 4}, m = 6
Output : 4
 * */

/*
Solution:
	Let
	A = xor of all elements from 0 till i
	B = xor of all elements from i+1 till j
	C = xor of all elements from 0 till j
	
	Then we have C = A ^ B
	Now A = B ^ C
	Now if we take k to be B and prefix xor till j to be C, then we have to find count of A
	
	We keep prefix xor in hashmap with key as prefix xor and value as count of occurrences
	
	Also when we are at j, if C == k, then it adds to our answer
	
	Complexity: 
		O(n) time 
		O(n) space for the map
 * */
package leetcode;

import java.util.HashMap;

public class SubarrayXor {

	public int subarrayXor(int[]arr, int k) {
		if(arr == null || arr.length == 0) {
			return 0;
		}
		int prefixXor = 0;
		int cnt = 0;
		HashMap<Integer, Integer> mp = new HashMap<>();
		for(int i=0; i<arr.length; i++) {
			prefixXor ^= arr[i];
			
			if(prefixXor == k) {  //xor of all elements from 0 till this element is k, hence part of answer
				cnt++;
			}
			
			if(mp.containsKey(prefixXor ^ k)) {		//count of occurrences of A
				cnt += mp.get(prefixXor ^ k);
			}
			
			mp.put(prefixXor, mp.getOrDefault(prefixXor, 0) + 1);  //put this prefixXor with its occurrence count
		}
		return cnt;
	}

	public static void main(String[] args) {
		int[]arr = {4, 2, 2, 6, 4};
		int k = 6;
		SubarrayXor ob = new SubarrayXor();
		System.out.println(ob.subarrayXor(arr, k));

	}

}
