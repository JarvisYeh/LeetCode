package Algorithm.DFS;

import java.util.*;

public class Test267_PalindromePermutationII {
	public List<String> generatePalindromes(String s) {
		HashMap<Character, Integer> freqMap = new HashMap<>();
		int oddCharCount = 0;
		Character oddChar = null;
		for (char c : s.toCharArray()) {
			int count = freqMap.getOrDefault(c, 0);
			freqMap.put(c, count + 1);
			if (count % 2 == 0) {
				oddCharCount++;
			} else {
				oddCharCount--;
			}
		}
		List<String> res = new ArrayList<>();
		if (oddCharCount > 1) return res;

		StringBuilder half = new StringBuilder();
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
			char c = entry.getKey();
			int count = entry.getValue();
			for (int i = 0; i < count/2; i++) {
				half.append(c);
			}
			if (count % 2 != 0) oddChar = c;
		}
		// do all permutation for half
		allPermutation(0, half.toString().toCharArray(), res, oddChar);
		return res;
	}

	private void allPermutation(int idx, char[] half, List<String> res, Character mid) {
		if (idx == half.length) {
			StringBuilder sb = new StringBuilder();
			sb.append(half);
			sb.reverse();
			res.add(new String(half) + (mid == null ? "":mid) + sb.toString());
			return;
		}

		HashSet<Character> used = new HashSet<>();
		for (int i = idx; i < half.length; i++) {
			if (!used.contains(half[i])) {
				used.add(half[i]);
				swap(half, idx, i);
				allPermutation(idx + 1, half, res, mid);
				swap(half, idx, i);
			}
		}
	}

	private void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		Test267_PalindromePermutationII t = new Test267_PalindromePermutationII();
		t.generatePalindromes("aaaabbbb");
	}
}
