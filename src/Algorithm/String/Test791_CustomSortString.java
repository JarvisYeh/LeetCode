package Algorithm.String;

import java.util.HashMap;

public class Test791_CustomSortString {
	// restrict to 26 alphabetic
	// O(n)
	public String customSortStringI(String order, String str) {
		StringBuilder sb = new StringBuilder();
		// generate frequency map of str, loop through str
		int[] freq = new int[26];
		for (char c : str.toCharArray()) {
			freq[c - 'a']++;
		}

		// add characters both in order and in str
		for (char c : order.toCharArray()) {
			while (freq[c - 'a'] > 0) {
				sb.append(c);
				freq[c - 'a']--;
			}
		}

		// add characters only in str, which means order doesn't matter
		for (int i = 0; i < freq.length; i++) {
			while (freq[i] > 0) {
				sb.append((char)(i + 'a'));
				freq[i]--;
			}
		}
		return new String(sb);
	}

	// for any characters
	public String customSortStringII(String order, String str) {
		StringBuilder sb = new StringBuilder();
		// generate frequency map for str
		HashMap<Character, Integer> freqMap = new HashMap<>();
		for (char c : str.toCharArray()) {
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}

		// loop through order string
		for (char c : order.toCharArray()) {
			// check if order[i] is in freqMap
			// if it exist, append map[order[i]] amount of order[i] to result string
			for (int i = 0; i < freqMap.getOrDefault(c, 0); i++) {
				sb.append(c);
			}
			freqMap.remove(c);
		}

		// append rest of the char in freqMap to result string
		for (char c : freqMap.keySet()) {
			for (int i = 0; i < freqMap.get(c); i++) {
				sb.append(c);
			}
		}
		return new String(sb);
	}

}
