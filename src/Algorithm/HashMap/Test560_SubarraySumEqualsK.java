package Algorithm.HashMap;

import java.util.HashMap;

public class Test560_SubarraySumEqualsK {
	// TC: O(n)
	// SC: O(n)
	public int subarraySum(int[] nums, int k) {
		// map[s]: how many presum = s, presum = sum(nums[0, i]) where i in [0, idx)
		HashMap<Integer, Integer> map = new HashMap<>();
		// 0 elements sum up to 0, that is one case that sum up to 0
		map.put(0, 1);

		// sum = sum(nums[0, idx])
		int count = 0, sum = 0;
		for (int idx = 0; idx < nums.length; idx++) {
			sum += nums[idx];
			// k is the sum of nums(i, idx]
			// preSum + k = sum => preSum = sum - k
			count += map.getOrDefault(sum - k, 0);
			// update map with sum(nums[0, idx])
			map.put(sum, map.getOrDefault(sum, 0) + 1);
		}
		return count;
	}
}
