package Algorithm.String;

public class Test1528_ShuffleString {
	public String restoreString(String s, int[] indices) {
		char[] input = s.toCharArray();
		for (int i = 0; i < input.length; i++) {
			// swap input[i] to its correct position
			// and continue swapping if the swapped back element is not in its correct position
			// terminate when finally some correct element is swapped back
			while (indices[i] != i) {
				int j = indices[i];
				swap(indices, i, j);
				swap(input, i, j);
			}
		}
		return new String(input);
	}

	private void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private void swap(char[] arr, int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
