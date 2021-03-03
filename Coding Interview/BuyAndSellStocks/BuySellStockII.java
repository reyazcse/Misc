//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
//Check leetcode solution page also

package leetcode;

public class BuySellStockII {
	//Collect all the profits
	public int maxProfitEfficient(int[] prices) {
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            int tmp = prices[i] - prices[i-1];
            if(tmp > 0) {							//if profit collect it
                profit += tmp;
            }
        }
        return profit;
    }
	
	
	
	
	
	//Slow expnential method : O(n ^ n) time and O(n) space for recursion
	//We can improve this solution using a table for keeping computed values.
	public int maxProfit(int[] prices) {
		return calculate(prices, 0);
    }
	private int calculate (int[]prices, int start) {
		int max = 0;
		for(int i=start; i<prices.length; i++) {
			int maxProfit = 0;
			for(int j=i+1; j<prices.length; j++) {
				
				int profit = calculate(prices, j+1) + prices[j]-prices[i];
				maxProfit = Math.max(maxProfit, profit);
			}
			max = Math.max(max, maxProfit);
		}
		return max;
	}
	public static void main(String[] args) {
		int[] prices = {7,1,5,3,6,4};
		BuySellStockII obj = new BuySellStockII();
		System.out.println(obj.maxProfit(prices));

	}

}
