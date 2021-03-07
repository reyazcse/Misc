//https://leetcode.com/problems/task-scheduler/
/*
Given a characters array tasks, representing the tasks a CPU needs to do, where each letter represents a different task. Tasks could be done in any order. Each task is done in one unit of time. For each unit of time, the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown period between two same tasks (the same letter in the array), that is that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish all the given tasks.

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.
 * */

/*
Solution:
	Greedy approach.
	First put the tasks which greater frequency.
	Hence create a count map for the tasks.
	
	Put the counts in max heap.
	The interval size for each iteration will be n+1.
	Put the tasks one by one by extracting from max heap. Decrease count by 1
	Finally put the tasks again in the heap.
	
References: https://www.youtube.com/watch?v=tGw5GbDekTU  [Knowledge Center]
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {
	public int leastInterval(char[] tasks, int n) {
		Map<Character, Integer> tasksCount = new HashMap<>();
		
		for(char t : tasks) {
			tasksCount.put(t, tasksCount.getOrDefault(t, 0) + 1);
		}
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		pq.addAll(tasksCount.values());
		
		int cycles = 0;
		
		while(!pq.isEmpty()) {
			int time = 0;
			List<Integer> tmp = new ArrayList<>();
			for(int i=0; i<=n; i++) {
				if(!pq.isEmpty()) {
					tmp.add(pq.remove() - 1);
					time++;
				}
				
			}
			for(int t : tmp) {
				if(t > 0) {
					pq.add(t);
				}
			}
			cycles += pq.isEmpty() ? time : n+1;
		
			
		}
		
		
		return cycles;
		
	}

}
