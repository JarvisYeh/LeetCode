package Algorithm.StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Deque;

public class Test946_ValidateStackSequences {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
		Deque<Integer> stack = new ArrayDeque<>();
		int i = 0, j = 0;
		while (i < pushed.length || j < popped.length) {
			if (stack.isEmpty() || stack.peekFirst() != popped[j]) {
				if (i >= pushed.length) {
					return false;
				}
				stack.offerFirst(pushed[i++]);
			} else {
				stack.pollFirst();
				j++;
			}
		}
		return true;
	}
}
