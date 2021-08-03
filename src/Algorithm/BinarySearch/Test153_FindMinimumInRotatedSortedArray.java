package Algorithm.BinarySearch;

public class Test153_FindMinimumInRotatedSortedArray {
	// method 1:
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		int min = nums[0];
		while (l < r) {
			int mid = l + (r - l)/2;
			// in total, there are 3 cases
			// for the first two cases, arr[l] > arr[r]
			// 1. l mid | r => l = mid + 1 => go right
			// 2. l | mid r => r = mid - 1 => go left
			// for the third case, arr[l] < arr[r]
			// 3. l mid r => r = mid - 1 => go left
			min = Math.min(nums[mid], min);
			if (nums[l] < nums[r] || (nums[l] > nums[r] && nums[mid] < nums[r])) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}
		}
		return min;
	}
}
