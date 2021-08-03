package Algorithm.DFS;

import java.util.ArrayList;
import java.util.List;

public class Test131_PalindromePartitioning {
	// method 1: DFS
	// TC: O(n*2^n) 每个node check left segment is palindrome
	// SC: O(n)
	// every node the remaining left to check substring is s[start:len(s)]
	// every node decide to cut remaining substring into two parts
	// abcd -> a|bcd, ab|cd, abc|d, abcd
	// if those left parts are palindrome, recursion call the remaining right segment
	public List<List<String>> partitionI(String s) {
		List<List<String>> res = new ArrayList<>();
		findPartitionsI(s, 0, new ArrayList<>(), res);
		return res;
	}

	private void findPartitionsI(String s, int start, List<String> curr, List<List<String>> res) {
		if (start == s.length()) {
			res.add(new ArrayList<>(curr));
			return;
		}

		// [start, end) => [start, i] U [i + 1, end)
		for (int i = start; i < s.length(); i++) {
			if (isPalindrome(s, start, i)) {
				curr.add(s.substring(start, i + 1));
				findPartitionsI(s, i + 1, curr, res);
				curr.remove(curr.size() - 1);
			}
		}
	}

	private boolean isPalindrome(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l++) != s.charAt(r--)) {
				return false;
			}
		}
		return true;
	}

	// method 2: DFS + DP
	// TC: O(2^n), time of checking if it's palindrome is O(1)
	// SC: O(n^2)
	// [start, end] is the reversed to be checked segment
	// every node of recursion tree has two children
	// abcd -> a|bcd, abcd, in this case, start = 0, end = 0 when entering the node
	// cut the [start, end] segment and check if it's palindrome, start = end + 1, end = start
	// not cut, end = end + 1
	// note that here check palindrome use DP
	// DP[j][i] means whether s[j, i] is palindrome
	public List<List<String>> partitionII(String s) {
		List<List<String>> res = new ArrayList<>();
		boolean[][] DP = new boolean[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j <= i; j++) {
				// base case
				if (i - j <= 1) {
					DP[j][i] = s.charAt(i) == s.charAt(j);
				}
				// induction rule
				else {
					DP[j][i] = s.charAt(i) == s.charAt(j) && DP[j + 1][i - 1];
				}
			}
		}

		findPartitionsII(s, 0, 0,  new ArrayList<>(), res, DP);
		return res;
	}


	private void findPartitionsII(String s, int start, int end, List<String> curr, List<List<String>> res, boolean[][] checkP) {
		if (end == s.length()) {
			// if start = end = s.length() means no remaining characters
			// every characters is added to curr list
			if (start == end) {
				res.add(new ArrayList<>(curr));
			}
			return;
		}


		// can decide to cut only if s[start:end] is palindrome
		if (checkP[start][end]) {
			curr.add(s.substring(start, end + 1));
			findPartitionsII(s, end + 1, end + 1, curr, res, checkP);
			curr.remove(curr.size() - 1);
		}
		// not cur, end = end + 1, reverse segment become [start, end + 1]
		findPartitionsII(s, start, end + 1, curr, res, checkP);
	}

	public static void main(String[] args) {
		Test131_PalindromePartitioning t = new Test131_PalindromePartitioning();
		t.partitionII("aab");
	}
}
