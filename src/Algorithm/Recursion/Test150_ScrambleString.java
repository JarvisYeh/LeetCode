package Algorithm.Recursion;

import java.util.HashMap;

public class Test150_ScrambleString {
	// Method 1: recursion
	// TC: O((4n)^n)
	public boolean isScrambleI(String s1, String s2) {
		// corner case
		if (s1.length() == 0 && s2.length() == 0) return true;
		return isScramble(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1);
	}

	private boolean isScramble(String s1, int l1, int r1, String s2, int l2, int r2) {
		if (r2 - l2 != r1 - l1) return false;
		if (l1 == r1) return s1.charAt(l1) == s2.charAt(l2);

		// pruning 1: if s1[l1, r1] == s2[l2, r2], directly return true
		boolean equal = false;
		for (int i = 0; l1 + i <= r1; i++) {
			if (s1.charAt(l1 + i) != s2.charAt(l2 + i)) equal = false;
		}
		if (equal) return true;

		// pruning 2: if character frequency in s1[l1, r1] and s2[l2, r2] are different, directly return false
		int[] count = new int[26];
		for (int i = 0; l1 + i <= r1; i++) {
			count[s1.charAt(l1 + i) - 'a']++;
			count[s2.charAt(l2 + i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) if (count[i] != 0) return false;

		// [l, r] => [l, l + i) U [l + i, r]
		for (int i = 1; l1 + i <= r1; i++) {
			// if left and right not scrambled
			// a | bcde
			// a | cbed
			if (isScramble(s1, l1, l1 + i - 1, s2, l2, l2 + i - 1)
					&& isScramble(s1, l1 + i, r1, s2, l2 + i, r2)) {
				return true;
			}
			// if left and right scrambled
			// a | bcde
			// bcde | a
			if (isScramble(s1, l1, l1 + i - 1, s2, r2 - i + 1, r2)
					&& isScramble(s1, l1 + i, r1, s2, l2, r2 - i)) {
				return true;
			}
		}
		return false;
	}


	// Method 2: use a hashmap to record calculated solution
	// recursion + memorization
	public boolean isScrambleII(String s1, String s2) {
		// corner case
		if (s1.length() == 0 && s2.length() == 0) return true;
		HashMap<String, Boolean> map = new HashMap<>();
		return isScramble(s1, 0, s1.length() - 1, s2, 0, s2.length() - 1, map);
	}

	private boolean isScramble(String s1, int l1, int r1, String s2, int l2, int r2, HashMap<String, Boolean> map) {
		String key = s1.substring(l1, r1 + 1) + s2.substring(l2, r2 + 1);
		if (map.containsKey(key)) return map.get(key);

		if (r2 - l2 != r1 - l1) return false;
		if (l1 == r1) return s1.charAt(l1) == s2.charAt(l2);

		// pruning 1: if s1[l1, r1] == s2[l2, r2], directly return true
		boolean equal = false;
		for (int i = 0; l1 + i <= r1; i++) {
			if (s1.charAt(l1 + i) != s2.charAt(l2 + i)) equal = false;
		}
		if (equal) return true;

		// pruning 2: if character frequency in s1[l1, r1] and s2[l2, r2] are different, directly return false
		int[] count = new int[26];
		for (int i = 0; l1 + i <= r1; i++) {
			count[s1.charAt(l1 + i) - 'a']++;
			count[s2.charAt(l2 + i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) if (count[i] != 0) return false;

		// [l, r] => [l, l + i) U [l + i, r]
		for (int i = 1; l1 + i <= r1; i++) {
			// if left and right not scrambled
			// a | bcde
			// a | cbed
			if (isScramble(s1, l1, l1 + i - 1, s2, l2, l2 + i - 1, map)
					&& isScramble(s1, l1 + i, r1, s2, l2 + i, r2, map)) {
				map.put(key, true);
				return true;
			}
			// if left and right scrambled
			// a | bcde
			// bcde | a
			if (isScramble(s1, l1, l1 + i - 1, s2, r2 - i + 1, r2, map)
					&& isScramble(s1, l1 + i, r1, s2, l2, r2 - i, map)) {
				map.put(key, true);
				return true;
			}
		}
		map.put(key, false);
		return false;
	}

	public static void main(String[] args) {
		Test150_ScrambleString t = new Test150_ScrambleString();
		t.isScrambleII("great", "treag");
	}
}
