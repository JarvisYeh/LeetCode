package Algorithm.DP;

public class Test198_HouseRobber {
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) return 0;

		// max[i] represents the max money robbed from nums[0, i]
		int[] max = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			if (i == 0) {
				max[i] = nums[0];
			} else if (i == 1) {
				max[i] = Math.max(nums[0], nums[1]);
			} else {
				// case1: robbed from i-th house
				// if (i-1)th house is not robbed
				// 		case 1 => rob = max[i - 1] + nums[i] = max[i - 2] + 0 + nums[i]
				// if (i-1)th house is robbed, can not use max[i - 1]
				// 		case 1 => rob = max[i - 2] + arr[i]
				int rob = max[i - 2] + nums[i];
				// case2: not robbed from i-th house
				int notRob = max[i - 1];
				max[i] = Math.max(rob, notRob);
			}
		}
		return max[nums.length - 1];
	}
}
