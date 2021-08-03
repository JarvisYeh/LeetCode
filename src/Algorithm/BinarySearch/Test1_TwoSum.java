package Algorithm.BinarySearch;

import java.util.Arrays;

public class Test1_TwoSum {

	public static void main(String[] args) {
		System.out.println( Arrays.toString(twoSum(new int[]{3,3}, 6)) );
	}

	public static int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			int left = 0, right = nums.length - 1;
			while (left <= right) {
				int mid = left + (right - left) / 2;
				if (nums[mid] > (target - nums[i])) {
					right = mid - 1;
				} else if (nums[mid] < (target - nums[i])) {
					left = mid + 1;
				} else {
					if (mid == i) {
						return new int[] { i, mid };
					} else {
						return new int[] { -1, -1 };
					}
				}
			}
		}
		return new int[] { -1, -1 };
	}

}
