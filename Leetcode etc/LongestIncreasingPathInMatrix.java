//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:
Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
 * */

//Solution: Do DFS at each cell. Keep precomputed values in a table.
package leetcode;

public class LongestIncreasingPathInMatrix {
	/*
	 * No need of visited matrix: let's say we go towards left from a cell and a certain
	 * path of cells is visited. Then we wont come to a cell which is on path because
	 * that cell's value will be less than cell we are coming from. Hence no need of visited
	 * matrix in this solution.
	 * We can also store the max path length starting at each cell. So whenever we 
	 * come to this cell from another cell, we need not calculate again the max path length
	 * from this cell.
	 * 
	 * */
	//NOT optimized solution
	public int longestIncreasingPathSlow(int[][] matrix) {
		if(matrix == null || matrix.length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		boolean[][]visited = new boolean[m][n];
		int max = 0;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				max = Math.max(max, dfs(matrix, i, j, visited));
			}
		}
		return max;
	}
	private int dfs(int[][]matrix, int i, int j, boolean[][]visited) {
		if(!isSafe(matrix,i,j) || visited[i][j]) {
			return 0;
		}
		int currMax  = 0;
		visited[i][j] = true;
		int[]dx = {0,0,1,-1};
		int[]dy = {-1,1,0,0};
		for(int k=0; k<4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			int temp = (isSafe(matrix, x, y) && matrix[x][y] > matrix[i][j])? 
					dfs(matrix, x, y, visited) : 0;
			currMax = Math.max(currMax, temp);
		}
		
		visited[i][j] = false;
		return 1 + currMax;
	}
	
	/*******************************************************************************/
	//FAST solution using DP
	public int longestIncreasingPathFast(int[][] matrix) {
		if(matrix == null || matrix.length == 0) {
			return 0;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int[][]dp = new int[m][n];
		int max = 0;
		//check for all paths starting at each cell
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				max = Math.max(max, dfsFast(matrix, i, j, dp));
			}
		}
		return max;
	}
	private int dfsFast(int[][]matrix, int i, int j, int[][]dp) {
		//out of boundary
		if(!isSafe(matrix, i, j)) {
			return 0;
		}
		//precomputed value
		if(dp[i][j] != 0) {
			return dp[i][j];
		}
		int currMax  = 0;
		int[]dx = {0,0,1,-1};
		int[]dy = {-1,1,0,0};
		
		//go to all 4 neighbours. Before calling dfs on a neighbour, check if the cell value of
		//neighbour is greater than current cell value
		for(int k=0; k<4; k++) {
			int x = i + dx[k];
			int y = j + dy[k];
			int temp = (isSafe(matrix, x, y) && matrix[x][y] > matrix[i][j])? 
					dfsFast(matrix, x, y, dp) : 0;
			currMax = Math.max(currMax, temp);
		}
		
		dp[i][j] = 1 + currMax;
		return dp[i][j];
		
	}
	
	public static void main(String[] args) {
		int[][] matrix = {{9,9,4},
						  {6,6,8},
						  {2,1,1}};
		LongestIncreasingPathInMatrix obj = new LongestIncreasingPathInMatrix();
		System.out.println(obj.longestIncreasingPathSlow(matrix));

	}
	
	private boolean isSafe(int[][] matrix, int i, int j) {
		return (i>=0 && i<matrix.length && j>=0 && j<matrix[0].length);
	}
}
