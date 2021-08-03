package Algorithm.DFS;

import java.util.HashMap;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Test505_TheMazeII {
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		int m = maze.length, n = maze[0].length;

		Queue<Cell> pq = new PriorityQueue<>((c1, c2) -> Integer.compare(c1.distance, c2.distance));
		HashMap<Cell, Integer> generated = new HashMap<>();
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		Cell startCell = new Cell(start[0], start[1], 0);
		pq.offer(startCell);
		generated.put(startCell, 0);

		while (!pq.isEmpty()) {
			Cell curr = pq.poll();
			for (int[] dir : dirs) {
				int newi = curr.i, newj = curr.j;
				while (checkValid(newi + dir[0], newj + dir[1], maze)) {
					newi += dir[0];
					newj += dir[1];
				}
				int distance = curr.distance + (int)Math.abs(newi  - curr.i) + (int)Math.abs(newj - curr.j);
				Cell nei = new Cell(newi, newj, distance);
				// never generated before
				if (!generated.containsKey(nei)) {
					generated.put(nei, distance);
					pq.offer(nei);
				}
				// already generated this cell before, update distance if necessary
				else if (generated.get(nei) > distance) {
					// update map
					generated.put(nei, distance);
					// update pq if Cell(newi, newj, *) is in pq since pq based on distance of cell
					if (pq.remove(nei)) {
						pq.offer(nei);
					}
				}
			}
		}
		Integer res = generated.get(new Cell(destination[0], destination[1], -1));
		return res == null ? -1 : res;
	}

	private boolean checkValid(int i, int j, int[][] maze) {
		int m = maze.length, n = maze[0].length;
		if (i < 0 || j < 0 || i >= m || j >= n || maze[i][j] == 1) {
			return false;
		} else {
			return true;
		}
	}


	class Cell {
		int i;
		int j;
		int distance;
		public Cell(int i, int j, int distance) {
			this.i = i;
			this.j = j;
			this.distance = distance;
		}

		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass()) return false;
			Cell cell = (Cell) o;
			return this.i == cell.i && this.j == cell.j;
		}

		@Override
		public int hashCode() {
			return Objects.hash(i, j);
		}
	}

	public static void main(String[] args) {
		Test505_TheMazeII t = new Test505_TheMazeII();
		t.shortestDistance(new int[][]{{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}},
				new int[]{0, 4}, new int[]{4, 4});
	}
}
