package Algorithm.HashMap;

import java.util.HashSet;

public class Test219_ContainsDuplicateII {
	// transfer to whether a size (k + 1) sliding window contains duplicate elements
	public boolean containsNearbyDuplicate(int[] nums, int k) {
		k = k + 1;
		HashSet<Integer> set = new HashSet<>();
		// nums[i] is the current number adding to window
		for (int i = 0; i < nums.length; i++) {
			if (i + 1 > k) {
				// remove first letter in last window
				set.remove(nums[i - k]);
			}
			if (set.contains(nums[i])) return true;
			set.add(nums[i]);
		}
		return false;
	}
}
