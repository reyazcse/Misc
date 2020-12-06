//https://leetcode.com/problems/rotting-oranges/

//Solution: Similar to RottingOranges.java but here no use of delimiter
package leetcode;

import java.util.LinkedList;
import java.util.Queue;


public class RottingOranges2 {

	public int orangesRotting(int[][] grid) {
		Queue<int[]> q = new LinkedList<>();
		int mins = 0;
		int[] x = {0,-1,0,1};
		int[] y = {-1,0,1,0};
		int fresh_count = 0;							//stores count of fresh oranges
		for(int i=0; i<grid.length; i++) {
			for(int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 2) {
					q.add(new int[] {i, j});
					
				}else if(grid[i][j] == 1) {
					fresh_count++;
				}
			}
		}
		
		while(!q.isEmpty()) {
			int size = q.size();
			for(int i=0; i<size; i++) {
				int[] curr = q.poll();
				
				//go to the adjacent 4 cells
				for(int k=0; k<4; k++) {
					int row = curr[0] + x[k];
					int col = curr[1] + y[k];
					if(isSafe(grid, row, col) && grid[row][col] == 1) {
						grid[row][col] = 2;
						fresh_count--;
						q.add(new int[] {row, col});
					}
				}
			}
			mins++;
		}
		
		
		return fresh_count==0? mins-1 : -1;			//use fresh_count saves iterating over the matrix again
		
		
	}

	private boolean isSafe(int[][] grid, int r, int c) {
		return (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length);
	}



	public static void main(String[] args) {
		int[][]grid = {{2,1,1},{1,1,0},{0,1,1}};
		System.out.println(new RottingOranges2().orangesRotting(grid));

	}

}
