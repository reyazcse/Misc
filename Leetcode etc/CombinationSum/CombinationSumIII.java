//https://leetcode.com/problems/combination-sum-iii/
/*
Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]

* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
	/*********************************************DFS with loop****************************************************/
	
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		utl(k, n, 0, 1, current, result);
		return result; 

	}
	
	private void utl(int k, int sum, int count, int start, List<Integer> current, List<List<Integer>> result) {
		if(sum == 0 && count == k) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(sum < 0  || count > k) {
			return;
		}
		
		for(int i=start; i<=9; i++) {
			current.add(i);
			utl(k, sum-i, count+1, i+1, current, result);		//for next call, i+1 since we can use a number at most once
			current.remove(current.size()-1);
		}
	}
	
	
	
	/***********************************DFS with include or exclude*****************************************************/
	public List<List<Integer>> combinationSum3A(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		int[] candidates = {1,2,3,4,5,6,7,8,9};
		utlA(candidates, k, n, 0, 0, current, result);
		return result; 
	}
	
	private void utlA(int[] candidates, int k, int sum, int count, int index, List<Integer> current, List<List<Integer>> result) {
		if(sum == 0 && count == k) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(index == candidates.length) {
			return;
		}
		
		//include
		current.add(candidates[index]);
		utlA(candidates, k, sum-candidates[index], count+1, index+1, current, result);		//for next call, index+1 since we can use a number at most once
		current.remove(current.size()-1);
		
		//exclude
		utlA(candidates, k, sum, count, index+1, current, result);
	}
	

}
