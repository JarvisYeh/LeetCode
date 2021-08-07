package Algorithm.HashMap;

import java.util.TreeSet;

public class Test220_ContainsDuplicateIII {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		k = k + 1;
		TreeSet<Integer> set = new TreeSet<>();
		// nums[i] is the current number adding to window
		for (int i = 0; i < nums.length; i++) {
			if (i + 1 > k) {
				// remove first letter in last window
				set.remove(nums[i - k]);
			}

			Integer ceiling = set.ceiling(nums[i]);
			Integer floor = set.floor(nums[i]);
			// transfer to long to avoid overflow
			if (ceiling != null && Math.abs(ceiling - (long)nums[i]) <= t) return true;
			if (floor != null && Math.abs(floor - (long)nums[i]) <= t) return true;
			set.add(nums[i]);
		}
		return false;
	}

	public static void main(String[] args) {
		Test220_ContainsDuplicateIII t = new Test220_ContainsDuplicateIII();
		t.containsNearbyAlmostDuplicate(new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE}, 1,1);
	}
}
