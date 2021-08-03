package Algorithm.DFS;

import java.util.ArrayDeque;
import java.util.Queue;

// a ball in maze can move up, down, left, right
// but it CAN NOT STOP until hit the wall
// int[][] maze is filled by 0 and 1 (1 is block)
// return whether a ball from start can stop at destination

public class Test490_TheMaze {
	// BFS
	public boolean hasPathI(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		boolean[][] generated = new boolean[m][n];
		Queue<Cell> q = new ArrayDeque<>();
		generated[start[0]][start[1]] = true;
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		q.offer(new Cell(start[0],start[1]));
		while (!q.isEmpty()) {
			Cell curr = q.poll();
			int i = curr.i, j = curr.j;
			if (i == destination[0] && j == destination[1]) return true;
			for (int[] dir : dirs) {
				int newi = i, newj = j;
				while (checkValid(newi + dir[0], newj + dir[1], m, n, maze)) {
					newi += dir[0];
					newj += dir[1];
				}
				if (!generated[newi][newj]) {
					q.offer(new Cell(newi, newj));
					generated[newi][newj] = true;
				}
			}
		}
		return false;
	}

	private boolean checkValid(int i, int j, int m, int n, int[][] maze) {
		if (i < 0 || j < 0 || i >= m || j >= n || maze[i][j] == 1) {
			return false;
		} else {
			return true;
		}
	}

	class Cell {
		int i;
		int j;
		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}


	// DFS
	public boolean hasPathII(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;
		boolean[][] visited = new boolean[m][n];
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		return dfs(start[0], start[1], dirs, destination, maze, visited);
	}

	private boolean dfs(int i, int j, int[][] dirs, int[] dest, int[][] maze, boolean[][] visited) {
		if (i == dest[0] && j == dest[1]) {
			return true;
		}

		if (visited[i][j]) {
			return false;
		}

		visited[i][j] = true;

		for (int[] dir : dirs) {
			int newi = i, newj = j;
			while (checkValid(newi + dir[0], newj + dir[1], maze)) {
				newi += dir[0];
				newj += dir[1];
			}
			if (dfs(newi, newj, dirs, dest, maze, visited)) return true;
		}
		return false;
	}

	private boolean checkValid(int i, int j, int[][] maze) {
		if (i < 0 || j < 0 || i >= maze.length || j >= maze[0].length || maze[i][j] == 1) {
			return false;
		} else {
			return true;
		}
	}


}
