//https://leetcode.com/problems/course-schedule/
/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
 * */

/*
Solution:
This is cycle detection in directed graph.
	Do dfs of graph.
	If cycle is found then we cannot finish the course.
 * */
package leetcode;

import java.util.ArrayList;
import java.util.List;

import mylibrary.Graph;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		if(numCourses == 0 || prerequisites == null) {
			return false;
		}
		if(prerequisites.length == 0) return true;  //base case when no prerequisites
		Graph g = getGraph(numCourses, prerequisites);
		int n = numCourses;
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		for(int i=0; i<n; i++) {
			if(!visited[i] && detectCycleUtl(g, i, visited, recStack)) {
				return false;
			}
		}
		return true;
    }

	private boolean detectCycleUtl(Graph g, int curr, boolean[] visited, boolean[] recStack) {
		visited[curr] = true;
		recStack[curr] = true;
		List<Integer> neighbours = g.adjList[curr];
		for(int i=0; i<neighbours.size(); i++) {
			int neighbour = neighbours.get(i);
			if(!visited[neighbour] && detectCycleUtl(g, neighbour, visited, recStack)) {
				return true;
			}else if(recStack[neighbour]) {  //visited but still on recStack
				return true;
			}
		}
		recStack[curr] = false;
		return false;
	}
	
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
		int numCourses = 1;
		int[][] prerequisites = {};
		System.out.println(prerequisites);
//		prerequisites[0][0] = 1;
//		prerequisites[0][1] = 0;
		CourseSchedule obj = new CourseSchedule();
		System.out.println(obj.canFinish(numCourses, prerequisites));
	}

}

/*
Graph class

public class Graph {
	public int totalVertices;
	public List<Integer> [] adjList;
	public Graph(int num) {
		totalVertices = num;
		adjList = new List[totalVertices];
		for(int i=0; i<totalVertices; i++) {
			adjList[i] = new ArrayList<>();
		}
	}
	
	public void addEdgeUndirected(int i, int j) {
		adjList[i].add(j);
		adjList[j].add(i);
	}
	
	public void addEdgeDirected(int i, int j) {
		adjList[i].add(j);
	}
}

 * */
