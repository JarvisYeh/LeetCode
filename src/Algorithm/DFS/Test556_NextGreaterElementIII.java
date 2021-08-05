package Algorithm.DFS;

import java.util.Arrays;

public class Test556_NextGreaterElementIII {
	// method 1: DFS permutation
	// TC: O(n!*n)
	// SC: O(n)
	public int nextGreaterElementI(int n) {
		char[] digits = String.valueOf(n).toCharArray();
		int[] min = {Integer.MAX_VALUE};
		findPermutation(digits, 0, min, n);
		return min[0] == Integer.MAX_VALUE ? -1 : min[0];
	}

	private void findPermutation(char[] digits, int idx, int[] min, int n) {
		if (idx == digits.length) {
			int num = getNum(digits);
			if (num > n) {
				min[0] = Math.min(min[0], num);
			}
			return;
		}

		for (int i = idx; i < digits.length; i++) {
			swap(digits, idx, i);
			findPermutation(digits, idx + 1, min, n);
			swap(digits, idx, i);
		}
	}

	private int getNum(char[] digits) {
		try {
			return Integer.valueOf(new String(digits));
		} catch (Exception e) {
			return -1;
		}
	}

	private void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}


	// TC: O(n)
	// SC: O(n)
	public int nextGreaterElementII(int n) {
		char[] digits = String.valueOf(n).toCharArray();
		int i = digits.length - 1;
		// backward search
		// find the first decreasing digit
		// xxxxxxx 3 5 2 1 => get index of 3
		while (i > 0) {
			if (digits[i] > digits[i - 1]) {
				break;
			}
		}
		// i == 0 || arr[i] > arr[i - 1]
		// if 54321, no larger number, return -1
		if (i == 0) {
			return -1;
		}

		// swap the first decreasing digits {index = i - 1}
		// with the first larger digit from index = len-1 to index = i
		// to make the number larger
		for (int j = digits.length - 1; j >= i; j--) {
			if (digits[j] > digits[i - 1]) {
				char tmp = digits[j];
				digits[j] = digits[i - 1];
				digits[i - 1] = tmp;
				break;
			}
		}

		// reverse [i, len)
		// prefix is already modified to make the number larger than n
		// so need to reverse (sort) the remaining digits.
		// prefix 4 3 2 1 1 => prefix 1 1 2 3 4
		int l = i, r = digits.length - 1;
		while (l < r) {
			char tmp = digits[l];
			digits[l] = digits[r];
			digits[r] = tmp;
			l++;
			r--;
		}

		try {
			return Integer.valueOf(new String(digits));
		} catch (Exception e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		Test556_NextGreaterElementIII t = new Test556_NextGreaterElementIII();
		t.nextGreaterElementII(12);
	}

}
