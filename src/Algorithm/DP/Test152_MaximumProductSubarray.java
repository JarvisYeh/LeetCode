package Algorithm.DP;

public class Test152_MaximumProductSubarray {
	public int maxProduct(int[] nums) {
		// maxPord[i] stores the max product of subarray end at i
		int[] maxProd = new int[nums.length];
		// minPord[i] stores the min product of subarray end at i
		int[] minProd = new int[nums.length];

		maxProd[0] = nums[0];
		minProd[0] = nums[0];
		int max = nums[0];

		for (int i = 1; i < nums.length; i++) {
			// 0 is a corner case
			// which actually means that
			// maxProd[i + 1] = minPord[i + 1] = nums[i + 1]
			// e.g. a new start
			if (nums[i] == 0) {
				maxProd[i] = 1;
				minProd[i] = 1;
				max = Math.max(0, max);
			} else {
				// there are 3 possibilities:
				// nums[i], maxProd[i - 1]*nums[i], minProd[i - 1]*nums[i]
				int n = nums[i];
				maxProd[i] = Math.max(n, Math.max(maxProd[i - 1]*n, minProd[i - 1]*n));
				minProd[i] = Math.min(n, Math.min(maxProd[i - 1]*n, minProd[i - 1]*n));
				max = Math.max(maxProd[i], max);
			}
		}
		return max;
	}
}
