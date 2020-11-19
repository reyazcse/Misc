//https://leetcode.com/problems/reverse-pairs/
/*
Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
 * */

/*
Solution:
	We use a binary search tree.
	The node contains the value and also count of elements greater than or equal to value.
	The left node contains value less than value in parent node
	The right node contains value less than or equal to value in parent node.
	
	The idea is when we are at an element, we find those elements which are left of it and greater than twice of current
	element.
	Once we find the count, we insert the current element in the tree.
	
	Complexity: O(n^2) time when we have skewed tree
				O(n) space
	References: https://leetcode.com/problems/reverse-pairs/solution/
	
 * */
package leetcode;

public class ReversePairs2 {
	public int reversePairs(int[] nums) {
		int count = 0;
		Node root = null;
		for(int num : nums) {
			count += search(root, 2 * (long)num + 1);
			root = insert(root, num);
		}
		return count;
	}
	
	private int search(Node root, long target) {
		if(root == null) {
			return 0;
		}
		if(root.value == target) {
			return root.count_ge;
		}
		else if(target < root.value) {
			return root.count_ge + search(root.left, target);
		}else {										//target >= root.value
			return search(root.right, target);
		}
	}
	
	private Node insert(Node root, int val) {
		if(root == null) {
			return new Node(val);
		}
		if(val == root.value) {
			root.count_ge++;
		}else if (val < root.value) {
			root.left = insert(root.left, val);
		}else {										// val >= root.value
			root.count_ge++;
			root.right = insert(root.right, val);
		}
		return root;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private class Node {
		public int value;
		public int count_ge;		//stores count of element greater than or equal to 'value'
		public Node left, right;
		
		public Node(int value) {
			this.value = value;
			this.count_ge = 1;
			this.left = this.right = null;
		}
	}

}

/*
Notes:
	1. We use long variable to take care of overflow as test case will fail if we have this data:
	   [inf, inf, inf]
	2. First call search and then call insert bcoz if we reverse the order of the calls, then code fails when we have negative
	   values. For example if have nums = [-5]
		
 * */

