package Algorithm.DFS;

import java.util.HashSet;

public class Test266_PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		HashSet<Character> set = new HashSet<>();
		for (char c : s.toCharArray()) {
			if (set.contains(c)) {
				set.remove(c);
			} else {
				set.add(c);
			}
		}
		// a string that can be re-order to palindrome
		// can only have at most 1 char with odd count
		return set.size() == 0 || set.size() == 1;
	}
}
