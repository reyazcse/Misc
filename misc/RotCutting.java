//https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
/*
Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n. Determine the maximum value obtainable by cutting up the rod and selling the pieces. 
For example, if length of the rod is 8 and the values of different pieces are given as following, then the maximum obtainable value is 22 (by cutting in two pieces of lengths 2 and 6) 

length   | 1   2   3   4   5   6   7   8  
--------------------------------------------
price    | 1   5   8   9  10  17  17  20
 * */

//Solution: Try cutting at each position and find maximum profit
package misc;

public class RotCutting {
	//recursion
	public int cutRod(int[] arr, int size) {
		if(size == 0) {
			return 0;
		}
		
		int max_profit = Integer.MIN_VALUE;
		//try cutting at each position
		for(int i=0; i<size; i++) {
			max_profit = Math.max(max_profit, arr[i] + cutRod(arr, size-(i+1)));
		}
		return max_profit;
	}

	//top down
		
	public int cutRod_topDown(int []arr, int size) {
		int[]dp = new int[size+1];
		int ans = utl_topDown(arr, size, dp);
		return ans;									//or return dp[size]
	}
	
	private int utl_topDown(int[] arr, int size, int[]dp) {
		
		if(size == 0) {
			return 0;
		}
		if(dp[size] != 0) {
			return dp[size];
		}
		int max_profit = Integer.MIN_VALUE;
		//cut at each position
		for(int i=0; i<size; i++) {
			max_profit = Math.max(max_profit, arr[i] + utl_topDown(arr, size-(i+1), dp));
		}
		dp[size] = max_profit;
		return max_profit;
	}
	
	
	
	//bottom up
	//O(n^2) time and O(n) time where n = size
	public int cutRod_bottomUp(int []arr, int size) {
		int[]dp = new int[size+1];
		dp[0] = 0;
		for(int i=1; i <= size; i++) {			//calculate for each size
			int max_profit = Integer.MIN_VALUE;
			for(int j=0; j<i; j++) {											//cut at each position for size = i
				max_profit = Math.max(max_profit, arr[j] + dp[i- (j+1)]);
			}
			dp[i] = max_profit;
		}
		return dp[size];
	}
	
	public static void main(String[] args) {
		int arr[] = {1, 5, 8, 9, 10, 17, 17, 20}; 
		int size = arr.length;
		RotCutting ob = new RotCutting();
		System.out.println(ob.cutRod(arr, size));
		System.out.println(ob.cutRod_topDown(arr, size));
		System.out.println(ob.cutRod_bottomUp(arr, size));

	}

}
