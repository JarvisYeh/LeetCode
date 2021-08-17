package Algorithm.Graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test200_NumberofIslands {
	// dfs
	// TC: O(m*n)
	// SC: O(m/n) in stack
	public int numIslandsI(char[][] grid) {
		// islandIdxs[i][j] represents the island idx of grid[i][j], start from 1
		// 0 means it is not traversed / it is ocean
		int[][] islandIdxs = new int[grid.length][grid[0].length];
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (islandIdxs[i][j] == 0 && grid[i][j] == '1')
					dfs(i, j, grid, islandIdxs, ++count);
			}
		}
		return count;
	}

	private void dfs(int i, int j, char[][] grid, int[][] islandIdxs, int idx) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
		if (grid[i][j] == '0') return;
		if (islandIdxs[i][j] != 0) return;

		// traverse grid[i][j]
		islandIdxs[i][j] = idx;
		// traverse 4 directions
		int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
		for (int[] dir : dirs) {
			dfs(i + dir[0], j + dir[1], grid, islandIdxs, idx);
		}
	}

	// bfs
	// TC: O(m*n)
	// SC: O(m*n) easy to reach memory limit
	public int numIslandsII(char[][] grid) {
		int[][] dirs ={{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
		int count = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == '0') continue;
				count++;
				Queue<Cell> q = new ArrayDeque<>();
				q.offer(new Cell(i, j));
				grid[i][j] = '0';
				while (!q.isEmpty()) {
					Cell curr = q.poll();
					grid[curr.i][curr.j] = '0';
					for (int[] dir : dirs) {
						int newI = curr.i + dir[0];
						int newJ = curr.j + dir[1];
						if (newI < 0 || newI >= grid.length || newJ < 0 || newJ >= grid[0].length) continue;
						if (grid[newI][newJ] == '0') continue;
						q.offer(new Cell(newI, newJ));
					}
				}
			}
		}
		return count;
	}

	static class Cell {
		int i;
		int j;
		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
