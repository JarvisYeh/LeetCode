package Algorithm.Offer;

import util.TreeNode;

public class Test26_SubStructureOfTree {
	// return whether B is a substructure of A
	public boolean isSubStructure(TreeNode A, TreeNode B) {
		// both null, return true
		if (A == null && B == null) return true;
		// one null, return false
		if (A == null || B == null) return false;
		if (A.key == B.key) {
			if (helper(A, B)) return true;
		}
		return isSubStructure(A.left, B) || isSubStructure(A.right, B);
	}

	// return whether B is the substructure of A with root = A = B
	private boolean helper(TreeNode A, TreeNode B) {
		if (A == null && B == null) return true;
		if (A == null) return false;
		if (B == null) return true;
		if (A.key == B.key) {
			return helper(A.left, B.left) && helper(A.right, B.right);
		}
		return false;
	}
	public static void main(String[] args) {
		Test26_SubStructureOfTree t = new Test26_SubStructureOfTree();
		System.out.println(
				t.isSubStructure(TreeNode.formTreeByLayer(new Integer[]{4,2,3,4,5,6,7,8,9}),
						TreeNode.formTreeByLayer(new Integer[]{4,8,9})));
	}
}
