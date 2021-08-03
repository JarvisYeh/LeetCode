package Algorithm.Heap;

import java.util.*;

public class Test973_KClosestPointstoOrigin {
	// maxHeap
	// TC: O(n*log k)
	// SC: O(k)
	public int[][] kClosestI(int[][] points, int k) {
		Queue<int[]> maxHeap = new PriorityQueue<>(new Comparator<int[]>(){
			@Override
			public int compare(int[] p1, int[] p2) {
				return -Integer.compare(p1[0]*p1[0] + p1[1]*p1[1], p2[0]*p2[0] + p2[1]*p2[1]);
			}
		});

		for (int i = 0; i < points.length; i++) {
			if (maxHeap.size() < k) {
				maxHeap.offer(points[i]);
			} else {
				int[] p = maxHeap.peek();
				if (p[0]*p[0] + p[1]*p[1] > points[i][0]*points[i][0] + points[i][1]*points[i][1]) {
					maxHeap.poll();
					maxHeap.offer(points[i]);
				}
			}
		}
		return maxHeap.toArray(new int[0][]);
	}

	// quickSelect
	// TC: avg: O(n/2 + n/4 + ... + 1) = O(n) worst: O(n + n-1 + n-2 + ... + 1) = O(n^2)
	// SC: avg: O(log n) worst: O(n)
	public int[][] kClosest(int[][] points, int k) {
		quickSelect(points, 0, points.length - 1, k);
		List<Integer> l = new ArrayList<>();
		return Arrays.copyOfRange(points, 0, k);
	}

	private void quickSelect(int[][] points, int l, int r, int k) {
		Random rand = new Random();
		int randIndex = rand.nextInt(r - l + 1) + l;
		int i = l, j = r - 1;
		swap(points, randIndex, r);
		int[] pivot = points[r];
		while (i <= j) {
			int distance = points[i][0]*points[i][0] + points[i][1]*points[i][1];
			if (distance <= pivot[0]*pivot[0] + pivot[1]*pivot[1]) {
				i++;
			} else {
				swap(points, i, j--);
			}
		}
		swap(points, i, r);
		// now points[0, i) > points[i]
		if (i + 1 == k) {
			return;
		} else if (i + 1 > k) {
			quickSelect(points, l, i - 1, k);
		} else {
			quickSelect(points, i + 1, r, k);
		}
	}
	private void swap(int[][] arr, int i, int j) {
		int[] tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
