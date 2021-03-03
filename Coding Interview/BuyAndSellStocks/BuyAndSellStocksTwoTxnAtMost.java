//https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/buy-sell-stocks-tta-official/ojquestion
/*
1. You are given a number n, representing the number of days.
2. You are given n numbers, where ith number represents price of stock on ith day.
3. You are required to print the maximum profit you can make if you are allowed two transactions at-most.
Note - There can be no overlapping transaction. One transaction needs to be closed (a buy followed by a sell) before opening another transaction (another buy).
Input Format
A number n
.. n more elements
Output Format
A number representing the maximum profit you can make if you are allowed a single transaction.

 * */
package misc;

import java.util.Scanner;

public class BuyAndSellStocksTwoTxnAtMost {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[]arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		
		int [] max_profit_so_far_till_today_sale = new int[n];	//stores maximum profit we can get if we have done one sell till today
		int [] max_profit_so_far_from_today_buy = new int[n];	//stores maximum profit we can get if we do one buy from today 	
		
		int min = Integer.MAX_VALUE;						//keeps track of minimum stock price on the left side
		int max_profit_so_far = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			if(arr[i] < min) {
				min = arr[i];
			}
			max_profit_so_far = Math.max(max_profit_so_far, arr[i] - min);		//sell today and stock price is the minimum value we have encountered on previous days
			max_profit_so_far_till_today_sale[i] = max_profit_so_far;
		}
		
		int max = Integer.MIN_VALUE;						//keeps track of maximum stock price on the right side
		max_profit_so_far = Integer.MIN_VALUE;
		
		for(int i=n-1; i>=0; i--) {
			if(arr[i] > max) {
				max = arr[i];
			}
			max_profit_so_far = Math.max(max_profit_so_far, max - arr[i]);		//buy today and sell when stock price is maximum 
			max_profit_so_far_from_today_buy[i] = max_profit_so_far;
		}
		
		//now for each day, get the sum of max_profit_so_far_sell_today[i] and max_profit_so_far_buy_today[i]
		//return the maximum of these values
		
		max = Integer.MIN_VALUE;
		for(int i=0; i<n; i++) {
			max = Math.max(max, max_profit_so_far_till_today_sale[i] + max_profit_so_far_from_today_buy[i]);
		}
		System.out.println(max);
	}

}
