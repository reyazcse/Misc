//https://leetcode.com/problems/merge-intervals/
/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Constraints:

intervals[i][0] <= intervals[i][1]
 * */

/*
The idea is sort the intervals on basis of start time.
If start time is same, then by end time since we merge second interval into the first here in our algorithm
Then start merging by comparing current interval with the last interval stored in the result array.

 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
	public int[][] merge(int[][] intervals) {
		List<int[]> result = new ArrayList<>();
		
		//sort intervals on basis of first element of interval. If first element are equal, then
		//sort by second element of the interval. [1,3], [1,4]
		Arrays.sort(intervals, (a1, a2) -> {
			if(a1[0] == a2[0]) {
				return a1[1]-a2[1];
			}else return a1[0] - a2[0];
		});
		//add the first interval to result
		result.add(intervals[0].clone());
		int i=0, j=1;						//j=1 as we start from second interval in the given array
		for(;j<intervals.length; j++) {
			int [] prevInterval = result.get(i);
			//merge case if start of 2nd interval is less than equal end of prev
			if(intervals[j][0] <= prevInterval[1]) {	
				//end of merged interval is max of (end of prev interval, start of current interval)
				int max = Math.max(prevInterval[1], intervals[j][1]);
				prevInterval[1] = max;		//update in result array
			}else {	//no merge
				i++;
				result.add(intervals[j].clone());	//simply put in the result array
			}
		}
		return result.toArray(new int[result.size()][]);
	}
	public static void main(String[] args) {
		
		MergeIntervals ob = new MergeIntervals();
		int [][] intervals = {{2,10},{1,6},{1,4}};
		ob.merge(intervals);
	}

}
