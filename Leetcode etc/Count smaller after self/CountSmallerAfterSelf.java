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
import java.util.Collections;
import java.util.List;

public class CountSmallerAfterSelf {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result = new ArrayList<>();
		if(nums == null || nums.length == 0) {
			return result;
		}
		int n = nums.length;
		TreeNode root = new TreeNode(nums[n-1]);
		result.add(0);
		for(int i=n-2; i>=0; i--) {
			int count = insert(root, nums[i]);
			result.add(count);
		}
		Collections.reverse(result);
		return result;
	}
	
	private int insert(TreeNode root, int val) {
		int thisCount = 0;
		while(true) {
			if(val <= root.value) {
				root.count++;
				if(root.left == null) {
					root.left = new TreeNode(val);
					break;
					
				}else {
					root = root.left;
				}
			}else {
				thisCount += root.count;
				if(root.right == null) {
					root.right = new TreeNode(val);
					break;
				}else {
					root = root.right;
				}
			}
		}
		return thisCount;
	}
	public static void main(String[] args) {
		//int[] nums = {5,2,6,1,1};
		int[] nums = {5,5};
		CountSmallerAfterSelf obj = new CountSmallerAfterSelf();
		List<Integer> result = obj.countSmaller(nums);
		for(int elt : result) {
			System.out.println(elt);
		}

	}
	private class TreeNode {
		public TreeNode left;
		public TreeNode right;
		public int value;			//value of the node
		public int count;			//count of elements less than equal to current element
		public TreeNode(int val) {
			this.value = val;
			this.count = 1;
		}
	}

}
