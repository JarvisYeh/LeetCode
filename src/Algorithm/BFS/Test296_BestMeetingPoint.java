package Algorithm.BFS;

import java.util.*;

public class Test296_BestMeetingPoint {
	// Method 1: BFS
	// TC: O(numOfPeople*m*n)
	// SC: O(m*n)
	public int minTotalDistanceI(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		List<int[]> startPoints = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) startPoints.add(new int[]{i, j});
			}
		}

		int[][] cost = new int[m][n];
		for (int[] start : startPoints) {
			bfs(start, cost, grid);
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				min = Math.min(min, cost[i][j]);
			}
		}
		return min;
	}

	private void bfs(int[] start, int[][] cost, int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] generated = new boolean[m][n];
		q.offer(start);
		generated[start[0]][start[1]] = true;
		int step = 0;
		while (!q.isEmpty()) {
			for (int c = q.size(); c > 0; c--) {
				int[] curr = q.poll();
				cost[curr[0]][curr[1]] += step;
				for (int[] dir : dirs) {
					int i = curr[0] + dir[0], j = curr[1] + dir[1];
					if (i < 0 || i >= m || j < 0 || j >= n) continue;
					if (generated[i][j]) continue;
					q.offer(new int[]{i, j});
					generated[i][j] = true;
				}
			}
			step++;
		}
	}

	// Method 2: sorting, find median
	public int minTotalDistanceII(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		List<Integer> xs = new ArrayList<>();
		List<Integer> ys = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 1) {
					xs.add(i);
					ys.add(j);
				}
			}
		}

		// xs is already sorted
		Collections.sort(ys);
		int mid = xs.size()/2;
		// median of all xs
		int midX = xs.get(mid);
		// median of all ys
		int midY = ys.get(mid);
		int min = 0;
		for (int i = 0; i < xs.size(); i++) {
			min += Math.abs(xs.get(i) - midX);
			min += Math.abs(ys.get(i) - midY);
		}
		return min;
	}
}
