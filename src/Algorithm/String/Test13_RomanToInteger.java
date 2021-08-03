package Algorithm.String;

import java.util.HashMap;

public class Test13_RomanToInteger {
	public int romanToInt(String s) {
		HashMap<Character, Integer> dict = new HashMap<>();
		dict.put('M', 1000);
		dict.put('D', 500);
		dict.put('C', 100);
		dict.put('L', 50);
		dict.put('X', 10);
		dict.put('V', 5);
		dict.put('I', 1);

		int sum = 0;
		int i = 0;
		while (i < s.length()) {
			int curr = dict.get(s.charAt(i));
			// if it has next value and curr < next
			// e.g. IV = 5 - 1 = 4 or IX = 10 - 1 = 9
			if (i + 1 < s.length() && curr < dict.get(s.charAt(i + 1))) {
				sum += dict.get(s.charAt(i + 1)) - curr;
				i += 2;
			} else {
				sum += curr;
				i++;
			}
		}
		return sum;
	}
}
