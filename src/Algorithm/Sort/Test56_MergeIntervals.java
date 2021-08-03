package Algorithm.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Test56_MergeIntervals {
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return intervals;
		}
		Arrays.sort(intervals, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] < o2[0] ? -1 : o1[0] == o2[0] ? 0 : 1;
			}
		});

//		Arrays.sort(intervals, (p1, p2) -> Integer.compare(p1[0], p2[0]));
		List<int[]> res = new ArrayList<>();
		int[] newPair = intervals[0];
		for (int[] pair : intervals) {
			if (pair[0] <= newPair[1]) { // there is overlap, merge two range
				newPair[1] = newPair[1] > pair[1] ? newPair[1] : pair[1];
			} else {					// no overlap, add newPair to result array, update newPair to the next to be checked pair
				res.add(newPair);
				newPair = pair;
			}
		}
		res.add(newPair);
		return res.toArray(new int[0][0]);
	}
}
