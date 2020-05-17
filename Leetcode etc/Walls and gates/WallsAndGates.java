//Leetcode: Walls and gates
//Solution using DFS
package leetcode;

public class WallsAndGates {
	private static int inf = Integer.MAX_VALUE;
	
	public void solution(int [][]grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) return;
		for(int i=0; i< grid.length; i++) {
			for (int j=0; j<grid[0].length; j++) {
				if(grid[i][j] == 0)
					dfs(grid,i,j,0);
			}
		}
	}
	private void dfs(int[][] grid, int i, int j, int steps) {
		if (outOfBounds(grid, i, j) || grid[i][j] < steps)  //-1 and inf value are taken care by last condition
			return;
		grid[i][j] = steps;
		dfs(grid, i+1, j, steps+1);
		dfs(grid, i-1, j, steps+1);
		dfs(grid, i, j+1, steps+1);
		dfs(grid, i, j-1, steps+1);
	}
	
	private boolean outOfBounds(int[][] grid, int r, int c) {
		if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) 
			return true;
		return false;
	}
	
	public void printGrid(int [][] grid) {
		if (grid == null) return;
		for(int i=0; i< grid.length; i++) {
			for (int j=0; j<grid[0].length; j++) {
				System.out.print(grid[i][j] + "    ");
			}
			System.out.println();
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
