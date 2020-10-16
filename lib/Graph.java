package mylibrary;

import java.util.ArrayList;
import java.util.List;

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