package Algorithm.String;

import java.util.HashMap;

public class Test12_IntegerToRoman {
	public String intToRoman(int num) {
		HashMap<Integer, Character> dict = new HashMap<>();
		dict.put(1, 'I');
		dict.put(5, 'V');
		dict.put(10, 'X');
		dict.put(50, 'L');
		dict.put(100, 'C');
		dict.put(500, 'D');
		dict.put(1000, 'M');

		// O(n)
		char[] arr = String.valueOf(num).toCharArray();
		StringBuilder sb = new StringBuilder();
		// O(n)
		for (int i = 0; i < arr.length; i++) {
			int digit = (int)(arr[i] - '0');
			int zeros = (int)Math.pow(10, arr.length - i - 1);
			// 90 = 100 - 10 = 10, 100 = XC
			if (digit == 9) {
				sb.append(dict.get(zeros));
				sb.append(dict.get(10*zeros));
			}
			// 40 = 50 - 10 = 10, 50 = XL
			else if (digit == 4) {
				sb.append(dict.get(zeros));
				sb.append(dict.get(5*zeros));
			}
			// 70 = 50 + 10 + 10 = 50, 10, 10 = LXX
			else {
				if (digit / 5 != 0) {
					sb.append(dict.get(5*zeros));
					digit %= 5;
				}
				while (digit != 0) {
					sb.append(dict.get(zeros));
					digit--;
				}
			}
		}
		return new String(sb);
	}
}
