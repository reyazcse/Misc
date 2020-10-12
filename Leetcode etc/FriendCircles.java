//https://leetcode.com/problems/friend-circles/
/*
Question:
	There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
	Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
	
	Example 1:
	
	Input: 
	[[1,1,0],
	 [1,1,0],
	 [0,0,1]]
	Output: 2
	Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
	The 2nd student himself is in a friend circle. So return 2.
	
	Constraints:
	
	1 <= N <= 200
	M[i][i] == 1
	M[i][j] == M[j][i]
 * */

/*
Solution:
	For a person, we visit his friends (bfs) OR we visit a friend then friend of friend and so on (dfs)
	When we get a new person, we increment circle count and then visit all friends in a circle
 * */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class FriendCircles {
	public int findCircleNum(int[][] M) {
		if(M == null || M.length == 0)
			return 0;
		int n = M.length;
		int result = 0;
		boolean[] visited = new boolean[n];
		for(int i=0; i<n ;i++) {
			for(int j=0; j<n; j++) {
				//if we visit this person for the first time
				if(M[i][j] == 1 && !visited[i]) {
					result++;
					bfs(i,visited,M);		//or call dfs(i,visited,M)
				}
			}
		}
		return result;
	}
	
	private void bfs(int person, boolean[]visited, int[][]M) {
		Queue<Integer> q = new LinkedList<>();
		q.add(person);
		//process the person
		visited[person] = true;
		while(!q.isEmpty()) {
			int row = q.poll();
			//process friends of the person
			for(int friend = 0; friend < M[0].length; friend++) {
				if(!visited[friend] && M[row][friend] == 1) {
					visited[friend] = true;
					q.add(friend);
				}
			}
					
		}
	}
	
	private void dfs(int person, boolean[] visited, int[][]M) {
		//process current person
		visited[person] = true;
		//process friends of this person
		for(int friend = 0; friend < M[0].length; friend++) {
			if(!visited[friend] && M[person][friend] == 1) {
				dfs(friend, visited, M);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
