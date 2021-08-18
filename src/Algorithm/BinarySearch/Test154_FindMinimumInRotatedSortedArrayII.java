package Algorithm.BinarySearch;

public class Test154_FindMinimumInRotatedSortedArrayII {
	// Method 1: use while (l <= r)
	// TC: O(n) for worst case, [1, 1, 1, 1] all same values
	// SC: O(1)
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		int min = nums[0];
		while (l <= r) {
			int mid = l + (r - l)/2;
			// in total, there are 3 cases
			// for the first two cases, arr[l] > arr[r]
			// 1) l mid | r => l = mid + 1 => go right
			//		arr[r] <= arr[l] <= arr[mid]
			// 2) l | mid r => r = mid - 1 => go left
			//		{arr[mid] <= arr[r]} <= arr[l]
			// for the third case, arr[l] < arr[r]
			// 3) l mid r => r = mid - 1 => go left
			//		arr[l] <= {arr[mid] <= arr[r]}

			min = Math.min(min, nums[mid]);
			// if (nums[l] < nums[r] || (nums[l] > nums[r] && nums[mid] < nums[r])) {
			// => go left cases can be summarized to
			if (nums[mid] < nums[r]) {
				r = mid - 1;	// go left
			// } else if (nums[l] > nums[r] && nums[mid] > nums[r]) {
			// => go right case can be summarized to
			} else if (nums[mid] > nums[r]) {
				l = mid + 1;	// go right
			} else {	// nums[mid] = nums[r], can not be sure to go left or right
				// one thing for sure is nums[r] is already checked
				r--;			// shrink search space by 1
			}
		}
		return min;
	}

	// Method 2:
	// use while (l < r - 1)
	public int findMinII(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			// go left cases can be summarized to
			if (nums[mid] < nums[r]) {
				r = mid;	// go left, but mid can be candidates, not rule it out
			} else if (nums[mid] > nums[r]){
				l = mid;	// go left, but mid can be candidates, not rule it out
			} else {
				r--;		// can not go left or right, only shrink right boundary
			}
		}
		return Math.min(nums[l], nums[r]);
	}
}
