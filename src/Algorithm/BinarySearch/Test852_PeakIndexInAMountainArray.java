package Algorithm.BinarySearch;

public class Test852_PeakIndexInAMountainArray {
	public int peakIndexInMountainArray(int[] arr) {
		int l = 0, r = arr.length - 1;
		// l < r - 1 assures 3 elements in array when in loop
		// for 3 elements, mid won't be the first or last element
		// therefore no need to worry about mid - 1, mid + 1 index out of bound exception
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			// go down
			if (arr[mid - 1] > arr[mid]) {
				r = mid - 1;
			}
			// go up
			else if (arr[mid + 1] > arr[mid]) {
				l = mid + 1;
			}
			// top of mountain
			else if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return mid;
			}
		}
		return arr[l] < arr[r] ? r : l;
	}
}
