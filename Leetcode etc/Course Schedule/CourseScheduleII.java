//https://leetcode.com/problems/course-schedule-ii/
/*
Question:
	There are a total of n courses you have to take labelled from 0 to n - 1.
	Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi] this means you must take the course bi before the course ai.
	Given the total number of courses numCourses and a list of the prerequisite pairs, return the ordering of courses you should take to finish all courses.
	If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
	
	Example 1:
	
	Input: numCourses = 2, prerequisites = [[1,0]]
	Output: [0,1]
	Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 * */

/*
Solution:
	We can solve it using topological sorting.
	If the graph has cycle, topological sorting is NOT possible.
	We do dfs and it finds cycle as well as does topological sorting.
 * */

package leetcode;

import java.util.List;
import java.util.Stack;

import mylibrary.Graph;

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] order = new int[numCourses];
		//base case1 : 0 courses 
		if(numCourses == 0) {
			return order;
		}
		//if no prequisites, then we simply return the the input order
		if(prerequisites == null || prerequisites.length == 0) {
			for(int i=0; i<numCourses; i++) {
				order[i] = i;
			}
			return order;
		}
		Graph g = getGraph(numCourses, prerequisites);
		int n = numCourses;
		//stack to store the vertices in the topological order
		Stack<Integer> vertices = new Stack<>();
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		
		//topological sort. We check for cycle also while doing the dfs.
		//If cycle is found, then we return empty response
		for(int i=0; i<n; i++) {
			if(!visited[i] && detectCycleUtl(g, i, visited, recStack, vertices)) { //if cycle found, empty solution
				return new int[0];  //empty array if cycle is found
			}
		}
		int i=n-1;
		while(!vertices.isEmpty()) {
			order[i--] = vertices.pop();
		}
		return order;
	}
	
	//dfs: it checks cycle as well as do topological order
	private boolean detectCycleUtl(Graph g, int curr, boolean[] visited, boolean[] recStack, Stack<Integer> stack) {
		visited[curr] = true;
		recStack[curr] = true;
		List<Integer> neighbours = g.adjList[curr];
		for(int i=0; i<neighbours.size(); i++) {
			int neighbour = neighbours.get(i);
			if(!visited[neighbour] && detectCycleUtl(g, neighbour, visited, recStack, stack)) {
				return true;
			}else if(recStack[neighbour]) {  //visited but still on recStack
				return true;
			}
		}
		recStack[curr] = false;
		stack.push(curr);     //topological ordering
		return false;
	}
	
	//builds graph
	private Graph getGraph(int v, int[][] prerequisites ) {
		Graph g = new Graph(v);
		for(int i=0; i<prerequisites.length; i++) {
			int fromVertex = prerequisites[i][0];
			int toVertex = prerequisites[i][1];
			g.addEdgeDirected(fromVertex, toVertex);
		}
		return g;
	}
	public static void main(String[] args) {
		int numCourses = 2;
		int[][] prerequisites = {{0,1},{1,0}};
		System.out.println(prerequisites);
//		prerequisites[0][0] = 1;
//		prerequisites[0][1] = 0;
		CourseScheduleII obj = new CourseScheduleII();
		int[] result = obj.findOrder(numCourses, prerequisites);
		for(int vertex : result) {
			System.out.println(vertex);
		}
	}

	

}
