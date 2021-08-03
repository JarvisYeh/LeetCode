package Algorithm.LCCI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test_Hanota {
	// move all A to C
	public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		helper(A, B, C, A.size());
	}


	// move top n elements from A to C
	private void helper(List<Integer> A, List<Integer> B, List<Integer> C, int n) {
		if (n == 1) {
			C.add(A.remove(A.size() - 1));
			return;
		}

		// first move top n - 1 elements from A to B
		helper(A, C, B, n - 1);
		// then move the largest element from A to C
		C.add(A.remove(A.size() - 1));
		// move the n - 1 elements from B to C
		helper(B, A, C, n - 1);
	}

	public static void main(String[] args) {
		Test_Hanota t = new Test_Hanota();
		List<Integer> A = new ArrayList<>(Arrays.asList(new Integer[]{5, 4, 3, 2, 1, 0}));
		List<Integer> C = new ArrayList<>();
		t.hanota(A, new ArrayList<>(), C);
		System.out.println(C);
	}
}
