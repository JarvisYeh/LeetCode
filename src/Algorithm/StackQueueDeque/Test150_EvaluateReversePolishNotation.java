package Algorithm.StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test150_EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		Deque<Integer> stack = new ArrayDeque<>();
		for (String s : tokens) {
			if ("+".equals(s)) {
				int a = stack.pollFirst();
				int b = stack.pollFirst();
				stack.offerFirst(b + a);
			} else if ("-".equals(s)) {
				int a = stack.pollFirst();
				int b = stack.pollFirst();
				stack.offerFirst(b - a);
			} else if ("*".equals(s)) {
				int a = stack.pollFirst();
				int b = stack.pollFirst();
				stack.offerFirst(b * a);
			} else if ("/".equals(s)) {
				int a = stack.pollFirst();
				int b = stack.pollFirst();
				stack.offerFirst(b / a);
			} else {
				stack.offerFirst(Integer.parseInt(s));
			}
		}
		return stack.pollFirst();
	}

	public static void main(String[] args) {
		Test150_EvaluateReversePolishNotation t= new Test150_EvaluateReversePolishNotation();
		System.out.println(t.evalRPN(new String[]{"4","13","5","/","+"}));
	}
}
