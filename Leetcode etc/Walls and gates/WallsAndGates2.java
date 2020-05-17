package leetcode;

import java.util.LinkedList;
//Leetcode: Walls and gates
//Solution using BFS
import java.util.Queue;

public class WallsAndGates2 {
	private static int inf = Integer.MAX_VALUE;
	public void solution(int [][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return;
		Queue<Integer> queue = new LinkedList<>();
		int m = grid.length; 
		int n = grid[0].length;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(grid[i][j] == 0) {
					int cell = i*n + j;
					queue.add(cell);
				}
			}
		}
		while(!queue.isEmpty()) {
			int cell = queue.poll();
			int x = cell/n;
			int y = cell%n;
			int steps = grid[x][y];
			//filling the adjacent cells which are empty rooms(i.e. inf) and adding those cells to queue
			if(x > 0 && grid[x-1][y] == inf) {
				grid[x-1][y] = steps+1;
				queue.add((x-1)*n + y);
			}
				
			if(x < m-1 && grid[x+1][y] == inf) {
				grid[x+1][y] = steps+1;
				queue.add((x+1)*n + y);
			} 
				
			if(y > 0 && grid[x][y-1] == inf) {
				grid[x][y-1] = steps+1;
				queue.add(x*n + y-1);
			}
			if(y < n-1 && grid[x][y+1] == inf) {
				grid[x][y+1] = steps+1;
				queue.add(x*n + y+1);
			}
				
		}
	}
	
	public static void main(String[] args) {
		int [][] grid = {{inf, -1, 0 , inf},
				{inf, inf, inf, -1},
				{inf, -1, inf, -1},
				{0, -1, inf, inf}};
		WallsAndGates obj = new WallsAndGates();
		obj.solution(grid);
		obj.printGrid(grid);

	}
}
