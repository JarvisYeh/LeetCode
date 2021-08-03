package Algorithm.DP;

public class Test64_MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		int[][] DP = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0) {
					DP[i][j] = grid[i][j];
				} else if (i == 0) {
					DP[i][j] = DP[i][j - 1] + grid[i][j];
				} else if (j == 0) {
					DP[i][j] = DP[i - 1][j] + grid[i][j];
				} else {
					DP[i][j] = Math.min(DP[i - 1][j], DP[i][j - 1]) + grid[i][j];
				}
			}
		}
		return DP[m - 1][n - 1];
	}
}
