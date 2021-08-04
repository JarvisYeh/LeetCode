package Algorithm.BinarySearch;

public class Test744_FindSmallestLetterGreaterThanTarget {
	public char nextGreatestLetter(char[] letters, char target) {
		int l = 0, r = letters.length - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			if (letters[mid] <= target) {
				l = mid;
			} else {
				r = mid;
			}
		}

		if (letters[l] > target) {
			return letters[l];
		} else if (letters[r] > target) {
			return letters[r];
		}
		return letters[0];
	}
}
