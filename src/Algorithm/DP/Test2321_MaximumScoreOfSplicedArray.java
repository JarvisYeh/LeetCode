package Algorithm.DP;

public class Test2321_MaximumScoreOfSplicedArray {
	public int maximumsSplicedArrayI(int[] nums1, int[] nums2) {
		if (nums1 == null && nums2 == null) return 0;
		if (nums1 == null) return sum(nums2);
		if (nums2 == null) return sum(nums1);
		return Math.max(maxScore(nums1, nums2), maxScore(nums2, nums1));
	}


	// always swap number from helper to source
	// find out the max score of source could be after swapping
	private int maxScore(int[] source, int[] helper) {
		int n = source.length;
		// swappedSumMax[i] is the MAX sum of source[0:i] where source[i] has to be swpped
		int[] swapSumMax = new int[n];
		// notSwappedSum[i] is the sum of source[0:i] i.e. not swapping any elements at all
		int[] notSwapSum = new int[n];
		// not SwapSumMax[i] is the MAX sum of source[0:i] where source[i] is not swapped (but some range beforehand might already swapped)
		int[] notSwapSumMax = new int[n];

		// init
		swapSumMax[0] = helper[0];
		notSwapSum[0] = source[0];
		notSwapSumMax[0] = source[0];
		for (int i = 1; i < n; i++) {
			// swap sum max of source[0: i] is:
			// helper[i] (since i-th score has to be swapped with helper) +
			// max(previous scores all not swapped sum, sum max of i - 1 score is swapped with helper)
			swapSumMax[i] = helper[i] + Math.max(notSwapSum[i - 1], swapSumMax[i - 1]);

			notSwapSum[i] = source[i] + notSwapSum[i - 1];  // i.e. sum(source[0:i]) = sum(source[0:i - 1]) + source[i]

			// not swap sum max of source[0: i] is:
			// source[i] (since i-th score not swap) +
			// max(sum max of i - 1 score is swapped, sum max of i - 1 score is swapped with helper)
			notSwapSumMax[i] = source[i] + Math.max(notSwapSumMax[i - 1], swapSumMax[i - 1]);
		}

		return Math.max(swapSumMax[n - 1], notSwapSumMax[n - 1]);
	}

	private int sum(int[] nums) {
		int sum = 0;
		for (int num : nums) sum += num;
		return sum;
	}

	public int maximumsSplicedArrayII(int[] nums1, int[] nums2) {
		int result = 0, sum1 = 0, sum2 = 0;
		for (int num : nums1) sum1 += num;
		for (int num : nums2) sum2 += num;
		result = Math.max(sum1, sum2);

		// transfer the question into find the max sum of subarray of diff array
		// example:
		// nums1 : 				 [10, 90, 10, 20]
		// nums2 : 				 [5, 100, 20, 5]
		// diff(nums2 - nums1) : [-5, 10, 10, -15]
		// maxSumDiffSubArray:   [-5, 10, 20, 5]
		// maxSumDiffSubArray[i] = max of subarray of diff ending at i where diff[i] is included
		int subSum1 = 0, subSum2 = 0, subSumMax1 = 0, subSumMax2 = 0;
		for (int i = 0; i < nums1.length; i++) {
			int diff = nums2[i] - nums1[i];
			subSum1 = Math.max(0, subSum1) + diff;
			subSumMax1 = Math.max(subSumMax1, subSum1);
			subSum2 = Math.max(0, subSum2) + (- diff);
			subSumMax2 = Math.max(subSumMax2, subSum2);
		}
		result = Math.max(result, sum1 + subSumMax1);
		result = Math.max(result, sum2 + subSumMax2);
		return result;
	}
}
