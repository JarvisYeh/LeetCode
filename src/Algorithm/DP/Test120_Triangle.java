package Algorithm.DP;

import java.util.List;

public class Test120_Triangle {
	// TC: O(mn)
	// SC: O(mn), can be reduced to O(n) since only previous row's DP value is used
	public int minimumTotal(List<List<Integer>> triangle) {
		int m = triangle.size(), n = triangle.get(m - 1).size();
		int[][] minSum = new int[m][n];

		// base case
		minSum[0][0] = triangle.get(0).get(0);
		// for each row, start from 2nd row
		for (int i = 1; i < m; i++) {
			int size = triangle.get(i).size();
			for (int j = 0; j < size; j++) {
				if (j == 0) minSum[i][j] = minSum[i - 1][0] + triangle.get(i).get(j);
				else if (j == size - 1) minSum[i][j] = minSum[i - 1][j - 1] + triangle.get(i).get(j);
				else {
					int pickLeft = minSum[i - 1][j - 1];
					int pickRight = minSum[i - 1][j];
					minSum[i][j] = Math.min(pickLeft, pickRight) + triangle.get(i).get(j);
				}
			}
		}

		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) min = Math.min(min, minSum[m - 1][i]);
		return min;
	}
}
