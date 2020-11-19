//https://www.geeksforgeeks.org/find-the-largest-subarray-with-0-sum/
/*
Given an array of integers, find the length of the longest sub-array with sum equals to 0.

Examples : 
Input: arr[] = {15, -2, 2, -8, 1, 7, 10, 23};
Output: 5
Explanation: The longest sub-array with 
elements summing up-to 0 is {-2, 2, -8, 1, 7}
 * */

/*
Solution:
	Brute force:
		Consider all sub-arrays one by one and check the sum of every sub-array.
		This can be done using two loops.
		
	Optimal solution:
		When we are at ith element, we have sum of elements from 0 to ith position : sum1
		Suppose we have sum from 0 to kth position where k < i : sum2
		Now if sum1 = sum2, it means sum of elements from k+1 till i will be 0
		
		To implement above solution, we use a map with key as sum and value as index.
		At each element, we add the element to the sum
		If the sum is already present in the map, then we update the max length
		else we put the sum and index in the map.
		
		Also note that we have to check if sum  is zero before we go to look into the map.
		This is because if sum is 0, then we have the largest subarray (0 to i)
		
		Complexity: O(n) time and O(n) space.
		
		Below is implementation of it:
	
	
	
 * */
package misc;

import java.util.HashMap;

public class LargestSubArrayZeroSum {
	int maxLen(int arr[], int n)
	{
		HashMap<Integer, Integer> sumIndexMap = new HashMap<>();
		int sum = 0;			//stores sum from 0 till ith element
		int maxLen  = 0;
		for(int i=0; i<arr.length; i++) {
			sum += arr[i];
			if(sum == 0) {			//if sum is zero then it means the largest subarray is from 0 till ith position
			    maxLen = i+1;
			}else {
			    if(sumIndexMap.containsKey(sum)) {
				int prevIndex = sumIndexMap.get(sum);
				maxLen = Math.max(maxLen, i - prevIndex);
			    }else {
				sumIndexMap.put(sum, i);
			}    
			}
			
		}
		return maxLen;
	}
	public static void main(String[] args) {
		int []arr = {-1,1,-1,1};
		LargestSubArrayZeroSum ob = new LargestSubArrayZeroSum();
		System.out.println(ob.maxLen(arr, arr.length));

	}

}
