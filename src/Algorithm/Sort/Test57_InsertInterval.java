package Algorithm.Sort;

import java.util.ArrayList;
import java.util.List;

public class Test57_InsertInterval {
	public int[][] insert(int[][] intervals, int[] newInterval) {
		// corner case, empty intervals
		if (intervals == null || intervals.length == 0) {
			return new int[][]{newInterval};
		}

		List<int[]> res = new ArrayList<>();
		int i = 0;
		// for range before newInterval
		while (i < intervals.length && intervals[i][1] < newInterval[0]) {
			res.add(intervals[i++]);
		}

		// for range overlap with newInterval, merge the range
		while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
			newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
			newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
			i++;
		}
		// append the merged range
		res.add(newInterval);

		// for range after newInterval
		while (i < intervals.length) {
			res.add(intervals[i++]);
		}
		return res.toArray(new int[0][0]);
	}
}
