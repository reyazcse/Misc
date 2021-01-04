//https://www.geeksforgeeks.org/0-1-knapsack-problem-dp-10/
/*
You are given weights and values of N items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack. Note that we have only one quantity of each item.
In other words, given two integer arrays val[0..N-1] and wt[0..N-1] which represent values and weights associated with N items respectively. Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

Example 1:

Input:
N = 3
W = 4
values[] = {1,2,3}
weight[] = {4,5,1}
Output: 3
 * */


package misc;

class Knapsack 
{ 
    // Returns the maximum value that can be put in a knapsack of capacity W 
    static int knapSack(int W, int wt[], int val[], int n) 
    { 
         int[][] dp = new int[wt.length + 1][W + 1];
         //dp[i][j] means maximum value we can get for wt[0]....wt[i-1] and weight = j
         
         //first row is 0 since for empty set of weights, we get 0 value
         //first column is also 0 since W = 0, so we get maximum value as 0
         
         for(int i=1; i<dp.length; i++) {
             for(int j=1; j<dp[0].length; j++) {
                 //include current wt[i-1]
                 int include = wt[i-1] <= j ? val[i-1] + dp[i-1][j - wt[i-1]] : 0;
                 
                 //exclude current wt[i-1]
                 int exclude = dp[i-1][j];
                 dp[i][j] = Math.max(include, exclude);
             }
         }
         return dp[wt.length][W];
    } 
}
