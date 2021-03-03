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
package leetcode;

import java.util.Stack;

/*
Solution:
	We create a table of (k+1) rows and prices.length columns.
	T[i][j] => profit by doing i transactions at jth day		(at most i transactions to be exact as we may not do the ith txn)
	T[i][j] will max of these two:
		T[i][j-1]  //not selling on jth day 
		prices[j] - prices[m] + T[i-1][m]; m belongs to [0,...j-1]  //selling on jth day and buying on mth day and profit at mth day
		


Complexity for finding the max profit:
	Time: O(k * days^2) 
	Spacek: O(k * days)
* */

public class BuyAndSellStockK {
	public int maxProfit(int[] prices, int k) {
		int[][]table = new int[k+1][prices.length];
		for(int j=0; j<prices.length; j++) {
			table[0][j] = 0;
		}
		for(int i=0; i<=k; i++) {
			table[i][0] = 0;
		}
		for(int i=1; i<=k; i++) {
			for(int j=1; j<prices.length; j++) {
				int tempMax = Integer.MIN_VALUE;
				for(int m = 0; m < j; m++) {
					tempMax = Math.max(tempMax, table[i-1][m] + prices[j] - prices[m]);	  //total profit at j = profit with (k-1) txn + buy at m and sell at j
				}
				table[i][j] = Math.max(table[i][j-1], tempMax);		//We take table[i][j-1] meaning no txn is done at jth day
			}
		}
		printTable(table);
		buySell(prices, table);
		return table[k][prices.length-1];
    }
	
	//printing the buy and sell transactions
	private void buySell (int[]prices, int[][] table) {
		int i = table.length-1;
		int j = table[0].length -1 ;
		Stack<Integer> stack = new Stack<>();
		while(true) {
			if(i == 0 || j == 0) {
				break;
			}
			if(table[i][j-1] == table[i][j]) {
				j--;
			} else {
				int currVal = table[i][j];
				stack.push(j);
				
				for(int m=j-1 ; m >=0 ; m--) {
					if(currVal == table[i-1][m] + prices[j] - prices[m]) {
						j = m;
						i--;
						stack.push(j);
						break;
					}
				}
			}
		}
		while(!stack.isEmpty()) {
			System.out.println("buy at " + stack.pop());
			System.out.println("sell at " + stack.pop());
		}
	}
	
	public static void main(String[] args) {
		int[] prices = {1,5,3,2,1};										//in this case we get max profit if we make just k=1 txn
		//int[] prices = {2,5,7,1,4,3,1,3};
		int k = 2;
		BuyAndSellStockK obj = new BuyAndSellStockK();
		System.out.println(obj.maxProfit(prices, k));

	}
	
	private void printTable(int [][] table) {
		for(int i=0; i<table.length; i++) {
			System.out.println();
			for(int j=0; j<table[0].length; j++) {
				System.out.print(table[i][j] + "  ");
			}
		}
	}

}

//Reference: https://www.youtube.com/watch?v=oDhu5uGq_ic
//Note that if cooldown is involved, then m = [0,....j-2] or m = [0,...j-3] etc depending upon
//cooldown number of days.


