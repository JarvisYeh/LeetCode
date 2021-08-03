package Algorithm.String;

// remove adjacent duplicates where elements amount = k only
public class Test1209_RemoveAllAdjacentDuplicatesInStringII {
	// maintain a count array
	public String removeDuplicates(String s, int k) {
		// count stack
		int[] count = new int[s.length()];
		char[] arr = s.toCharArray();

		int i = 0, j = 0;
		while (j < arr.length) {
			// always reserve first
			arr[i] = arr[j];
			if (i == 0 || arr[i] != arr[i - 1]) {
				count[i] = 1;
			} else {
				count[i] = count[i - 1] + 1;
			}

			// if count == k, means all reserved duplicates has to be removed
			if (count[i] == k) {
				i -= k;
			}
			i++;
			j++;
		}
		return new String(arr, 0, i);
	}
}
