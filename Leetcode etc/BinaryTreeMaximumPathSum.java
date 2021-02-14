//src: https://leetcode.com/problems/binary-tree-maximum-path-sum/
/*
 
 	Given a non-empty binary tree, find the maximum path sum.
	The path must contain at least one node and does not need to go through the root.
 * */

/*
When we are at a node, then can have two things to go:
	1. return a value. This value must include current node value (plus value of left subtree OR right subtree if they increase total). 
	2. check if global maximum value has to be updated.
	
	(This node can be the root node of the max path sum  => update global max)
	(This node is part of the path of the max path sum   => return curr node value + max(left_val, right_val) )	
 
 * */

package leetcode;

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
   
 }   
public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {
		int []maxSum = new int[1];			//stores global max at any instant
		maxSum[0] = Integer.MIN_VALUE;		//initialize
		maxPathSumUtl(root, maxSum);
		return maxSum[0];
    }
	
	public int maxPathSumUtl(TreeNode node, int[] maxSum) {
		if (node == null) return 0;
		
		int left = maxPathSumUtl(node.left, maxSum);
		int right = maxPathSumUtl(node.right, maxSum);
		
		
		//this value is to be returned to calculate future maximum value
		int current = Math.max(node.val,  node.val + Math.max(left, right));
		
		//updating the global max path sum
		int tempMax = Math.max(node.val + left + right, current);
		maxSum[0] = Math.max(maxSum[0], tempMax);
		
		
		return current;
	}
	
	public int maxOfThree(int a, int b, int c) {
		return Math.max(a, Math.max(b, c));
	}

	public static void main(String[] args) {
//		TreeNode root = new TreeNode(-10);
//		root.left = new TreeNode(9);
//		root.right = new TreeNode(20);
//		root.right.left = new TreeNode(-15);
//		root.right.right = new TreeNode(-7);
		
		TreeNode root = new TreeNode(-10);
		root.left = new TreeNode(-5);
		root.right = new TreeNode(-10);
		BinaryTreeMaximumPathSum obj = new BinaryTreeMaximumPathSum();
		System.out.println(obj.maxPathSum(root));
		System.out.println(obj.maxPathSum2(root));
		
	}
	
	/* *****************ANOTHER METHOD***************************************************/
	public int maxPathSum2(TreeNode root) {
		int []maxSum = new int[1];			//stores global max at any instant
		maxSum[0] = Integer.MIN_VALUE;		//initialize
		maxPathSumUtl2(root, maxSum);
		return maxSum[0];
    }
	
	private int maxPathSumUtl2(TreeNode node, int[]maxSum) {
		if(node == null)
			return 0;
		int left_gain = Math.max(0, maxPathSumUtl2(node.left, maxSum));
		int right_gain = Math.max(0, maxPathSumUtl2(node.right, maxSum));
		maxSum[0] = Math.max(maxSum[0], node.val + left_gain + right_gain);
		//while returning we must include current node value
		return node.val + Math.max(left_gain, right_gain);
	}
	/* **********************************************************************************/
}
