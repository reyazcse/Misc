//https://leetcode.com/problems/super-egg-drop/
/*
You are given K eggs, and you have access to a building with N floors from 1 to N. 

Each egg is identical in function, and if an egg breaks, you cannot drop it again.

You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break, and any egg dropped at or below floor F will not break.

Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N). 

Your goal is to know with certainty what the value of F is.

What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 
 * */

/*
Solution:
	f(i, j) ==> i eggs and n floors
	Then find values for each floor 1, 2, ..., j.
	At each floor, egg can break and cannot break. So we have two values at each floor.
	Take max of these two values.
	
	Now take minimum of all the max values collected for each floor from 1 till j.
	Add 1 to the minimum value since we are making one attempt.
	So (1 + minimum ) is the minimum number of attempts. f(i, j) = 1 + minimum

Complexity: O(K * N^2) time and O(K * n) space

Base cases:
	if number of floor is 0, then minimum number of attempts = 0. f(k,0) = 0
	if number of floor is 1, then minimum number of attempts = 1. f(k,1) = 0
	if number of eggs is 0, then 0. f(0,n) = 0
	if number of eggs is 1, then minimum number of attempts = number of floors. f(1,k) = k

Since row and column 0 are all zeroes by default, no need to fill first row and column explicitly. Hence start from row=1, column=1

	Complexity:
		O(K * N^2) time 
		O(K * N) space

Note:
	There is a much more efficient method on leetcode but that is not intuitive: K * N 
	and also K * log N.
	See that reference.
 * */

package leetcode;

public class EggDrop {
	//Gives TLE on leetcode
	public int superEggDrop(int K, int N) {			
		int[][] dp = new int[K+1][N+1];


		//since row 0 and column 0 are all zeroes by default, start from row=1 and column=1
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				if(j == 1) {							//floor = 1
					dp[i][j] = 1;
				}else if(i == 1) {						//egg = 1
					dp[i][j] = j;
				}else {
					int min = Integer.MAX_VALUE;
					for(int f=1; f<=j; f++) {			//try at all floors from 1 till jth floor
						int egg_break = dp[i-1][f-1];
						int no_break = dp[i][j-f];
						int max_at_floor = Math.max(egg_break, no_break);
						min = Math.min(max_at_floor, min);
					}
					dp[i][j] = 1 + min;					// 1 is added since we do one attempt
				}

			}
		}
		return dp[K][N];

	}
	
	/*
	 * Little clean up: See the below pattern:
	 * The value of egg_break is from previous row: i-1
	 * Value of no_break is from current row: i
	 * Also k-1 = 0, 1, ..., j-1 , which means 0th column till current column - 1
	 * j-k = j-1, j-2, ..., 0, which means current column-1 till 0th column
	 * */
	
	//Gives TLE on leetcode
	public int superEggDrop_smallOptimization(int K, int N) {			
		int[][] dp = new int[K+1][N+1];

		//since row 0 and column 0 are all zeroes by default, start from row=1 and column=1
		for(int i=1; i<=K; i++) {
			for(int j=1; j<=N; j++) {
				if(j == 1) {										//floor = 1
					dp[i][j] = 1;
				}else if(i == 1) {									//egg = 1
					dp[i][j] = j;
				}else {
					int min = Integer.MAX_VALUE;
					for(int c1 = j-1, c2 = 0; c1>=0; c1--, c2++) {			//no need to put condition for c2 as c1 will take care of loop exit
						int egg_break = dp[i-1][c2];
						int no_break = dp[i][c1];
						int max_at_floor = Math.max(egg_break, no_break);
						min = Math.min(min, max_at_floor);
					}
					dp[i][j] = 1 + min;					// 1 is added since we do one attempt
				}

			}
		}
		return dp[K][N];

	}
}
