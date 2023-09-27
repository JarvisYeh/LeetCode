package Algorithm.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Test305_NumberofIslandsII {
	// Method 1: add-hoc
	// len(ops) = L, meaning at most L islands
	// TC: O(L^2) each operation will flip at most all island, in total L operations
	// SC: O(m*n + L) for the grid and hashset
	public List<Integer> numIslands2I(int m, int n, int[][] positions) {
		int[][] grid = new int[m][n];
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		List<Integer> res = new ArrayList<>();
		int idx = 1;
		int count = 0;
		for (int[] pos : positions) {
			int i = pos[0];
			int j = pos[1];
			// if it's already an island, island number won't change
			if (grid[i][j] != 0) {
				res.add(count);
				continue;
			}

			// calculate how many island is at the neighbor of (i, j)
			HashSet<Integer> neiIdxs = new HashSet<>();
			for (int[] dir : dirs) {
				int newI = i + dir[0];
				int newJ = j + dir[1];
				if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
				if (grid[newI][newJ] != 0) {
					neiIdxs.add(grid[newI][newJ]);
				}
			}

			// if neighbor has no island, (i, j) is a new island
			if (neiIdxs.size() == 0) {
				grid[i][j] = idx++;
				count++;
			}
			// if neighbor has 1 island, merge (i, j) with neighbor
			else if (neiIdxs.size() == 1) {
				grid[i][j] = neiIdxs.iterator().next();
			}
			// if neighbor has more than 1 islands, merge (i, j) and those islands into one island
			else {
				count -= (neiIdxs.size() - 1);
				flip(i, j, grid, idx++);
			}

			res.add(count);
		}
		return res;
	}

	// dfs flip all islands connected into one new island
	private void flip(int i, int j, int[][] grid, int idx) {
		if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return;
		if (grid[i][j] == 0) return;    // won't flip water
		if (grid[i][j] == idx) return;
		grid[i][j] = idx;
		flip(i + 1, j, grid, idx);
		flip(i - 1, j, grid, idx);
		flip(i, j + 1, grid, idx);
		flip(i, j - 1, grid, idx);
	}

	// Method 2: union find
	// flatten 2d matrix into 1d array, and regard each node as separate cell
	// TC: O(m*n + L), m*n for Arrays.fill(), L for loop
	// SC: O(m*n)
	public List<Integer> numIslands2II(int m, int n, int[][] positions) {
		int[] clusters = new int[m*n];
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		int count = 0;
		List<Integer> res = new ArrayList<>();

		// init as all -1, meaning all water, no island
		Arrays.fill(clusters, -1);
		for (int[] pos : positions) {
			int i = pos[0], j = pos[1];
			// set (i, j) as a new cluster (i.e. new island)
			// init current root as (i, j)
			int root = i*n + j;
			// if it's already an island, no operations needed
			if (clusters[root] != -1) {
				res.add(count);
				continue;
			}
			clusters[root] = root;
			count++;

			for (int[] dir : dirs) {
				int newI = i + dir[0];
				int newJ = j + dir[1];
				// if neighbor is out of boundary, skip it
				if (newI < 0 || newI >= m || newJ < 0 || newJ >= n) continue;
				// if neighbor is water, skip it
				if (clusters[newI*n + newJ] == -1) continue;

				int neiRoot = find(newI*n + newJ, clusters);
				// if nei's root is not same as current root, merge current root to nei's cluster
				if (neiRoot != root) {
					// union(root, neiRoot)
					clusters[root] = neiRoot;
					// current root is update to neiRoot
					root = neiRoot;
					count--;
				}
			}
			res.add(count);
		}
		return res;
	}

	private int find(int i, int[] clusters) {
		if (clusters[i] == i) return i;
		clusters[i] = find(clusters[i], clusters);
		return clusters[i];
	}

	public static void main(String[] args) {
		Test305_NumberofIslandsII t = new Test305_NumberofIslandsII();
		System.out.println(t.numIslands2II(3, 3, new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}}));
	}

}
