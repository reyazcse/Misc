//https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/
/*
Given a knapsack weight W and a set of n items with certain value vali and weight wti, we need to calculate the maximum amount that could make up this quantity exactly. 
Here we are allowed to use unlimited number of instances of an item.
Input: N = 2, W = 3
val[] = {1, 1}
wt[] = {2, 1}
Output: 3
Explaination: Pick the 2nd element thrice.
 * */

//Solution: O(N^2) time and O(W) space
package misc;

public class KnapsackUnbounded {
	static int knapSack(int N, int W, int val[], int wt[])
    {
        int[] dp = new int [W + 1];
        dp[0] = 0;
        for(int i=1; i<=W; i++) {
            for(int j=0; j<wt.length; j++) {            //try using all valid weights
                if(i >= wt[j]) {                        //check if this weight can be used
                    dp[i] = Math.max(dp[i], val[j] + dp[i - wt[j]]);            //val[j] is value of item of weight wt[j]
                }
            }
        }
        return dp[W];
    }

}
