package Algorithm.Graph;

public class Test463_IslandPerimeter {
	// TC: O(m*n)
	// SC: O(m/n)
	public int islandPerimeterI(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					return dfs(i, j, grid, dirs);
				}
			}
		}
		// if no island found, return 0
		return 0;
	}

	private int dfs(int i, int j, int[][] grid, int[][] dirs) {
		int m = grid.length, n = grid[0].length;
		int len = 0;
		// mark 2 as visited
		grid[i][j] = 2;
		for (int[] dir : dirs) {
			int newI = i + dir[0];
			int newJ = j + dir[1];
			// if it's in border, perimeter++
			if (newI < 0 || newI >= m) len++;
			if (newJ < 0 || newJ >= n) len++;
			// in border, no need to dfs neighbor
			if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
			// if neighbor is water, perimeter++
			if (grid[newI][newJ] == 0) len++;
			// if neighbor is not visited, dfs neighbor
			if (grid[newI][newJ] == 1) len += dfs(newI, newJ, grid, dirs);
		}
		return len;
	}

	// TC: O(m*n)
	// SC: O(1)
	// for a cell, check its four neighbor cells
	// if neighbor is out of board or is water, count++
	public int islandPerimeterII(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int count = 0;
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					for (int[] dir : dirs) {
						int newI = i + dir[0], newJ = j + dir[1];
						if (newI < 0 || newI >= m || newJ < 0 || newJ >= n || grid[newI][newJ] == 0) {
							count++;
						}
					}
				}
			}
		}
		return count;
	}
}
