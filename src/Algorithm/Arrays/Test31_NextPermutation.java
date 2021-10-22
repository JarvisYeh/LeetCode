package Algorithm.Arrays;

import java.util.Arrays;

public class Test31_NextPermutation {
	public void nextPermutation(int[] nums) {
		if (nums.length == 1) return;
		int firstIdx = nums.length;   // index of first non-descending index, start from RHS
		for (int i = nums.length - 1; i > 0; i--) {
			if (nums[i - 1] < nums[i]) {
				firstIdx = i - 1;
				break;
			}
		}

		if (firstIdx == nums.length) {   // the numbers in array is in descending order
			Arrays.sort(nums);
			return;
		}

		// [firstIdx + 1, len) is a non-descending array
		// start from right, find the first number that is larger than nums[firstIdx]
		int largerThanFirstPos = -1;
		for (int i = nums.length - 1; i >= firstIdx + 1; i--) {
			if (nums[i] > nums[firstIdx]) {
				largerThanFirstPos = i;
				break;
			}
		}
		// [1, 3, 2] => [2, 3, 1]
		swap(nums, firstIdx, largerThanFirstPos);
		// [2, 3, 1] => [2, 1, 3]
		Arrays.sort(nums, firstIdx + 1, nums.length);
	}

	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void main(String[] args) {
		Test31_NextPermutation t = new Test31_NextPermutation();
		t.nextPermutation(new int[]{1,3,2});
	}
}
