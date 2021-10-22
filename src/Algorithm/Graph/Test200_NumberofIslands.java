package Algorithm.Graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test200_NumberofIslands {
	// Method 1: dfs
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

	// Method 2: bfs
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

	// Method 3: use union find
	public int numIslandsIII(char[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[] parent = new int[m*n];
		int[] rank = new int[m*n];
		int count = 0;

		// flatten the 2d grid into 1d array
		// initially all single cell = '1' is an island
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1') {
					parent[i*n + j] = i*n + j;
					rank[i*n + j] = 1;
					count++;
				}
			}
		}

		// merge island with 4 direction neighbors if possible
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '0') continue;
				for (int[] dir : dirs) {
					int newI = i + dir[0];
					int newJ = j + dir[1];
					if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
					if (grid[newI][newJ] == '0') continue;
					// if merge successfully, number of island decrease by 1
					if (union(i, j, newI, newJ, parent, rank, n)) count--;
				}
			}
		}
		return count;
	}

	// return true if merge successful
	// return false elsewhere
	private boolean union(int a, int b, int i, int j, int[] parent, int[] rank, int n) {
		int root1 = find(a*n + b, parent);
		int root2 = find(i*n + j, parent);
		// (a, b) and (i, j) are in same clusters, no need to merge
		// return false
		if (root1 == root2) return false;

		// parent[small rank root] = large rank root
		if (rank[root1] > rank[root2]) {
			parent[root2] = root1;
		} else if (rank[root1] < rank[root2]) {
			parent[root1] = root2;
		} else {    // same rank
			parent[root2] = root1;
			// update rank
			rank[root1]++;
		}
		return true;
	}

	// find root of cell i with path compression
	private int find(int i, int[] parent) {
		if (parent[i] != i) parent[i] = find(parent[i], parent);
		return parent[i];
	}

	public static void main(String[] args) {
		Test200_NumberofIslands t = new Test200_NumberofIslands();
		t.numIslandsIII(new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}});
	}
}
