//Leetcode: Walls and gates
//Solution using BFS
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates3 {
	private static int inf = Integer.MAX_VALUE;
	public void solution (int [][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return;
		Queue<Cell> queue = new LinkedList<>();
		int m = grid.length; 
		int n = grid[0].length;
		int [][] moves = {{-1,0},{1,0},{0,-1},{0,1}};
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				if(grid[i][j] == 0) {
					queue.add(new Cell(i,j));
				}
			}
		}
		while(!queue.isEmpty()) {
			Cell cell = queue.poll();
			int x = cell.x;
			int y = cell.y;
			for(int i=0; i<4; i++) {		//adding candidate cells
				addToQueue(x+moves[i][0], y+moves[i][1], grid, grid[x][y], queue);
			}
			
		}
	}
	public void addToQueue(int x, int y, int[][] grid, int steps, Queue<Cell> q) {
		if(x<0 || x>=grid.length || y<0 || y>=grid[0].length || grid[x][y] == -1 || grid[x][y] != inf)
			return;
		grid[x][y] = steps+1;
		q.add(new Cell(x,y));
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
	
	private static class Cell {
		public int x;
		public int y;
		public Cell(int i, int j) {
			this.x = i;
			this.y = j;
		}
	}
}
