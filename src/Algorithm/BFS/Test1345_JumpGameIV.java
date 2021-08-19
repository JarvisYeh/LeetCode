package Algorithm.BFS;

import java.util.*;

public class Test1345_JumpGameIV {
	public int minJumps(int[] arr) {
		int n = arr.length;
		HashMap<Integer, List<Integer>> indexMap = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int num = arr[i];
			indexMap.putIfAbsent(num, new ArrayList<>());
			indexMap.get(num).add(i);
		}

		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n];
		q.offer(0);
		visited[0] = true;
		int step = 0;
		while (!q.isEmpty()) {
			for (int i = q.size(); i > 0; i--) {
				int currIdx = q.poll();
				if (currIdx == n - 1) return step;
				// generate next index
				if (currIdx + 1 < arr.length && !visited[currIdx + 1]) {
					q.offer(currIdx + 1);
					visited[currIdx + 1] = true;
				}
				// generate previous index
				if (currIdx - 1 >= 0 && !visited[currIdx - 1]) {
					q.offer(currIdx - 1);
					visited[currIdx + 1] = true;
				}
				// generate same value index
				for (int nei : indexMap.get(arr[currIdx])) {
					if (!visited[nei]) {
						q.offer(nei);
						visited[nei] = true;
					}
				}
				// when number i's same values offer to queue, clear i's list
				// so that when another number i with different index polled out from queue
				// no need to check all i again, they definitely have been visisted before
				indexMap.get(arr[currIdx]).clear();
			}
			step++;
		}
		return -1;
	}
}
