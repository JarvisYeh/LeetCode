package Algorithm.String;

import java.util.ArrayList;
import java.util.List;

public class Test43_MultiplyStrings {
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) return "0";
		List<StringBuilder> list = new ArrayList<>();
		for (int i = num2.length() - 1; i >= 0; i--) {
			StringBuilder sb = multiply(num2.charAt(i), num1, (num2.length() - 1) - i);
			list.add(sb);
		}

		StringBuilder sum = new StringBuilder();
		for (StringBuilder sb : list) {
			sum = add(sum, sb);
		}
		sum.reverse();
		return new String(sum);
	}

	// num1 is a single char
	// num2 is a string
	// zeros are the zeros need to append to head first
	// both represents numbers
	// note that the sb returns are the reverse version of the result string
	private StringBuilder multiply(char num1, String num2, int zeros) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < zeros; i++) {
			sb.append('0');
		}
		int add = 0;
		for (int i = num2.length() - 1; i >= 0; i--) {
			int mul = (num1 - '0') * (num2.charAt(i) - '0') + add;
			sb.append(mul % 10);
			add = mul/10;
		}
		if (add != 0) sb.append(add);
		return sb;
	}

	private StringBuilder add(StringBuilder num1, StringBuilder num2) {
		StringBuilder res = new StringBuilder();
		int add = 0;
		int idx = 0;
		for (;idx < num1.length() && idx < num2.length(); idx++) {
			int sum = add + num1.charAt(idx) - '0' + num2.charAt(idx) - '0';
			res.append(sum % 10);
			add = sum / 10;
		}

		while (idx < num1.length()) {
			int sum = add + num1.charAt(idx) - '0';
			res.append(sum % 10);
			add = sum / 10;
			idx++;
		}

		while (idx < num2.length()) {
			int sum = add + num2.charAt(idx) - '0';
			res.append(sum % 10);
			add = sum / 10;
			idx++;
		}

		if (add != 0) res.append(add);
		return res;
	}


	public static void main(String[] args) {
		Test43_MultiplyStrings t = new Test43_MultiplyStrings();
		System.out.println(t.multiply("9", "9"));
	}
}
