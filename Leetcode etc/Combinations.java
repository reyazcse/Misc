//https://leetcode.com/problems/combinations/
/*
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

You may return the answer in any order.

Example 1:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
 * */

/*
Solution:
	The idea is when we are at a particular value of n, let's say n=1, then we put 1 or 2 or...till n and then go the next value
	We also keep a count variable so that we can know when we are done with k.
	We recurse with a number and then backtrack to put the other value of n.
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> path = new ArrayList<>();
		dfs(result,  path, n, k, 1, 0);
		return result;
	}
	
	private void dfs(List<List<Integer>> result, List<Integer> path, int n, int k, int val, int count) {
		if(count == k) {						//we have put k numbers on the path
			result.add(new ArrayList<>(path));
			return;
		}
		
		//for each val till n, we put it on path each time and then recurse. We backtrack to try with next number
		//In the next recursive call, we try with next value (i+1)
		for(int i=val; i<=n; i++) {
			path.add(i);
			dfs(result, path, n, k, i+1, count+1);
			path.remove(path.size()-1);		//backtrack
		}
	}
	public static void main(String[] args) {
	
		

	}

}
