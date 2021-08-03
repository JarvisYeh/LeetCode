package Algorithm.Arrays;

import java.util.HashMap;

public class Test454_4SumII {
	// for 4 arrays nums1, 2, 3, 4
	// find numbers of i, j, k, l
	// where nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
	// TC: O(n^2)
	// SC: O(n^2) for worst if all nums1[i] + nums2[j] sum up to different values
	public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
		int res = 0;
		// count map <key = s, value = number of {i, j} where nums1[i] + nums2[j] equal to s>
		HashMap<Integer, Integer> countMap = new HashMap<>();
		int n = nums1.length;
		// construct count map w.r.t. nums1 and nums2
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = nums1[i] + nums2[j];
				countMap.put(sum, countMap.getOrDefault(sum, 0) + 1);
			}
		}

		// for each pair sum of nums3 and nums4
		// add countMap[target - nums3[i] - nums4[j]]
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = nums3[i] + nums4[j];
				res += countMap.getOrDefault(0 - sum, 0);
			}
		}
		return res;
	}
}
