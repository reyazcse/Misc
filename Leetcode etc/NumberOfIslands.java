//src: https://leetcode.com/problems/number-of-islands/
//use DFS to solve. here we use visited as we dont want to alter the original grid.
//else we can make grid cell '1' as '0' when we visit.

package misc;

public class NumberOfIslands {
	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0)  return 0;    //for empty or null input
		int m =grid.length;
		int n = grid[0].length;
		int cnt=0;
		boolean[][] visited = new boolean[m][n];
		for(int i=0; i<m; i++) {
			for(int j=0;j<n;j++) {
				//we get island when this cell is '1' and has not been visited before
				if(!visited[i][j] && grid[i][j] == '1') {
					cnt++;						
					dfs(i,j,visited, grid);    //mark all other cells of the island as visited
				}
			}
		}
		return cnt;
		
    }
	public void dfs (int i, int j, boolean[][] visited, char [][] grid) {
		if(i < 0 || i>= grid.length || j<0 || j>= grid[0].length || visited[i][j] || grid[i][j] == '0')
			return;
		visited[i][j] = true;
		dfs(i,j+1, visited, grid);
		dfs(i+1,j, visited, grid);
		dfs(i-1,j, visited, grid);
		dfs(i,j-1, visited, grid);
	}
	public static void main(String[] args) {
		NumberOfIslands obj = new NumberOfIslands();
		char [][] grid = {{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}};
		
		char [][] grid2 = {{'1','1','0','0','0'},
						   {'1','1','0','0','0'},
				           {'0','0','1','0','0'},
				           {'0','0','0','1','1'}};
		System.out.println(obj.numIslands(grid));
	}

}
