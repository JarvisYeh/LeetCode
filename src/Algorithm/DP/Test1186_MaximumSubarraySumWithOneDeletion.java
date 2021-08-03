package Algorithm.DP;

public class Test1186_MaximumSubarraySumWithOneDeletion {
	// O(n)
	public int maximumSum(int[] arr) {
		int n = arr.length;
		int[] leftSum = new int[n];
		int[] rightSum = new int[n];
		int max = Integer.MIN_VALUE;

		// first consider normal max sum subarray
		for (int i = 0; i < n; i++) {
			if (i != 0 && leftSum[i - 1] > 0) {
				leftSum[i] = leftSum[i - 1] + arr[i];
			} else {
				leftSum[i] = arr[i];
			}

			if (n - 1 - i != n - 1 && rightSum[n - i] > 0) {
				rightSum[n - 1 - i] = rightSum[n - i] + arr[n - 1 - i];
			} else {
				rightSum[n - 1 - i] = arr[n - 1 - i];
			}
			max = Math.max(max, Math.max(leftSum[i], rightSum[n - 1 - i]));
		}

		// if contains only 1 element, no need to consider deletion
		if (n == 1) {
			return max;
		}

		// if array contains more than one elements, consider deletion of each element
		// deletion i-th element is same as
		// adding max sum looking to left of i-th element
		// adding max sum looking to right of i-th element
		for (int i = 0; i < n; i++) {
			int curr = 0;
			if (i - 1 >= 0) {
				curr += leftSum[i - 1];
			}
			if (i + 1 < n) {
				curr += rightSum[i + 1];
			}
			max = Math.max(max, curr);
		}
		return max;
	}

	public static void main(String[] args) {
		Test1186_MaximumSubarraySumWithOneDeletion t = new Test1186_MaximumSubarraySumWithOneDeletion();
		System.out.println(t.maximumSum(new int[]{1,-2,0,3}));
	}
}
