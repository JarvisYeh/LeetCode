package Algorithm.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class Test286_WallsandGates {
	public void wallsAndGates(int[][] rooms) {
		int m = rooms.length, n = rooms[0].length;
		int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] generated = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (rooms[i][j] == 0) {
					q.offer(new int[]{i, j});
					generated[i][j] = true;
				}
			}
		}

		int step = 0;
		while (!q.isEmpty()) {
			for (int c = q.size(); c > 0; c--) {
				int[] curr = q.poll();
				int i = curr[0], j = curr[1];
				if (rooms[i][j] == Integer.MAX_VALUE) rooms[i][j] = step;
				for (int[] dir : dirs) {
					i = curr[0] + dir[0];
					j = curr[1] + dir[1];
					if (i < 0 || i >= m || j < 0 || j >= n) continue;
					if (generated[i][j]) continue;
					if (rooms[i][j] == -1 || rooms[i][j] == 0) continue;
					q.offer(new int[]{i, j});
					generated[i][j] = true;
				}
			}
			step++;
		}
	}
}
