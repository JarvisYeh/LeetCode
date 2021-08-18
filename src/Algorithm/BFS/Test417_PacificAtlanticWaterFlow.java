package Algorithm.BFS;

import java.util.*;

public class Test417_PacificAtlanticWaterFlow {
	// Method 1: BFS
	// TC: O(2*m*n) two times traverse
	// SC: O(m*n) for queue and generated matrix
	public List<List<Integer>> pacificAtlanticI(int[][] heights) {
		int m = heights.length, n = heights[0].length;
		Queue<Cell> pacQ = new ArrayDeque<>();
		Queue<Cell> altQ = new ArrayDeque<>();
		boolean[][] pacGenerated = new boolean[m][n];
		boolean[][] altGenerated = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			// left
			pacQ.offer(new Cell(i, 0));
			pacGenerated[i][0] = true;
			// right
			altQ.offer(new Cell(i, n - 1));
			altGenerated[i][n - 1] = true;
		}
		for (int j = 0; j < n; j++) {
			// top
			pacQ.offer(new Cell(0, j));
			pacGenerated[0][j] = true;
			// bottom
			altQ.offer(new Cell(m - 1, j));
			altGenerated[m - 1][j] = true;
		}

		bfs(pacQ, heights, pacGenerated);
		bfs(altQ, heights, altGenerated);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pacGenerated[i][j] && altGenerated[i][j]) {
					res.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}
		return res;
	}

	private void bfs(Queue<Cell> q, int[][] heights, boolean[][] generated) {
		int m = heights.length, n = heights[0].length;
		int[][] dirs = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

		while (!q.isEmpty()) {
			Cell curr = q.poll();
			int currHeight = heights[curr.i][curr.j];
			for (int[] dir : dirs) {
				int i = curr.i + dir[0];
				int j = curr.j + dir[1];
				if (i < 0 || i >= m || j < 0 || j >= n) continue;
				if (generated[i][j]) continue;
				// add to queue only if neighbor's height is larger
				if (heights[i][j] >= currHeight) {
					generated[i][j] = true;
					Cell nei = new Cell(i, j);
					q.offer(nei);
				}
			}
		}
	}

	private static class Cell {
		int i;
		int j;
		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	// Method 2: dfs
	// TC: O(5*m*n)
	// SC: O(m*n)
	public List<List<Integer>> pacificAtlanticII(int[][] heights) {
		int m = heights.length, n = heights[0].length;
		boolean[][] pacVisited = new boolean[m][n];
		boolean[][] altVisited = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			// start from left
			dfs(i, 0, pacVisited, Integer.MIN_VALUE, heights);
			// start from right
			dfs(i, n - 1, altVisited, Integer.MIN_VALUE, heights);
		}

		for (int j = 0; j < n; j++) {
			// start from top
			dfs(0, j, pacVisited, Integer.MIN_VALUE, heights);
			// start from bottom
			dfs(m - 1, j, altVisited, Integer.MIN_VALUE, heights);
		}

		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (pacVisited[i][j] && altVisited[i][j]) {
					res.add(new ArrayList<>(Arrays.asList(i, j)));
				}
			}
		}
		return res;
	}

	// dfs those cells can be flow from edge
	private void dfs(int i, int j, boolean[][] visited, int minH, int[][] mat) {
		int m = mat.length, n = mat[0].length;
		if (i < 0 || i >= m || j < 0 || j >= n) return;
		if (mat[i][j] < minH) return;
		if (visited[i][j]) return;
		visited[i][j] = true;
		dfs(i + 1, j, visited, mat[i][j], mat);
		dfs(i - 1, j, visited, mat[i][j], mat);
		dfs(i, j + 1, visited, mat[i][j], mat);
		dfs(i, j - 1, visited, mat[i][j], mat);
	}
}
