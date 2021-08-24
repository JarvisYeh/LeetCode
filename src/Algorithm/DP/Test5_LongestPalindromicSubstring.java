package Algorithm.DP;

public class Test5_LongestPalindromicSubstring {
	// Method 1: DP
	// TC: O(n^2)
	// SC: O(n^2)
	public String longestPalindromeI(String s) {
		int n = s.length();
		// isPalindrome[i][j] means whether s[i, j] is a palindrome
		boolean[][] isPalindrome = new boolean[n][n];
		int left = 0, right = 0, max = 1;
		// i <= j is valid
		// from small substring to large substring construct isPalindrome matrix
		// O(n^2)
		for (int j = 0; j < n; j++) {
			for (int i = 0; i <= j; i++) {
				if (s.charAt(i) == s.charAt(j)) {
					if (j - i <= 1 || isPalindrome[i + 1][j - 1]) {
						isPalindrome[i][j] = true;
						if (j - i + 1 > max) {
							max = j - i + 1;
							left = i;
							right = j;
						}
					}
				}
			}
		}

		return s.substring(left, right + 1);
	}

	// Method 2: 1 pass
	// TC: O(n^2)
	// SC: O(1)
	public String longestPalindromeII(String s) {
		int n = s.length();
		int max = 1, left = 0, right = 0;
		for (int i = 0; i < n; i++) {
			// for odd length solution
			int start = i, end = i;
			while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			if (max < end - start - 1) {
				max = end - start - 1;
				left = start + 1;
				right = end - 1;
			}
			// for even length solution
			start = i;
			end = i + 1;
			while (start >= 0 && end < n && s.charAt(start) == s.charAt(end)) {
				start--;
				end++;
			}
			if (max < end - start - 1) {
				max = end - start - 1;
				left = start + 1;
				right = end - 1;
			}

		}
		return s.substring(left, right + 1);
	}

	public static void main(String[] args) {
		Test5_LongestPalindromicSubstring t = new Test5_LongestPalindromicSubstring();
		t.longestPalindromeII("ccc");
	}
}
