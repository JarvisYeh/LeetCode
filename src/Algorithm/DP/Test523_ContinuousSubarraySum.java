package Algorithm.DP;

import java.util.HashMap;

public class Test523_ContinuousSubarraySum {
	// check sum1%k - sum2%k = (sum1 - sum2)%k == 0
	public boolean checkSubarraySum(int[] nums, int k) {
		// map stores <(subarray sum)%k, subarray last index>
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum = (sum + nums[i]) % k;
			// if sum mod k is zero
			// and it is sum of more than two elements, return true
			if (i >= 1 && sum == 0) {
				return true;
			}
			if (!map.containsKey(sum)) {
				map.put(sum, i);
			} else if (i - map.get(sum) >= 2) {	// if map.contains(sum) && i - map.get(sum) >= 2
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Test523_ContinuousSubarraySum t = new Test523_ContinuousSubarraySum();
		t.checkSubarraySum(new int[]{5,0,0,0}, 3);
	}
}
