package Algorithm.String;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test394_DecodeString {
	public String decodeString(String s) {
		Deque<Integer> countStack = new ArrayDeque<>();
		Deque<StringBuilder> sbStack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				count *= 10;
				count += s.charAt(i) - '0';
			} else if (s.charAt(i) == '[') {
				countStack.offerFirst(count);
				count = 0;
				// store the current string content to stack first
				sbStack.offerFirst(sb);
				// create a new sb to store content in next []
				sb = new StringBuilder();
			} else if (s.charAt(i) == ']') {
				// append [count * current sb] to previous sb in stack
				StringBuilder prevSb = sbStack.pollFirst();
				for (int j = countStack.pollFirst() - 1; j >= 0; j--) {
					prevSb.append(sb);
				}
				sb = prevSb;
			} else {
				sb.append(s.charAt(i));
			}
		}
		return new String(sb);
	}

	public static void main(String[] args) {
		Test394_DecodeString t = new Test394_DecodeString();
		t.decodeString("3[a]2[bc]");
	}
}
