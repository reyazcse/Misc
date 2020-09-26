//Problem: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
/*
	Say you have an array for which the i-th element is the price of a given stock on day i.

	Design an algorithm to find the maximum profit. You may complete at most k transactions.

	Note:
	You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	
	Example 1:
	Input: [2,4,1], k = 2
	Output: 2
	Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 * */



/*
Solution:
	We create a table of (k+1) rows and prices.length columns.
	T[i][j] => profit by doing i transactions at jth day
	T[i][j] will max of these two:
		T[i][j-1]  //not selling on jth day 
		prices[j] - prices[m] + T[i-1][m]; m belongs to [0,...j-1]  //selling on jth day and buying on mth day and profit at mth day
		
We did an optimization here: (prices[j] - prices[m] + T[i-1][m])
=> maxDiff + prices[j] where maxDiff = 	- prices[m] + T[i-1][m] for m = [0,...j-1]	

Complexity:
	Time: O(k * days)
	Spacek: O(k * days)
 * */

package leetcode;
public class BuyAndSellStockKOptimized {
	public int maxProfit(int[] prices, int k) {
		int[][]table = new int[k+1][prices.length];
		for(int j=0; j<prices.length; j++) {
			table[0][j] = 0;
		}
		for(int i=0; i<=k; i++) {
			table[i][0] = 0;
		}
	
		for(int i=1; i<=k; i++) {
			int maxDiff = table[i-1][0] - prices[0] ;  // = -prices[0] since table[i-1][0] = 0
			for(int j=1; j<prices.length; j++) {
				table[i][j] = Math.max(table[i][j-1], maxDiff + prices[j]);
				maxDiff = Math.max(maxDiff, table[i-1][j] - prices[j]); 
			}
		}
		return table[k][prices.length-1];
	}

	public static void main(String[] args) {
		BuyAndSellStockKOptimized obj = new BuyAndSellStockKOptimized();
		//int[] prices = {2,5,7,1,4,3,1,3};
		int[] prices = {2,4,};
		int k = 2;
		System.out.println(obj.maxProfit(prices, k));

	}

}

//Reference: https://www.youtube.com/watch?v=oDhu5uGq_ic
//Note that if cooldown is involved, then m = [0,....j-2] or m = [0,...j-3] etc depending upon
//cooldown number of days.
