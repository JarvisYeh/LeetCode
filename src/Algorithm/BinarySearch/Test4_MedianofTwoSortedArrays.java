package Algorithm.BinarySearch;

public class Test4_MedianofTwoSortedArrays {
	// iteration
	// TC: O(log m + log n)
	// SC: O(1)
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		if ((m + n) % 2 != 0) {
			return findK(nums1, nums2, (m + n)/2 + 1);
		} else {
			return (findK(nums1, nums2, (m + n)/2) + findK(nums1, nums2, (n + m)/2 + 1))/2.0;
		}
	}

	private int findK(int[] nums1, int[] nums2, int k) {
		int l1 = 0, l2 = 0;
		while (k > 1) {
			int mid1 = l1 + k/2 - 1;
			int mid2 = l2 + k/2 - 1;
			if (mid1 >= nums1.length) {
				l2 += k/2;
			} else if (mid2 >= nums2.length) {
				l1 += k/2;
			} else if (nums1[mid1] < nums2[mid2]) {
				l1 += k/2;
			} else {
				l2 += k/2;
			}
			k -= k/2;
		}
		if (l1 >= nums1.length) return nums2[l2];
		if (l2 >= nums2.length) return nums1[l1];
		return Math.min(nums1[l1], nums2[l2]);
	}
}
