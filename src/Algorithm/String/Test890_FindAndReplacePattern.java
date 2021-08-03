package Algorithm.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test890_FindAndReplacePattern {
	public List<String> findAndReplacePattern(String[] words, String pattern) {
		char[] patArr = pattern.toCharArray();
		normalize(patArr);
		List<String> res = new ArrayList<>();

		for (String word : words) {
			// if size not match, check next word
			if (word.length() != pattern.length()) {
				continue;
			}

			char[] wordArr = word.toCharArray();
			normalize(wordArr);
			if (Arrays.equals(wordArr, patArr)) {
				res.add(word);
			}
		}
		return res;
	}

	private void normalize(char[] arr) {
		HashMap<Character, Character> map = new HashMap<>();
		char character = 'a';
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if (map.containsKey(c)) {
				arr[i] = map.get(c);
			} else {
				arr[i] = character;
				map.put(c, character);
				character++;
			}
		}
	}
}
