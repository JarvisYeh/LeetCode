package Algorithm.BinarySearch;

public class Test81_SearchInRotatedSortedArrayII {
	// return true/false
	// TC: worst case O(n)
	// SC: O(1)
	public boolean searchI(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		// case 1: search range has rotation => nums[l] > nums[r]
		// 1.1) l mid | r
		//  1.1.1) l target mid | r => go left
		//  1.1.2) l mid target | r => go right
		//  1.1.3) l mid | target r => go right
		// 1.2) l | mid r
		//  1.1.1) l target | mid r => go left
		//  1.1.2) l | target mid r => go left
		//  1.1.3) l | mid target r => go right
		// case 2: search range doesn't have rotation => nums[l] < nums[r]
		// 2.1) l target mid r
		// 2.2) l mid target r
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			if (nums[mid] == target) {
				return true;
			}
			if (nums[l] == target) {
				return true;
			}
			if (nums[r] == target) {
				return true;
			}

			if (nums[l] > nums[r]) {    // case 1
				if (nums[mid] >= nums[l]) {    // 1.1)
					if (target >= nums[l] && target <= nums[mid]) {    // 1.1.1)
						r = mid - 1;
					} else {                                         // 1.1.2) & 1.1.3)
						l = mid + 1;
					}
				} else {                      // 1.2)
					if (target >= nums[mid] && target <= nums[r]) {   // 1.2.3)
						l = mid + 1;
					} else {                                        // 1.2.1) && 1.2.2)
						r = mid - 1;
					}
				}

			} else if (nums[l] < nums[r]) {                    // case 2
				if (nums[mid] > target) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				l++;
				r--;
			}
		}
		if (nums[l] == target || nums[r] == target) {
			return true;
		}
		return false;
	}

	// return first target index
	// TC: worst case O(n)
	// SC: O(1)
	public int searchII(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
		int startIdx = findMinIdx(nums);
		int l = 0, r = nums.length - 1;
		if (startIdx != 0 && target >= nums[0]) { // in left side
			r = startIdx - 1;
		} else if (startIdx != 0 && target < nums[0]) {
			l = startIdx;
		}

		while (l < r - 1) {
			int mid = l + (r - l)/2;
			if (nums[mid] >= target) {
				r = mid;
			} else {
				l = mid;
			}
		}
		if (nums[l] == target) {
			return l;
		} else if (nums[r] == target) {
			return r;
		}
		return -1;
	}

	private int findMinIdx(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			// l mid | r => r <= l <= m
			// l | mid r => m <= r <= l
			// l mid r   => l <= m <= r
			if (nums[r] > nums[mid]) {
				r = mid;
			} else if (nums[r] < nums[mid]) {
				l = mid;
			} else {
				// before shrink r, check if r is the answer
				if (r != 0 && nums[r - 1] > nums[r]) {
					return r;
				}
				r--;
			}
		}
		if (nums[l] <= nums[r]) {
			return l;
		} else {
			return r;
		}
	}

	public static void main(String[] args) {
		Test81_SearchInRotatedSortedArrayII t = new Test81_SearchInRotatedSortedArrayII();
		System.out.println(t.searchII(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1}, 2));
	}
}
