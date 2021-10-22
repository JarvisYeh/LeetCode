package Algorithm.DP;

public class Test486_PredicttheWinner {
	// TC: O(n^2)
	// SC: O(n^2)
	public boolean PredictTheWinner(int[] nums) {
		int n = nums.length;
		// maxScores[i][j] is the max score one could get if he starts pick first
		int[][] maxScores = new int[n][n];

		// pre-calculate for prefix sum
		int[] sumPrefix = new int[n];
		sumPrefix[0] = nums[0];
		for (int i = 1; i < n; i++) sumPrefix[i] = sumPrefix[i - 1] + nums[i];

		// calculate maxScores[i][j]
		// i must be less than j, for range nums[i, j]
		for (int j = 0; j < n; j++) {
			for (int i = j; i >= 0; i--) {
				if (i == j) maxScores[i][j] = nums[i];
				else if (i == j - 1) maxScores[i][j] = Math.max(nums[i], nums[j]);
				else {
					// nums[i] + sum[i + 1, j] - max[i + 1][j]
					int pickLeft = nums[i] + sumPrefix[j] - sumPrefix[i + 1] + nums[i + 1] - maxScores[i + 1][j];
					// nums[j] + sum[i, j - 1] - max[i][j - 1]
					int pickRight = nums[j] + sumPrefix[j - 1] - sumPrefix[i] + nums[i] - maxScores[i][j - 1];
					maxScores[i][j] = Math.max(pickLeft, pickRight);
				}
			}
		}
		return maxScores[0][n - 1] >= sumPrefix[n - 1] - maxScores[0][n - 1];
	}
}
