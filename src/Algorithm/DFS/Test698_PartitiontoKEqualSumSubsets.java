package Algorithm.DFS;

import java.util.Arrays;

public class Test698_PartitiontoKEqualSumSubsets {
	// TC: O(k^n)
	// SC: O(n)
	public boolean canPartitionKSubsets(int[] nums, int k) {
		int sum = 0;
		for (int i : nums) sum += i;
		if (sum % k != 0) return false;
		Arrays.sort(nums);
		return dfs(nums.length - 1, nums, new int[k], sum);
	}

	private boolean dfs(int index, int[] nums, int[] groups, int sum) {
		if (index < 0) {
			return true;
		}

		int k = groups.length;
		for (int i = 0; i < k; i++) {
			if (groups[i] + nums[index] <= sum/k) {
				groups[i] += nums[index];	// add to group[i]
				if (dfs(index - 1, nums, groups, sum)) {
					return true;			// early return true
				}
				groups[i] -= nums[index];	// no add to group[i]
			}
		}
		return false;
	}
}
