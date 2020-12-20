//https://www.geeksforgeeks.org/count-distinct-elements-in-every-window-of-size-k/
/*
Given an array of size n and an integer k, return the count of distinct numbers in all windows of size k.
Example:

Input: arr[] = {1, 2, 1, 3, 4, 2, 3};
       k = 4
Output: 3 4 4 3

Explanation:
First window is {1, 2, 1, 3}, count of distinct numbers is 3
Second window is {2, 1, 3, 4} count of distinct numbers is 4
Third window is {1, 3, 4, 2} count of distinct numbers is 4
Fourth window is {3, 4, 2, 3} count of distinct numbers is 3
 * */

/*
Solution:
	Using hashmap which stores array element as key and its count in a window as value
	Sliding window approach
 * */
package misc;

import java.util.ArrayList;
import java.util.HashMap;

public class CountDistinctElementsEveryWindow {
	/*
	 * n = size of A
	 * k = size of window
	 * */
	ArrayList<Integer> countDistinct(int A[], int n, int k)
    {
		ArrayList<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> countMap = new HashMap<>();
        
        int left=0, right=0;
        //first window
        while(right < k) {
        	countMap.put(A[right], countMap.getOrDefault(A[right], 0) + 1);
        	right++;
        }
        result.add(countMap.size());
        
        //other window
        while(right < n) {
        	countMap.put(A[right], countMap.getOrDefault(A[right], 0) + 1);		//add new element of window
        	countMap.put(A[left], countMap.get(A[left]) - 1);					//remove old element which is at 'left'
        	if(countMap.get(A[left]) == 0) {
        		countMap.remove(A[left]);
        	}
        	left++;
        	result.add(countMap.size());
        	right++;								//next window
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		CountDistinctElementsEveryWindow ob = new CountDistinctElementsEveryWindow();
		int arr[] = {1, 2, 1, 3, 4, 2, 3};
	    int k = 4;
	    System.out.println(ob.countDistinct(arr, arr.length, k));
	}

}
