package Algorithm.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test1293_ShortestPathinaGridwithObstaclesElimination {
	public int shortestPath(int[][] grid, int k) {
		int m = grid.length, n = grid[0].length;
		// whether for grid[i][j] with obstacle = k has occurs before
		boolean[][][] generated = new boolean[m][n][k + 1];
		Queue<Tuple> q = new ArrayDeque<>();
		Tuple start = new Tuple(0, 0, 0, 0);
		generated[0][0][0] = true;
		q.offer(start);
		int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		while (!q.isEmpty()){
			Tuple curr = q.poll();
			if (curr.i == m - 1 && curr.j == n - 1) return curr.step;
			for (int[] dir : dirs) {
				int i = curr.i + dir[0];
				int j = curr.j + dir[1];
				int step = curr.step + 1;
				// boundary check first
				if (i < 0 || i >= m || j < 0 || j >= n) continue;
				int obCount = curr.obCount + grid[i][j];
				// if grid[i][j] with obCount = NEI'S obCount has generated before, skip it
				// if obCount for nei > k, skip it
				if (obCount > k || generated[i][j][obCount]) continue;
				// gernaete that node with specific obCount
				generated[i][j][obCount] = true;
				q.offer(new Tuple(i, j, step, obCount));
			}
		}
		return -1;
	}

	static class Tuple {
		int i;
		int j;
		int step;
		int obCount;
		public Tuple (int i, int j, int step, int obCount) {
			this.i = i;
			this.j = j;
			this.step = step;
			this.obCount = obCount;
		}
	}
}
