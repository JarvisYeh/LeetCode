package Algorithm.DP;

import java.util.Arrays;

public class Test238_ProductOfArrayExceptSelf {
	// TC: O(n)
	// SC: O(n)
	public int[] productExceptSelfI(int[] nums) {
		int n = nums.length;
		int[] left = new int[n];
		int[] right = new int[n];
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				left[i] = nums[i];
				right[n - 1 - i] = nums[n - i - 1];
			} else {
				left[i] = left[i - 1] * nums[i];
				right[n - 1 - i] = right[n - i] * nums[n - i - 1];
			}
		}

		for (int i = 0; i < n; i++) {
			nums[i] = (i == 0 ?  1 : left[i - 1]) * (i == n - 1 ? 1 : right[i + 1]);
		}
		return nums;
	}

	// improved SC to O(1)
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];

		// left = nums[0]*num[1]*...*nums[i - 1]
		int left = 1;
		for (int i = 0; i < n; i++) {
			res[i] = left;
			left *= nums[i];
		}

		// right = nums[i + 1]*num[i + 2]*...*nums[n - 1]
		int right = 1;
		for (int i = n - 1; i >= 0; i--) {
			res[i] *= right;
			right *= nums[i];
		}
		return res;
	}
}
