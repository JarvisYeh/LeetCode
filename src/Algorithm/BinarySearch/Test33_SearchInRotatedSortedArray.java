package Algorithm.BinarySearch;

public class Test33_SearchInRotatedSortedArray {
	// Method 1:
	// cases discussion w.r.t. has/not has rotation
	public int searchI(int[] nums, int target) {
		if (nums == null || nums.length == 0) return -1;
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
				return mid;
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

			} else {                    // case 2
				if (nums[mid] > target) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}
		}
		if (nums[l] == target) {
			return l;
		} else if (nums[r] == target) {
			return r;
		}
		return -1;
	}


	// Method 2:
	// cases discussion w.r.t. target, mid in/not in same side
	public int searchII(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			if (nums[mid] == target) return mid;
			if (nums[mid] > nums[r] && target > nums[r] || nums[mid] <= nums[r] && target <= nums[r]) {
				// mid and target on the same side
				// both on left || both on right
				// l mid target | r
				// l target mid | r
				// l | mid target r
				// l | target mid r
				if (nums[mid] > target) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				// mid and target on different side
				// l target | mid r
				// l mid | target r
				if (nums[mid] < target) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}
		}
		if (nums[l] == target) {
			return l;
		} else if (nums[r] == target) {
			return r;
		}
		return -1;
	}

	// Method 3:
	// cases discussion w.r.t. position of mid
	public int searchIII(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l) / 2;
			// [l, mid] monotonically increase
			if (nums[mid] >= nums[l]) {
				// l target mid | r => go left
				if (target >= nums[l] && target <= nums[mid]) {
					r = mid;
				}
				// l mid target | r => go right
				// l mid | target r => go right
				else {
					l = mid;
				}
			}
			// [mid, r] monotonically increase
			else {
				// l | mid target r => go right
				if (target >= nums[mid] && target <= nums[r]) {
					l = mid;
				}
				// l target | mid r => go left
				// l | target mid r => go left
				else {
					r = mid;
				}
			}
		}
		if (nums[l] == target) {
			return l;
		} else if (nums[r] == target) {
			return r;
		}
		return -1;
	}

	// Method 4:
	// find pivot then do ordinal binary search for the specific side
	public int searchIV(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			// l mid | r => go right	r < l < mid
			// l | mid r => go left		mid < r < l
			// l mid r => go left		l < mid < r
			if (nums[r] > nums[mid]) {
				r = mid;
			} else {
				l = mid;
			}
		}
		int startIdx = 0;
		if (nums[l] < nums[r]) {
			startIdx = l;
		} else {
			startIdx = r;
		}

		// find index of min, now determine which side target belongs to
		l = 0;
		r = nums.length - 1;
		if (startIdx != 0 && target >= nums[0]) {	// target in left side
			r = startIdx - 1;
		} else if (startIdx != 0 && target < nums[0]) {	// target in right side
			l = startIdx;
		}
		while (l <= r) {
			int mid = l + (r - l)/2;
			if (nums[mid] > target) {
				r = mid - 1;
			} else if (nums[mid] < target) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Test33_SearchInRotatedSortedArray t = new Test33_SearchInRotatedSortedArray();
		t.searchII(new int[]{5, 1, 3}, 5);
	}
}
