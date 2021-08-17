package Algorithm.DFS;

public class Test416_PartitionEqualSubsetSum {
	/**
	 * DFS
	 * O(2^n)
	 */
	public boolean canPartitionI(int[] nums) {
		int sum = 0, currSum = 0;
		for (int i : nums) {
			sum += i;
		}
		return dfsI(nums, 0, currSum, sum);
	}

	private boolean dfsI(int[] nums, int index, int currSum, int sum) {
		// base case
		if (index == nums.length) {
			if (currSum*2 == sum) {
				return true;
			}
			return false;
		}

		// current level
		if (currSum*2 < sum) {
			if (dfsI(nums, index + 1, currSum + nums[index], sum)) return true;
		}
		return dfsI(nums, index + 1, currSum, sum);
	}


	// DP: knapsack problem
	// TC: O(n * sum)
	// SC: O(n * sum)
	public boolean canPartitionII(int[] nums) {
		int sum = 0;
		for (int i : nums) sum += i;
		if (sum % 2 == 1) return false;

		// DP[i][j]: first i numbers [0, i) can be sum to j
		boolean[][] DP = new boolean[nums.length + 1][sum/2 + 1];
		// first i nums can be summed up tp 0 (i.e. choose 0 of them)
		for (int i = 0; i <= nums.length; i++) DP[i][0] = true;
		for (int i = 1; i <= nums.length; i++) {
			// first i numbers, nums[0, i), so the new considered num is nums[i - 1]
			int num = nums[i - 1];
			for (int j = 0; j <= sum/2; j++) {
				if (num == j || DP[i - 1][j]) {
					DP[i][j] = true;
				} else if (j - num >= 0 && DP[i - 1][j - num]) {
					DP[i][j] = true;
				}
			}
		}
		return DP[nums.length][sum/2];
	}

	// DP: knapsack problem - optimize space
	// for previous DP solution, note that all true in DP[i-1][*] will still be true in DP[i][*]
	// 这是因为前i-1个数能达成的sum一定也是前i个数能达成的sum（只要不选第i个数即可）
	// 所以只需要保留一行即可，每次更新这一行DP
	// TC: O(n * sum)
	// SC: O(n * sum)
	public boolean canPartitionIII(int[] nums) {
		int sum = 0;
		for (int i : nums) sum += i;
		if (sum % 2 == 1) return false;

		// DP[j]: numbers can sum to j
		boolean[] DP = new boolean[sum/2 + 1];
		// {0} can be summed up tp 0
		DP[0] = true;
		for (int i = 1; i <= nums.length; i++) {
			// for first i numbers [0, i) subset, actually consider nums[i - 1]
			int num = nums[i - 1];
			// DP[j - num]只能根据上一个i的值来更新
			// 从后往前iterate，防止DP[j - num]可能使用到同一个i，上一个j的结果
			for (int j = sum/2; j >=0; j--) {
				if (num == j) {
					DP[j] = true;
				} else if (j - num >= 0 && DP[j - num]) {
					DP[j] = true;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		return DP[sum/2];
	}


	public static void main(String[] args) {
		Test416_PartitionEqualSubsetSum t = new Test416_PartitionEqualSubsetSum();
		System.out.println(t.canPartitionIII(new int[]{1,5,11,5}));
	}
}
