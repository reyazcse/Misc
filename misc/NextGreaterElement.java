//Next greater element
/*
Given an array A[] of size N having distinct elements, the task is to find the next greater element for each element of the array in order of their appearance in the array.

Next greater element of an element in the array is the nearest element on the right which is greater than the current element.

If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the last element is always -1.

Example:
Input: 
N = 4, arr[] = [1 3 2 4]
Output:
3 4 4 -1

 * */

//Solution: Using stack. O(n) time and O(n) space
package misc;

import java.util.Stack;

public class NextGreaterElement {
	public static long[] nextLargerElement(long[] arr, int n) { 
		long[] result = new long[n];
		Stack<Long> stack = new Stack<Long>();
		for(int i=n-1; i>=0; i--) {
			while(!stack.isEmpty() && stack.peek() <= arr[i] ) {		// Note: <= since we need larger element
				stack.pop();
			}
			if(stack.isEmpty()){
				result[i] = -1;
			}else {
				result[i] = stack.peek();
			}
			stack.add(arr[i]);
		}
		return result;
	} 
	public static void main(String[] args) {
		int[] arr = {8,0,4,1,3};
		int[] arr1 = {8,8,8,1,3};

	}

}
