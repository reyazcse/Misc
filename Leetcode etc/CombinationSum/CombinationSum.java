//https://leetcode.com/problems/combination-sum/
/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]

 * */


package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	
	/*****************************************DFS include or exclude****************************************************/
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		utl(candidates, 0, target, current, result);
		return result;
	}
	
	private void utl(int[] candidates, int i, int sum, ArrayList<Integer> current, List<List<Integer>> result) {
		if(sum == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(sum < 0 || i == candidates.length) {
			return;
		}
		
		//include candidate i
		current.add(candidates[i]);
		utl(candidates, i, sum-candidates[i], current, result);
		current.remove(current.size()-1);
		
		//exclude candidate i
		utl(candidates, i+1, sum, current, result);
	}
	
	/***************************************DFS include or exclude with sort and prune*********************************/
	public List<List<Integer>> combinationSumA(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		Arrays.sort(candidates);
		utlA(candidates, 0, target, current, result);
		return result;
	}
	
	private void utlA(int[] candidates, int i, int sum, ArrayList<Integer> current, List<List<Integer>> result) {
		if(sum == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(sum < 0 || i == candidates.length) {
			return;
		}
		
		if(candidates[i] > sum) {								//early return
			return;
		}
		
		//include candidate i
		current.add(candidates[i]);
		utlA(candidates, i, sum-candidates[i], current, result);
		current.remove(current.size()-1);
		
		//exclude candidate i
		utlA(candidates, i+1, sum, current, result);
	}
	
	
	/**************************************DFS with loop*******************************************************/
	
	public List<List<Integer>> combinationSumB(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		utlB(candidates, 0, target, current, result);
		return result;
	}
	
	private void utlB(int[] candidates, int i, int sum, ArrayList<Integer> current, List<List<Integer>> result) {
		if(sum == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(sum < 0) {
			return;
		}
		
		for(int pos = i; pos < candidates.length; pos++) {
			current.add(candidates[pos]);
			utlB(candidates, pos, sum-candidates[pos], current, result);
			current.remove(current.size()-1);
		}
	}
	
	
/**************************************DFS with loop | sort and prune*******************************************************/
	
	public List<List<Integer>> combinationSumC(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		ArrayList<Integer> current = new ArrayList<>();
		Arrays.sort(candidates);
		utlC(candidates, 0, target, current, result);
		return result;
	}
	
	private void utlC(int[] candidates, int i, int sum, ArrayList<Integer> current, List<List<Integer>> result) {
		if(sum == 0) {
			result.add(new ArrayList<>(current));
			return;
		}
		if(sum < 0) {
			return;
		}
		
		for(int pos = i; pos < candidates.length; pos++) {
			if(candidates[pos] > sum) {							//pruning: early return					
				break;												
			}
			current.add(candidates[pos]);
			utlC(candidates, pos, sum-candidates[pos], current, result);
			current.remove(current.size()-1);
		}
	}
	
	
	public static void main(String[] args) {
//		int[] candidates = {2,3,6,7};
//		int target = 7;
		
		int[] candidates = {1,2,3};
		int target = 4;
		List<List<Integer>> result = new CombinationSum().combinationSum(candidates, target);
		for(List<Integer> ans : result) {
			System.out.println(ans);
		}
		

	}

}
