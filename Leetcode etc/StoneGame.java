/*
	src: https://leetcode.com/problems/stone-game/
	Assumption: In the given problem, there are even no of values.
 * */
package misc;

public class StoneGame {
	//given : length of array is even
	public boolean stoneGame(int []stones) {
		int totalVal = 0;
		for (int i=0; i< stones.length; i++)
			totalVal += stones[i];
		int valOfFirstUser = stoneGameUtl(stones, 0, stones.length-1);
		System.out.println(valOfFirstUser);
		return (valOfFirstUser > (totalVal - valOfFirstUser));
	} 
	public int stoneGameUtl(int[] stones, int start, int end) {
		if (end < start) return 0;
		
		int x = stoneGameUtl(stones, start+2,end);
		int y =  stoneGameUtl(stones, start+1,end-1);
		int z = stoneGameUtl(stones, start,end-2);
		//we take min as the other player is also smart!
		int a = stones[start] + Math.min(x, y);
		int b = stones[end] + Math.min(y, z);
		return Math.max(a, b); 
	}
	
	//top down dp
	public boolean stoneGameTopDown(int[] stones) {
		int n = stones.length;
		int [][] dp = new int[n][n];
		initialize(dp);				//initialize with -1 as there can 0 value for the player
		int totalVal = 0;
		for (int i=0; i< stones.length; i++)
			totalVal += stones[i];
		int valOfFirstUser = stoneGameTopDownUtl(stones, 0, stones.length-1,dp);
		System.out.println(valOfFirstUser);
		return (valOfFirstUser > (totalVal - valOfFirstUser));
	}
	public int stoneGameTopDownUtl(int[]stones, int start, int end, int[][]dp) {
		if (end < start) return 0;
		if(dp[start][end] != -1) return dp[start][end];
		int x = stoneGameTopDownUtl(stones, start+2,end, dp);
		int y =  stoneGameTopDownUtl(stones, start+1,end-1, dp);
		int z = stoneGameTopDownUtl(stones, start,end-2, dp);
		
		//we take min as the other player is also smart!
		int a = stones[start] + Math.min(x, y);  
		int b = stones[end] + Math.min(y, z);
		dp[start][end] = Math.max(a, b);
		return dp[start][end];
		
	}
	
	//bottom up approach
	public boolean bottomUp(int[] stones) {
		int n = stones.length;
		int[][] dp = new int[n][n];
		//we fill the matrix diagonally
		for(int len=1; len < n; len++) {
			for(int i=0; i < n-len; i++) {
				int j = len + i;
				int x = (i+2)<n ? dp[i+2][j] : 0;
				int y = ((i+1) < n ) && ((j-1) >= 0) ? dp[i+1][j-1] : 0;
				int z = (j-2) >= 0 ? dp[i][j-2] : 0;
				dp[i][j] = Math.max(stones[i] + Math.min(x, y), stones[j] + Math.min(y, z));
			}
		}
		
		int totalVal = 0;
		for (int i=0; i< stones.length; i++)
			totalVal += stones[i];
		return dp[0][n-1] > (totalVal - dp[0][n-1]);
		
	}
	
	public void initialize(int [][]dp) {
		int m = dp.length;
		int n = dp[0].length;
		for (int i=0; i<m; i++) {
			for(int j=0; j<n; j++)
				dp[i][j] = -1;
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//another solution:
	public boolean func(int [] piles) {
		int n = piles.length;
		boolean[][]visited = new boolean[n][n];		//we use this as dp values wont exactly tell if 
		int [][] dp = new int [n][n];
		int maxVal = funcUtl(piles,visited, dp, 0, n-1);
		if (maxVal > 0)
			return true;
		return false;
	}
	
	//returns maximum val won by player when we have l ... r stones
	public int funcUtl(int[]piles, boolean [][]visited, int[][]dp, int l, int r) {
		//when we have two values, the user will pick the max value
		if (l+1 == r) return Math.abs(piles[l] - piles[r]);
		
		if (visited[l][r])	return dp[l][r];
		visited[l][r] = true;
		
		//user picks leftmost value; so other user will have l+1...r values which gets subtracted
		int currMax = piles[l] - funcUtl(piles, visited, dp, l+1, r);
		//user pics rightmost value
		currMax = Math.max(currMax, piles[r] - funcUtl(piles, visited, dp, l, r-1));
		dp[l][r] = currMax;
		
		return currMax;
	}
	public static void main(String[] args) {
		//int []stones = {5,3, 4,5 };
		int []stones = {3,7,3,2,5,1,6,3,10,7};
		StoneGame game = new StoneGame();
		System.out.println(game.stoneGame(stones));
		System.out.println(game.stoneGameTopDown(stones));
		System.out.println(game.bottomUp(stones));
		System.out.println(game.func(stones));

	}

}
