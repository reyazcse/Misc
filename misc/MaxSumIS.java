//https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/
/*
Given an array of n positive integers. Write a program to find the sum of maximum sum subsequence of the given array such that the integers in the subsequence are sorted in increasing order. 
For example, if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100)
* */
/*
Solution: Similar to LIS problem. Here instead of length, take sum
Complexity: O(n^2)

Note: We can achieve O(n logn) complexity if we use binary search. See this: LongestIncreasingSubsequence2.java | take u forward youtube | https://www.youtube.com/watch?v=TocJOW6vx_I
**/
package misc;

public class MaxSumIS {
	public int maxSumIS(int arr[], int n)  
	{  
		if(arr == null || arr.length == 0) {
			return 0;
		}
		
		int[] maxSum = new int[n];
		maxSum[0] = arr[0];
		
		for(int i=1; i<n; i++) {
			maxSum[i] = arr[i];
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j] && maxSum[j] + arr[i] > maxSum[i]) {
					maxSum[i] = maxSum[j] + arr[i];
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			max = Math.max(max, maxSum[i]);
		}
		return max;
	}

}
