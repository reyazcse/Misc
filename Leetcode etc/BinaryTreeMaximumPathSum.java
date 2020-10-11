//src: https://leetcode.com/problems/binary-tree-maximum-path-sum/
/*
 
 	Given a non-empty binary tree, find the maximum path sum.
	The path must contain at least one node and does not need to go through the root.
 * */

/*
When we are at a node, then can have two options:
	1. This node can be the root node of the max path sum  => update global max
	2. This node is part of the path of the max path sum   => return curr node value + max(left_val, right_val)	
 
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
		int currMax = node.val;
		int lSubtreeMax = maxPathSumUtl(node.left, maxSum);
		int rSubtreeMax = maxPathSumUtl(node.right, maxSum);
		//this node acts as root. so we total path sum = this node val + values of two subtrees
		int maxIfNodeIncludedWithSubtrees = currMax + lSubtreeMax + rSubtreeMax;
		//this value we need to return as the parent of this node can lie on the path. Note we need not return 'maxIfNodeIncludedWithSubtrees'
		//as the parent of this node will not lie on the path then
		currMax = Math.max(currMax,  currMax + Math.max(lSubtreeMax, rSubtreeMax));
		
		//updating the global max path sum
		int tempMax = Math.max(currMax, maxIfNodeIncludedWithSubtrees);
		if(maxSum[0] < tempMax)
			maxSum[0] = tempMax;
		return currMax;
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
