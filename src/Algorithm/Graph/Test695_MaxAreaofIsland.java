package Algorithm.Graph;

public class Test695_MaxAreaofIsland {
	// check each cell, if it's 1, do dfs checking all connected 1s
	// return the number of all connect 1s
	// and flip those 1 into 0
	// TC: O(m*n)
	// SC: O(m/n) dfs stack at most height(grid)/width(grid)
	public int maxAreaOfIsland(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					max = Math.max(max, dfs(i, j, grid));
				}
			}
		}
		return max;
	}

	private int dfs(int i, int j, int[][] grid) {
		int m = grid.length, n = grid[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n) return 0;
		if (grid[i][j] == 0) return 0;

		grid[i][j] = 0;
		int left = dfs(i, j - 1, grid);
		int right = dfs(i, j + 1, grid);
		int up = dfs(i - 1, j, grid);
		int down = dfs(i + 1, j, grid);
		return 1 + left + right + up + down;
	}
}
