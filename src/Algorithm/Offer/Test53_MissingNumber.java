package Algorithm.Offer;

public class Test53_MissingNumber {
	// An array with size n - 1
	// contains number 0 to n
	public int missingNumber(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l)/2;
			if (nums[mid] == mid) {
				l = l + 1;
			} else {
				r = r - 1;
			}
		}
		// [0, l)       nums[i] = i
		// (r, len - 1] nums[i] = i - 1
		if (l == 0) return 0;
		if (l == nums.length) return nums.length;
		return l;
	}
}
