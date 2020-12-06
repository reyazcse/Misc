//https://leetcode.com/problems/rotting-oranges/
/*
In a given grid, each cell can have one of three values:

	the value 0 representing an empty cell;
	the value 1 representing a fresh orange;
	the value 2 representing a rotten orange.

Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

 * */

/*
Solution: BFS using 'null' as delimiter

Complexity: O(r * c) time and O(r * c )space

 * */
package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {
	public int orangesRotting(int[][] grid) {
		int mins = 0;
		int[] x = {0,-1,0,1};
		int[] y = {-1,0,1,0};
		Queue<Pair> q = new LinkedList<>();
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 2) {
					q.add(new Pair(i,j));
				}
			}
		}
		
		q.add(null);
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			if(p == null) {					//if delimiter
				if(q.isEmpty()) {			//if popping null marker, if queue is empty, then break
					break;					//else we run into infinite loop
				}
				q.add(null);
				mins++;
			}else {
				
				//process adjacent four cells
				for(int i=0; i<4; i++) {
					int row = p.row + x[i];
					int col = p.col + y[i];
					if(isSafe(grid, row, col) && grid[row][col] == 1) {
						grid[row][col] = 2;
						q.add(new Pair(row, col));
					}
				}
			}
		}
		
		//if any fresh orange found, return -1
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 1) {
					return -1;
				}
			}
		}
		
		//all oranges rotten, hence return minutes
		return mins;
		
	}
	
	private boolean isSafe(int[][] grid, int r, int c) {
		return (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length);
	}
	private class Pair{
		public int row;
		public int col;
		public Pair(int r, int c) {
			this.row = r;
			this.col = c;
		}
	}
	public static void main(String[] args) {
		int[][]grid = {{2,1,1},{1,1,0},{0,1,1}};
		System.out.println(new RottingOranges().orangesRotting(grid));

	}

}
