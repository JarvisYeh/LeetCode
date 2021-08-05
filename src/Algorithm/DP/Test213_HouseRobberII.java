package Algorithm.DP;

import java.util.Arrays;

public class Test213_HouseRobberII {
	public int rob(int[] nums) {
		if (nums.length == 1) return nums[0];
		int left = findMax(Arrays.copyOfRange(nums, 0, nums.length - 1));
		int right = findMax(Arrays.copyOfRange(nums, 1, nums.length));
		return Math.max(left, right);
	}

	private int findMax(int[] nums) {
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
				// 		case 1 => rob = max[i - 1] + nums[i]
				// 				= max[i - 2] + arr[i]
				// if (i-1)th house is robbed, can not use max[i - 1]
				// 		case 1 => rob
				// 				= max[i - 2] + arr[i]
				// case2: robbed from i-th house
				// 		case 2 => notRob
				//				= max[i - 1]
				max[i] = Math.max(max[i - 1], max[i - 2] + nums[i]);
			}
		}
		return max[nums.length - 1];
	}
}
