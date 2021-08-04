package Algorithm.BinarySearch;

interface MountainArray {
    public int get(int index);
    public int length();
}

class MountainArr implements MountainArray {
	int [] arr;

	public MountainArr(int[] arr) {
		this.arr = arr;
	}

	@Override
	public int get(int index) {
		return arr[index];
	}

	@Override
	public int length() {
		return arr.length;
	}
}


public class Test1095_FindInMountainArray {
	public int findInMountainArray(int target, MountainArray mountainArr) {
		int peakIdx = findPeak(mountainArr);
		// binary search in left half
		int l = 0, r = peakIdx;
		while (l <= r) {
			int mid = l + (r - l)/2;
			int midVal = mountainArr.get(mid);
			if (midVal > target) {
				r = mid - 1;
			} else if (midVal < target) {
				l = mid + 1;
			} else {
				return mid;
			}
		}

		// binary search in right half
		l = peakIdx;
		r = mountainArr.length() - 1;
		while (l <= r) {
			int mid = l + (r - l)/2;
			int midVal = mountainArr.get(mid);
			if (midVal < target) {
				r = mid - 1;
			} else if (midVal > target) {
				l = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	private int findPeak(MountainArray arr) {
		int l = 0, r = arr.length() - 1;
		while (l < r - 1) {
			int mid = l + (r - l)/2;
			int midVal = arr.get(mid);
			int prevVal = arr.get(mid - 1);
			int nextVal = arr.get(mid + 1);
			if (prevVal > midVal) {
				r = mid;
			} else if (nextVal > midVal) {
				l = mid;
			} else {
				return mid;
			}
		}
		if (arr.get(l) > arr.get(r)) {
			return l;
		} else {
			return r;
		}
	}

	public static void main(String[] args) {
		Test1095_FindInMountainArray t = new Test1095_FindInMountainArray();
		t.findInMountainArray(81, new MountainArr(
				new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,100,99,98,97,96,95,94,93,92,91,90,89,88,87,86,85,84,83,82})
		);
	}
}
