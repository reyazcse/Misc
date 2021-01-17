//https://leetcode.com/problems/queue-reconstruction-by-height/
/*
ou are given an array of people, people, which are the attributes of some people in a queue (not necessarily in order). Each people[i] = [hi, ki] represents the ith person of height hi with exactly ki other people in front who have a height greater than or equal to hi.

Reconstruct and return the queue that is represented by the input array people. The returned queue should be formatted as an array queue, where queue[j] = [hj, kj] is the attributes of the jth person in the queue (queue[0] is the person at the front of the queue).

 

Example 1:

Input: people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
Output: [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]

Constraints:

1 <= people.length <= 2000
0 <= hi <= 106
0 <= ki < people.length
It is guaranteed that the queue can be reconstructed.
 * */

/*
Solution:
	First sort the pairs in decreasing order of heights.
	For same height, sort in ascending order of k
	Then iterate over the sorted list and for each element put it at index equal to its k value
	
	------------------------------------------------------------------------------------------
	For pairs with same height, we put the one having smaller k value first:
	(7,0) then (7,1) and NOT (7,1) (7,0)
	
	After we have sorted heights in decreasing order, for a pair (h,k), all pairs to its left side have heights greater than h.
	So if we place this pair anywhere on its left side, then the k values of any pair on the left side will not be affected.
	We place this pair at the k index. Then we have k pairs to its left whose heights are greater than or equal to the height of current pair.
	And there will be shifting of pairs from index k towards right side.
	 
	
Complexity:
	O(n^2) time for inserting each pair in the sorted list
	O(n) space
	n = number of pairs
	
 * */
package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueueReconstructionHeight {
	public int[][] reconstructQueue(int[][] people) {
		
		Arrays.sort(people, (p1, p2) -> {
			if(p1[0] == p2[0]) {							//same height
				return p1[1] - p2[1];
			}else {											//sort in descending order of heights
				return p2[0] - p1[0];
			}
		});
		
		List<int []> result = new ArrayList<>();
		//takes O(n^2) time where n = number of pairs
		for(int[] p : people) {
			result.add(p[1], p);
		}
		
		return result.toArray(new int [people.length][2]);
	}
	
	

}
