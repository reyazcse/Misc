//https://leetcode.com/problems/count-of-smaller-numbers-after-self/
/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 
Example 1:

Input: nums = [5,2,6,1]
Output: [2,1,1,0]

 * */
/*
Solution:
	We solve it using binary search tree.

	Time Complexity: O(nlogn) average and O(n^2) worst case (when elements are in asc or desc order)
	We can reduce worst case to nlogn using height adjusting bst
	Space Complexity: O(n) auxiliary space
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountSmallerAfterSelf2 {
	public List<Integer> countSmaller(int[] nums) {
		Integer[] result = new Integer[nums.length];
		//base case
		if(nums == null || nums.length == 0) {
			return new ArrayList<Integer>();
		}
		int n = nums.length;
		TreeNode root = null;
		for(int i=n-1; i>=0; i--) {
			root = insert(root, nums[i], i, 0, result);
		}
		return Arrays.asList(result);
	}
	//sum is the number of elements smaller than elements at nums[index] 
	private TreeNode insert(TreeNode root, int value, int index, int sum, Integer[] result) {
		if(root == null) {
			result[index] = sum;
			return new TreeNode(value, 0); //lessThan = 0 since no node less than this node currently
		}
		//current element is less than root element, hence we increment lessThan of root and recurse left
		if(value < root.value) {
			root.lessThan++;
			root.left =  insert(root.left, value, index, sum, result);
		}else {
			if(value == root.value) {
				root.right = insert(root.right, value, index, sum+root.lessThan, result);
			}else {
				root.right = insert(root.right, value, index, sum+root.lessThan + 1, result); //+1 since root is smaller than current element
			}
		}
		return root;
		
	}
	public static void main(String[] args) {
		//int[] nums = {5,2,6,1,1};
		//int[] nums = {5,2,6,1};
		int[] nums = {5,5};
		CountSmallerAfterSelf2 obj = new CountSmallerAfterSelf2();
		List<Integer> result = obj.countSmaller(nums);
		for(int elt : result) {
			System.out.println(elt);
		}

	}
	private class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int value;
		public int lessThan;  //number of nodes whose value is less than value of this node
		public TreeNode(int val, int lessThan) {
			this.value = val;
			this.lessThan = lessThan;
		}
	}

}
