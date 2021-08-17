package Algorithm.DFS;

import java.util.Arrays;

public class Test698_PartitiontoKEqualSumSubsets {
	// Method 1:
	// recursion tree has n level, each level represents an index in nums array
	// each level has k branch, each branch means one group
	// level i node k means nums[i] add into group k
	// TC: O(k^n)
	// SC: O(n)
	public boolean canPartitionKSubsetsI(int[] nums, int k) {
		int sum = 0;
		for (int i : nums) sum += i;
		if (sum % k != 0) return false;
		Arrays.sort(nums);
		return dfs(nums.length - 1, nums, new int[k], sum);
	}

	private boolean dfs(int index, int[] nums, int[] groups, int sum) {
		if (index < 0) return true;

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

	// Method 2:
	// k*n levels, consider one group at a time
	public boolean canPartitionKSubsetsII(int[] nums, int k) {
		int sum = 0;
		for (int i : nums) sum += i;
		if (sum % k != 0) return false;
		Arrays.sort(nums);
		return helper(nums.length - 1, 0, nums, k, sum/k, new boolean[nums.length]);
	}

	private boolean helper(int startIdx, int currSum, int[] nums, int k, int sum, boolean[] used) {
		if (k == 1) return true;

		if (currSum == sum) {
			return helper(nums.length - 1, 0, nums, k - 1, sum, used);
		}

		for (int i = startIdx; i >= 0; i--) {
			if (!used[i] && currSum + nums[i] <= sum) {
				used[i] = true;
				if (helper(i - 1, currSum + nums[i], nums, k, sum, used)) return true;
				used[i] = false;
			}
		}
		return false;
	}
}
