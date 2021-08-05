package Algorithm.StackQueueDeque;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Test496_NextGreaterElementI {
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		// map from <x, next larger element of x>
		HashMap<Integer, Integer> map = new HashMap<>();
		// non-decreasing order
		Deque<Integer> stack = new ArrayDeque<>();
		for (int i = 0; i < nums2.length; i++) {
			while (!stack.isEmpty() && stack.peekFirst() < nums2[i]) {
				map.put(stack.pollFirst(), nums2[i]);
			}
			stack.offerFirst(nums2[i]);
		}

		int[] res = new int[nums1.length];
		for (int i = 0; i < nums1.length; i++) {
			res[i] = map.getOrDefault(nums1[i], -1);
		}
		return res;
	}

	public static void main(String[] args) {
		Test496_NextGreaterElementI t = new Test496_NextGreaterElementI();
		t.nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2});
		System.out.println(Integer.valueOf(String.valueOf(Integer.MAX_VALUE + 1)));
	}
}
